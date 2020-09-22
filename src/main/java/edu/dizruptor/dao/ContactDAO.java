package edu.dizruptor.dao;
import java.util.*;

import edu.dizruptor.model.Address;
import edu.dizruptor.model.Contact;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactDAO {

    private Connection db;

    private static final String CONTACT_INSERT = "INSERT INTO CONTACT("
            + "FIRST_NAME,"
            + "LAST_NAME,"
            + "PHONE_NUMBER,"
            + "ID"
            + ") VALUES("
            + "?,"
            + "?,"
            + "?,"
            + "?"
            + ")";

    private static final String CONTACT_UPDATE = "UPDATE CONTACT "
            + "SET FIRST_NAME = ?, "
            + "LAST_NAME = ?, "
            + "PHONE_NUMBER = ? "
            + "WHERE ID = ?";

    private static final String ADDRESS_INSERT = "INSERT INTO ADDRESS("
            + "ID,"
            + "CONTACT_ID,"
            + "ADDRESS_TYPE,"
            + "ADDRESS_LINE,"
            + "COUNTRY,"
            + "CITY,"
            + "STATE,"
            + "POSTAL_CODE"
            + ") VALUES("
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?"
            + ")";


    public ContactDAO () {
        try {
            db = DatabaseConnection.getDataSource().getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Contact recordContact(Contact contact) {
        try {
            if(contact.getId() != null) {
                PreparedStatement contactStatement = db.prepareStatement(CONTACT_UPDATE);
                contactStatement.setString(4, contact.getId());
                contactStatement.setString(1, contact.getFirstName());
                contactStatement.setString(2, contact.getLastName());
                contactStatement.setString(3, contact.getPhoneNumber());
                contactStatement.executeUpdate();
                contactStatement.close();
            } else {
                contact.setId(UUID.randomUUID().toString());
                PreparedStatement contactStatement = db.prepareStatement(CONTACT_INSERT);
                contactStatement.setString(4, contact.getId());
                contactStatement.setString(1, contact.getFirstName());
                contactStatement.setString(2, contact.getLastName());
                contactStatement.setString(3, contact.getPhoneNumber());
                contactStatement.executeUpdate();
                contactStatement.close();
            }

            PreparedStatement addressStatement =db.prepareStatement(ADDRESS_INSERT);
            for(int i = 0; i < contact.getAddresses().size(); i++) {
                Address currentAddress = contact.getAddresses().get(i);
                String addressId = UUID.randomUUID().toString();
                addressStatement.setString(1, addressId);
                addressStatement.setString(2, contact.getId());
                addressStatement.setString(3, currentAddress.getType());
                addressStatement.setString(4, currentAddress.getAddress());
                addressStatement.setString(5, currentAddress.getCountry());
//                addressStatement.setString(5, currentAddress.getAddressLine2());
                addressStatement.setString(6, currentAddress.getCity());
                addressStatement.setString(7, currentAddress.getState());
                addressStatement.setString(8, currentAddress.getPostalCode());

                currentAddress.setId(addressId);

                addressStatement.addBatch();

                System.out.println(addressStatement.toString());

            }

            addressStatement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error Inserting Contact");
        }

        return contact;
    }

    public List<Contact> getContacts() {
        Map<String, Contact> contactMap = new HashMap<>();
//        System.out.println("testing");
        String sql = "SELECT "
                + "c.ID as contactId,"
                + "c.FIRST_NAME as firstName,"
                + "c.LAST_NAME as lastName,"
                + "c.PHONE_NUMBER as phoneNumber,"
                + "a.ID as addressId,"
                + "a.ADDRESS_TYPE as addressType,"
                + "a.ADDRESS_LINE as addressLine,"
                + "a.COUNTRY as country,"
                + "a.CITY as city,"
                + "a.STATE as state,"
                + "a.POSTAL_CODE as postalCode "
                + "FROM CONTACT c "
                + "INNER JOIN ADDRESS a ON c.ID = a.CONTACT_ID "
                + "WHERE 1=1";
        try {
            PreparedStatement contactStatement = db.prepareStatement(sql);
            ResultSet rs = contactStatement.executeQuery();
            while (rs.next()) {
//                System.out.println(rs.getString("contactId"));
                String contactId = rs.getString("contactId");
                Contact contact = contactMap.get(contactId);
                if(contact != null) {
                    contact.addAddress(getAddressFromResultSet(rs));
                } else {
                    contact = new Contact();
                    contact.setId(contactId);
                    contact.setFirstName(rs.getString("firstName"));
                    contact.setLastName(rs.getString("lastName"));
                    contact.setPhoneNumber(rs.getString("phoneNumber"));
                    contact.addAddress(getAddressFromResultSet(rs));
                    contact.getAddresses().get(0).generateCombinedAddress();
                    contactMap.put(contactId, contact);
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ArrayList<Contact> conts = new ArrayList<>(contactMap.values());
//        System.out.println("test: " + conts.get(0).getAddresses().get(0).getAddress());

        return new ArrayList<Contact>(contactMap.values());

    }

    private Address getAddressFromResultSet(ResultSet rs) throws SQLException {
        Address address = new Address();
        address.setId(rs.getString("addressId"));
        address.setType(rs.getString("addressType"));
        address.setAddress(rs.getString("addressLine"));
        address.setCountry(rs.getString("country"));
//        address.setAddressLine2(rs.getString("addressLine2"));
        address.setCity(rs.getString("city"));
        address.setState(rs.getString("state"));
        address.setPostalCode(rs.getString("postalCode"));
        System.out.println(address.getAddress());
        return address;
    }


    public Contact getContactByFirstName(String firstName) {
        Contact response = null;

        return response;
    }

}
