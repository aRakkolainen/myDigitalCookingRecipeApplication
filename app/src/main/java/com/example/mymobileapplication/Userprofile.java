/* Created by: Aino Räkköläinen Last edited: 25.7.2022 (Only this comment edited)
* Purpose: class for making Userprofile objects that have attributes:
* username, email and password. */
package com.example.mymobileapplication;

public class Userprofile {
    private String username;
    private String email;
    private String password;
    public Userprofile(String u, String e, String p) {
        username=u;
        email=e;
        password=p;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
