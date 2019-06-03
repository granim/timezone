package com.timezone.demo.controllers;

import com.timezone.demo.model.Worker;
import com.timezone.demo.repositories.WorkerRepository;
import com.timezone.demo.services.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/workers")
@Controller
public class WorkerController {

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "/workers/createOrUpdateWorkerForm";
    private final BaseUserService baseUserService;
    private final WorkerRepository workerRepository;

    @Autowired
    public WorkerController(BaseUserService baseUserService, WorkerRepository workerRepository) {
        this.baseUserService = baseUserService;
        this.workerRepository = workerRepository;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }


    @RequestMapping("/find")
    public String findUser(Model model){
        model.addAttribute("worker", Worker.builder().build());
        return "workers/findUsers";
    }

    @GetMapping
    public String processFindForm(Worker worker, BindingResult result, Model model) {
        if(worker.getLastName() == null) {
            worker.setLastName("");
        }
        List<Worker> results = baseUserService.findAllByLastNameLike("%" + worker.getLastName() + "%");
        if(results.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return "workers/findUsers";
        } else if (results.size() == 1) {
            worker = results.get(0);
            return "redirect:/workers/" + worker.getId();
        } else {
            model.addAttribute("selections", results);
            return "workers/workersList";
        }
    }

    @GetMapping("/{workerId}")
    public ModelAndView showBaseUser(@PathVariable("workerId") Long workerId) {
        ModelAndView mav = new ModelAndView("workers/baseUserDetails");
        mav.addObject(baseUserService.findById(workerId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("worker", Worker.builder().build());
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Worker worker, BindingResult result){
        if(result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            Worker savedUser = baseUserService.save(worker);
            return "redirect:/workers/" + savedUser.getId();
        }
    }

    @GetMapping("/{workerId}/edit")
    public String initUpdateBaseUserForm(@PathVariable Long workerId, Model model){
        model.addAttribute(baseUserService.findById(workerId));
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{workerId}/edit")
    public String processUpdateBaseUserForm(@Valid Worker worker, BindingResult result, @PathVariable Long workerId){
        if(result.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            worker.setId(workerId);
            Worker savedUser = baseUserService.save(worker);
            return "redirect:/workers/" + savedUser.getId();
        }
    }


}
