package com.timezone.demo.controllers;

import com.timezone.demo.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private final WorkerRepository workerRepository;

    @Autowired
    public IndexController(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String index(Model model){
        model.addAttribute("workers", workerRepository.findAll());
        return "index";
    }

    @RequestMapping("/oops")
    public String oopsHandler(){
        return "Sorry not implemented";
    }

}
