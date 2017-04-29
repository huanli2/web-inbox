package lih.server.controller;

import lih.services.ContactService;
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

    @Autowired
    private ContactService contactService;

    /**
     * 根据用户id获取联系人列表
     */
    @GetMapping("/list/{userName}")
    public String list(@PathVariable("userName")String userName, Model model) {

        model.addAttribute("contactsList", contactService.getContacts(userName));
        model.addAttribute("title", "联系人列表");

        return "contacts/list";
    }

    @RequestMapping(value = "/{userName1}/add/{userName2}")
    public String add(
            @PathVariable("userName1")String userName1,
            @PathVariable("userName2")String userName2,
            Model model) {

        contactService.addContact(userName1, userName2);

        model.addAttribute("title", userName2 + "已经添加到您的联系人中");
        model.addAttribute("user", userName1);
        model.addAttribute("isSearched", false);

        return "users/search";
    }
}
