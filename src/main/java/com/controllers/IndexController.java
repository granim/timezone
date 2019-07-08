package com.controllers;


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

    private final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

   @GetMapping(path = { "/", "index", "index.html"})
    public String Index(Model model){
       User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       com.model.User loggedUser = userService.findByEmail(user.getUsername());
       Long id = loggedUser.getId();
        model.addAttribute("userId", id);
        return "index";
    }

    @RequestMapping("/oops")
    public String oopsHandler(){
        return "oops";
    }



    @GetMapping ("login")
    public String login() {
        return "login";
    }


}
