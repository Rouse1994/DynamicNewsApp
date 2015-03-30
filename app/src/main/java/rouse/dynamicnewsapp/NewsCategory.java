package rouse.dynamicnewsapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class NewsCategory {
    private String category;
    private ArrayList<Article> articles;

    private String tempTitle;
    private String tempDate;
    private String tempFileName;

    public NewsCategory(){
        //default
    }

    public NewsCategory(String n_category){
        category = n_category;
        articles = new ArrayList<Article>();
        getArticles();
    }

    public String getArticleFileName(int index){
        return articles.get(index).getFileName();
    }
    public String getArticleTitle(int index){
        return articles.get(index).getTitle();
    }

    public ArrayList<String> getTitles(){
        ArrayList<String> articleTitles = new ArrayList<String>();
        for (int i = 0; i < articles.size(); i++){
            articleTitles.add(articles.get(i).getTitle());
        }

        return articleTitles;
    }

    public void getArticles(){
        articles = ArticleDatabase.getAllArticles(category);
    }
}