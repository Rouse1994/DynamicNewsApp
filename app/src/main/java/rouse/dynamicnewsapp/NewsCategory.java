package rouse.dynamicnewsapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class NewsCategory {
    private String category;
    private String content; //will contain the raw text value from the server
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
        content = new String();
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
            try {
                URL url = new URL("http://kc-sce-netrx5.umkc.edu:1214/AndroidServer/rest/news/"+category);

                URLConnection urlConn = url.openConnection();
                InputStream inputStream = urlConn.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                String line = in.readLine();

                String output = new String();

                while (line != null) {
                    output += line;
                    output += "\n";
                    tempTitle = line;
                    line = in.readLine();
                    tempDate = line;
                    line = in.readLine();
                    tempFileName = line;

                    Article n_article = new Article(tempFileName, tempDate, tempTitle);
                    articles.add(n_article);

                    line = in.readLine();
                }

                in.close();
                content = output;
            } catch (Exception e) {
                //do nothing
            }
    }
}