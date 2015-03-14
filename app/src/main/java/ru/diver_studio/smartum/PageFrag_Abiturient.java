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
public class PageFrag_Abiturient extends Fragment {

    private static View mView;

    public static final PageFrag_Abiturient newInstance(String sFragTitle){
        PageFrag_Abiturient frag = new PageFrag_Abiturient();
        Bundle b = new Bundle();
        b.putString("bStr", sFragTitle);
        frag.setArguments(b);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        mView = inflater.inflate(R.layout.fragment_abiturient, container, false);

        // AdNews button click listener:
        Button btnAdNews = (Button) mView.findViewById(R.id.a_btn_1);
        btnAdNews.setOnClickListener(new View.OnClickListener() {
            boolean s;
            @Override
            public void onClick(View v) {
                s = Utils.IsOnline(getActivity());
                if (!s){
                    Toast.makeText(getActivity(), "Network connection failed!", Toast.LENGTH_LONG).show();
                }else {
                    Intent mIntent = new Intent(getActivity(), WebViewURLActivity.class);
                    // Optional parameter
                    mIntent.putExtra("myURL", "http://mami.ru/index.php?id=508");
                    startActivity(mIntent);
                }
            }
        });

        // Areas of study button click listener:
        Button btnAreas = (Button) mView.findViewById(R.id.a_btn_2);
        btnAreas.setOnClickListener(new View.OnClickListener() {
            boolean s;
            @Override
            public void onClick(View v) {
                s = Utils.IsOnline(getActivity());
                if (!s){
                    Toast.makeText(getActivity(), "Network connection failed!", Toast.LENGTH_LONG).show();
                }else {
                    Intent mIntent = new Intent(getActivity(), WebViewURLActivity.class);
                    // Optional parameter
                    mIntent.putExtra("myURL", "http://abiturientum.ru/first_course");
                    startActivity(mIntent);
                }
            }
        });

        // Master courses button click listener:
        Button btnMasters = (Button) mView.findViewById(R.id.a_btn_3);
        btnMasters.setOnClickListener(new View.OnClickListener() {
            boolean s;
            @Override
            public void onClick(View v) {
                s = Utils.IsOnline(getActivity());
                if (!s){
                    Toast.makeText(getActivity(), "Network connection failed!", Toast.LENGTH_LONG).show();
                }else {
                    Intent mIntent = new Intent(getActivity(), WebViewURLActivity.class);
                    // Optional parameter
                    mIntent.putExtra("myURL", "http://abiturientum.ru/magistratura");
                    startActivity(mIntent);
                }
            }
        });

        // Dormitory button click listener:
        Button btnDorm = (Button) mView.findViewById(R.id.a_btn_4);
        btnDorm.setOnClickListener(new View.OnClickListener() {
            boolean s;
            @Override
            public void onClick(View v) {
                s = Utils.IsOnline(getActivity());
                if (!s){
                    Toast.makeText(getActivity(), "Network connection failed!", Toast.LENGTH_LONG).show();
                }else {
                    Intent mIntent = new Intent(getActivity(), WebViewURLActivity.class);
                    // Optional parameter
                    mIntent.putExtra("myURL", "http://www.mami.ru/index.php?id=77");
                    startActivity(mIntent);
                }
            }
        });

        // Open doors button click listener:
        Button btnODoors = (Button) mView.findViewById(R.id.a_btn_5);
        btnODoors.setOnClickListener(new View.OnClickListener() {
            boolean s;
            @Override
            public void onClick(View v) {
                s = Utils.IsOnline(getActivity());
                if (!s){
                    Toast.makeText(getActivity(), "Network connection failed!", Toast.LENGTH_LONG).show();
                }else {
                    Intent mIntent = new Intent(getActivity(), WebViewURLActivity.class);
                    // Optional parameter
                    mIntent.putExtra("myURL", "http://www.mami.ru/index.php?id=93");
                    startActivity(mIntent);
                }
            }
        });

        // Ask button click listener:
        Button btnAsk = (Button) mView.findViewById(R.id.a_btn_6);
        btnAsk.setOnClickListener(new View.OnClickListener() {
            boolean s;
            @Override
            public void onClick(View v) {
                s = Utils.IsOnline(getActivity());
                if (!s){
                    Toast.makeText(getActivity(), "Network connection failed!", Toast.LENGTH_LONG).show();
                }else {
                    Intent mIntent = new Intent(getActivity(), WebViewURLActivity.class);
                    // Optional parameter
                    mIntent.putExtra("myURL", "http://www.mami.ru/index.php?id=1333");
                    startActivity(mIntent);
                }
            }
        });

        // FAQ button click listener:
        Button btnFAQ = (Button) mView.findViewById(R.id.a_btn_7);
        btnFAQ.setOnClickListener(new View.OnClickListener() {
            boolean s;
            @Override
            public void onClick(View v) {
                s = Utils.IsOnline(getActivity());
                if (!s){
                    Toast.makeText(getActivity(), "Network connection failed!", Toast.LENGTH_LONG).show();
                }else {
                    Intent mIntent = new Intent(getActivity(), WebViewURLActivity.class);
                    // Optional parameter
                    mIntent.putExtra("myURL", "http://www.mami.ru/index.php?id=1333");
                    startActivity(mIntent);
                }
            }
        });

        // Distance EDU button click listener:
        Button btnDistance = (Button) mView.findViewById(R.id.a_btn_8);
        btnDistance.setOnClickListener(new View.OnClickListener() {
            boolean s;
            @Override
            public void onClick(View v) {
                s = Utils.IsOnline(getActivity());
                if (!s){
                    Toast.makeText(getActivity(), "Network connection failed!", Toast.LENGTH_LONG).show();
                }else {
                    Intent mIntent = new Intent(getActivity(), WebViewURLActivity.class);
                    // Optional parameter
                    mIntent.putExtra("myURL", "http://cito.ins-iit.ru/");
                    startActivity(mIntent);
                }
            }
        });


        return mView;
    }


}
