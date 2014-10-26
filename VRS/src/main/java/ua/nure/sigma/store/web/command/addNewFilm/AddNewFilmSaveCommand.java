package ua.nure.sigma.store.web.command.addNewFilm;

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
 * @author Vlad Samotskiy
 * 
 * @version 1.0
 */
public class AddNewFilmSaveCommand extends Command {

    private static final String FILM_TITLE_PARAM_NAME = "filmTitle";
    private static final String FILM_CATEGORIES_PARAM_NAME = "categoryName";
    private static final String FILM_AMOUNT_PARAM_NAME = "amount";
    private static final String FILM_DESCRIPTION_PARAM_NAME = "description";
    private static final String FILM_GENERAL_PRICE_PARAM_NAME = "generalPrice";
    private static final String FILM_RENT_PRICE_PARAM_NAME = "rentPrice";
    private static final String FILM_BONUS_PARAM_NAME = "bonus";
    private static final String FILM_YEAR_PARAM_NAME = "year";

    private static final Logger LOG = Logger.getLogger(AddNewFilmSaveCommand.class);

    private final List<String> imageExtensions;
    private final Validator validator;

    public AddNewFilmSaveCommand(List<String> imageExtensions, Validator validator) {
        this.imageExtensions = imageExtensions;
        this.validator = validator;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("AddNewFilmSaveCommand started.");
        
        Film film = new Film();

        // Validates new values for current film and sets them if they are correct.
        String errorMessage = setUpFieldValues(request, film);
        if (errorMessage != null) {

            // Has to be transferred between two requests.
            request.getSession().setAttribute("errorMessage", errorMessage);

            return Paths.COMMAND_ADD_NEW_FILM;
        }

        // Sets categories for current film.
        setUpCategories(request, film);

        // Request call for film cover change.
        setUpFilmCover(request, film);

        // Commits new state of the current film to database.
        DAOFactory.getInstance().getFilmDAO().createFilm(film);
        if (LOG.isDebugEnabled()) {
            LOG.debug("New film added");
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
     * @param film that will be modified.
     */
    private String setUpFieldValues(HttpServletRequest request, Film film) {
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

        film.setTitle(titleString);
		film.setCover("100");
        film.setAmount(Integer.parseInt(amountString));
        film.setDescription(descriptionString);
        film.setGeneralPrice((long) Double.parseDouble(generalPriceString)*100);
        film.setRentPrice((long) Double.parseDouble(rentPriceString)*100);
        film.setBonusForRent((long) Double.parseDouble(bonusForRentString)*100);
        film.setYear(Integer.parseInt(yearString));
		
		LOG.debug("Fields sets.");
        return null;
    }

    /**
     * @param request that will provide parameter values.
     * @param film that will be modified.
     */
    private void setUpCategories(HttpServletRequest request, Film film) {
        String[] categories = request.getParameterValues(FILM_CATEGORIES_PARAM_NAME);
        if (categories != null) {
            List<Category> categoryList = new ArrayList<Category>();
            for (String categoryName : categories) {
                LOG.debug(categoryName);
                int categoryId = DAOFactory.getInstance().getCategoryDAO().findCategoryIdByName(categoryName);
                categoryList.add(DAOFactory.getInstance().getCategoryDAO().findCategoryByID(categoryId));
            }
            DAOFactory.getInstance().getFilmCategoryDAO().updateFilmCategories(film, categoryList);
			LOG.debug("Categories sets.");
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
     * @param film that will be modified.
     */
    private void setUpFilmCover(HttpServletRequest request, Film film) {
        LOG.debug("Started to update film cover image.");
        try {
            Part filePart = request.getPart("inputFile");
            String filename = getFilename(filePart);
            if (filename.isEmpty()) {
                LOG.debug("No film cover image.");
                film.setCover("0.jpg");
                return;
            }
            String coverExtension = filename.split("\\.")[1];
            String coverRepositoryPath = (String) request.getServletContext().getAttribute("COVERS_DIR");
            String newCoverPath = coverRepositoryPath + filename;
            if (LOG.isDebugEnabled()) {
                LOG.debug("Obtained image to set as film cover: " + filename);
                LOG.debug("Will work with repository path equals to: " + coverRepositoryPath);
                LOG.debug("Image extension to process: " + coverExtension);
                LOG.debug("New film cover path: " + newCoverPath);
            }
            InputStream in = filePart.getInputStream();
            File file = new File(newCoverPath);
            for (String extension : imageExtensions) {
                File cover = new File(coverRepositoryPath + filename.split("\\.")[0] + extension);
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
            film.setCover(filename);
        } catch (ServletException ex) {
            LOG.error("Servlet exception occurred while setting film cover.", ex);
        } catch (IOException ex) {
            LOG.error("Input data processing exception occurred while setting film cover.", ex);
        }
        LOG.debug("Finished to upload film cover image.");
    }
}
