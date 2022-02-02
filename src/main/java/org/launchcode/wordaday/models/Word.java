package org.launchcode.wordaday.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Word extends AbstractEntity {

    private String word;
    private String definition;

    @ManyToMany(mappedBy="words")
    private Set<Deck> deck = new HashSet<>();

    public Word() {}

    public Set<Deck> getDeck() {
        return deck;
    }

    public void setDeck(Set<Deck> deck) {
        this.deck = deck;
    }

}