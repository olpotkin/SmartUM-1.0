package ru.diver_studio.smartum;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Oleg Potkin on 08.03.2015.
 * input - string with URL
 * output - source html-code of page
 */
public class Util_RetrieveSiteData extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {
        StringBuilder builder = new StringBuilder(100000);

        for (String url: urls){
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            try{
                HttpResponse execute = client.execute(httpGet);
                InputStream content = execute.getEntity().getContent();
                BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                String s = "";
                while ((s = buffer.readLine()) != null){
                    builder.append(s);
                }
            }
            catch (Exception e){
                e.printStackTrace();
                Log.e("ERR","Error", e);
            }
        }
        return builder.toString();
    }

    @Override
    protected void onPostExecute(String result){
    }
}
