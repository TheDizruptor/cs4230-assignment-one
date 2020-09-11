package edu.dizruptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.dizruptor.model.Address;
import edu.dizruptor.model.Contact;

@WebServlet(name="MyFirstServlet", urlPatterns="/")
public class MyFirstServlet extends HttpServlet
{

	private static List<Contact> contacts = new ArrayList<>();

//	static {
//		contacts.add(new Contact());
//	}

	protected List<Contact> getContacts() {
		return contacts;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		Contact contact = new Contact(
//				"Kali",
//				"Winn",
//				"111-111-1111",
//				new Address("1310 wattup", "ogden", "utah", "84403", "USA")
//		);
//		contacts.add(contact);
//		Contact contact2 = new Contact(
//				"Justin",
//				"Edwards",
//				"222-222-3333",
//				new Address("4239 Monroe Blvd", "ogden", "utah", "84403", "USA")
//		);
//		contacts.add(contact2);
		
		req.setAttribute("contacts", getContacts());
		req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// GET ALL FORM INPUTS FROM JSP //

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
		System.out.println(type1);
		// personal info validation
		if (firstName == null || lastName == null || phoneNumber == null) {
			req.setAttribute("error", "Personal Info Can't Be Empty");
		}
		// address 1 validation
		else if (type1 == null || address1 == null || city1 == null ||
		state1 == null || postal1 == null || country1 == null) {
			req.setAttribute("error", "Address 1 Can't Be Empty");
		}
		// validated
		else {
			System.out.println("here");
			List<Address> tempAddresses = new ArrayList<>();
			tempAddresses.add(new Address(type1, address1, city1, state1, postal1, country1));
			contacts.add(new Contact(firstName, lastName, phoneNumber, tempAddresses));
		}



		this.doGet(req, resp);
//		req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
	}

	protected void addContact() {
		System.out.println("testing");
	}


}
