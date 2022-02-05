package org.launchcode.wordaday.models;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class User extends AbstractEntity {

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    @OneToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(name="deck_id", referencedColumnName = "id")
    private Deck deck;


    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User (String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public User () {}

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }
}
