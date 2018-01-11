package lih.server.controller;


import lih.server.domain.Contact;
import lih.services.ContactService;
import lih.utils.AuthUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@WebAppConfiguration
public class ContactControllerTest {
    private MockMvc mvc;

    @Mock
    private ContactService contactService;

    @Mock
    private AuthUtils utils;

    @InjectMocks
    private ContactController contactController;

    private static final String USERNAME = "username";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(contactController).build();
        Mockito.when(utils.getUserName()).thenReturn(USERNAME);
    }

    @Test
    public void get_list_should_be_ok() throws Exception {
        List<Contact> contacts = new ArrayList<>();
        Mockito.when(contactService.getContacts(USERNAME)).thenReturn(contacts);

        mvc.perform(MockMvcRequestBuilders.get("/contacts/list"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("title", "联系人列表"))
                .andExpect(MockMvcResultMatchers.model().attribute("contactsList", contacts));
    }

    @Test
    public void delete_should_be_ok() throws Exception {
        String name = "name";
        List<Contact> contacts = new ArrayList<>();
        Mockito.when(contactService.getContacts(USERNAME)).thenReturn(contacts);
        mvc.perform(MockMvcRequestBuilders.delete("/contacts/delete/" + name))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("title", name + "已经从您的联系人列表删除"))
                .andExpect(MockMvcResultMatchers.model().attribute("contactsList", contacts));

        Mockito.verify(contactService).deleteContact(USERNAME, name);
    }

    @Test
    public void add_should_be_ok() throws Exception {
        String name = "name";

        mvc.perform(MockMvcRequestBuilders.post("/contacts/add/" + name))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("title", name + "已经添加到您的联系人中"))
                .andExpect(MockMvcResultMatchers.model().attribute("isSearched", false));

        Mockito.verify(contactService).addContact(USERNAME, name);
    }
}
