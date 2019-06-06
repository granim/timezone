package com.timezone.demo.controllers;

import com.timezone.demo.model.User;
import com.timezone.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
@Controller
public class UserController {

    private static final String VIEWS_USER_CREATE_OR_UPDATE_FORM = "/users/createOrUpdateWorkerForm";
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/find")
    public String findUser(Model model){
        model.addAttribute("user", User.builder().build());
        return "users/findUsers";
    }



}
