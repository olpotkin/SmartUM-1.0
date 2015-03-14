
package ru.diver_studio.smartum;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private SmartumPageAdapter pageAdapter;
    private ViewPager          mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        // Fragments and ViewPager initialization
        List<Fragment> fragments = getFragments();
        pageAdapter = new SmartumPageAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(pageAdapter);
    }

    private List<Fragment> getFragments(){
        List<Fragment> fList = new ArrayList<Fragment>();

        // Here we add our fragments
        // Parameter - string (forTextView)
        PageFrag_Home homeFrag = PageFrag_Home.newInstance("Home Fragment");
        PageFrag_Abiturient abiturientFrag = PageFrag_Abiturient.newInstance("Abiturient Fragment");
//        PageFrag_Contacts contactsFrag = PageFrag_Contacts.newInstance("Contacts Fragment");

        fList.add(homeFrag);
        fList.add(abiturientFrag);
//        fList.add(contactsFrag);

        return fList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
