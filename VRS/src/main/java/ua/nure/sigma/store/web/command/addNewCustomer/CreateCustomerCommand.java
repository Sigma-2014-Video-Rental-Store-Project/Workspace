package ua.nure.sigma.store.web.command.addNewCustomer;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.exeption.NotEnoughOfBonusExeption;
import ua.nure.sigma.store.validator.UserValidator;
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
import java.util.List;

/**
 * Created by vlad on 27.10.14.
 */
public class CreateCustomerCommand extends Command {
	private static final String CUSTOMER_NAME_PARAM_FIRST_NAME = "firstName";
	private static final String CUSTOMER_NAME_PARAM_LAST_NAME = "lastName";
	private static final String CUSTOMER_NAME_PARAM_MIDDLE_NAME = "middleName";
	private static final String CUSTOMER_PHONE_PARAM_NAME = "phone";
	private static final String CUSTOMER_BONUS_PARAM_NAME = "bonus";
	private static final String CUSTOMER_SEX_PARAM_NAME = "sex";
	private static final String CUSTOMER_EMAIL_PARAM_NAME = "email";

	private static final Logger LOG = Logger
			.getLogger(CreateCustomerCommand.class);
	private List<String> imageExtensions;

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
		if (customer.getCustomerID() == 0){
			errorMessage = "coudn't create customer";
			request.getSession().setAttribute("errorMessage", errorMessage);

			return Paths.COMMAND_ADD_NEW_CUSTOMER;
		}
		// Request call for customer cover change.
		setUpCoverRepresentation(request, customer);

		if (LOG.isDebugEnabled()) {
			LOG.debug("New customer added");
			LOG.debug("EditCustomerSaveCommand finished.");
		}

		// Removes error message of this page if it exists.
		request.getSession().removeAttribute("errorMessage");

		return Paths.COMMAND_CUSTOMER_LIST;
	}
	

	private String setUpFieldValues(HttpServletRequest request,
			Customer customer) {
		String firstName = request.getParameter(CUSTOMER_NAME_PARAM_FIRST_NAME);
		String lastName = request.getParameter(CUSTOMER_NAME_PARAM_LAST_NAME);
		String middleName = request.getParameter(CUSTOMER_NAME_PARAM_MIDDLE_NAME);
		String bonus = request.getParameter(CUSTOMER_BONUS_PARAM_NAME);
		String phone = request.getParameter(CUSTOMER_PHONE_PARAM_NAME);
		String sex = request.getParameter(CUSTOMER_SEX_PARAM_NAME);
		String email = request.getParameter(CUSTOMER_EMAIL_PARAM_NAME);

		String errorMessage;
		errorMessage = UserValidator.validatePartOfName(firstName);
		if (errorMessage != null) {
			return errorMessage;
		}
		customer.setFirstName(firstName);
		errorMessage = UserValidator.validatePartOfName(lastName);
		if (errorMessage != null) {
			return errorMessage;
		}
		customer.setLastName(lastName);
		errorMessage = UserValidator.validatePartOfName(middleName);
		if (errorMessage != null) {
			return errorMessage;
		}
		customer.setMiddleName(middleName);
		errorMessage = UserValidator.validateEmail(email);
		if (errorMessage != null) {
			return errorMessage;
		}
		customer.setCustomerEmail(email);
		phone = clearPhone(phone);
		errorMessage = UserValidator.validatePhone(phone);
		System.out.println(phone);
		if (errorMessage != null) {
			return errorMessage;
		}
		customer.setCustomerPhone(phone);
		errorMessage = UserValidator.validateBonus(bonus);
		if (errorMessage != null) {
			return errorMessage;
		}
		try {
			customer.addBonus((long) Double.parseDouble(bonus) * 100);
		} catch (NotEnoughOfBonusExeption notEnoughOfBonusExeption) {
			return "Count of bonus must be possitive";
		}
		int sexID = DAOFactory.getInstance().getSexDAO()
				.findSexIDBySexName(sex).getSexID();
		customer.setSexID(sexID);

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

	private void setUpCoverRepresentation(HttpServletRequest request,
			Customer customer) {
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
				LOG.debug("Obtained image to set as customer cover: "
						+ filename);
				LOG.debug("Will work with repository path equals to: "
						+ coverRepositoryPath);
				LOG.debug("customer ID to process: " + customerID);
				LOG.debug("Image extension to process: " + coverExtension);
				LOG.debug("New customer cover path: " + newCoverPath);
			}
			InputStream in = filePart.getInputStream();
			File file = new File(newCoverPath);
			for (String extension : imageExtensions) {
				File cover = new File(coverRepositoryPath + customerID
						+ extension);
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
			LOG.error(
					"Servlet exception occurred while setting customer cover.",
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

	public CreateCustomerCommand(List<String> imageExtensions) {
		this.imageExtensions = imageExtensions;
	}
	/*
	 * clear phone from no need symbols
	 */
	private String clearPhone(String input){
		input = input.replace(" ", "");
		input = input.replace("(", "");
		input = input.replace(")", "");
		input = input.replace("-", "");
		input = input.replace("+", "");
		return input;
	}
}
