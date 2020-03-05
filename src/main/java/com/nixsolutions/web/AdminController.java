package com.nixsolutions.web;

import com.nixsolutions.domain.User;
import com.nixsolutions.dto.UserDto;
import com.nixsolutions.service.RoleService;
import com.nixsolutions.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView homePage(ModelAndView modelAndView, HttpServletRequest req) {
        modelAndView.addObject("adminName", req.getSession().getAttribute("login"));
        modelAndView.addObject("users", userService.findAll());
        modelAndView.setViewName("admin");
        modelAndView.addObject("adminName", req.getSession().getAttribute("login"));
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser(Model model, HttpServletRequest request) {
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("adminName", request.getSession().getAttribute("login"));
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    protected String submitAdd(@Valid @ModelAttribute("userDto") UserDto userDto,
                               BindingResult bindingResult, Model model,
                               @RequestParam("passwordAgain") String passwordAgain) {

        if (!isUniqueLogin(userDto)) {
            FieldError loginAlreadyUse = new FieldError("login", "login",
                "login already in use");
            bindingResult.addError(loginAlreadyUse);
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

        if (bindingResult.hasErrors()) {
            model.addAttribute("error",
                bindingResult.getFieldError().getDefaultMessage());
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("userDto", userDto);
            return "add";
        }
        try {
            User user = UserDto.dtoToUser(userDto);
            userService.create(user);
            model.addAttribute("error", "user successfully created");
            model.addAttribute("users", userService.findAll());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getCause());
        }
        return "redirect:admin";
    }

    protected boolean isUniqueLogin(UserDto userDto) {
        for (User user : userService.findAll()) {
            if (user.getLogin().equals(userDto.getLogin())) {
                return false;
            }
        }
        return true;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(
        HttpServletRequest req) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin");
        String loginToDelete = req.getParameter("userLogin");
        User user = userService.findByLogin(loginToDelete);
        userService.remove(user);
        modelAndView.addObject("users", userService.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("userId") Long id, ModelMap model, HttpServletRequest request) {
        User user = userService.findById(id);
        model.put("roles", roleService.findAll());
        model.put("users", userService.findAll());
        model.addAttribute("adminName", request.getSession().getAttribute("login"));
        UserDto userDto = UserDto.userToDto(user);
        model.put("userDto", userDto);
        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    protected String submitEdit(@Valid @ModelAttribute("userDto") UserDto userDto,
                                BindingResult bindingResult, Model model,
                                @RequestParam("passwordAgain") String passwordAgain
        , @RequestParam("password") String password) {
        String login = userDto.getLogin();
        User user = userService.findByLogin(login);
        userDto.setUserId(user.getUserId());

        if (password.isEmpty() && passwordAgain.isEmpty()) {
            userDto.setPassword(user.getPassword());
            passwordAgain = user.getPassword();
        } else if (!passwordAgain.equals(password)) {
            FieldError passwordNotEquals = new FieldError("password",
                "password", "password not equals");
            bindingResult.addError(passwordNotEquals);
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("logintoedit", login);
            model.addAttribute("error",
                bindingResult.getFieldError().getDefaultMessage());
            return "edit";
        }
        try {
            User user1 = UserDto.dtoToUser(userDto);
            userService.update(user1);
            model.addAttribute("users", userService.findAll());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getCause());
        }
        model.addAttribute("error", "Successfully update");
        return "redirect:admin";
    }

}
