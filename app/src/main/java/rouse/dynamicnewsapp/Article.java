package rouse.dynamicnewsapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Article {
    private String title;
    private String fileName;
    private String date;
    private String content;

    public Article(String n_fileName, String n_date, String n_title){
        fileName = n_fileName;
        date = n_date;
        title = n_title;
    }

    public String getContent(){
        if ((content.equals("")) || (content.equals(null))) {
            try {
                URL url = new URL("http://kc-sce-netrx5.umkc.edu:1214/AndroidServer/rest/news/article/?articleName="+fileName);

                URLConnection urlConn = url.openConnection();
                InputStream inputStream = urlConn.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                String line = in.readLine();

                String output = new String();

                while (line != null) {
                    output += line;
                    output += "\n";
                    line = in.readLine();
                }

                in.close();
                content = output;
                return content;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return content;
    }

    public String getTitle() {return title;}
    public String getDate() {return date;}
}