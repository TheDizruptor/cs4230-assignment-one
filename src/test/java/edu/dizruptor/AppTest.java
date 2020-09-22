package edu.dizruptor;

import edu.dizruptor.model.Address;
import edu.dizruptor.model.Contact;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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

//    // test that servlet creation works and creates an empty
//    // contacts list
//    @Test
//    public void testMyServlet() {
//        MyFirstServlet firstServlet = new MyFirstServlet();
//        Assert.assertEquals(0, firstServlet.getContacts().size());
//    }
//
//    // test constructors for contact and address
//    // as well as add contact to list in servlet
//    @Test
//    public void testConstructorsAndAddContact() {
//
//        MyFirstServlet firstServlet = new MyFirstServlet();
//        // check servlet initially has no contacts
//        Assert.assertEquals(0, firstServlet.getContacts().size());
//        // create addresses
//        List<Address> addresses = new ArrayList<>();
//        addresses.add(new Address("Home", "4239 Monroe Blvd", "Ogden", "UT", "84403", "USA"));
//        // create contact with created addresses
//        Contact contact = new Contact("Justin", "Edwards", "2084038421", addresses);
//        // add contact
//        firstServlet.getContacts().add(contact);
//        // check contact was added
//        Assert.assertEquals(1, firstServlet.getContacts().size());
//        firstServlet.getContacts().clear(); // reset contacts
//    }
//
//    // test null creation of contact as well as getters and setters
//    @Test
//    public void TestContactNullCreationGettersSetters() {
//        // create null contact and set variables
//        Contact contact = new Contact();
//        contact.setFirstName("Justin");
//        contact.setLastName("Edwards");
//        contact.setPhoneNumber("2084038421");
//        contact.setAddresses(new ArrayList<Address>());
//
//        // assert variables set
//        Assert.assertEquals("Justin", contact.getFirstName());
//        Assert.assertEquals("Edwards", contact.getLastName());
//        Assert.assertEquals("2084038421", contact.getPhoneNumber());
//        Assert.assertEquals(new ArrayList<Address>(), contact.getAddresses());
//    }
//
//    // test null creation of address as well as getters and setters
//    @Test
//    public void TestAddressNullCreationGettersSetters() {
//        // create null address
//        Address address = new Address();
//        address.setType("Home");
//        address.setAddress("4239 Monroe Blvd");
//        address.setCity("Ogden");
//        address.setState("UT");
//        address.setPostalCode("84403");
//        address.setCountry("USA");
//        address.setCombinedAddress("Test");
//        // assert variables set
//        Assert.assertEquals("Home", address.getType());
//        Assert.assertEquals("4239 Monroe Blvd", address.getAddress());
//        Assert.assertEquals("Ogden", address.getCity());
//        Assert.assertEquals("UT", address.getState());
//        Assert.assertEquals("84403", address.getPostalCode());
//        Assert.assertEquals("USA", address.getCountry());
//        Assert.assertEquals("Test", address.getCombinedAddress());
//    }
//
//    // tests error set for personal info when there's a
//    // null value
//    @Test
//    public void testThatPostSetsPersonalInfoError() throws ServletException, IOException {
//        MyFirstServlet firstServlet = new MyFirstServlet();
//        // mock request and dispatcher
//        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
//        RequestDispatcher mockedDispatcher = Mockito.mock(RequestDispatcher.class);
//        // return null when fname requested
//        Mockito.when(mockedRequest.getParameter("fname")).thenReturn(null);
//        Mockito.when(mockedRequest.getRequestDispatcher(ArgumentMatchers.anyString())).thenReturn(mockedDispatcher);
//        // create mocked response
//        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
//        // captures string
//        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
//        // posts using mocked request/response, set error appropriately
//        firstServlet.doPost(mockedRequest, mockedResponse);
//        Mockito.verify(mockedRequest).setAttribute(Mockito.eq("error"), captor.capture());
//        String errorValue = captor.getValue();
//        // check error was set correctly
//        Assert.assertEquals("Personal Info Can't Be Empty", errorValue);
//    }
//
//    // tests error set for address when there's a null value
//    @Test
//    public void testThatPostSetsAddressEmptyError() throws ServletException, IOException {
//        MyFirstServlet firstServlet = new MyFirstServlet();
//        // mock request and dispatcher
//        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
//        RequestDispatcher mockedDispatcher = Mockito.mock(RequestDispatcher.class);
//        // return real values when personal info requested
//        Mockito.when(mockedRequest.getParameter("fname")).thenReturn("Justin");
//        Mockito.when(mockedRequest.getParameter("lname")).thenReturn("Edwards");
//        Mockito.when(mockedRequest.getParameter("phone")).thenReturn("2084038421");
//        Mockito.when(mockedRequest.getParameter("address1")).thenReturn(null);
//        Mockito.when(mockedRequest.getRequestDispatcher(ArgumentMatchers.anyString())).thenReturn(mockedDispatcher);
//        // create mocked response
//        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
//        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
//        // posts using mocked request/response, set error appropriately
//        firstServlet.doPost(mockedRequest, mockedResponse);
//        Mockito.verify(mockedRequest).setAttribute(Mockito.eq("error"), captor.capture());
//        String errorValue = captor.getValue();
//        // assert correct error set
//        Assert.assertEquals("Address 1 Can't Be Empty", errorValue);
//    }
//
//    // test post creates new contact when given personal
//    // info, 1 address, and a null value for 2nd address
//    @Test
//    public void testThatPostCreatesContact() throws ServletException, IOException {
//        MyFirstServlet firstServlet = new MyFirstServlet();
//        // mock request and dispatcher
//        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
//        RequestDispatcher mockedDispatcher = Mockito.mock(RequestDispatcher.class);
//        // return real values when personal and address
//        // info requested
//        Mockito.when(mockedRequest.getParameter("fname")).thenReturn("Justin");
//        Mockito.when(mockedRequest.getParameter("lname")).thenReturn("Edwards");
//        Mockito.when(mockedRequest.getParameter("phone")).thenReturn("2084038421");
//        Mockito.when(mockedRequest.getParameter("type1")).thenReturn("Home");
//        Mockito.when(mockedRequest.getParameter("address1")).thenReturn("4239 Monroe Blvd");
//        Mockito.when(mockedRequest.getParameter("city1")).thenReturn("Ogden");
//        Mockito.when(mockedRequest.getParameter("state1")).thenReturn("UT");
//        Mockito.when(mockedRequest.getParameter("postal1")).thenReturn("84403");
//        Mockito.when(mockedRequest.getParameter("country1")).thenReturn("USA");
//        Mockito.when(mockedRequest.getParameter("address2")).thenReturn(null);
//        Mockito.when(mockedRequest.getRequestDispatcher(ArgumentMatchers.anyString())).thenReturn(mockedDispatcher);
//        // create mocked response
//        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
//        // posts using mocked request/response, this should add
//        // a new contact
//        firstServlet.doPost(mockedRequest, mockedResponse);
//        // assert new contact added with 1 address
//        Assert.assertEquals(1, firstServlet.getContacts().size());
//        firstServlet.getContacts().clear(); // clear contacts
//    }
//
//    // test post creates a contact with 2 addresses
//    @Test
//    public void testThatPostCreatesContactWithTwoAddresses() throws ServletException, IOException {
//        MyFirstServlet firstServlet = new MyFirstServlet();
//        // mock request and dispatcher
//        HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
//        RequestDispatcher mockedDispatcher = Mockito.mock(RequestDispatcher.class);
//        // return real values when personal and address
//        // info requested (both addresses)
//        Mockito.when(mockedRequest.getParameter("fname")).thenReturn("Justin");
//        Mockito.when(mockedRequest.getParameter("lname")).thenReturn("Edwards");
//        Mockito.when(mockedRequest.getParameter("phone")).thenReturn("2084038421");
//        Mockito.when(mockedRequest.getParameter("type1")).thenReturn("Home");
//        Mockito.when(mockedRequest.getParameter("address1")).thenReturn("4239 Monroe Blvd");
//        Mockito.when(mockedRequest.getParameter("city1")).thenReturn("Ogden");
//        Mockito.when(mockedRequest.getParameter("state1")).thenReturn("UT");
//        Mockito.when(mockedRequest.getParameter("postal1")).thenReturn("84403");
//        Mockito.when(mockedRequest.getParameter("country1")).thenReturn("USA");
//        Mockito.when(mockedRequest.getParameter("type2")).thenReturn("Home");
//        Mockito.when(mockedRequest.getParameter("address2")).thenReturn("4239 Monroe Blvd");
//        Mockito.when(mockedRequest.getParameter("city2")).thenReturn("Ogden");
//        Mockito.when(mockedRequest.getParameter("state2")).thenReturn("UT");
//        Mockito.when(mockedRequest.getParameter("postal2")).thenReturn("84403");
//        Mockito.when(mockedRequest.getParameter("country2")).thenReturn("USA");
//        Mockito.when(mockedRequest.getRequestDispatcher(ArgumentMatchers.anyString())).thenReturn(mockedDispatcher);
//        // create mocked response
//        HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
//        // posts using mocked request/response, this should add
//        // a new contact with 2 addresses
//        firstServlet.doPost(mockedRequest, mockedResponse);
//        // assert new contact added with 2 addresses
//        Assert.assertEquals(2, firstServlet.getContacts().get(0).getAddresses().size());
//        firstServlet.getContacts().clear(); // clear contacts
//    }

}
