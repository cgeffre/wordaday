package org.launchcode.wordaday.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Note extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name="word_id")
    private Word word;

    private String notes;

    public Note (String notes) {
        this.notes = notes;
    }

    public Note() {}

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
