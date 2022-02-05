package org.launchcode.wordaday.models;

import javax.persistence.Entity;

@Entity
public class Definition extends AbstractEntity {

    private String text;

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
}
