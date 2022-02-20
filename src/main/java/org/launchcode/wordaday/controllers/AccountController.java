package org.launchcode.wordaday.controllers;

import org.launchcode.wordaday.models.User;
import org.launchcode.wordaday.models.data.*;
import org.launchcode.wordaday.models.dto.UpdatePasswordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping
public class AccountController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/user/account")
    public String userAccount(Model model, HttpSession session) {
        model.addAttribute(new UpdatePasswordDTO());
        String userSessionKey = "user";
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        User user = userRepository.findById(userId).orElse(new User());
        return "/user/account";
    }

    @PostMapping("/user/account")
    public String updateAccount(@ModelAttribute @Valid UpdatePasswordDTO updatePasswordDTO, Errors errors, Model model, HttpSession session, HttpServletRequest request) {
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

        if (user.isMatchingPassword(password) && updatePasswordDTO.isDeleteAccount()) {
            userRepository.delete(user);
            request.getSession().invalidate();
            return "redirect:..";
        }

        if (user.isMatchingPassword(password) && !updatePasswordDTO.isDeleteAccount()) {
            String newPassword = updatePasswordDTO.getNewPassword();
            String verifyPassword = updatePasswordDTO.getVerifyPassword();
            if (newPassword.length() < 5 || newPassword.length() > 20) {
                errors.rejectValue("newPassword", "passwords.invalid", "Invalid password. Must be between 5 and 20 characters.");
                return "/user/account";
            }
            if (!newPassword.equals(verifyPassword)) {
                errors.rejectValue("newPassword", "passwords.mismatch", "Passwords do not match");
                return "/user/account";
            }
            user.updatePassword(newPassword);
            userRepository.save(user);
            model.addAttribute("passwordUpdated", "Your password has been changed.");
            return "user/account";
        }
        return "/user/account";
    }

}
