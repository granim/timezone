package com.timezone.demo.controllers;

import com.timezone.demo.model.Coworker;
import com.timezone.demo.model.Worker;
import com.timezone.demo.repositories.CoWorkerRepository;
import com.timezone.demo.repositories.WorkerRepository;
import com.timezone.demo.services.CoWorkerService;
import com.timezone.demo.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/workers/{workerId}")
public class CoWorkerController {

    private static final String VIEWS_COWORKER_CREATE_OR_UPDATE_FORM = "coworkers/createOrUpdateCoworkerForm";
   private final WorkerService workerService;
   private final CoWorkerService coWorkerService;
   private final WorkerRepository workerRepository;
   private final CoWorkerRepository coWorkerRepository;

    @Autowired
    public CoWorkerController(WorkerService workerService, CoWorkerService coWorkerService, WorkerRepository workerRepository, CoWorkerRepository coWorkerRepository) {
        this.workerService = workerService;
        this.coWorkerService = coWorkerService;
        this.workerRepository = workerRepository;
        this.coWorkerRepository = coWorkerRepository;
    }

    @ModelAttribute("worker")
    public Worker findBaseUser(@PathVariable("workerId") Long workerId) {
        return workerService.findById(workerId);
    }

    @InitBinder("worker")
    public void initBaseUserBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/coworkers/find")
    public String findCoworker(Model model){
        model.addAttribute("coworker", Coworker.builder().build());
        return "coworkers/findCoworkers";
    }

    @GetMapping("/coworkers")
    public String processFindCoworkerForm(Coworker coworker, BindingResult result, Worker worker, Model model) {
        if(coworker.getlName() == null) {
            coworker.setlName("");
        }
        List<Coworker> coworkeresults = coWorkerService.findAllByLastNameLike("%" + coworker.getlName() + "%");
        if(coworkeresults.isEmpty()) {
            result.rejectValue("lName", "notFound", "not found");
            return "coworkers/findCoworkers";
        } else if (coworkeresults.size() == 1) {
            coworker = coworkeresults.get(0);
            return "redirect:/workers/" + worker.getId() + "/coworkers/" + coworker.getId();
        } else {
            model.addAttribute("selections", coworkeresults);
            return "coworkers/coworkerList";
        }
    }

    @GetMapping("/coworkers/{coworkerId}")
    public ModelAndView showBaseUser(@PathVariable("coworkerId") Long coworkerId) {
        ModelAndView mav = new ModelAndView("coworkers/coworkerDetails");
        mav.addObject(coWorkerService.findById(coworkerId));
        return mav;
    }

    @GetMapping("/coworkers/new")
    public String initCreationForm(Worker worker, ModelMap model) {
        Coworker coworker = new Coworker();
        worker.addCoworker(coworker);
        model.put("coworker", coworker);
        return VIEWS_COWORKER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/coworkers/new")
    public String processCreationForm(Worker worker, @Valid Coworker coworker, BindingResult result, Model model) {
        if (StringUtils.hasLength(coworker.getfName()) && coworker.isNew() && worker.getCoworker(coworker.getfName(), true) != null){
            result.rejectValue("fName", "duplicate", "already exists");
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
            coworker.setWorker(worker);
            model.addAttribute("coworker", coworker);
            return VIEWS_COWORKER_CREATE_OR_UPDATE_FORM;
        } else {
            worker.addCoworker(coworker);
            coWorkerService.save(coworker);
            return "redirect:/workers/" + worker.getId();
        }
    }







}
