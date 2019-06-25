package com.timezone.demo.controllers;

import com.timezone.demo.model.Coworker;
import com.timezone.demo.model.User;
import com.timezone.demo.services.CoWorkerService;
import com.timezone.demo.services.UserService;
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
@RequestMapping("/users/{userId}")
public class CoWorkerController {

    private static final String VIEWS_COWORKER_CREATE_OR_UPDATE_FORM = "coworkers/createOrUpdateCoworkerForm";
   private final UserService userService;
   private final CoWorkerService coWorkerService;

    @Autowired
    public CoWorkerController(UserService userService, CoWorkerService coWorkerService) {
        this.userService = userService;
        this.coWorkerService = coWorkerService;

    }

    @ModelAttribute("user")
    public User findBaseUser(@PathVariable("userId") Long userId) {
        return userService.findById(userId);
    }

    @InitBinder("user")
    public void initBaseUserBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }


    @RequestMapping("/coworkers/find")
    public String findCoworker(Model model){
        model.addAttribute("coworker", Coworker.builder().build());
        return "coworkers/findCoworkers";
    }

    @GetMapping("/coworkers")
    public String processFindCoworkerForm(Coworker coworker, BindingResult result, User user, Model model) {
        if(coworker.getlName() == null) {
            coworker.setlName("");
        }
        List<Coworker> coworkeresults = coWorkerService.findAllByLastNameLike("%" + coworker.getlName() + "%");
        if(coworkeresults.isEmpty()) {
            result.rejectValue("lName", "notFound", "not found");
            return "coworkers/findCoworkers";
        } else if (coworkeresults.size() == 1) {
            coworker = coworkeresults.get(0);
            return "redirect:/users/" + user.getId() + "/coworkers/" + coworker.getId();
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
    public String initCreationForm(User user, ModelMap model) {
        Coworker coworker = new Coworker();
        user.addCoworker(coworker);
        model.put("coworker", coworker);
        return VIEWS_COWORKER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/coworkers/new")
    public String processCreationForm(User user, @Valid Coworker coworker, BindingResult result, Model model) {
        if (StringUtils.hasLength(coworker.getfName()) && coworker.isNew() && user.getCoworker(coworker.getfName(), true) != null){
            result.rejectValue("fName", "duplicate", "already exists");
        }
        user.addCoworker(coworker);
        if(result.hasErrors()) {
            model.addAttribute("coworker", coworker);
            return VIEWS_COWORKER_CREATE_OR_UPDATE_FORM;
        } else {
            coWorkerService.save(coworker);
            return "redirect:/users/{userId}";
        }
    }

    @GetMapping("/coworkers/{coworkerId}/edit")
    public String initUpdateForm(@PathVariable Long coworkerId, Model model){
        model.addAttribute("coworker" , coWorkerService.findById(coworkerId));
        return VIEWS_COWORKER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/coworkers/{coworkerId}/edit")
    public String processUpdateForm(@Valid Coworker coworker, BindingResult result, User user, Model model) {
        if(result.hasErrors()) {
            coworker.setUser(user);
            model.addAttribute("coworker", coworker);
            return VIEWS_COWORKER_CREATE_OR_UPDATE_FORM;
        } else {
            user.addCoworker(coworker);
            coWorkerService.save(coworker);
            return "redirect:/users/" + user.getId();
        }
    }

}
