package com.controllers;

import com.repositories.UserRepository;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final UserRepository userRepository;
    private final UserService userService;


    @Autowired
    public IndexController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @RequestMapping({ "/", "index", "index.html"})
    public String layout(Model model){
     User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       com.model.User loggedUser = userService.findByEmail(user.getUsername());
       Long id = loggedUser.getId();
        model.addAttribute("userId", id);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @RequestMapping("/oops")
    public String oopsHandler(){
        return "oops";
    }

    @GetMapping
    public String login(Model model){
        return "login";
    }


}
