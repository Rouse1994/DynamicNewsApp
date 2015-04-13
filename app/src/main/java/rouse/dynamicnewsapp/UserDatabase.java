package rouse.dynamicnewsapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Rouse on 3/29/2015.
 */
public abstract class UserDatabase {

    public static int loginAttempts = 0;

    public static String ValidLogIn(User n_user) throws IOException{
        try {

            loginAttempts++;

            assert (loginAttempts < 5) : "Too many incorrect login attempts..";

            URL url = new URL("http://kc-sce-netrx5.umkc.edu:1214/AndroidServer/rest/news/login?username="
                               + n_user.getUsername() + "&password=" + n_user.getPassword());

            URLConnection urlConn = url.openConnection();

            assert (urlConn!=null) : "Unable to reach network - please check VPN status and network connection.";

            InputStream inputStream = urlConn.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line = in.readLine();

            String output = new String();

            while (line != null) {
                output += line;
                line = in.readLine();
            }

            if (output.equals("True")){
                in.close();
                return "Valid";
            } else{
                in.close();
                throw new IOException(output);
            }

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public static String makeUser(User n_user) throws IOException{
        try {
            URL url = new URL("http://kc-sce-netrx5.umkc.edu:1214/AndroidServer/rest/news/register?username="
                    + n_user.getUsername() + "&password=" + n_user.getPassword());

            URLConnection urlConn = url.openConnection();

            assert (urlConn!=null) : "Unable to reach network - please check VPN status and network connection.";

            InputStream inputStream = urlConn.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line = in.readLine();

            String output = new String();

            while (line != null) {
                output += line;
                line = in.readLine();
            }
        if (output.equals("New user added")){
            return "New user added. Logging in...";
        } else{
            throw new IOException(output);
        }

        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public static String Save(String username, String articleName){
        try {
            URL url = new URL("http://kc-sce-netrx5.umkc.edu:1214/AndroidServer/rest/news/saved/modify?username="
                    + username + "&articleName=" + articleName);

            URLConnection urlConn = url.openConnection();

            assert (urlConn!=null) : "Unable to reach network - please check VPN status and network connection.";

            InputStream inputStream = urlConn.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line = in.readLine();

            String output = new String();

            while (line != null) {
                output += line;
                line = in.readLine();
            }

            return output;

        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public static String Later(String username, String articleName){
        try {
            URL url = new URL("http://kc-sce-netrx5.umkc.edu:1214/AndroidServer/rest/news/later/modify?username="
                    + username + "&articleName=" + articleName);

            URLConnection urlConn = url.openConnection();

            assert (urlConn!=null) : "Unable to reach network - please check VPN status and network connection.";

            InputStream inputStream = urlConn.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line = in.readLine();

            String output = new String();

            while (line != null) {
                output += line;
                line = in.readLine();
            }

            return output;

        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public static Boolean inLater(){
        return false; //placeholder for now
    }

    public static Boolean inSaved(){
        return false; //placeholder for now
    }

    public static ArrayList<String> getSaved(String c_user){
        ArrayList<String> articles = new ArrayList<>();
        try {
            URL url = new URL("http://kc-sce-netrx5.umkc.edu:1214/AndroidServer/rest/news/saved?username="
                               + c_user);

            URLConnection urlConn = url.openConnection();

            assert (urlConn!=null) : "Unable to reach network - please check VPN status and network connection.";

            InputStream inputStream = urlConn.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line = in.readLine();

            while (line != null) {
                articles.add(line);
                line = in.readLine();
            }

        } catch (Exception e) {
            return null;
        }
        return articles;
    }

    public static ArrayList<String> getLater(String c_user){
        ArrayList<String> articles = new ArrayList<>();
        try {
            URL url = new URL("http://kc-sce-netrx5.umkc.edu:1214/AndroidServer/rest/news/later?username="
                    + c_user);

            URLConnection urlConn = url.openConnection();

            assert (urlConn!=null) : "Unable to reach network - please check VPN status and network connection.";

            InputStream inputStream = urlConn.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line = in.readLine();

            while (line != null) {
                articles.add(line);
                line = in.readLine();
            }

        } catch (Exception e) {
            return null;
        }
        return articles;
    }
}
