package edu.dizruptor.model;

import edu.dizruptor.dao.ContactDAO;

import java.util.List;

public class Contacts {
    private List<Contact> contacts;
    private ContactDAO dbContact = new ContactDAO();

    public List<Contact> getContacts() {
        return contacts;
    }
    public void addContact(Contact contact) {
        contacts.add(contact);
        dbContact.recordContact(contact);
    }

    // get contacts from db
    public Contacts() {
        this.contacts = dbContact.getContacts();
    }
}
