package edu.dizruptor.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Contacts implements Serializable {
    ArrayList<Contact> contacts;

    public Contacts() {
        super();
        this.contacts = new ArrayList<>();
    }
}
