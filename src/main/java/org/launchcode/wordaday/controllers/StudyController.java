package org.launchcode.wordaday.controllers;

import org.launchcode.wordaday.models.Deck;
import org.launchcode.wordaday.models.Definition;
import org.launchcode.wordaday.models.User;
import org.launchcode.wordaday.models.Word;
import org.launchcode.wordaday.models.data.DeckRepository;
import org.launchcode.wordaday.models.data.DefinitionRepository;
import org.launchcode.wordaday.models.data.UserRepository;
import org.launchcode.wordaday.models.data.WordRepository;
import org.launchcode.wordaday.models.dto.WordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
    public String studyView(Model model, HttpSession session) {
        String userSessionKey = "user";
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        User user = userRepository.findById(userId).orElse(new User());
        Deck deck = user.getDeck();
        List<Word> words = deck.getWords();
        model.addAttribute("words", words);
        return "user/study";
    }

    @PostMapping("study")
    public String addWord (@ModelAttribute @Valid WordDTO wordDTO, HttpSession session, Model model) {
        String userSessionKey = "user";
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        User user = userRepository.findById(userId).orElse(new User());
        Word newWord = new Word();
        newWord.setName(wordDTO.getName().toLowerCase());
        Deck deck = user.getDeck();
        try {
            newWord.addDefinitions(newWord);
            if (newWord.getDefinitions().size() > 0) {
                newWord.setDeck(deck);
                Definition definition1 = new Definition();
                Definition definition2 = new Definition();
                Definition definition3 = new Definition();
                wordRepository.save(newWord);
                if (newWord.getDefinitions().get(0) != null) {
                    definition1 = newWord.getDefinitions().get(0);
                    definition1.setWord(newWord);
                    definitionRepository.save(definition1);
                }
                if (newWord.getDefinitions().get(1) != null) {
                    definition2 = newWord.getDefinitions().get(1);
                    definition2.setWord(newWord);
                    definitionRepository.save(definition2);
                }
                if (newWord.getDefinitions().get(2) != null) {
                    definition3 = newWord.getDefinitions().get(2);
                    definition3.setWord(newWord);
                    definitionRepository.save(definition3);
                }
                newWord.setDeck(deck);
                wordRepository.save(newWord);
                deck.setWords(newWord);
                deckRepository.save(deck);
                return "redirect:../user/study";
            }
        }
        catch (Exception e) {
            List<Word> words = deck.getWords();
            model.addAttribute("newWordError", "Sorry, we couldn't find that word.");
            model.addAttribute("words", words);
            return "user/study";
        }
        return "redirect:../user/study";
    }

    @GetMapping("view/{wordId}")
    public String viewWord(Model model, @PathVariable int wordId, HttpSession session) {
        String userSessionKey = "user";
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        User user = userRepository.findById(userId).orElse(new User());
        Deck deck = deckRepository.findById(user.getDeck().getId()).orElse(new Deck());
        Word word = wordRepository.findById(wordId).orElse(new Word());
        if (deck.getId() == word.getDeck().getId()) {
            model.addAttribute("word", word);
            return "user/view";
        }
        return "redirect:..";
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
