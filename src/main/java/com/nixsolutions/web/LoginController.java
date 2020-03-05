package com.nixsolutions.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.google.code.kaptcha.Constants;
import com.nixsolutions.domain.User;
import com.nixsolutions.dto.RegistrationUserDto;
import com.nixsolutions.dto.UserDto;
import com.nixsolutions.service.RoleService;
import com.nixsolutions.service.UserService;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

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

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String loadFormPage(Model model) {
        model.addAttribute("registrationUserDto", new RegistrationUserDto());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String submitForm(@ModelAttribute("registrationUserDto") RegistrationUserDto userDto, Model model, BindingResult bindingResult,
                             @RequestParam("passwordAgain") String passwordAgain, HttpServletRequest request) {
        if (!isUniqueLogin(userDto)) {
            FieldError loginAlreadyUse = new FieldError("login", "login",
                "login already in use");
            bindingResult.addError(loginAlreadyUse);
        }
        if (!isUniqueEmail(userDto)) {
            FieldError emailAlreadyUse = new FieldError("email", "email",
                "email already in use");
            bindingResult.addError(emailAlreadyUse);
        }
        if (userDto.getPassword().isEmpty()){
            FieldError emptyPassword = new FieldError("password", "password",
                "password should not be empty");
            bindingResult.addError(emptyPassword);
        }

        if (passwordAgain.isEmpty()){
            FieldError confirmPassword = new FieldError("passwordAgain", "passwordAgain",
                "confirm your password");
            bindingResult.addError(confirmPassword);
        }

        if (!passwordAgain.equals(userDto.getPassword())) {
            FieldError passwordNotEquals = new FieldError("password",
                "password", "password not equals");
            bindingResult.addError(passwordNotEquals);
        }
        isCorrectCaptcha(request, userDto, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("error",
                bindingResult.getFieldError().getDefaultMessage());
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("userDto", userDto);
            return "registration";
        }

        try {
            userDto.setRole(roleService.findById(1L));
            User user = UserDto.dtoToUser(userDto);
            userService.create(user);
            model.addAttribute("error", "user successfully created");
            model.addAttribute("users", userService.findAll());
            model.addAttribute("userName", user.getLogin());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getCause());
        }
        return "user";

    }

    protected boolean isUniqueLogin (RegistrationUserDto userDto){
        for (User user : userService.findAll()) {
            if (user.getLogin().equals(userDto.getLogin())) {
                return false;
            }
        }
        return true;
    }
    protected boolean isUniqueEmail (RegistrationUserDto userDto){
        for (User user : userService.findAll()) {
            if (user.getEmail().equals(userDto.getEmail())) {
                return false;
            }
        }
        return true;
    }
    private void isCorrectCaptcha(HttpServletRequest request, RegistrationUserDto userDto, BindingResult result) {
        String captchaId = (String) request.getSession().getAttribute(
            Constants.KAPTCHA_SESSION_KEY);
        String response = userDto.getCaptcha();
        if (!response.equalsIgnoreCase(captchaId)) {
            result.rejectValue("captcha", "InvalidCaptcha", "Invalid Entry");
        }
    }
}


