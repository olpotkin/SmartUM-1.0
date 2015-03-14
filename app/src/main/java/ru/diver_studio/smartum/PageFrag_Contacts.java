package ru.diver_studio.smartum;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Oleg Potkin on 07.03.2015.
 */
public class PageFrag_Contacts extends Fragment{

    private static View mView;

    public static final PageFrag_Contacts newInstance(String sFragTitle){
        PageFrag_Contacts frag = new PageFrag_Contacts();
        Bundle b = new Bundle();
        b.putString("bStr", sFragTitle);
        frag.setArguments(b);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        mView = inflater.inflate(R.layout.fragment_contacts, container,false);

        return mView;
    }



}
