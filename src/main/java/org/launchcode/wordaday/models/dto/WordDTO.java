package org.launchcode.wordaday.models.dto;

public class WordDTO {

    private String name;
    private String definition1;
    private String definition2;
    private String definition3;

    public WordDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefinition1() {
        return definition1;
    }

    public void setDefinition1(String definition1) {
        this.definition1 = definition1;
    }

    public String getDefinition2() {
        return definition2;
    }

    public void setDefinition2(String definition2) {
        this.definition2 = definition2;
    }

    public String getDefinition3() {
        return definition3;
    }

    public void setDefinition3(String definition3) {
        this.definition3 = definition3;
    }
}
