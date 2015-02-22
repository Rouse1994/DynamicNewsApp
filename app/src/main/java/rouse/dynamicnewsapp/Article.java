package rouse.dynamicnewsapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Article {

    public static final String URL = "http://b.web.umkc.edu/burrise/json/businesses.aspx?parm=notinvalid";

    public ArrayList<String> getListOfArticlesJSON()  {
        ArrayList<String> listOfBusinesses = new ArrayList<String>();

        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL);
        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                    Log.w("Appointment Service", line);
                }
                JSONObject jObject = new JSONObject(builder.toString());
                JSONArray merchantsArray = jObject.getJSONArray("businesses");


                for (int i=0; i < merchantsArray.length(); i++)
                {
                    JSONObject merchant = merchantsArray.getJSONObject(i);
                    // Pulling items from the array
                    listOfBusinesses.add(merchant.getString("name"));
                }

            } else {
                Log.w("Appointment Service", "Fail to fetch JSON object for merchants");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return listOfBusinesses;

    }
}