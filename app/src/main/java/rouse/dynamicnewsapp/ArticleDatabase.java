package rouse.dynamicnewsapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Rouse on 3/29/2015.
 */
public abstract class ArticleDatabase {

    public static String getArticle(String name){
        String content = new String();
        try {
            URL url = new URL("http://kc-sce-netrx5.umkc.edu:1214/AndroidServer/rest/news/article/?articleName="+name);

            URLConnection urlConn = url.openConnection();

            assert (urlConn!=null) : "Unable to reach network - please check VPN status and network connection.";

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
        return content;
    }

    public static ArrayList<Article> getAllArticles(String category){

        String tempTitle = new String();
        String tempDate = new String();
        String tempFileName = new String ();

        ArrayList<Article> articles = new ArrayList<Article>();

        try {
            URL url = new URL("http://kc-sce-netrx5.umkc.edu:1214/AndroidServer/rest/news/"+category);

            URLConnection urlConn = url.openConnection();

            assert (urlConn!=null) : "Unable to reach network - please check VPN status and network connection.";

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
        } catch (Exception e) {
            //do nothing
        }
        return articles;
    }
}
