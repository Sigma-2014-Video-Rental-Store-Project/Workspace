package ua.nure.sigma.store.web.command.addNewCustomer;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.SexDAO;
import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.exeption.NotEnoughOfBonusExeption;
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
 * Created by vlad on 27.10.14.
 */
public class CreateCustomerCommand extends Command{
    private static final String CUSTOMER_NAME_PARAM_NAME = "name";
    private static final String CUSTOMER_PHONE_PARAM_NAME = "phone";
    private static final String CUSTOMER_BONUS_PARAM_NAME = "bonus";
    private static final String CUSTOMER_SEX_PARAM_NAME = "sex";

    private static final Logger LOG = Logger
            .getLogger(CreateCustomerCommand.class);
    public static final int LAST_NAME_POSSITION = 0;
    public static final int MIDLE_NAME_POSSITION = 1;
    public static final int FIRST_NAME_POSSITION = 2;

    private final List<String> imageExtensions;
    private final Validator validator;

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("AddNewcustomerSaveCommand started.");

        Customer customer = new Customer();

        // Validates new values for current customer and sets them if they are
        // correct.
        String errorMessage = setUpFieldValues(request, customer);
        if (errorMessage != null) {

            // Has to be transferred between two requests.
            request.getSession().setAttribute("errorMessage", errorMessage);

            return Paths.COMMAND_ADD_NEW_CUSTOMER;
        }

        // Sets categories for current customer.

        // Commits new state of the current customer to database.
        DAOFactory.getInstance().getCustomerDAO().createCustomer(customer);

        // Request call for customer cover change.
        setUpCoverRepresentation(request, customer);

        if (LOG.isDebugEnabled()) {
            LOG.debug("New customer added");
            LOG.debug("EditCustomerSaveCommand finished.");
        }

        // Removes error message of this page if it exists.
        request.getSession().removeAttribute("errorMessage");

        return Paths.PAGE_CUSTOMER_LIST;
    }


    private List<String> splitCustomerName(String name){
        List<String> list = new ArrayList<String>();
        String[] strings = name.split("\\s+");
        if (strings.length < 3){
            return null;
        }
        list.add(strings[LAST_NAME_POSSITION]);
        list.add(strings[MIDLE_NAME_POSSITION]);
        list.add(strings[FIRST_NAME_POSSITION]);

        return list;

    }

    private String setUpFieldValues(HttpServletRequest request, Customer customer) {
        String name = request.getParameter(CUSTOMER_NAME_PARAM_NAME);
        String bonus = request.getParameter(CUSTOMER_BONUS_PARAM_NAME);
        String phone = request.getParameter(CUSTOMER_PHONE_PARAM_NAME);
        String sex = request.getParameter(CUSTOMER_SEX_PARAM_NAME);
        List<String> nameList = splitCustomerName(name);
        if (nameList == null){
            return "Customer name must consist of three part";
        }
        Map<String, String> attributes = new HashMap<String, String>(4);
        attributes.put("name", name);
        attributes.put("bonus", bonus);
        attributes.put("sex", sex);
        attributes.put("phone", phone);

        String errorMessage = validator.validate(attributes);
        if (errorMessage != null) {
            return errorMessage;
        }

        int sexID = DAOFactory.getInstance().getSexDAO().findSexIDBySexName(sex).getSexID();
        customer.setLastName(nameList.get(LAST_NAME_POSSITION));
        customer.setMidleName(nameList.get(MIDLE_NAME_POSSITION));
        customer.setFirstName(nameList.get(FIRST_NAME_POSSITION));
        customer.setSexID(sexID);
        try {
            customer.addBonus((long) Double.parseDouble(bonus) * 100);
        } catch (NotEnoughOfBonusExeption notEnoughOfBonusExeption) {
           return "Count of bonus must be possitive";
        }

        LOG.debug("Fields sets.");
        return null;
    }

       /**
     * This method processes a part of network transfer as a file, that
     * represents new image for customer cover. Returns filename of this image.
     *
     * @param part
     *            that represents image.
     * @return filename of the image.
     */
    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim()
                        .replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1)
                        .substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    /**
     * Sets new customer cover if it was changed since the edit form call.
     *
     * @param request
     *            that will provide parameter values.
     * @param customer
     *            that will be modified.
     */

    private void setUpCoverRepresentation(HttpServletRequest request, Customer customer) {
        LOG.debug("Started to update customer cover image.");
        int customerID = customer.getCustomerID();
        try {
            Part filePart = request.getPart("inputFile");
            String filename = getFilename(filePart);
            if (filename.isEmpty()) {
                LOG.debug("No customer cover image.");
                customer.setCustomerPhoto("0.jpg");
                return;
            }
            String coverExtension = filename.split("\\.")[1];
            String coverRepositoryPath = (String) request.getServletContext()
                    .getAttribute("PHOTO_DIR");
            String newCoverPath = coverRepositoryPath + customerID + "."
                    + coverExtension;
            if (LOG.isDebugEnabled()) {
                LOG.debug("Obtained image to set as customer cover: " + filename);
                LOG.debug("Will work with repository path equals to: "
                        + coverRepositoryPath);
                LOG.debug("customer ID to process: " + customerID);
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
                        RuntimeException e = new RuntimeException(
                                "Delete permission denied.");
                        LOG.error(
                                "Can not specified file from image repository.",
                                e);
                        throw e;
                    }
                    break;
                }
            }
            FileOutputStream out = new FileOutputStream(file);
            IOUtils.copy(in, out);
            customer.setCustomerPhoto(customerID + "." + coverExtension);
        } catch (ServletException ex) {
            LOG.error("Servlet exception occurred while setting customer cover.",
                    ex);
        } catch (IOException ex) {
            LOG.error(
                    "Input data processing exception occurred while setting customer cover.",
                    ex);
        }
        LOG.debug("Finished to upload customer cover image.");
        DAOFactory.getInstance().getCustomerDAO().updateCustomer(customer);
        LOG.debug("Customer updated.");
    }

    public CreateCustomerCommand(List<String> imageExtensions, Validator validator) {
        this.imageExtensions = imageExtensions;
        this.validator = validator;
    }

}
