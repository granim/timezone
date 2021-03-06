package com.controllers;

import com.model.User;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RequestMapping("/users")
@Controller
public class UserController {

    private static final String VIEWS_USER_CREATE_OR_UPDATE_FORM = "/users/createOrUpdateUserForm";
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

    @GetMapping("/{userId}")
    public ModelAndView showBaseUser(@PathVariable("userId") Long userId) {
        ModelAndView mav = new ModelAndView("users/userDetails");
        mav.addObject(userService.findById(userId));
        return mav;
    }


    @GetMapping("/{userId}/edit")
    public String initUpdateUserForm(@PathVariable Long userId, Model model){
        model.addAttribute(userService.findById(userId));
        return VIEWS_USER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{userId}/edit")
    public String processUpdateUserForm(@Valid User user, BindingResult result, @PathVariable Long userId){
        if(result.hasErrors()){
            return VIEWS_USER_CREATE_OR_UPDATE_FORM;
        } else {
            user.setId(userId);
            User savedUser = userService.update(user);
            return "redirect:/users/" + savedUser.getId();
        }
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("user", User.builder().build());
        return VIEWS_USER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid User user, BindingResult result){
        if(result.hasErrors()) {
            return VIEWS_USER_CREATE_OR_UPDATE_FORM;
        } else {
            User savedUser = userService.save(user);
            return "redirect:/users/" + savedUser.getId();
        }
    }


}
