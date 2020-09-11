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

	static {
		contacts.add(new Contact());
	}

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

		// TODO: get contact from jsp
		String firstName = req.getParameter("fname");
		String lastName = req.getParameter("lname");
		String phoneNumber = req.getParameter("phone");
		System.out.println(firstName);
		if (firstName == null) {
			req.setAttribute("error", "FirstName Is Empty");
		}

		contacts.add(new Contact(firstName, lastName, phoneNumber, null));

		this.doGet(req, resp);
//		req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
	}

	protected void addContact() {
		System.out.println("testing");
	}


}
