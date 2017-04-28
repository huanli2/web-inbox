package lih.service;

import lih.server.domain.Contact;

import java.util.List;

/**
 * Created by sxcheng on 29/04/2017.
 */
public interface ContactService {

    List<Contact> getContacts(String username);

    void deleteContact(String deletedBy, String deletedUser);
}
