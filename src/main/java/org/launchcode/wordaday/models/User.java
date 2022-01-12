package org.launchcode.wordaday.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=20, message="Username must be 3-20 characters")
    private String username;

    @NotNull
    @Size(min=5, max=20, message="Password must be 5-20 characters")
    private String password;

    private User (String username, String password) {
        this.username = username;
        this.password = password;
    }

    private User () {}

}
