package com.timezone.demo.controllers;

import com.timezone.demo.Model.BaseClient;
import com.timezone.demo.Model.BaseUser;
import com.timezone.demo.Services.BaseClientService;
import com.timezone.demo.Services.BaseUserService;
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
public class BaseClientController {

    private static final String VIEWS_BASECLIENT_CREATE_OR_UPDATE_FORM = "baseClient/createOrUpdateBaseClientForm";
    private final BaseClientService baseClientService;
    private final BaseUserService baseUserService;


    public BaseClientController(BaseClientService baseClientService, BaseUserService baseUserService) {
        this.baseClientService = baseClientService;
        this.baseUserService = baseUserService;
    }

    @ModelAttribute("baseUser")
    public BaseUser findBaseUser(@PathVariable("baseUserId") Long baseUserId) {
        return baseUserService.findById(baseUserId);
    }

    @InitBinder("baseUser")
    public void initBaseUserBinder(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/baseUser/new")
    public String initCreationForm(BaseUser baseUser, Model model) {
        BaseClient baseClient = new BaseClient();
        baseUser.getBaseClients().add(baseClient);
        baseClient.setBaseuser(baseUser);
        model.addAttribute("baseClient", baseClient);
        return VIEWS_BASECLIENT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("baseClient/new")
    public String processCreationForm(BaseUser baseUser, @Valid BaseClient baseClient, BindingResult result, ModelMap model){
        if (StringUtils.hasLength(baseClient.getCompanyName()) && baseClient.isNew() && baseUser.getBaseClient(baseClient.getCompanyName(), true) != null){
            result.rejectValue("name", "duplicate", "already exists");
        }
        baseUser.getBaseClients().add(baseClient);
        if(result.hasErrors()) {
            model.put("baseClient", baseClient);
            return VIEWS_BASECLIENT_CREATE_OR_UPDATE_FORM;
        } else {
            baseClientService.save(baseClient);
            return "redirect:/baseUser/" + baseUser.getId();
        }
    }
    @GetMapping("baseClient/{baseClientId}/edit")
    public String initUpdateForm(@PathVariable Long baseClientId, Model model){
        model.addAttribute("baseclient" , baseClientService.findById(baseClientId));
        return VIEWS_BASECLIENT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/baseClient/{baseClientId}/edit")
    public String processUpdateForm(@Valid BaseClient baseClient, BindingResult result, BaseUser baseUser, Model model) {
        if(result.hasErrors()) {
            baseClient.setBaseuser(baseUser);
            model.addAttribute("baseClient", baseClient);
            return VIEWS_BASECLIENT_CREATE_OR_UPDATE_FORM;
        } else {
            baseUser.getBaseClients().add(baseClient);
            baseClientService.save(baseClient);
            return "redirect:/baseUser/" + baseClient.getId();
        }
    }
}
