package lih.server.controller;

import lih.server.domain.ChatHistory;
import lih.server.domain.UserContact;
import lih.services.UserService;
import lih.services.objects.MessageService;
import lih.utils.AuthUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
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
public class UserControllerTest {
    private MockMvc mvc;

    @Mock
    private UserService service;

    @Mock
    private AuthUtils utils;

    @Mock
    private MessageService messageService;

    @InjectMocks
    private UserController controller;

    private static final String USERNAME = "username";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
        Mockito.when(utils.getUserName()).thenReturn(USERNAME);
    }

    @Test
    public void get_search_should_be_ok() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users/search"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("title", "搜索联系人"))
                .andExpect(MockMvcResultMatchers.model().attribute("isSearched", false));
    }

    @Test
    public void post_search_should_be_ok() throws Exception {
        List<UserContact> userList = new ArrayList<>();
        String input = "input";
        Mockito.when(service.searchUsers(input, USERNAME)).thenReturn(userList);

        mvc.perform(MockMvcRequestBuilders.post("/users/search")
                .accept(MediaType.APPLICATION_JSON)
                .param("search", input))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.model().attribute("userList", userList))
                .andExpect(MockMvcResultMatchers.model().attribute("isSearched", true));
    }

    @Test
    public void get_chat_should_be_ok() throws Exception {
        String name = "name";
        List<ChatHistory> chatHistories = new ArrayList<>();
        Mockito.when(messageService.getChatHistory(name, USERNAME)).thenReturn(chatHistories);

        mvc.perform(MockMvcRequestBuilders.get("/users/chats/" + name))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.model().attribute("username2", name))
                .andExpect(MockMvcResultMatchers.model().attribute("chatHistory", chatHistories));

        Mockito.verify(messageService).readAllMessages(name, USERNAME);
    }

    @Test
    public void post_chat_should_be_ok() throws Exception {
        String name = "name";
        List<ChatHistory> chatHistories = new ArrayList<>();
        Mockito.when(messageService.getChatHistory(USERNAME, name)).thenReturn(chatHistories);

        mvc.perform(MockMvcRequestBuilders.post("/users/chats/" + name + "/send")
                .param("message", "rdwhu"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.model().attribute("username2", name))
                .andExpect(MockMvcResultMatchers.model().attribute("chatHistory", chatHistories));

    }

    @Test
    public void post_chat_with_empty_error() throws Exception {
        String name = "name";

        mvc.perform(MockMvcRequestBuilders.post("/users/chats/" + name + "/send"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.model().attribute("username2", "发送消息不能为空！"));
    }
}
