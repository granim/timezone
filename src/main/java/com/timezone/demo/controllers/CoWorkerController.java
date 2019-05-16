package com.timezone.demo.controllers;

import com.timezone.demo.model.Worker;
import com.timezone.demo.model.Coworker;
import com.timezone.demo.services.BaseUserService;
import com.timezone.demo.services.CoWorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/baseUser/{baseUserId}")
public class CoWorkerController {

    private static final String VIEWS_BASECLIENT_CREATE_OR_UPDATE_FORM = "baseClient/createOrUpdateBaseClientForm";
   private final BaseUserService baseUserService;
   private final CoWorkerService coWorkerService;


    public CoWorkerController(BaseUserService baseUserService, CoWorkerService coWorkerService) {
        this.baseUserService = baseUserService;
        this.coWorkerService = coWorkerService;
    }

    @ModelAttribute("baseUser")
    public Worker findBaseUser(@PathVariable("baseUserId") Long baseUserId) {
        return baseUserService.findById(baseUserId);
    }

    @InitBinder("workers")
    public void initBaseUserBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/baseUser/new")
    public String initCreationForm(Worker Worker, Model model) {
        Coworker coworker = new Coworker();
        Worker.getCoworkers().add(coworker);
        coworker.setBaseuser(Worker);
        model.addAttribute("baseClient", coworker);
        return VIEWS_BASECLIENT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("coworker/new")
    public String processCreationForm(Worker Worker, @Valid Coworker coworker, BindingResult result, ModelMap model){
        if (StringUtils.hasLength(coworker.getFirstName()) && coworker.isNew() && Worker.getBaseClient(coworker.getFirstName(), true) != null){
            result.rejectValue("name", "duplicate", "already exists");
        }
        Worker.getCoworkers().add(coworker);
        if(result.hasErrors()) {
            model.put("coworker", coworker);
            return VIEWS_BASECLIENT_CREATE_OR_UPDATE_FORM;
        } else {
            coWorkerService.save(coworker);
            return "redirect:/baseUser/" + Worker.getId();
        }
    }

    @GetMapping("coworker/{coworkerId}/edit")
    public String initUpdateForm(@PathVariable Long coworkerId, Model model){
        model.addAttribute("baseclient" , coWorkerService.findById(coworkerId));
        return VIEWS_BASECLIENT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/baseClient/{coworkerId}/edit")
    public String processUpdateForm(@Valid Coworker coworker, BindingResult result, Worker Worker, Model model) {
        if(result.hasErrors()) {
            coworker.setBaseuser(Worker);
            model.addAttribute("coworker", coworker);
            return VIEWS_BASECLIENT_CREATE_OR_UPDATE_FORM;
        } else {
            Worker.getCoworkers().add(coworker);
            coWorkerService.save(coworker);
            return "redirect:/baseUser/" + coworker.getId();
        }
    }







}
