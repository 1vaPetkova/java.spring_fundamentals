package com.example.java_spring_fund_lab_01.web;

import com.example.java_spring_fund_lab_01.models.service.UserLoginServiceModel;
import com.example.java_spring_fund_lab_01.security.CurrentUser;
import com.example.java_spring_fund_lab_01.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userModel")
    public UserLoginServiceModel userModel() {
        return new UserLoginServiceModel();
    }

    @GetMapping("/users/login")
    public String showLogin() {
        return "auth-login";
    }

    @PostMapping("/users/login")
    public String login(@Valid @ModelAttribute UserLoginServiceModel userModel,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.userModel",
                    bindingResult
            );
            return "redirect:/users/login";
        }
        if (userService.isAuthenticated(userModel.getUsername(), userModel.getPassword())) {
            this.userService.loginUser(userModel.getUsername());
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("notFound", true);
            return "redirect:/users/login";
        }
    }

    @PostMapping("/users/logout")
    public String logout() {
        this.userService.logoutCurrentUser();
        return "redirect:/";
    }
}
