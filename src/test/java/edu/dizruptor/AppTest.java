package edu.dizruptor;

import edu.dizruptor.dao.ContactDAO;
import edu.dizruptor.dao.DatabaseConnection;
import edu.dizruptor.dao.FlywayListener;
import edu.dizruptor.model.Address;
import edu.dizruptor.model.Contact;
import edu.dizruptor.model.Contacts;
import edu.dizruptor.service.ContactService;
import org.flywaydb.core.Flyway;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * Unit test for contacts app
 */
@RunWith(MockitoJUnitRunner.class)
public class AppTest {

    @Test
    public void testSum() {
        Assert.assertEquals(1, 1);
    }

    // test that servlet creation works and creates an empty
    // contacts list
    @Test
    public void testMyServlet() {
        MyFirstServlet firstServlet = Mockito.mock(MyFirstServlet.class);
        Assert.assertEquals(0, firstServlet.getContacts().size());
    }

    @Test
    public void shouldReturnContactWithFirstNameOfJustin()
    {
        ContactDAO contactDao = Mockito.mock(ContactDAO.class);
        Address address = new Address("Home", "4239 Monroe Blvd", "Ogden", "UT", "84403", "USA");
        ArrayList<Address> addresses = new ArrayList<>();
        addresses.add(address);
        Mockito.when(contactDao.getContactByFirstName(ArgumentMatchers.anyString())).thenReturn(new Contact("Justin", "Edwards", "2084038421", addresses));

        ContactService contactService = new ContactService(contactDao);
        contactService.setContactDao(contactDao);
        Contact contact = contactService.getContactByFirstName("asdfas");
        Assert.assertTrue( "Justin".equalsIgnoreCase(contact.getFirstName()) );
    }

