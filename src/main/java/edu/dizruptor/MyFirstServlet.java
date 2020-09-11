package edu.dizruptor;

import java.io.IOException;
import java.util.ArrayList;

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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Contact> contacts = new ArrayList<>();
		Contact contact = new Contact(
				"Kali",
				"Winn",
				"111-111-1111", 
				new Address("1310 wattup", "ogden", "utah", "84403", "USA")
		);
		contacts.add(contact);
		Contact contact2 = new Contact(
				"Justin",
				"Edwards",
				"222-222-3333",
				new Address("4239 Monroe Blvd", "ogden", "utah", "84403", "USA")
		);
		contacts.add(contact2);
		
		req.setAttribute("contact", contact);
		req.setAttribute("contacts", contacts);
		req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
	}

	protected void addContact() {
		System.out.println("testing");
	}
	
}
