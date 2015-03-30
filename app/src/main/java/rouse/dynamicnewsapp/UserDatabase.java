package rouse.dynamicnewsapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Rouse on 3/29/2015.
 */
public abstract class UserDatabase {


    public static Boolean ValidLogIn(String username, String password){
        try {
            URL url = new URL("http://kc-sce-netrx5.umkc.edu:1214/AndroidServer/rest/news/login?username="
                               + username + "&password=" + password);

            URLConnection urlConn = url.openConnection();
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
                return true;
            } else{
                in.close();
                return false;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static Boolean makeUser(String username, String password){
        try {
            URL url = new URL("http://kc-sce-netrx5.umkc.edu:1214/AndroidServer/rest/news/register?username="
                    + username + "&password=" + password);

            URLConnection urlConn = url.openConnection();
            InputStream inputStream = urlConn.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line = in.readLine();

            String output = new String();

            while (line != null) {
                output += line;
                line = in.readLine();
            }
        if (output.equals("New user added")){
            return true;
        }

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    return false;
    }
}
