package org.launchcode.wordaday.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Deck extends AbstractEntity {

    @OneToMany
    private Set<Word> words = new HashSet<>();

    @OneToOne(mappedBy="deck")
    private User user;

    public Deck() {}

    public ArrayList<Word> deckRandomizer(Deck aDeck) {
        ArrayList<Word> words = new ArrayList<>();
        words.addAll(aDeck.getWords());
        Collections.shuffle(words);
        return words;
    }

    public Set<Word> getWords() {
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
