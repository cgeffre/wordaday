package org.launchcode.wordaday.models;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private User (String username, String password) {
        this.username = username;
        this.password = password;
    }

    private User () {}

}
