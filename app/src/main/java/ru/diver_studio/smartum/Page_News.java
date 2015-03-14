package ru.diver_studio.smartum;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Oleg Potkin on 08.03.2015.
 */
public class Page_News extends ListActivity {

    final int NEWS_VOLUME = 16;				        // News
    private TextView myText;
    private ArrayList<MyPageNewsListData> catalog;

    String[] titles = new String[NEWS_VOLUME];		// Headers
    String[] pub_dates = new String[NEWS_VOLUME];	// Dates
    String[] img_urls = new String[NEWS_VOLUME];	// Links for images
    String[] describes = new String[NEWS_VOLUME];	// Links for full news

    private String pageSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getting HTML source code
        try{
            pageSource = new Util_RetrieveSiteData().execute("http://www.mami.ru/rss/").get();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        catch (ExecutionException e){
            e.printStackTrace();
        }

        // HTML parsing
        String workString = pageSource;
        int i;
        int str_index_start, str_index_end;

        str_index_start = workString.indexOf("<item>");
        workString = workString.substring(str_index_start + 6);

        // we need NEWS_VOLUME number of news:
        for (i = 0; i < NEWS_VOLUME; ++i){
            str_index_start = workString.indexOf("<title>");            // Header
            str_index_end = workString.indexOf("</title>");
            titles[i] = workString.substring(str_index_start + 7, str_index_end);

            str_index_start = workString.indexOf("<pubDate>");          // Publication date
            str_index_end = workString.indexOf("</pubDate>");
            pub_dates[i] = workString.substring(str_index_start + 9, str_index_end - 15);

            str_index_start = workString.indexOf("<enclosure url='");   // link to image
            str_index_end = workString.indexOf("' />");
            img_urls[i] = workString.substring(str_index_start + 16, str_index_end);

            str_index_start = workString.indexOf("<link>");		        // link to full news
            str_index_end = workString.indexOf("</link>");
            describes[i] = workString.substring(str_index_start + 6, str_index_end);

            str_index_start = workString.indexOf("</item>");
            workString = workString.substring(str_index_start + 7);	    // Cut string to before next news
        }

        setContentView(R.layout.activity_news_list);

        catalog = new ArrayList<MyPageNewsListData>();
        for (i = 1; i <= NEWS_VOLUME ; i++){
            catalog.add(new MyPageNewsListData(titles[i-1], pub_dates[i-1], img_urls[i-1],describes[i-1]));
        }
        // create adapter
        Util_NewsAdapter catAdapter;
        catAdapter = new Util_NewsAdapter(this, catalog);
        setListAdapter(catAdapter);
    }

    public void onListItemClick(ListView parent, View v, int position, long id){
            Intent myIntent = new Intent(this, WebViewURLActivity.class);
            myIntent.putExtra("myURL", describes[position]); 						// Optional parameters
            startActivity(myIntent);
    }


}
