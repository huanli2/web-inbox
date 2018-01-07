package lih.server.controller;

import lih.server.domain.User;
import lih.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lih.services.SecurityService;

/**
 * Created by huanli on 29/04/2017.
 */

@Controller
public class MainController {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @GetMapping(value={"/", "/index"})
    public String root() {

        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {

        model.addAttribute("isLoginError", true);
        model.addAttribute("errMsg", "登陆失败，请检查您的用户名或者密码是否正确！");

        return login();
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user, Model model) {
        LOGGER.info("register user with username:" + user.getUserName());

        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {

            model.addAttribute("isEmpty", true);
            model.addAttribute("errMsg", "用户名和密码不能为空！");

            return register();
        } else {

            userService.addUser(user);

            securityService.autologin(user.getUserName(), user.getPassword());

            return "redirect:/";
        }

    }
}
