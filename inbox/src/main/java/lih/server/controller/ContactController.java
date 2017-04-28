package lih.server.controller;

import lih.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by sxcheng on 29/04/2017.
 */
@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    /**
     * 根据用户id获取联系人列表
     */
    @GetMapping("/list/{userName}")
    public ModelAndView list(@PathVariable("userName")String userName, Model model) {

        model.addAttribute("contactsList", contactService.getContacts(userName));
        model.addAttribute("title", "联系人列表");

        return new ModelAndView("contacts/list", "contactModel", model);
    }
}
