package com.timezone.demo.controllers;

import com.timezone.demo.model.Coworker;
import com.timezone.demo.model.Worker;
import com.timezone.demo.repositories.UserRepository;
import com.timezone.demo.services.BaseUserService;
import com.timezone.demo.services.CoWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/workers/{workerId}")
public class CoWorkerController {

    private static final String VIEWS_COWORKER_CREATE_OR_UPDATE_FORM = "coworkers/createOrUpdateCoworkerForm";
   private final BaseUserService baseUserService;
   private final CoWorkerService coWorkerService;
   private final UserRepository userRepository;

    @Autowired
    public CoWorkerController(BaseUserService baseUserService, CoWorkerService coWorkerService, UserRepository userRepository) {
        this.baseUserService = baseUserService;
        this.coWorkerService = coWorkerService;
        this.userRepository = userRepository;
    }

    @ModelAttribute("worker")
    public Worker findBaseUser(@PathVariable("workerId") Long workerId) {
        return baseUserService.findById(workerId);
    }

    @InitBinder("worker")
    public void initBaseUserBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }


    @GetMapping("/coworkers/new")
    public String initCreationForm(Worker worker, Model model) {
        Coworker coworker = new Coworker();
        worker.addCoworker(coworker);
        worker.getCoworkers().add(coworker);
        model.addAttribute("coworker", coworker);
        return VIEWS_COWORKER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/coworkers/new")
    public String processCreationForm(Worker worker, @Valid Coworker coworker, BindingResult result, Model model) {
        if (StringUtils.hasLength(coworker.getFirstName()) && coworker.isNew() && worker.getCoworker(coworker.getFirstName(), true) != null){
            result.rejectValue("firstName", "duplicate", "already exists");
        }
        worker.addCoworker(coworker);
        if(result.hasErrors()) {
            model.addAttribute("coworker", coworker);
            return VIEWS_COWORKER_CREATE_OR_UPDATE_FORM;
        } else {
            coWorkerService.save(coworker);
            return "redirect:/workers/{workerId}";
        }
    }


    @GetMapping("coworkers/{coworkerId}/edit")
    public String initUpdateForm(@PathVariable Long coworkerId, Model model){
        model.addAttribute("coworker" , coWorkerService.findById(coworkerId));
        return VIEWS_COWORKER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/coworkers/{coworkerId}/edit")
    public String processUpdateForm(@Valid Coworker coworker, BindingResult result, Worker worker, Model model) {
        if(result.hasErrors()) {
            coworker.setBaseuser(worker);
            model.addAttribute("coworker", coworker);
            return VIEWS_COWORKER_CREATE_OR_UPDATE_FORM;
        } else {
            worker.addCoworker(coworker);
            coWorkerService.save(coworker);
            return "redirect:/workers/" + worker.getId();
        }
    }


}
