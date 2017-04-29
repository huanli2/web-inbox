package lih.services.impl;

import lih.server.domain.Contact;
import lih.services.ContactService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huanli on 29/04/2017.
 */
@Service
public class ContactServiceImpl implements ContactService {

    @Override
    public List<Contact> getContacts(String username) {
        List<Contact> contacts = new ArrayList<Contact>();
        contacts.add(new Contact("张三"));
        contacts.add(new Contact("lli", 1));

        return contacts;
    }

    @Override
    public void deleteContact(String deletedBy, String deletedUser) {
        //TODO
    }

    @Override
    public void addContact(String user1, String user2) {
        //TODO
    }
}
