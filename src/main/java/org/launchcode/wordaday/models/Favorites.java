package org.launchcode.wordaday.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Favorites extends AbstractEntity {

//    @OneToOne
//    private User user;

    @ManyToMany
    private Set<Word> words = new HashSet<>();

    public Favorites() {}
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }
}
