package com.timezone.demo.controllers;

import com.timezone.demo.repositories.UserRepository;
import com.timezone.demo.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final WorkerRepository workerRepository;
    private final UserRepository userRepository;

    @Autowired
    public IndexController(WorkerRepository workerRepository, UserRepository userRepository) {
        this.workerRepository = workerRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String index(Model model){
        model.addAttribute("workers", workerRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @RequestMapping("/oops")
    public String oopsHandler(){
        return "Sorry not implemented";
    }


    /*@GetMapping
    public String login(){
        return "login";
    }*/

    @GetMapping
    public String login(Model model){
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }


}
