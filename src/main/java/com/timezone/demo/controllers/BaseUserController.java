package com.timezone.demo.controllers;

import com.timezone.demo.Model.BaseUser;
import com.timezone.demo.Services.BaseUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/users")
@Controller
public class BaseUserController {

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "/users/createOrUpdateUserForm";
    private final BaseUserService baseUserService;


    public BaseUserController(BaseUserService baseUserService) {
        this.baseUserService = baseUserService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/find")
    public String findUser(Model model){
        model.addAttribute("user", BaseUser.builder().build());
        return "users/findUsers";
    }

    @GetMapping
    public String processFind(BaseUser baseUser, BindingResult result, Model model) {
        if(baseUser.getLastName() == null) {
            baseUser.setLastName("");
        }
        List<BaseUser> results = baseUserService.findAllByLastName(baseUser.getLastName());
        if(results.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return "users/findusers";
        } else if (results.size() == 1) {
            baseUser = results.get(0);
            return "redirect:/owners/" + baseUser.getId();
        } else {
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/{baseUserId}")
    public ModelAndView showBaseUser(@PathVariable("baseUserId") Long baseUserId) {
        ModelAndView mav = new ModelAndView("baseUsers/baseUserDetails");
        mav.addObject(baseUserService.findById(baseUserId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("baseUser", BaseUser.builder().build());
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping("/new")
    public String processCreationForm(@Valid BaseUser baseUser, BindingResult result){
        if(result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            BaseUser savedUser = baseUserService.save(baseUser);
            return "redirect/baseUser/" + savedUser.getId();
        }
    }

    @GetMapping("/{baseUserId}/edit")
    public String initUpdateBaseUserForm(@PathVariable Long baseUserId, Model model){
        model.addAttribute(baseUserService.findById(baseUserId));
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{baseUserId}/edit")
    public String processUpdateBaseUserForm(@Valid BaseUser baseUser, BindingResult result, @PathVariable Long baseUserId){
        if(result.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            baseUser.setId(baseUserId);
            BaseUser savedUser = baseUserService.save(baseUser);
            return "redirect/baseUsers/" + savedUser.getId();
        }
    }


}
