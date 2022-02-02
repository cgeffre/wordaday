package org.launchcode.wordaday.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.launchcode.wordaday.models.User;
import org.launchcode.wordaday.models.data.UserRepository;
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

    @GetMapping("/")
    public String index(Model model) {
        try {
            URL jsonWordUrl = new URL("https://random-word-api.herokuapp.com/word?number=1");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode wordArray = mapper.readValue(jsonWordUrl, JsonNode.class);
            String wordText = wordArray.get(0).textValue();
            URL jsonDefURL = new URL("https://dictionaryapi.com/api/v3/references/collegiate/json/" + wordText + "?key=b5117ef5-0b38-4857-911e-f6c32b7a4eb1");
            JsonNode defApi = mapper.readValue(jsonDefURL, JsonNode.class);
            String defText = defApi.get(0).get("shortdef").get(0).textValue();
            if (wordText != null && defText != null) {
                model.addAttribute("wordApi", wordText);
                model.addAttribute("defText", defText);
            }
            if (wordText == null || defText == null) {
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
            URL jsonWordUrl = new URL("https://random-word-api.herokuapp.com/word?number=1");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode wordArray = mapper.readValue(jsonWordUrl, JsonNode.class);
            String wordText = wordArray.get(0).textValue();
            URL jsonDefURL = new URL("https://dictionaryapi.com/api/v3/references/collegiate/json/" + wordText + "?key=b5117ef5-0b38-4857-911e-f6c32b7a4eb1");
            JsonNode defApi = mapper.readValue(jsonDefURL, JsonNode.class);
            String defText = defApi.get(0).get("shortdef").get(0).textValue();
            if (wordText != null && defText != null) {
                model.addAttribute("wordApi", wordText);
                model.addAttribute("defText", defText);
            }
            if (wordText == null || defText == null) {
                model.addAttribute("wordApi", "Oops! We encountered an error. Please wait a moment and then try again.");
                model.addAttribute("defText", "");
            }
            return "/user/index";
        } catch (Exception e) {
            model.addAttribute("wordApi", "Oops! We encountered an error. Please wait a few moments and then try again.");
            model.addAttribute("defText", "");
            return "/user/index";
        }
    }
}
