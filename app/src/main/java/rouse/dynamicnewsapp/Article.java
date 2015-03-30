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
        content = new String();
    }

    public Article(String n_fileName){
        fileName = n_fileName;
    }

    public String getContent(){
        return ArticleDatabase.getArticle(fileName);
    }

    public String getTitle() {return title;}
    public String getDate() {return date;}
    public String getFileName() {return fileName;}
}