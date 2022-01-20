package org.launchcode.wordaday.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Word extends AbstractEntity {

    @ManyToMany(mappedBy="words")
    private Set<Deck> deck = new HashSet<>();

    @OneToMany
    private Set<Note> notes = new HashSet<>();

    @ManyToMany(mappedBy="words")
    private Set<Favorites> favorites = new HashSet<>();

    public Word() {}

    public Set<Deck> getDeck() {
        return deck;
    }

    public void setDeck(Set<Deck> deck) {
        this.deck = deck;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
    }

    public Set<Favorites> getFavorites() {
        return favorites;
    }

    public void setFavorites(HashSet<Favorites> favorites) {
        this.favorites = favorites;
    }
}