/**
 * http://zhaozhiming.github.io/blog/2014/06/16/spring-mvc-unit-test-part-1/
 */
package lih.server.controller;

import lih.services.SecurityService;
import lih.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@WebAppConfiguration
public class MainControllerTest {
    private MockMvc mvc;

    @Mock
    private UserService userService;

    @Mock
    private SecurityService securityService;

    @InjectMocks
    private MainController mainController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    public void post_register_should_be_ok() throws Exception {
        String username = "username";
        String password = "password";

        mvc.perform(MockMvcRequestBuilders.post("/register")
                .param("userName", username)
                .param("password", password)
                .accept(MediaType.APPLICATION_JSON)
                ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isMovedTemporarily())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        Mockito.verify(securityService).autologin(username, password);
        Mockito.verify(userService).addUser(Mockito.any());
    }
}
