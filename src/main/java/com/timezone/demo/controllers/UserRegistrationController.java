package com.timezone.demo.controllers;

import com.timezone.demo.dto.UserRegistrationDto;
import com.timezone.demo.model.User;
import com.timezone.demo.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {


   private UserService userService;

   public UserRegistrationDto userRegistrationDto() {
       return new UserRegistrationDto();
   }

   @GetMapping
   public String showRegistrationForm(Model model){
       return "registration";
   }

   @PostMapping
   public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult result) {

       User existing = userService.findByEmail(userDto.getEmail());
       if(existing != null){
           result.rejectValue("email", null, "An anncount already exist with that email");
       }

       if(result.hasErrors()){
           return "registration";
       }

       userService.save((userDto));
       return "redirect:/registration?success";
   }
   
}
