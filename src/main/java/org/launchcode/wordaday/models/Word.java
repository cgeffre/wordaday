package org.launchcode.wordaday.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Word extends AbstractEntity {

    private String word;
    private ArrayList<String> definitions;

    public Word() {}

    public Word generateRandomFromApis(Word word) throws IOException {
        URL jsonWordUrl = new URL("https://random-word-api.herokuapp.com/word?number=1");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode wordApi = mapper.readValue(jsonWordUrl, JsonNode.class);
        String wordText = wordApi.get(0).textValue();
        URL jsonDefURL = new URL("https://dictionaryapi.com/api/v3/references/collegiate/json/" + wordText + "?key=b5117ef5-0b38-4857-911e-f6c32b7a4eb1");
        JsonNode defApi = mapper.readValue(jsonDefURL, JsonNode.class);
        ArrayNode defArrayNode = (ArrayNode) defApi.get(0).get("shortdef");
        this.definitions = new ArrayList<>();
        if (defArrayNode.isArray()) {
            for (JsonNode defText : defArrayNode) {
                definitions.add(defText.textValue());
            }
        }
        if (!defArrayNode.isArray()) {
            JsonNode singleDef = defApi.get(0).get("shortdef");
            definitions.add(singleDef.textValue());
        }
        word.setWord(wordText);
        word.setDefinitions(definitions);
        if (word.getDefinitions().size() < 1) {
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

    public ArrayList<String> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(ArrayList<String> definitions) {
        this.definitions = definitions;
    }
}