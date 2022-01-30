package org.launchcode.wordaday.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.launchcode.wordaday.models.dto.WordApiDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.net.URL;

@Controller
@RequestMapping
public class IndexController {

    @GetMapping("/")
    public String index(Model model) throws IOException {
        URL jsonWordUrl = new URL("http://api.wordnik.com/v4/words.json/randomWord?api_key=le18ljtuvicne235szv9snumadds2xyvy4xfbs82tymfkbn66");
        ObjectMapper mapper = new ObjectMapper();
        WordApiDTO wordApiDTO = mapper.readValue(jsonWordUrl, WordApiDTO.class);
        model.addAttribute("wordApi", wordApiDTO.getWord());
        URL jsonDefURL = new URL("https://api.wordnik.com/v4/word.json/" + wordApiDTO.getWord() + "/definitions?limit=200&includeRelated=false&useCanonical=false&includeTags=false&api_key=le18ljtuvicne235szv9snumadds2xyvy4xfbs82tymfkbn66");

        return "index";
    }

}
