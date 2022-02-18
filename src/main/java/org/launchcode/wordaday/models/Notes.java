package org.launchcode.wordaday.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Notes extends AbstractEntity {

    @OneToOne(mappedBy="notes")
    private Word word;

    @Column(length=500)
    private String notes;

    public Notes(){}

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
