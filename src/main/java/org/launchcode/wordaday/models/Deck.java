package org.launchcode.wordaday.models;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.*;

@Entity
public class Deck extends AbstractEntity {

    @OneToMany(mappedBy="deck")
    @Cascade(CascadeType.ALL)
    private List<Word> words = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
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
