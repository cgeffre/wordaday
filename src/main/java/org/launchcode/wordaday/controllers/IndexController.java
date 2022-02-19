package org.launchcode.wordaday.controllers;

import org.launchcode.wordaday.models.*;
import org.launchcode.wordaday.models.data.*;
import org.launchcode.wordaday.models.dto.WordDTO;
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
import java.util.ArrayList;

@Controller
@RequestMapping
public class IndexController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WordRepository wordRepository;

    @Autowired
    DeckRepository deckRepository;

    @Autowired
    DefinitionRepository definitionRepository;

    @Autowired
    NotesRepository notesRepository;

    @GetMapping("/")
    public String index(Model model) {
        Word word = new Word();
        int count = 0;
        int maxTries = 2;
        while (word.getDefinitions().size() < 1) {
            try {
                word.generateRandomFromApis(word);
                model.addAttribute("wordText", word.getName());
                model.addAttribute("definitions", word.getDefinitions());
            } catch (Exception e) {
                count++;
                if (count == maxTries) {
                    model.addAttribute("wordText", "Oops! We encountered an error getting your word. Please try again.");
                    model.addAttribute("definitions", "");
                }
            }
        }
        return "index";
    }

    @GetMapping("/user")
    public String loggedInIndex(Model model, HttpSession session) {
        String userSessionKey = "user";
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        User user = userRepository.findById(userId).orElse(new User());
        model.addAttribute("username", user.getUsername());
        Word word = new Word();
        int count = 0;
        int maxTries = 2;
        while (word.getDefinitions().size() < 1) {
            try {
                word.generateRandomFromApis(word);
                model.addAttribute("wordText", word.getName());
                model.addAttribute("definitions", word.getDefinitions());
            } catch (Exception e) {
                count++;
                if (count == maxTries) {
                    model.addAttribute("wordText", "Oops! We encountered an error getting your word. Please try again.");
                    model.addAttribute("definitions", "");
                }
            }
        }
        return "user/index";
    }

    @PostMapping("/user")
    public String saveRandomWord(@ModelAttribute @Valid WordDTO wordDTO, HttpSession session) {
        String userSessionKey = "user";
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        User user = userRepository.findById(userId).orElse(new User());
        Deck deck = deckRepository.findById(user.getDeck().getId()).orElse(new Deck());
        Notes notes = new Notes();
        try {
            Word newWord = new Word();
            newWord.setName(wordDTO.getName());
            Definition definition1 = new Definition();
            Definition definition2 = new Definition();
            Definition definition3 = new Definition();
            wordRepository.save(newWord);
            ArrayList<Definition> definitions = new ArrayList<>();
            if (wordDTO.getDefinition1() != null) {
                definition1.setText(wordDTO.getDefinition1());
                definition1.setWord(newWord);
                definitionRepository.save(definition1);
                definitions.add(definition1);
            }
            if (wordDTO.getDefinition2() != null) {
                definition2.setText(wordDTO.getDefinition2());
                definition2.setWord(newWord);
                definitionRepository.save(definition2);
                definitions.add(definition2);
            }
            if (wordDTO.getDefinition3() != null) {
                definition3.setText(wordDTO.getDefinition3());
                definition3.setWord(newWord);
                definitionRepository.save(definition3);
                definitions.add(definition3);
            }
            notes.setWord(newWord);
            notesRepository.save(notes);
            newWord.setDefinitions(definitions);
            newWord.setDeck(deck);
            newWord.setNotes(notes);
            wordRepository.save(newWord);
            deck.setWords(newWord);
            deckRepository.save(deck);

            return "redirect:/user";
        }
        catch (Exception e) {
            return "redirect:/user";
        }
    }

}
