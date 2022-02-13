package org.launchcode.wordaday.models;

import javax.persistence.*;
import java.util.*;

@Entity
public class Deck extends AbstractEntity {

    @OneToMany(mappedBy="deck")
    private List<Word> words = new ArrayList<>();

    @OneToOne(mappedBy="deck")
    private User user;

    public Deck() {}

    public ArrayList<Word> randomizeDeck(Deck aDeck) {
        ArrayList<Word> words = new ArrayList<>();
        words.addAll(aDeck.getWords());
        Collections.shuffle(words);
        return words;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(Word words) {
        this.words.add(words);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
