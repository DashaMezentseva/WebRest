package com.nixsolutions.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.nixsolutions.domain.User;
import com.nixsolutions.service.RoleDao;
import com.nixsolutions.service.UserDao;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private UserDao userService;
    @Autowired
    private RoleDao roleService;

    @RequestMapping(value = {"/login", "/"}, method = GET)
    public String showLogin(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessge = null;
        if (error != null) {
            errorMessge = "Username or Password is incorrect !";
        }
        if (logout != null) {
            errorMessge = "You have been successfully logged out !";
        }
        model.addAttribute("errorMessge", errorMessge);
        return "login";
    }


//
//    @RequestMapping(value = {"/login"}, method = POST)
//    public String loginUser(@RequestParam(name = "username", required = true) String login,
//                            @RequestParam(name = "password", required = true) String password, Model model, HttpSession session) {
//        User user = userService.findByLogin(login);
//
//
//        if (user.getUserId() != null && user.getPassword().equals(password)) {
//            model.addAttribute("loggedUser", user);
//            session.setAttribute("login", login);
//            if (user.getRole().getName().equals("admin")) {
//                return "redirect:admin";
//            } else {
//                model.addAttribute("userName", user.getLogin());
//                return "user";
//            }
//
//        }
//        model.addAttribute("message",
//            "Wrong users name or password");
//        return "login";
//    }

    @RequestMapping(method = GET, value = {"*/logout", "/logout"})
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:login";
    }

    @RequestMapping(method = GET, value = "/home")
    public ModelAndView process(
        Principal principal, HttpSession session) {
        ModelAndView modelAndViewUser = new ModelAndView("redirect:user");
        ModelAndView modelAndViewAdmin = new ModelAndView("redirect:admin");
        String login = principal.getName();
        User userDB = userService.findByLogin(login);
        if (userDB.getRole().getRoleId() == 1L) {
            session.setAttribute("userName", userDB.getLogin());
            return modelAndViewUser;
        }
        if (userDB.getRole().getRoleId() == 2L) {
            session.setAttribute("adminName", userDB.getLogin());
            return modelAndViewAdmin;
        }

        return new ModelAndView("redirect:login");
    }

}


