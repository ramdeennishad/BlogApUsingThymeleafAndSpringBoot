package com.blog.controller;
import com.blog.dto.RegistrationDto;
import com.blog.entities.User;
import com.blog.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {
    private UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    // handler method to handle user registration request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // this object holds form data
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "/blog/register";
    }
    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegistrationDto
                                   user,
                           BindingResult result,
                           Model model){
        User existingUser = userService.findByEmail(user.getEmail());
        if(existingUser != null && existingUser.getEmail() !=null &&
                !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null, "There is already a user with same email id");
        }
        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/register?success";
    }
    @GetMapping("/login")
    public String loginPage(){
        return "/blog/login";
    }
}
