package com.interview.sii.model;

public class LoginForm {
    private String login;
    private String email;

    public LoginForm(String login, String email) {
        this.login = login;
        this.email = email;
    }

    public LoginForm() {
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }


}
