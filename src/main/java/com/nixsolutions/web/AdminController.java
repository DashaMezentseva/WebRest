package com.nixsolutions.web;

import com.nixsolutions.domain.User;
import com.nixsolutions.dto.UserDto;
import com.nixsolutions.service.RoleDao;
import com.nixsolutions.service.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @Autowired
    private UserDao userService;
    @Autowired
    private RoleDao roleService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView homePage(ModelAndView modelAndView, HttpServletRequest req) {
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
    public String submitAdd( @ModelAttribute("userDto") @Valid UserDto userDto
            , BindingResult result, ModelMap modelMap) {
        isUniqueLogin(userDto, result);
        isUniqueEmail(userDto, result);
        isValidPasswords(userDto, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("roles", roleService.findAll());
            modelMap.addAttribute("users", userService.findAll());
            return "add";
        }
        User user = UserDto.dtoToUser(userDto);
        userService.create(user);
        return "redirect:admin";
    }

    private void isUniqueLogin(UserDto userDto, BindingResult result) {
        User user = userService.findByLogin(userDto.getLogin());
        if (user != null) {
            result.rejectValue("login", "loginExists");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("userId"));
        User user = (User) userService.findById(id);
        userService.remove(user);
        return "redirect:admin";
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
    public String submitEdit(@ModelAttribute("userDto") @Valid UserDto userDto
            , BindingResult result, ModelMap modelMap) {
        isUniqueEmail(userDto, result);
        isValidPasswords(userDto, result);
        if (result.hasErrors()) {
            modelMap.put("roles", roleService.findAll());
            modelMap.put("users", userService.findAll());
            return "edit";
        }
        User user = UserDto.dtoToUser(userDto);
        userService.update(user);
        return "redirect:admin";
    }

    private void isValidPasswords(UserDto userDto, BindingResult result) {
        if (!userDto.getPassword().equals(userDto.getPasswordAgain())) {
            result.rejectValue("password", "passwordsNotEquals");
        }
    }

    private void isUniqueEmail(UserDto userDto, BindingResult result) {
        User user = userService.findByEmail(userDto.getEmail());
        if (user != null && !userDto.getEmail().equals(user.getEmail())) {
            result.rejectValue("email", "emailExists");
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String loadFormPage(Model model) {
        model.addAttribute("userDto", new UserDto());
            //new RegistrationUserDto());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView submitForm( @ModelAttribute("userDto") @Valid UserDto userDto
                                      //RegistrationUserDto userDto
            , BindingResult result, HttpServletRequest request) {
        isUniqueLogin(userDto, result);
        isUniqueEmail(userDto, result);
        isValidPasswords(userDto, result);
//        isValidCaptcha(request, userDto, result);
        if (result.hasErrors()) {
            return new ModelAndView("registration", "userDto", userDto);
        }
        userDto.setRole(roleService.findById(2L));
        User user = UserDto.dtoToUser(userDto);
        userService.create(user);
        return new ModelAndView("user", "userDto", userDto);
    }

//    private void isValidCaptcha(HttpServletRequest request, RegistrationUserDto userDto, BindingResult result) {
//        String captchaId = (String) request.getSession().getAttribute(
//                Constants.KAPTCHA_SESSION_KEY);
//        String response = userDto.getCaptcha();
//        if (!response.equalsIgnoreCase(captchaId)) {
//            result.rejectValue("captcha", "InvalidCaptcha", "Invalid Entry");
//        }
//    }


}

