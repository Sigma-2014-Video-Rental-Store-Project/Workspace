package ua.nure.sigma.store.web.command.editfilm;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.validator.Validator;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final List<String> imageExtensions;
    private final Validator validator;

    public EditFilmSaveCommand(List<String> imageExtensions, Validator validator) {
        this.imageExtensions = imageExtensions;
        this.validator = validator;
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

        // Validates new values for current film and sets them if they are correct.
        String errorMessage = setUpFieldValues(request, filmToEdit);
        if (errorMessage != null) {

            // Has to be transferred between two requests.
            request.getSession().setAttribute("errorMessage", errorMessage);

            return Paths.COMMAND_EDIT_FILM + "&filmId=" + filmId;
        }

        // Sets categories for current film.
        setUpCategories(request, filmToEdit);

        // Request call for film cover change.
        setUpFilmCover(request, filmToEdit);

        // Commits new state of the current film to database.
        DAOFactory.getInstance().getFilmDAO().updateFilm(filmToEdit);
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("Next film with id equals to '%d' has been updated.",
                    filmId));
            LOG.debug("EditFilmSaveCommand finished.");
        }

        // Removes error message of this page if it exists.
        request.getSession().removeAttribute("errorMessage");

        return Paths.COMMAND_FULL_FILM_LIST;
    }

    /**
     * Sets new values for title, amount, description, general price,
     * rent price, bonus for rent and year fields for the current film.
     * Also validates them and if the validation fails, returns error message
     * to set on edit film form.
     *
     * @param request    that will provide parameter values.
     * @param filmToEdit that will be modified.
     */
    private String setUpFieldValues(HttpServletRequest request, Film filmToEdit) {
        String titleString = request.getParameter(FILM_TITLE_PARAM_NAME);
        String amountString = request.getParameter(FILM_AMOUNT_PARAM_NAME);
        String descriptionString = request.getParameter(FILM_DESCRIPTION_PARAM_NAME);
        String generalPriceString = request.getParameter(FILM_GENERAL_PRICE_PARAM_NAME);
        String rentPriceString = request.getParameter(FILM_RENT_PRICE_PARAM_NAME);
        String bonusForRentString = request.getParameter(FILM_BONUS_PARAM_NAME);
        String yearString = request.getParameter(FILM_YEAR_PARAM_NAME);

        Map<String, String> attributes = new HashMap<String, String>(10);
        attributes.put("title", titleString);
        attributes.put("amount", amountString);
        attributes.put("description", descriptionString);
        attributes.put("generalPrice", generalPriceString);
        attributes.put("rentPrice", rentPriceString);
        attributes.put("bonusForRent", bonusForRentString);
        attributes.put("year", yearString);
        String errorMessage = validator.validate(attributes);
        if (errorMessage != null) {
            return errorMessage;
        }

        filmToEdit.setTitle(titleString);
        filmToEdit.setAmount(Integer.parseInt(amountString));
        filmToEdit.setDescription(descriptionString);
        filmToEdit.setGeneralPrice(Long.parseLong(generalPriceString));
        filmToEdit.setRentPrice(Long.parseLong(rentPriceString));
        filmToEdit.setBonusForRent(Long.parseLong(bonusForRentString));
        filmToEdit.setYear(Integer.parseInt(yearString));

        return null;
    }

    /**
     * @param request    that will provide parameter values.
     * @param filmToEdit that will be modified.
     */
    private void setUpCategories(HttpServletRequest request, Film filmToEdit) {
        String[] categories = request.getParameterValues(FILM_CATEGORIES_PARAM_NAME);
        if (categories != null) {
            List<Category> categoryList = new ArrayList<Category>();
            for (String categoryName : categories) {
                LOG.debug(categoryName);
                int categoryId = DAOFactory.getInstance().getCategoryDAO().findCategoryIdByName(categoryName);
                categoryList.add(DAOFactory.getInstance().getCategoryDAO().findCategoryByID(categoryId));
            }
            DAOFactory.getInstance().getFilmCategoryDAO().updateFilmCategories(filmToEdit, categoryList);
        }
    }

    /**
     * This method processes a part of network transfer as a file, that represents
     * new image for film cover. Returns filename of this image.
     *
     * @param part that represents image.
     * @return filename of the image.
     */
    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    /**
     * Sets new film cover if it was changed since the edit form call.
     *
     * @param request    that will provide parameter values.
     * @param filmToEdit that will be modified.
     */
    private void setUpFilmCover(HttpServletRequest request, Film filmToEdit) {
        LOG.debug("Started to update film cover image.");
        int filmId = filmToEdit.getFilmId();
        try {
            Part filePart = request.getPart("inputFile");
            String filename = getFilename(filePart);
            if (filename.isEmpty()) {
                LOG.debug("No need to update film cover image.");
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
            InputStream in = filePart.getInputStream();
            File file = new File(newCoverPath);
            for (String extension : imageExtensions) {
                File cover = new File(coverRepositoryPath + filmId + extension);
                if (cover.exists()) {
                    boolean success = cover.delete();
                    if (!success) {
                        RuntimeException e = new RuntimeException("Delete permission denied.");
                        LOG.error("Can not specified file from image repository.", e);
                        throw e;
                    }
                    break;
                }
            }
            FileOutputStream out = new FileOutputStream(file);
            IOUtils.copy(in, out);
            filmToEdit.setCover(filmId + "." + coverExtension);
        } catch (ServletException ex) {
            LOG.error("Servlet exception occurred while setting film cover.", ex);
        } catch (IOException ex) {
            LOG.error("Input data processing exception occurred while setting film cover.", ex);
        }
        LOG.debug("Finished to upload film cover image.");
    }
}
