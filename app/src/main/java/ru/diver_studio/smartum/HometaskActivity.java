package ru.diver_studio.smartum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class HometaskActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hometask);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new HometaskListFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hometask, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

       // noinspection SimplifiableIfStatement
       // if (id == R.id.action_settings) {
       //     return true;
       // }
       // return super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.item1:
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);
                Intent intent = new Intent(getApplicationContext(),HometaskActivityDetail.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    public static class HometaskListFragment extends Fragment {

        private ListView obj;
        DBHelper mydb;

        public HometaskListFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_hometask, container, false);

            mydb = new DBHelper(getActivity());
            ArrayList array_list = mydb.getAllCotacts();

            ArrayAdapter arrayAdapter =
                    new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, array_list);

            //adding it to the list view.
            obj = (ListView) rootView.findViewById(R.id.listView1);
            obj.setAdapter(arrayAdapter);

            obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    int id_To_Search = arg2 + 1;
                    Bundle dataBundle = new Bundle();
                    dataBundle.putInt("id", id_To_Search);
                    Intent intent = new Intent(getActivity(), HometaskActivityDetail.class);
                    intent.putExtras(dataBundle);
                    startActivity(intent);
                }
            });





            return rootView;
        }
    }
}
