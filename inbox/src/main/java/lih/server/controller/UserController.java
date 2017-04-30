package lih.server.controller;

import lih.server.domain.Search;
import lih.services.UserService;
import lih.utils.AuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by huanli on 28/04/2017.
 */


@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @Autowired
    private AuthUtils utils;

    @GetMapping("/search")
    public String search(Model model) {

        String name = utils.getUserName();

        LOGGER.info("enter search user page with username:{}", name);

        model.addAttribute("title", "搜索联系人");
        model.addAttribute("isSearched", false);

        return "users/search";
    }

    @PostMapping(value = "/search")
    public String search(Search search, Model model) {

        String name = utils.getUserName();

        String input = search.getSearch();
        LOGGER.info("search user lists with username input:{}, username:{}", input, name);

        model.addAttribute("title", "搜索联系人");
        model.addAttribute("isSearched", true);
        model.addAttribute("userList", service.searchUsers(input, name));

        return "users/search";

    }

    @RequestMapping("/chats/{username}")
    public String chat(@PathVariable("userName")String userName) {

        //TODO
        return "users/chats";
    }
}
