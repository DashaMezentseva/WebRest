package com.nixsolutions.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Model model, HttpServletRequest request) {

        model.addAttribute("userName", request.getSession().getAttribute("login"));
        return "user";
    }
}
