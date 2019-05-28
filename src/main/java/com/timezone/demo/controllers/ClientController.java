package com.timezone.demo.controllers;

import com.timezone.demo.model.Client;
import com.timezone.demo.model.Worker;
import com.timezone.demo.repositories.ClientRepository;
import com.timezone.demo.repositories.UserRepository;
import com.timezone.demo.services.BaseUserService;
import com.timezone.demo.services.ClientService;
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
public class ClientController {

    private static final String VIEWS_CLIENT_CREATE_OR_UPDATE_FORM = "clients/createOrUpdateClientForm";
    private final BaseUserService baseUserService;
    private final ClientService clientService;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public ClientController(BaseUserService baseUserService, ClientService clientService, UserRepository userRepository, ClientRepository clientRepository) {
        this.baseUserService = baseUserService;
        this.clientService = clientService;
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
    }

    @ModelAttribute("worker")
    public Worker findBaseUser(@PathVariable("workerId")Long workerId){
        return baseUserService.findById(workerId);
    }

    @InitBinder("worker")
    public void initBaseUserBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/clients/find")
    public String findClient(Model model){
        model.addAttribute("client", Client.builder().build());
        return "clients/findClients";
    }

    @GetMapping("/clients")
    public String processFindClientForm(Client client, BindingResult result, Model model) {
        if(client.getCompanyName() == null) {
            client.setCompanyName("");
        }
        List<Client> clientResults = clientService.findAllByCompanyNameLike("%" + client.getCompanyName() + "%");
        if(clientResults.isEmpty()) {
            result.rejectValue("companyName", "notFound", "not found");
            return "clients/findClients";
        } else if (clientResults.size() == 1) {
            client = clientResults.get(0);
            return "redirect:/clients/" + client.getId();
        } else {
            model.addAttribute("selections", clientResults);
            return "clients/clientList";
        }
    }

    @GetMapping("/clients/{clientId}")
    public ModelAndView showBaseUser(@PathVariable("clientId") Long clientId) {
        ModelAndView mav = new ModelAndView("clients/clientDetails");
        mav.addObject(clientService.findById(clientId));
        return mav;
    }


    @GetMapping("/clients/new")
    public String initCreationForm(Worker worker, ModelMap model){
        Client client = new Client();
        worker.addClient(client);
        model.put("client", client);
                return VIEWS_CLIENT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/clients/new")
    public String processCreationForm(Worker worker, @Valid Client client, BindingResult result, Model model){
        if(StringUtils.hasLength(client.getCompanyName()) && client.isNew() && worker.getBaseClient(client.getCompanyName(), true) != null){
            result.rejectValue("companyName", "duplicate", "already exists");
        }
        worker.addClient(client);
        if(result.hasErrors()){
            model.addAttribute("client", client);
            return VIEWS_CLIENT_CREATE_OR_UPDATE_FORM;
        } else {
            clientService.save(client);
            return "redirect:/workers/{workerId}";
        }
    }

    @GetMapping("/clients/{clientId}/edit")
    public String initUpdateForm(@PathVariable Long clientId, Model model){
        model.addAttribute("client", clientService.findById(clientId));
        return VIEWS_CLIENT_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("clients/{clientId}/edit")
    public String processUpdateForm(@Valid Client client, BindingResult result, Worker worker, Model model) {
        if(result.hasErrors()) {
            client.setBaseuser(worker);
            model.addAttribute("client", client);
            return VIEWS_CLIENT_CREATE_OR_UPDATE_FORM;
        }
          worker.addClient(client);
          clientService.save(client);
          return "redirect:/workers/" + worker.getId();
    }




}
