package org.launchcode.wordaday.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Definition extends AbstractEntity {

    @Column(length=500)
    private String text;

    @ManyToOne
    Word word;

    public Definition(String text) {
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
