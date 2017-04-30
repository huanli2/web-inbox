package lih.services.impl;

import lih.server.domain.Contact;
import lih.services.ContactService;
import lih.services.mapper.ContactMapper;
import lih.services.objects.Contacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huanli on 29/04/2017.
 */
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactMapper mapper;

    @Override
    public List<Contact> getContacts(String username) {

        return mapper.getContactsUnreadNum(username);
    }

    @Override
    public void deleteContact(String deletedBy, String deletedUser) {

        mapper.deleteContacts(deletedBy, deletedUser);
    }

    @Override
    public void addContact(String user1, String user2) {
        Contacts c = new Contacts(user1, user2);
        mapper.addContact(c);
    }
}
