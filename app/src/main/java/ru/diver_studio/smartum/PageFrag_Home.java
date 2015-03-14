package ru.diver_studio.smartum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Oleg Potkin on 07.03.2015.
 */
public class PageFrag_Home extends Fragment {

    private static View mView;

    public static final PageFrag_Home newInstance(String sFragTitle){
        PageFrag_Home frag = new PageFrag_Home();
        Bundle b = new Bundle();
        b.putString("bStr", sFragTitle);
        frag.setArguments(b);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        mView = inflater.inflate(R.layout.fragment_home, container, false);

        // About button click listener:
        Button btnAbout = (Button) mView.findViewById(R.id.button_news);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            boolean s;
            @Override
            public void onClick(View v) {
                s = Utils.IsOnline(getActivity());
                if (!s){
                    Toast.makeText(getActivity(), "Network connection failed!", Toast.LENGTH_LONG).show();
                }else {
                    Intent mIntent = new Intent(getActivity(), WebViewURLActivity.class);
                    // Optional parameter
                    mIntent.putExtra("myURL", "http://mami.ru/index.php?id=13");
                    startActivity(mIntent);
                }
            }
        });

        // News list button click listener:
        Button btnNewsList = (Button) mView.findViewById(R.id.button_news_list);
        btnNewsList.setOnClickListener(new View.OnClickListener() {
            boolean s;
            @Override
            public void onClick(View v) {
                s = Utils.IsOnline(getActivity());
                if (!s){
                    Toast.makeText(getActivity(), "Network connection failed!", Toast.LENGTH_LONG).show();
                }else {
                    Intent mIntent = new Intent(getActivity(), Page_News.class);
                    startActivity(mIntent);
                }
            }
        });

        // Hometask button click listener:
        Button btnHometask = (Button) mView.findViewById(R.id.button_hometask);
        btnHometask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getActivity(), HometaskActivity.class);
                startActivity(mIntent);
            }
        });

        return mView;
    }

}
