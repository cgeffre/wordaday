package org.launchcode.wordaday.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Word extends AbstractEntity {

    private String word;
    private String definition;

    public Word() {}

    public Word generateRandomFromApis(Word word) throws IOException {
        URL jsonWordUrl = new URL("https://random-word-api.herokuapp.com/word?number=1");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode wordArray = mapper.readValue(jsonWordUrl, JsonNode.class);
        String wordText = wordArray.get(0).textValue();
        URL jsonDefURL = new URL("https://dictionaryapi.com/api/v3/references/collegiate/json/" + wordText + "?key=b5117ef5-0b38-4857-911e-f6c32b7a4eb1");
        JsonNode defApi = mapper.readValue(jsonDefURL, JsonNode.class);
        String defText = defApi.get(0).get("shortdef").get(0).textValue();
        word.setWord(wordText);
        word.setDefinition(defText);
        if (word.getDefinition() == null) {
            word.generateRandomFromApis(word);
        }
        return word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}