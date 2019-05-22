package com.timezone.demo.controllers;

import com.timezone.demo.model.BaseClient;
import com.timezone.demo.model.Worker;
import com.timezone.demo.services.BaseClientService;
import com.timezone.demo.services.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("/baseUser/{baseUserId}")
@Controller
public class BaseClientController {

    private static final String VIEWS_BASECLIENT_CREATE_OR_UPDATE_FORM = "baseClient/createOrUpdateBaseClientForm";
    private final BaseClientService baseClientService;
    private final BaseUserService baseUserService;

    @Autowired
    public BaseClientController(BaseClientService baseClientService, BaseUserService baseUserService) {
        this.baseClientService = baseClientService;
        this.baseUserService = baseUserService;
    }

    @ModelAttribute("baseUser")
    public Worker findBaseUser(@PathVariable("baseUserId") Long baseUserId) {
        return baseUserService.findById(baseUserId);
    }

    @InitBinder("workers")
    public void initBaseUserBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/baseClient/new")
    public String initCreationForm(Worker Worker, Model model) {
        BaseClient baseClient = new BaseClient();
        Worker.getBaseClients().add(baseClient);
        baseClient.setBaseuser(Worker);
        model.addAttribute("baseClient", baseClient);
        return VIEWS_BASECLIENT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("baseClient/new")
    public String processCreationForm(Worker Worker, @Valid BaseClient baseClient, BindingResult result, ModelMap model){
        if (StringUtils.hasLength(baseClient.getCompanyName()) && baseClient.isNew() && Worker.getBaseClient(baseClient.getCompanyName(), true) != null){
            result.rejectValue("name", "duplicate", "already exists");
        }
        Worker.getBaseClients().add(baseClient);
        if(result.hasErrors()) {
            model.put("baseClient", baseClient);
            return VIEWS_BASECLIENT_CREATE_OR_UPDATE_FORM;
        } else {
            baseClientService.save(baseClient);
            return "redirect:/baseUser/" + Worker.getId();
        }
    }
    @GetMapping("baseClient/{baseClientId}/edit")
    public String initUpdateForm(@PathVariable Long baseClientId, Model model){
        model.addAttribute("baseclient" , baseClientService.findById(baseClientId));
        return VIEWS_BASECLIENT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/baseClient/{baseClientId}/edit")
    public String processUpdateForm(@Valid BaseClient baseClient, BindingResult result, Worker Worker, Model model) {
        if(result.hasErrors()) {
            baseClient.setBaseuser(Worker);
            model.addAttribute("baseClient", baseClient);
            return VIEWS_BASECLIENT_CREATE_OR_UPDATE_FORM;
        } else {
            Worker.getBaseClients().add(baseClient);
            baseClientService.save(baseClient);
            return "redirect:/baseUser/" + baseClient.getId();
        }
    }
}
