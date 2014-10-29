package ua.nure.sigma.store.web.command.addNewCustomer;

import org.apache.log4j.Logger;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.postgresql.PosgreSqlDAO;
import ua.nure.sigma.store.entity.Sex;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.list.Sexes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * Created by Vlad Samotskiy on 27.10.14.
 */

// this command invoke addNewFilm.jsp
public class AddNewCustomerCommand extends Command{
	
	private static final Logger LOG = Logger.getLogger(AddNewCustomerCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		LOG.debug("AddNewCustomerCommand started.");
	    List<Sex> sexes = DAOFactory.getInstance().getSexDAO().findAllSex();
		Sexes paramSexes = new Sexes(sexes);
		for (Sex sex : sexes) {
			LOG.debug(sex.getSexName());
		}
		request.setAttribute("sexes", paramSexes);
        return Paths.PAGE_ADD_NEW_CUSTOMER;
    }
}
