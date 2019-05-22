package com.timezone.demo.controllers;

import com.timezone.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final UserRepository userRepository;

    @Autowired
    public IndexController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String index(Model model){
        model.addAttribute("workers", userRepository.findAll());
        return "index";
    }

    @RequestMapping("/oops")
    public String oopsHandler(){
        return "Sorry not implemented";
    }

}
