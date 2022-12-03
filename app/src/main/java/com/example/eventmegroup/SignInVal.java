package com.example.eventmegroup;

import java.util.regex.Pattern;

public class SignInVal {
    private String email;
    private String pass;

    public SignInVal(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    protected int check() {
        String regexPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        boolean emailReg = Pattern.compile(regexPattern).matcher(email).matches();

        if(email.isEmpty()) {
            return 2;
        }
        else if(pass.isEmpty()) {
            return 3;
        }
        else if(pass.length() < 7) {
            return 4;
        }
        else if(!emailReg) {
            return 5;
        }
        else {
            return 1;
        }
    }
}
