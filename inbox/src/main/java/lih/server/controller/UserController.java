package lih.server.controller;

import lih.server.domain.Chat;
import lih.server.domain.ChatHistory;
import lih.server.domain.Search;
import lih.services.UserService;
import lih.services.objects.MessageService;
import lih.utils.AuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


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

    @Autowired
    private MessageService messageService;

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

    @GetMapping("/chats/{username}")
    public String chat(@PathVariable("username")String username, Model model) {

        String name = utils.getUserName();


        model.addAttribute("username2", username);

        messageService.readAllMessages(username, name);

        List<ChatHistory> historyList = messageService.getChatHistory(name, username);

        LOGGER.info("history size:{} between {} and {}", historyList.size(), username, name);

        model.addAttribute("chatHistory", historyList);

        return "users/chats";
    }

    @PostMapping("/chats/{username}/send")
    public String chat(Chat chat, @PathVariable("username") String username, Model model) {

        String name = utils.getUserName();
        String message = chat.getMessage();

        LOGGER.info("{} send message:{} to {}", name, message, username);

        if (StringUtils.isEmpty(chat.getMessage())) {

            model.addAttribute("username2", "发送消息不能为空！");

        } else {

            messageService.sendMessage(name, username, message);

            model.addAttribute("username2", username);
        }

        model.addAttribute("chatHistory", messageService.getChatHistory(name, username));

        return "users/chats";
    }
}