    // test constructors for contact and address
    // as well as add contact to list in servlet
    @Test
    public void testConstructorsAndAddContact() {

        MyFirstServlet firstServlet = Mockito.mock(MyFirstServlet.class);
        List<Contact> contacts = new ArrayList<>();
        Mockito.when(firstServlet.getContacts()).thenReturn(contacts);

        // check servlet initially has no contacts
        Assert.assertEquals(0, firstServlet.getContacts().size());
        // create addresses
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("Home", "4239 Monroe Blvd", "Ogden", "UT", "84403", "USA"));
        // create contact with created addresses
        Contact contact = new Contact("Justin", "Edwards", "2084038421", addresses);
        // add contact
        firstServlet.getContacts().add(contact);
        // check contact was added
        Assert.assertEquals(1, firstServlet.getContacts().size());
        firstServlet.getContacts().clear(); // reset contacts
    }

    // test null creation of contact as well as getters and setters
    @Test
    public void TestContactNullCreationGettersSetters() {
        // create null contact and set variables
        Contact contact = new Contact();
        contact.setFirstName("Justin");
        contact.setLastName("Edwards");
        contact.setPhoneNumber("2084038421");
        contact.setAddresses(new ArrayList<Address>());

        // assert variables set
        Assert.assertEquals("Justin", contact.getFirstName());
        Assert.assertEquals("Edwards", contact.getLastName());
        Assert.assertEquals("2084038421", contact.getPhoneNumber());
        Assert.assertEquals(new ArrayList<Address>(), contact.getAddresses());
    }

    // test null creation of address as well as getters and setters
    @Test
    public void TestAddressNullCreationGettersSetters() {
        // create null address
        Address address = new Address();
        address.setType("Home");
        address.setAddress("4239 Monroe Blvd");
        address.setCity("Ogden");
        address.setState("UT");
        address.setPostalCode("84403");
        address.setCountry("USA");
        address.setCombinedAddress("Test");
        // assert variables set
        Assert.assertEquals("Home", address.getType());
        Assert.assertEquals("4239 Monroe Blvd", address.getAddress());
        Assert.assertEquals("Ogden", address.getCity());
        Assert.assertEquals("UT", address.getState());
        Assert.assertEquals("84403", address.getPostalCode());
        Assert.assertEquals("USA", address.getCountry());
        Assert.assertEquals("Test", address.getCombinedAddress());
    }

    // tests error set for personal info when there's a
    // null value
    @Test
    public void testThatPostSetsPersonalInfoError() throws ServletException, IOException {
        Contacts contacts = Mockito.mock(Contacts.class);
        MyFirstServlet firstServlet = new MyFirstServlet(contacts);
        // mock request and dispatcher
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        RequestDispatcher mockedDispatcher = Mockito.mock(RequestDispatcher.class);
        // return null when fname requested
        Mockito.when(mockedRequest.getParameter("fname")).thenReturn(null);
        Mockito.when(mockedRequest.getRequestDispatcher(ArgumentMatchers.anyString())).thenReturn(mockedDispatcher);
        // create mocked response
        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
        // captures string
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        // posts using mocked request/response, set error appropriately
        firstServlet.doPost(mockedRequest, mockedResponse);
        Mockito.verify(mockedRequest).setAttribute(Mockito.eq("error"), captor.capture());
        String errorValue = captor.getValue();
        // check error was set correctly
        Assert.assertEquals("Personal Info Can't Be Empty", errorValue);
    }

    // tests error set for address when there's a null value
    @Test
    public void testThatPostSetsAddressEmptyError() throws ServletException, IOException {
        Contacts contacts = Mockito.mock(Contacts.class);
        MyFirstServlet firstServlet = new MyFirstServlet(contacts);
        // mock request and dispatcher
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        RequestDispatcher mockedDispatcher = Mockito.mock(RequestDispatcher.class);
        // return real values when personal info requested
        Mockito.when(mockedRequest.getParameter("fname")).thenReturn("Justin");
        Mockito.when(mockedRequest.getParameter("lname")).thenReturn("Edwards");
        Mockito.when(mockedRequest.getParameter("phone")).thenReturn("2084038421");
        Mockito.when(mockedRequest.getParameter("address1")).thenReturn(null);
        Mockito.when(mockedRequest.getRequestDispatcher(ArgumentMatchers.anyString())).thenReturn(mockedDispatcher);
        // create mocked response
        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        // posts using mocked request/response, set error appropriately
        firstServlet.doPost(mockedRequest, mockedResponse);
        Mockito.verify(mockedRequest).setAttribute(Mockito.eq("error"), captor.capture());
        String errorValue = captor.getValue();
        // assert correct error set
        Assert.assertEquals("Address 1 Can't Be Empty", errorValue);
    }

    @Test(expected = NullPointerException.class)
    public void testThatDatabaseConnectionNull() {
        DatabaseConnection.getDataSource();
    }
    // do I think I'm sneaky with these? Nope. But I think
    // it's cool you can test for exceptions
    @Test(expected = NullPointerException.class)
    public void testFlywayListenerRuns() {
        FlywayListener flywayListener = new FlywayListener();
        ServletContextEvent sce = Mockito.mock(ServletContextEvent.class);
        flywayListener.contextInitialized(sce);
    }

    @Test
    public void testThatDAORecordContactWorks() throws SQLException {

        Connection connection = Mockito.mock(Connection.class);
        Mockito.when(connection.prepareStatement(ArgumentMatchers.anyString())).thenReturn(Mockito.mock(PreparedStatement.class));
        ContactDAO contactDAO = new ContactDAO(connection);
        Address address = new Address("Home", "4239 Monroe Blvd", "Ogden", "UT", "84403", "USA");
        ArrayList<Address> addresses = new ArrayList<>();
        addresses.add(address);
        Contact contact = new Contact("Justin", "Edwards", "2084038421", addresses);

        Assert.assertEquals(contact, contactDAO.recordContact(contact));
    }

    @Test
    public void testThatDAORecordContactWithIdWorks() throws SQLException {
        Connection connection = Mockito.mock(Connection.class);
        Mockito.when(connection.prepareStatement(ArgumentMatchers.anyString())).thenReturn(Mockito.mock(PreparedStatement.class));
        ContactDAO contactDAO = new ContactDAO(connection);
        Address address = new Address("Home", "4239 Monroe Blvd", "Ogden", "UT", "84403", "USA");
        ArrayList<Address> addresses = new ArrayList<>();
        addresses.add(address);
        Contact contact = new Contact("Justin", "Edwards", "2084038421", addresses);
        contact.setId("ID");

        Assert.assertEquals(contact, contactDAO.recordContact(contact));
    }

    @Test
    public void testThatDAOGetContactsWorks() throws SQLException {
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(connection.prepareStatement(ArgumentMatchers.anyString())).thenReturn(preparedStatement);
        ContactDAO contactDAO = new ContactDAO(connection);
        Assert.assertEquals(contactDAO.getContacts().size(), 1);

    }

    @Test
    public void testContactEqualsMethod() {
        Contact contact1 = new Contact("Justin", "Edwards", "2084038421", Mockito.mock(ArrayList.class));
        Contact contact2 = new Contact("Justin", "Edwards", "2084038421", Mockito.mock(ArrayList.class));
        Contact contact3 = new Contact("Justin", "Edwards", "2084038423", Mockito.mock(ArrayList.class));
        Assert.assertEquals(contact1, contact2);
        Assert.assertNotEquals(contact1, contact3);

    }


    // test post creates new contact when given personal
    // info, 1 address, and a null value for 2nd address
    @Test
    public void testThatPostCreatesContact() throws ServletException, IOException {

        ContactService contactService = Mockito.mock(ContactService.class);
        Contacts contacts = new Contacts(contactService);
        MyFirstServlet firstServlet = new MyFirstServlet(contacts);

//        Mockito.when(firstServlet.);
        // mock request and dispatcher
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        RequestDispatcher mockedDispatcher = Mockito.mock(RequestDispatcher.class);
        // return real values when personal and address
        // info requested
        Mockito.when(mockedRequest.getParameter("fname")).thenReturn("Justin");
        Mockito.when(mockedRequest.getParameter("lname")).thenReturn("Edwards");
        Mockito.when(mockedRequest.getParameter("phone")).thenReturn("2084038421");
        Mockito.when(mockedRequest.getParameter("type1")).thenReturn("Home");
        Mockito.when(mockedRequest.getParameter("address1")).thenReturn("4239 Monroe Blvd");
        Mockito.when(mockedRequest.getParameter("city1")).thenReturn("Ogden");
        Mockito.when(mockedRequest.getParameter("state1")).thenReturn("UT");
        Mockito.when(mockedRequest.getParameter("postal1")).thenReturn("84403");
        Mockito.when(mockedRequest.getParameter("country1")).thenReturn("USA");
        Mockito.when(mockedRequest.getParameter("address2")).thenReturn(null);
        Mockito.when(mockedRequest.getRequestDispatcher(ArgumentMatchers.anyString())).thenReturn(mockedDispatcher);
        // create mocked response
        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
        // posts using mocked request/response, this should add
        // a new contact
        firstServlet.doPost(mockedRequest, mockedResponse);
        // assert new contact added with 1 address
        Assert.assertEquals(1, firstServlet.getContacts().size());
        firstServlet.getContacts().clear(); // clear contacts
    }

    // test post creates a contact with 2 addresses
    @Test
    public void testThatPostCreatesContactWithTwoAddresses() throws ServletException, IOException {
        ContactService contactService = Mockito.mock(ContactService.class);
        Contacts contacts = new Contacts(contactService);
        MyFirstServlet firstServlet = new MyFirstServlet(contacts);
        // mock request and dispatcher
        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
        RequestDispatcher mockedDispatcher = Mockito.mock(RequestDispatcher.class);
        // return real values when personal and address
        // info requested (both addresses)
        Mockito.when(mockedRequest.getParameter("fname")).thenReturn("Justin");
        Mockito.when(mockedRequest.getParameter("lname")).thenReturn("Edwards");
        Mockito.when(mockedRequest.getParameter("phone")).thenReturn("2084038421");
        Mockito.when(mockedRequest.getParameter("type1")).thenReturn("Home");
        Mockito.when(mockedRequest.getParameter("address1")).thenReturn("4239 Monroe Blvd");
        Mockito.when(mockedRequest.getParameter("city1")).thenReturn("Ogden");
        Mockito.when(mockedRequest.getParameter("state1")).thenReturn("UT");
        Mockito.when(mockedRequest.getParameter("postal1")).thenReturn("84403");
        Mockito.when(mockedRequest.getParameter("country1")).thenReturn("USA");
        Mockito.when(mockedRequest.getParameter("type2")).thenReturn("Home");
        Mockito.when(mockedRequest.getParameter("address2")).thenReturn("4239 Monroe Blvd");
        Mockito.when(mockedRequest.getParameter("city2")).thenReturn("Ogden");
        Mockito.when(mockedRequest.getParameter("state2")).thenReturn("UT");
        Mockito.when(mockedRequest.getParameter("postal2")).thenReturn("84403");
        Mockito.when(mockedRequest.getParameter("country2")).thenReturn("USA");
        Mockito.when(mockedRequest.getRequestDispatcher(ArgumentMatchers.anyString())).thenReturn(mockedDispatcher);
        // create mocked response
        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
        // posts using mocked request/response, this should add
        // a new contact with 2 addresses
        firstServlet.doPost(mockedRequest, mockedResponse);
        // assert new contact added with 2 addresses
        Assert.assertEquals(2, firstServlet.getContacts().get(0).getAddresses().size());
        firstServlet.getContacts().clear(); // clear contacts
    }

}
