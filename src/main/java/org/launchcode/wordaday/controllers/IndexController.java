package org.launchcode.wordaday.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        URL jsonWordUrl = new URL("https://random-word-api.herokuapp.com/word?number=1");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode wordArray = mapper.readValue(jsonWordUrl, JsonNode.class);
        JsonNode wordObject = wordArray.get(0);
        String wordText = wordObject.textValue();
        URL jsonDefURL = new URL("https://dictionaryapi.com/api/v3/references/collegiate/json/" + wordObject + "?key=b5117ef5-0b38-4857-911e-f6c32b7a4eb1");
        JsonNode defApi = mapper.readValue(jsonDefURL, JsonNode.class);
        String defText1 = defApi.get(0).get("shortdef").get(0).textValue();
        return "index";
    }

}
