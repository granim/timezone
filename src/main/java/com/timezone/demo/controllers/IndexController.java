package com.timezone.demo.controllers;

import com.timezone.demo.repositories.UserRepository;
import com.timezone.demo.repositories.WorkerRepository;
import com.timezone.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    private final WorkerRepository workerRepository;
    private final UserRepository userRepository;
    private final UserService userService;


    @Autowired
    public IndexController(WorkerRepository workerRepository, UserRepository userRepository, UserService userService) {
        this.workerRepository = workerRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @RequestMapping({"", "/", "index", "index.html", "layout.html", "/layout.html"})
    public String index(Model model){
      User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      User user1 = user;
       com.timezone.demo.model.User loggedUser = userService.findByEmail(user1.getUsername());
       Long id = loggedUser.getId();
        model.addAttribute("username", id);
        model.addAttribute("workers", workerRepository.findAll());
        model.addAttribute("users", userRepository.findAll());

        return "index";
    }

    @RequestMapping({ "clientDetails", "/clientDetails.html", "layout.html", "/layout.html"})
    public String clientDetails(Model model){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user1 = user;
        com.timezone.demo.model.User loggedUser = userService.findByEmail(user1.getUsername());
        Long id = loggedUser.getId();
        model.addAttribute("username", id);
        return "clientDetails";
    }


    @RequestMapping("/oops")
    public String oopsHandler(){
        return "Sorry not implemented";
    }

    @GetMapping
    public String login(Model model){
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }

    @RequestMapping({ "/layout.html", "index.html"})
    String index(HttpSession session, Long id) {
        session.setAttribute("mySessionAttribute", userService.findById(id));
        return "index";
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }


}
