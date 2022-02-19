package org.launchcode.wordaday.controllers;

import org.launchcode.wordaday.models.Deck;
import org.launchcode.wordaday.models.User;
import org.launchcode.wordaday.models.data.DeckRepository;
import org.launchcode.wordaday.models.data.UserRepository;
import org.launchcode.wordaday.models.dto.LoginFormDTO;
import org.launchcode.wordaday.models.dto.RegisterFormDTO;
import org.launchcode.wordaday.models.dto.UpdatePasswordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DeckRepository deckRepository;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute(new RegisterFormDTO());
        model.addAttribute("title", "Register");
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "register";
        }

        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "register";
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "register";
        }

        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword());
        Deck newDeck = new Deck();
        deckRepository.save(newDeck);
        newUser.setDeck(newDeck);
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);

        return "redirect:/user";
    }

    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Log In");
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "login";
        }

        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

        if (theUser == null) {
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "login";
        }

        String password = loginFormDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "login";
        }

        setUserInSession(request.getSession(), theUser);

        return "redirect:/user";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "redirect:/login";
    }

    @GetMapping("/user/account")
    public String userAccount(Model model, HttpSession session) {
        model.addAttribute(new UpdatePasswordDTO());
        String userSessionKey = "user";
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        User user = userRepository.findById(userId).orElse(new User());
        return "/user/account";
    }

    @PostMapping("/user/account")
    public String updateAccount(@ModelAttribute @Valid UpdatePasswordDTO updatePasswordDTO, Errors errors, Model model, HttpSession session) {
        String userSessionKey = "user";
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        User user = userRepository.findById(userId).orElse(new User());

        if (errors.hasErrors()) {
            return "/user/account";
        }

        String password = updatePasswordDTO.getPassword();

        if (!user.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            return "/user/account";
        }

        if (user.isMatchingPassword(password)) {
            String newPassword = updatePasswordDTO.getNewPassword();
            String verifyPassword = updatePasswordDTO.getVerifyPassword();
            if (!newPassword.equals(verifyPassword)) {
                errors.rejectValue("newPassword", "passwords.mismatch", "Passwords do not match");
                return "/user/account";
            }
            user.updatePassword(newPassword);
            userRepository.save(user);
        }
        model.addAttribute("passwordUpdated", "Your password has been changed.");
        return "/user/account";
    }
}
