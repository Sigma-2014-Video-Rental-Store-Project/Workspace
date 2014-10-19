package ua.nure.sigma.store.web.command.editfilm;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * This class is responsible for submitting changes made on edit form
 * for particular film. Processes both image and text resources that
 * may be modified.
 *
 * @author Maksim Sinkevich
 * @author Denys Shevchenko
 * @version 1.0
 */
public class EditFilmSaveCommand extends Command {

    private static final String FILM_TITLE_PARAM_NAME = "filmTitle";
    private static final String FILM_CATEGORIES_PARAM_NAME = "categoryName";
    private static final String FILM_AMOUNT_PARAM_NAME = "amount";
    private static final String FILM_DESCRIPTION_PARAM_NAME = "description";
    private static final String FILM_GENERAL_PRICE_PARAM_NAME = "generalPrice";
    private static final String FILM_RENT_PRICE_PARAM_NAME = "rentPrice";
    private static final String FILM_BONUS_PARAM_NAME = "bonus";
    private static final String FILM_YEAR_PARAM_NAME = "year";

    private static final Logger LOG = Logger.getLogger(EditFilmSaveCommand.class);

    private List<String> imageExtensions;

    public EditFilmSaveCommand(List<String> imageExtensions){
        this.imageExtensions = imageExtensions;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("EditFilmSaveCommand started.");
        String filmIdString = request.getParameter(EditFilmCommand.FILM_ID_PARAM_NAME);

        // If this command has been called plain - return 'no page' handler.
        if (filmIdString == null) {
            LOG.warn("Cannot get 'filmId' parameter.");

            return Paths.PAGE_NO_PAGE;
        }
        int filmId;
        try {

            // Notice, that if this parameter cannot be parsed properly,
            // NumberFormatException will be thrown.
            filmId = Integer.parseInt(filmIdString);
        } catch (NumberFormatException e) {
            LOG.error("Cannot parse 'filmId' as Integer value.", e);

            return Paths.PAGE_NO_PAGE;
        }

        Film filmToEdit = DAOFactory.getInstance().getFilmDAO().findFilmById(filmId);

        // If the current database does not contain film with specified ID -
        // return 'no page' handler.
        if (filmToEdit == null) {
            LOG.warn(String.format("Cannot find film with id equals to %d in the database.", filmId));

            return Paths.PAGE_NO_PAGE;
        }

        String categoryName = request.getParameter(FILM_CATEGORIES_PARAM_NAME);
        int categoryId = DAOFactory.getInstance().getCategoryDAO().findCategoryIdByName(categoryName);

        filmToEdit.setTitle(request.getParameter(FILM_TITLE_PARAM_NAME));
        filmToEdit.setAmount(Integer.parseInt(request.getParameter(FILM_AMOUNT_PARAM_NAME)));
        filmToEdit.setDescription(request.getParameter(FILM_DESCRIPTION_PARAM_NAME));
        filmToEdit.setGeneralPrice(Long.parseLong(
                request.getParameter(FILM_GENERAL_PRICE_PARAM_NAME)));
        filmToEdit.setRentPrice(Long.parseLong(
                request.getParameter(FILM_RENT_PRICE_PARAM_NAME)));
        filmToEdit.setBonusForRent(Long.parseLong(
                request.getParameter(FILM_BONUS_PARAM_NAME)));
        filmToEdit.setYear(Integer.parseInt(
                request.getParameter(FILM_YEAR_PARAM_NAME)));

        // TODO Category processing.
        // DAOFactory.getInstance().getFilmCategoryDAO().createFilmCategory(filmToEdit,
        //      DAOFactory.getInstance().getCategoryDAO().findCategoryByID(categoryId));

        // Request call for film cover change.
        setUpFilmCover(request, filmId, filmToEdit);

        DAOFactory.getInstance().getFilmDAO().updateFilm(filmToEdit);
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("Next film with id equals to '%d' has been updated.",
                    filmId));
            LOG.debug("EditFilmSaveCommand finished.");
        }

        return Paths.COMMAND_FULL_FILM_LIST;
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    private void setUpFilmCover(HttpServletRequest request, int filmId, Film filmToEdit) {
        LOG.debug("Started to upload film cover image.");
        InputStream in = null;
        FileOutputStream out = null;
        try {
            Part filePart = request.getPart("inputFile");
            String filename = getFilename(filePart);
            if(filename.isEmpty()){
                return;
            }
            String coverExtension = filename.split("\\.")[1];
            String coverRepositoryPath = (String) request.getServletContext().getAttribute("COVERS_DIR");
            String newCoverPath = coverRepositoryPath + filmId + "." + coverExtension;
            if (LOG.isDebugEnabled()) {
                LOG.debug("Obtained image to set as film cover: " + filename);
                LOG.debug("Will work with repository path equals to: " + coverRepositoryPath);
                LOG.debug("Film ID to process: " + filmId);
                LOG.debug("Image extension to process: " + coverExtension);
                LOG.debug("New film cover path: " + newCoverPath);
            }
            in = filePart.getInputStream();
            File file = new File(newCoverPath);

            for(String extension: imageExtensions){
                File cover = new File(coverRepositoryPath + filmId + extension);
                if(cover.exists()){
                    boolean success = cover.delete();
                    if (!success) {
                        RuntimeException e = new RuntimeException("Delete permission denied.");
                        LOG.error("Can not specified file from image repository.", e);
                        throw e;
                    }
                    break;
                }
            }
            out = new FileOutputStream(file);
            IOUtils.copy(in, out);
            filmToEdit.setCover(filmId+"." + coverExtension);
        } catch (ServletException ex) {
            LOG.error("Servlet exception occurred while setting film cover.", ex);
        } catch (IOException ex) {
            LOG.error("Input data processing exception occurred while setting film cover.", ex);
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException ioException){
                LOG.error("Cannot close film cover streams.", ioException);
            } catch (NullPointerException nullPointerException){
                LOG.error("Cannot close film cover streams.", nullPointerException);
            }
        }
        LOG.debug("Finished to upload film cover image.");
    }
}
