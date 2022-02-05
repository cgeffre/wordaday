package org.launchcode.wordaday.controllers;

import org.launchcode.wordaday.models.Deck;
import org.launchcode.wordaday.models.User;
import org.launchcode.wordaday.models.Word;
import org.launchcode.wordaday.models.data.DeckRepository;
import org.launchcode.wordaday.models.data.UserRepository;
import org.launchcode.wordaday.models.data.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping
public class IndexController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WordRepository wordRepository;

    @Autowired
    DeckRepository deckRepository;

    @GetMapping("/")
    public String index(Model model) {
        try {
            Word word = new Word();
            word.generateRandomFromApis(word);
            if (word.getName() == null || word.getDefinitions().size() == 0) {
                word.generateRandomFromApis(word);
            }
            model.addAttribute("wordText", word.getName());
            model.addAttribute("definitionsText", word.getDefinitions());
            return "index";
        }
        catch (Exception e) {
            model.addAttribute("wordText", "Oops! We encountered an error getting your word. Please try again.");
            model.addAttribute("definitionsText", "");
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
            if (word.getName() == null || word.getDefinitions().size() == 0) {
                word.generateRandomFromApis(word);
            }
            model.addAttribute("wordText", word.getName());
            model.addAttribute("definitionsText", word.getDefinitions());
            return "/user/index";
        }
        catch (Exception e) {
            model.addAttribute("wordText", "Oops! We encountered an error getting your word. Please try again.");
            model.addAttribute("definitionsText", "");
            return "/user/index";
        }
    }

    @PostMapping("/user")
    public String saveRandomWord(@ModelAttribute @Valid Word newWord, Errors errors, HttpSession session) {
        String userSessionKey = "user";
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        User user = userRepository.findById(userId).orElse(new User());
        if (errors.hasErrors()) {
            return "redirect:/user";
        }
        wordRepository.save(newWord);
        Deck deck = user.getDeck();
        deck.setWords(newWord);
        deckRepository.save(deck);

        return "redirect:/user";
    }

}
