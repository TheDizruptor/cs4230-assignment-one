package edu.dizruptor.model;

import edu.dizruptor.dao.ContactDAO;
import edu.dizruptor.service.ContactService;

import java.util.List;

public class Contacts {
    private List<Contact> contacts;

    private ContactService contactService = null;
//    private ContactDAO dbContact = new ContactDAO();

    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        contactService.recordContact(contact);
    }

    // get contacts from db
    public Contacts() {
        this.contactService = new ContactService();
        this.contacts = contactService.getContacts();
    }

}
