package ua.nure.sigma.store.web.command.editCustomer;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.SexDAO;
import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.exeption.NotEnoughOfBonusExeption;
import ua.nure.sigma.store.validator.Validator;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.command.addNewCustomer.CreateCustomerCommand;
import ua.nure.sigma.store.web.command.editfilm.EditFilmCommand;

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
 * Created by vlad on 27.10.14.
 */
public class UpdateCustomerCommand extends Command {
    private static final String CUSTOMER_NAME_PARAM_FIRST_NAME = "firstName";
    private static final String CUSTOMER_NAME_PARAM_LAST_NAME = "lastName";
    private static final String CUSTOMER_NAME_PARAM_MIDDLE_NAME = "middleName";
    private static final String CUSTOMER_PHONE_PARAM_NAME = "phone";
    private static final String CUSTOMER_BONUS_PARAM_NAME = "bonus";
    private static final String CUSTOMER_SEX_PARAM_NAME = "sex";

    private static final Logger LOG = Logger.getLogger(UpdateCustomerCommand.class);

    private final List<String> imageExtensions;
    private final Validator validator;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("EditCustomerSaveCommand started.");
        String customerIdString = request.getParameter(EditCustomerCommand.CUSTOMER_ID_PARAM_NAME);

        // If this command has been called plain - return 'no page' handler.
        if (customerIdString == null) {
            LOG.warn("Cannot get 'customerID' parameter.");
            return Paths.PAGE_NO_PAGE;
        }
        int customerID;
        try {
            // Notice, that if this parameter cannot be parsed properly,
            // NumberFormatException will be thrown.
            customerID = Integer.parseInt(customerIdString);
        } catch (NumberFormatException e) {
            LOG.error("Cannot parse 'customerID' as Integer value.", e);
            return Paths.PAGE_NO_PAGE;
        }

        Customer customerToEdit = DAOFactory.getInstance().getCustomerDAO().findCustomerByID(customerID);
        // If the current database does not contain customer with specified ID -
        // return 'no page' handler.
        if (customerToEdit == null) {
            LOG.warn(String.format("Cannot find customer with id equals to %d in the database.", customerID));
            return Paths.PAGE_NO_PAGE;
        }

        // Validates new values for current customer and sets them if they are correct.
        String errorMessage = setUpFieldValues(request, customerToEdit);
        if (errorMessage != null) {

            // Has to be transferred between two requests.
            request.getSession().setAttribute("errorMessage", errorMessage);

            return Paths.COMMAND_EDIT_CUSTOMER + "&customerID=" + customerID;
        }

        // Request call for customer cover change.
        setUpFilmCover(request, customerToEdit);

        // Commits new state of the current customer to database.
        DAOFactory.getInstance().getCustomerDAO().updateCustomer(customerToEdit);
        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("Next customer with id equals to '%d' has been updated.",
                    customerID));
            LOG.debug("EditCustomerSaveCommand finished.");
        }

        // Removes error message of this page if it exists.
        request.getSession().removeAttribute("errorMessage");

        return Paths.PAGE_CUSTOMER_LIST;
    }

    /**
     * Sets new values for title, amount, description, general price,
     * rent price, bonus for rent and year fields for the current customer.
     * Also validates them and if the validation fails, returns error message
     * to set on edit customer form.
     *
     * @param request    that will provide parameter values.
     * @param customer that will be modified.
     */

    private String setUpFieldValues(HttpServletRequest request, Customer customer) {
        String firstName = request.getParameter(CUSTOMER_NAME_PARAM_FIRST_NAME);
        String lastName = request.getParameter(CUSTOMER_NAME_PARAM_LAST_NAME);
        String middleName = request.getParameter(CUSTOMER_NAME_PARAM_MIDDLE_NAME);
        String bonus = request.getParameter(CUSTOMER_BONUS_PARAM_NAME);
        String phone = request.getParameter(CUSTOMER_PHONE_PARAM_NAME);
        String sex = request.getParameter(CUSTOMER_SEX_PARAM_NAME);

        Map<String, String> attributes = new HashMap<String, String>(6);
        attributes.put("name", firstName);
        attributes.put("name", lastName);
        attributes.put("name", middleName);
        attributes.put("bonus", bonus);
        attributes.put("sex", sex);
        attributes.put("phone", phone);

        String errorMessage = validator.validate(attributes);
        if (errorMessage != null) {
            return errorMessage;
        }
        int sexID = DAOFactory.getInstance().getSexDAO().findSexIDBySexName(sex).getSexID();
        customer.setLastName(lastName);
        customer.setMiddleName(middleName);
        customer.setFirstName(firstName);
        customer.setSexID(sexID);
        customer.setCustomerPhone(phone);
        try {
            customer.addBonus((long) Double.parseDouble(bonus) * 100);
        } catch (NotEnoughOfBonusExeption notEnoughOfBonusExeption) {
            return "Count of bonus must be possitive";
        }

        return null;
    }


    /**
     * This method processes a part of network transfer as a file, that represents
     * new image for customer cover. Returns filename of this image.
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
     * Sets new customer cover if it was changed since the edit form call.
     *
     * @param request    that will provide parameter values.
     * @param customerToEdit that will be modified.
     */
    private void setUpFilmCover(HttpServletRequest request, Customer customerToEdit) {
        LOG.debug("Started to update customer cover image.");
        int customerID = customerToEdit.getCustomerID();
        try {
            Part filePart = request.getPart("inputFile");
            String filename = getFilename(filePart);
            if (filename.isEmpty()) {
                LOG.debug("No need to update customer cover image.");
                return;
            }
            String coverExtension = filename.split("\\.")[1];
            String coverRepositoryPath = (String) request.getServletContext().getAttribute("PHOTO_DIR");
            String newCoverPath = coverRepositoryPath + customerID + "." + coverExtension;
            if (LOG.isDebugEnabled()) {
                LOG.debug("Obtained image to set as customer cover: " + filename);
                LOG.debug("Will work with repository path equals to: " + coverRepositoryPath);
                LOG.debug("Customer ID to process: " + customerID);
                LOG.debug("Image extension to process: " + coverExtension);
                LOG.debug("New customer cover path: " + newCoverPath);
            }
            InputStream in = filePart.getInputStream();
            File file = new File(newCoverPath);
            for (String extension : imageExtensions) {
                File cover = new File(coverRepositoryPath + customerID + extension);
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
            customerToEdit.setCustomerPhoto(customerID + "." + coverExtension);
        } catch (ServletException ex) {
            LOG.error("Servlet exception occurred while setting customer cover.", ex);
        } catch (IOException ex) {
            LOG.error("Input data processing exception occurred while setting customer cover.", ex);
        }
        LOG.debug("Finished to upload customer cover image.");
    }

    public UpdateCustomerCommand(List<String> imageExtensions, Validator validator) {
        this.imageExtensions = imageExtensions;
        this.validator = validator;
    }
}
