package org.launchcode.wordaday.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import javax.persistence.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Word extends AbstractEntity {

    private String name;

    @ManyToOne
    Deck deck;

    @OneToMany(mappedBy="word")
    private List<Definition> definitions = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="notes_id", referencedColumnName = "id")
    private Notes notes;

    public Word() {}

    public Word generateRandomFromApis(Word word) throws IOException {
        word.setDefinitions(definitions);
        URL jsonWordUrl = new URL("https://random-word-api.herokuapp.com/word?number=1");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode wordApi = mapper.readValue(jsonWordUrl, JsonNode.class);
        String wordText = wordApi.get(0).textValue();
        URL jsonDefURL = new URL("https://dictionaryapi.com/api/v3/references/collegiate/json/" + wordText + "?key=b5117ef5-0b38-4857-911e-f6c32b7a4eb1");
        JsonNode defApi = mapper.readValue(jsonDefURL, JsonNode.class);
        ArrayNode defArrayNode = (ArrayNode) defApi.get(0).get("shortdef");
        this.definitions = new ArrayList<>();
        for (JsonNode defText : defArrayNode) {
            definitions.add(new Definition(defText.textValue()));
        }
        word.setName(wordText);
        return word;
    }

    public Word addDefinitions(Word word) throws IOException {
        this.definitions = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        URL jsonDefURL = new URL("https://dictionaryapi.com/api/v3/references/collegiate/json/" + word.getName() + "?key=b5117ef5-0b38-4857-911e-f6c32b7a4eb1");
        JsonNode defApi = mapper.readValue(jsonDefURL, JsonNode.class);
        ArrayNode defArrayNode = (ArrayNode) defApi.get(0).get("shortdef");
        for (JsonNode defText : defArrayNode) {
            definitions.add(new Definition(defText.textValue()));
        }
        word.setDefinitions(definitions);
        return word;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }
}