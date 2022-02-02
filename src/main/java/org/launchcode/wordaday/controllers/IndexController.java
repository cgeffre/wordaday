package org.launchcode.wordaday.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.launchcode.wordaday.models.User;
import org.launchcode.wordaday.models.Word;
import org.launchcode.wordaday.models.data.UserRepository;
import org.launchcode.wordaday.models.data.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.net.URL;

@Controller
@RequestMapping
public class IndexController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WordRepository wordRepository;

    @GetMapping("/")
    public String index(Model model) {
        try {
            Word word = new Word();
            word.generateRandomFromApis(word);
            if (word.getWord() != null && word.getDefinition() != null) {
                model.addAttribute("wordApi", word.getWord());
                model.addAttribute("defText", word.getDefinition());
            }
            if (word.getWord() == null || word.getDefinition() == null) {
                model.addAttribute("wordApi", "Oops! We encountered an error. Please wait a moment and then try again.");
                model.addAttribute("defText", "");
            }
            return "index";
        }
        catch (Exception e) {
            model.addAttribute("wordApi", "Oops! We encountered an error. Please wait a few moments and then try again.");
            model.addAttribute("defText", "");
            return "index";
        }
    }

    @GetMapping("/user")
    public String loggedInIndex(Model model, HttpSession session) {
        String userSessionKey = "user";
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        User user = userRepository.findById(userId).orElse(new User());
        model.addAttribute("username", user.getUsername());
        try {
            Word word = new Word();
            word.generateRandomFromApis(word);
            if (word.getWord() != null && word.getDefinition() != null) {
                model.addAttribute("wordApi", word.getWord());
                model.addAttribute("defText", word.getDefinition());
            }
            if (word.getWord() == null || word.getDefinition() == null) {
                model.addAttribute("wordApi", "Oops! We encountered an error. Please wait a moment and then try again.");
                model.addAttribute("defText", "");
            }
            return "/user/index";
        }
        catch (Exception e) {
            model.addAttribute("wordApi", "Oops! We encountered an error. Please wait a few moments and then try again.");
            model.addAttribute("defText", "");
            return "/user/index";
        }
    }
}
