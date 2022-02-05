package org.launchcode.wordaday.models;

import javax.persistence.Entity;

@Entity
public class Definition extends AbstractEntity {

    String definition;

    public Definition() {}

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
