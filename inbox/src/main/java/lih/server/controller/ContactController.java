package lih.server.controller;

import lih.services.ContactService;
import lih.utils.AuthUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huanli on 29/04/2017.
 */
@Controller
@RequestMapping("/contacts")
public class ContactController {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(UserController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private AuthUtils utils;

    /**
     * 根据用户id获取联系人列表
     */
    @GetMapping("/list")
    public String list(Model model) {

        String username = utils.getUserName();

        LOGGER.info("get contacts list with username: {}", username);

        model.addAttribute("contactsList", contactService.getContacts(username));
        model.addAttribute("title", "联系人列表");
        return "contacts/list";
    }

    @RequestMapping("/delete/{userName}")
    public String delete(
            @PathVariable("userName")String userName,
            Model model) {

        String name = utils.getUserName();

        LOGGER.info("delete contacts for user: with contacts: {}", name, userName);

        contactService.deleteContact(name, userName);

        model.addAttribute("contactsList", contactService.getContacts(name));
        model.addAttribute("title", userName + "已经从您的联系人列表删除");

        return "contacts/list";
    }

    @RequestMapping(value = "/add/{userName}")
    public String add(
            @PathVariable("userName")String userName,
            Model model) {

        String name = utils.getUserName();

        LOGGER.info("add contacts for user: with contacts: {}", name, userName);

        contactService.addContact(name, userName);

        model.addAttribute("title", userName + "已经添加到您的联系人中");
        model.addAttribute("isSearched", false);

        return "users/search";
    }
}
