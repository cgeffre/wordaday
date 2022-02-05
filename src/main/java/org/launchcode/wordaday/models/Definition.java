package org.launchcode.wordaday.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Definition extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name="word_id")
    private Word word;

    private String text;

    public Definition(Word word, String text) {
        this.word = word;
        this.text = text;
    }

    public Definition() {}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}
