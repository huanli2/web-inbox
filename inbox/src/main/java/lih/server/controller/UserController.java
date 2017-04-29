package lih.server.controller;

import lih.server.domain.UserContact;
import lih.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by huanli on 28/04/2017.
 */


@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserService service;

    @GetMapping("/search/{username}")
    public String search(Model model) {

        model.addAttribute("title", "搜索联系人");
        model.addAttribute("isSearched", false);

        return "users/search";
    }

    @PostMapping(value = "/search")
    public String search(UserContact user, Model model) {

        String username = user.getUsername();
        LOGGER.info("search user lists with username input:{}", username);

        model.addAttribute("title", "搜索联系人");
        model.addAttribute("isSearched", true);
        model.addAttribute("userList", service.searchUsers(username));

        return "users/search";

    }

}
