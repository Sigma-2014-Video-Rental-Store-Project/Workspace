package ua.nure.sigma.store.web.command.editfilm;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sinkevich Maksim on 14.10.2014.
 */
public class EditFilmSaveCommand extends Command {
    private static final Logger LOG = Logger.getLogger(EditFilmSaveCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String filmIDString = request.getParameter(EditFilmCommand.FILMID_PARAM_NAME);
        String remove = request.getParameter(EditFilmCommand.REMOVE_PARAM_NAME);

        int filmId = 0;
        if (filmIDString != null) {
            filmId = Integer.parseInt(filmIDString);
        }

        if (!Boolean.valueOf(remove)) {
            Film editingFilm = DAOFactory.getInstance().getFilmDAO().findFilmByID(filmId);
            if (editingFilm == null) {
                editingFilm = new Film();
            }

            try {
                String categoryName = request.getParameter(EditFilmCommand.FILM_CATEGORIES_PARAM_NAME);
                int categoryId = DAOFactory.getInstance().getCategoryDAO().findCategoryIdByName(categoryName);

                editingFilm.setTitle(request.getParameter(EditFilmCommand.FILM_TITLE_PARAM_NAME));
                editingFilm.setAmount(Integer.parseInt(request.getParameter(EditFilmCommand.FILM_AMOUNT_PARAM_NAME)));
                editingFilm.setDescription(request.getParameter(EditFilmCommand.FILM_DESCRIPTION_PARAM_NAME));
                editingFilm.setGeneralPrice(Long.parseLong(
                        request.getParameter(EditFilmCommand.FILM_GENERAL_PRICE_PARAM_NAME)));
                editingFilm.setRentPrice(Long.parseLong(
                        request.getParameter(EditFilmCommand.FILM_RENT_PRICE_PARAM_NAME)));
                editingFilm.setBonusForRent(Long.parseLong(
                        request.getParameter(EditFilmCommand.FILM_BONUS_PARAM_NAME)));
                editingFilm.setYear(Integer.parseInt(
                        request.getParameter(EditFilmCommand.FILM_YEAR_PARAM_NAME)));

                if (DAOFactory.getInstance().getFilmDAO().findFilmByID(filmId) == null) {
                    DAOFactory.getInstance().getFilmDAO().createFilm(editingFilm);
                    List<Film> filmList = DAOFactory.getInstance().getFilmDAO().findAllFilms();
                    filmId = filmList.get(filmList.size() - 1).getFilmId();
                    editingFilm.setCover(filmId + ".jpg");
                }
                editingFilm.setFilmId(filmId);
                DAOFactory.getInstance().getFilmDAO().updateFilm(editingFilm);
                categorySetup(request, editingFilm);
                // Temp call for film cover change.
                setUpFilmCover(request, filmId);

                LOG.debug("updateFilm");
            } catch (Exception e) {
                LOG.error("Can not save film edition.");
            }
        }
        return null;
    }

    private void categorySetup(HttpServletRequest request, Film editingFilm) {
        String[] categories = request.getParameterValues(EditFilmCommand.FILM_CATEGORIES_PARAM_NAME);
        LOG.debug(categories);
        if (categories != null) {
            int categoryId = -1;
            List<Category> categoriesList = new ArrayList<Category>();
            for (int i = 0; i < categories.length; i++) {
                LOG.debug(categories[i]);
                categoryId = DAOFactory.getInstance().getCategoryDAO().findCategoryIdByName(categories[i]);
                categoriesList.add(DAOFactory.getInstance().getCategoryDAO().findCategoryByID(categoryId));
            }
            DAOFactory.getInstance().getFilmCategoryDAO().updateFilmCategories(editingFilm, categoriesList);
        }
    }

    private void setUpFilmCover(HttpServletRequest request, int filmId) {
        LOG.debug("Started to upload film cover image.");
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        File coversDirFull = (File) request.getServletContext().getAttribute("COVERS_DIR_FULL");

        if (LOG.isDebugEnabled()) {
            LOG.debug("Got full cover directory: " + coversDirFull.getAbsolutePath());
        }
        fileFactory.setRepository(coversDirFull);
        ServletFileUpload uploader = new ServletFileUpload(fileFactory);
        if (!ServletFileUpload.isMultipartContent(request)) {
            RuntimeException e = new RuntimeException("Content type is not multiform/form-data");

            LOG.error("Can not process incorrect data type.", e);
            throw e;
        }
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> fileItemsList = (List<FileItem>) uploader.parseRequest(request);
            if (!fileItemsList.isEmpty()) {
                FileItem fileItem = fileItemsList.get(0);
                String coverRepositoryPath = (String) request.getServletContext().getAttribute("COVERS_DIR");
                String coverExtension = fileItem.getName().split("\\.")[1];

                if (LOG.isDebugEnabled()) {
                    LOG.debug("Will work with repository path equals to: " + coverRepositoryPath);
                    LOG.debug("Film ID to process: " + filmId);
                    LOG.debug("Image extension to process: " + coverExtension);
                }
                String newCoverName = coverRepositoryPath + filmId + "." + coverExtension;
                File file = new File(newCoverName);
                if (file.exists()) {
                    boolean success = file.delete();
                    if (!success) {
                        RuntimeException e = new RuntimeException("Delete permission denied.");

                        LOG.error("Can not specified file from image repository.", e);
                        throw e;
                    }
                }
                fileItem.write(file);
            }
        } catch (Exception e) {
            LOG.error("Exception occurred while uploading file.", e);
        }
        LOG.debug("Finished to upload film cover image.");
    }
}
