package org.launchcode.wordaday.models.dto;

import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdatePasswordDTO {

    @NotNull
    @NotBlank
    @Size(min = 5, max = 20, message = "Invalid password. Must be between 5 and 20 characters.")
    private String password;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 20, message = "Invalid password. Must be between 5 and 20 characters.")
    private String newPassword;

    private String verifyPassword;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
