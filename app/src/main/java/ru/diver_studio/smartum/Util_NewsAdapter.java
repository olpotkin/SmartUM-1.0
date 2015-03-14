package ru.diver_studio.smartum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * Created by Oleg Potkin on 08.03.2015.
 */
public class Util_NewsAdapter extends BaseAdapter {
    Context cont;
    LayoutInflater lInflater;
    ArrayList<MyPageNewsListData> objects;

    ImageLoader imageLoader = ImageLoader.getInstance();

    Util_NewsAdapter(Context context, ArrayList<MyPageNewsListData> mylist){
        cont = context;
        objects = mylist;
        lInflater = (LayoutInflater) cont.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .showImageOnFail(R.drawable.preview_loading)    // resource or drawable
                .showStubImage(R.drawable.preview_loading)
                .cacheInMemory(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(cont)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) 				// 2 Mb memory cache
                .defaultDisplayImageOptions(defaultOptions)
                .build();

        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null){
            // получаем LayoutInflater для работы с layout-ресурсами
            view = lInflater.inflate(R.layout.activity_news, parent, false);
        }

        MyPageNewsListData p = ((MyPageNewsListData) getItem(position));

        // заполняем View в пункте списка данными
        ((TextView) view.findViewById(R.id.textView1)).setText(p.title);
        ((TextView) view.findViewById(R.id.textView2)).setText(p.pubDate);

        ImageLoader.getInstance().init(config);
        imageLoader.displayImage(p.image, (ImageView) view.findViewById(R.id.imageView1)); // Запустили асинхронный показ картинки

        return view;
    }
}


class MyPageNewsListData{
    String title;           // News header
    String pubDate;         // Publication date
    String image;           // Link to image
    String discribe;        // News URL on web-site

    MyPageNewsListData(String _title, String _pubDate, String _image, String _discribe){
        title = _title;
        pubDate = _pubDate;
        image = _image;
        discribe =_discribe;
    }
}
