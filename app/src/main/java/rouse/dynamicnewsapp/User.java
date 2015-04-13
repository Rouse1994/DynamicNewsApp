package rouse.dynamicnewsapp;

import java.io.IOException;

/**
 * Created by Rouse on 3/29/2015.
 */
public class User {

    private String username;
    private String password;

    public User(String n_user, String n_pass, Boolean new_user) throws IOException{
        //Constructor for new user
        if (n_user.length() < 4) {
                throw new IOException("Username must be at least 5 characters.");
            } else {
                username = n_user;
            }

            if (n_pass.length() < 4) {
                throw new IOException("Password must be at least 5 characters");
            } else {
                password = n_pass;
            }
    }

    public User(String n_user, String n_pass){
        //Constructor for existing user
        username = n_user;
        password = n_pass;
    }

    String getUsername() {return username;}
    String getPassword() {return password;}
}
