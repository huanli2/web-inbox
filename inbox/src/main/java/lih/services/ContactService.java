package lih.services;

import lih.server.domain.Contact;

import java.util.List;

/**
 * Created by huanli on 29/04/2017.
 */
public interface ContactService {

    List<Contact> getContacts(String username);

    void deleteContact(String deletedBy, String deletedUser);

    void addContact(String user1, String user2);
}
