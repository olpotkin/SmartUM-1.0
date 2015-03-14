package ru.diver_studio.smartum;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Oleg Potkin on 03.03.2015.
 */
public class SmartumPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public SmartumPageAdapter(FragmentManager fm, List<Fragment> fragments){
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position){
        return this.fragments.get(position);
    }

    @Override
    public int getCount(){
        return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "MAIN PAGE";
            case 1:
                return "FOR ENTRANT";
            case 2:
                return "CONTACTS";
        }
        return null;
    }



}
