package edu.dizruptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.dizruptor.model.Address;
import edu.dizruptor.model.Contact;
import edu.dizruptor.model.Contacts;
import edu.dizruptor.service.ContactService;

@WebServlet(name="MyFirstServlet", urlPatterns="/")
public class MyFirstServlet extends HttpServlet
{
	// data model
	private static Contacts contacts = new Contacts();

	// get list of contacts
	protected List<Contact> getContacts() {
		return contacts.getContacts();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// set attributes
		req.setAttribute("contacts", getContacts());
		// connection to jsp
		req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// GET ALL FORM INPUTS FROM JSP //

		String error = "";

		// personal info
		String firstName = req.getParameter("fname");
		String lastName = req.getParameter("lname");
		String phoneNumber = req.getParameter("phone");
		// address 1 info
		String type1 = req.getParameter("type1");
		String address1 = req.getParameter("address1");
		String city1 = req.getParameter("city1");
		String state1 = req.getParameter("state1");
		String postal1 = req.getParameter("postal1");
		String country1 = req.getParameter("country1");
		// address 2 info
		String type2 = req.getParameter("type2");
		String address2 = req.getParameter("address2");
		String city2 = req.getParameter("city2");
		String state2 = req.getParameter("state2");
		String postal2 = req.getParameter("postal2");
		String country2 = req.getParameter("country2");
		// personal info validation
		if (firstName == null || lastName == null || phoneNumber == null ||
			firstName.equals("") || lastName.equals("") || phoneNumber.equals("")) {
			error = "Personal Info Can't Be Empty";	// set error message
		}
		// address 1 validation
		else if (type1 == null || address1 == null || city1 == null ||
				state1 == null || postal1 == null || country1 == null ||
				type1.equals("") || address1.equals("") || city1.equals("") ||
				state1.equals("") || postal1.equals("") || country1.equals("")) {
			error = "Address 1 Can't Be Empty";	// set error message
		}
		// validated
		else {

			error = "";	// reset error
			List<Address> tempAddresses = new ArrayList<>();	// temp list of addresses
			// store first address
			tempAddresses.add(new Address(type1, address1, city1, state1, postal1, country1));
			// check for second address
			if (!(type2 == null || address2 == null || city2 == null || state2 == null ||
				postal2 == null || country2 == null || type2.equals("") || address2.equals("") ||
				city2.equals("") || state2.equals("") || postal2.equals("") || country2.equals(""))) {
				// add second address
				tempAddresses.add(new Address(type2, address2, city2, state2, postal2, country2));
			}
			// add contact with list of addresses
			contacts.addContact(new Contact(firstName, lastName, phoneNumber, tempAddresses));
		}
		// update jsp
		req.setAttribute("contacts", getContacts());
		req.setAttribute("error", error);
		req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
	}



}
