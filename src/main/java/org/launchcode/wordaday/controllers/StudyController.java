package org.launchcode.wordaday.controllers;

import org.launchcode.wordaday.models.Deck;
import org.launchcode.wordaday.models.Definition;
import org.launchcode.wordaday.models.User;
import org.launchcode.wordaday.models.Word;
import org.launchcode.wordaday.models.data.DeckRepository;
import org.launchcode.wordaday.models.data.DefinitionRepository;
import org.launchcode.wordaday.models.data.UserRepository;
import org.launchcode.wordaday.models.data.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user")
public class StudyController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DeckRepository deckRepository;

    @Autowired
    WordRepository wordRepository;

    @Autowired
    DefinitionRepository definitionRepository;

    @GetMapping("study")
    public String study(Model model, HttpSession session) {
        String userSessionKey = "user";
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        User user = userRepository.findById(userId).orElse(new User());
        Deck deck = user.getDeck();
        List<Word> words = deck.getWords();
        model.addAttribute("words", words);
        return "user/study";
    }

    @GetMapping("view/{wordId}")
    public String viewWord(Model model, @PathVariable int wordId) {
        Word word = wordRepository.findById(wordId).orElse(new Word());
        model.addAttribute("word", word);
        return "user/view";
    }

    @PostMapping("view/{wordId}")
    public String processDeleteWord(@PathVariable int wordId) {
        Word word = wordRepository.findById(wordId).orElse(new Word());
        for (Definition definition : word.getDefinitions()) {
            definitionRepository.delete(definition);
        }
        wordRepository.delete(word);
        return "redirect:../study";
    }

}
