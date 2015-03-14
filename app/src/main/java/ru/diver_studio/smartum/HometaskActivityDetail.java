package ru.diver_studio.smartum;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class HometaskActivityDetail extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hometask_activity_detail);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {
        int from_Where_I_Am_Coming = 0;
        public DBHelper mydb ;
        TextView subject ;
        TextView date;
        TextView description;
        TextView teacher;
        int id_To_Update = 0;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_hometask_activity_detail, container, false);

            setHasOptionsMenu(true);    // add menu to fragment

            subject = (TextView) rootView.findViewById(R.id.editTextSubject);
            date = (TextView) rootView.findViewById(R.id.editTextDate);
            description = (TextView) rootView.findViewById(R.id.editTextDescription);
            teacher = (TextView) rootView.findViewById(R.id.editTextTeacher);

            mydb = new DBHelper(getActivity());

            Bundle extras = getActivity().getIntent().getExtras();

            if(extras != null){
                int Value = extras.getInt("id");
                if(Value > 0){
                    // means this is the view part not the add contact part.
                    Cursor rs = mydb.getData(Value);
                    id_To_Update = Value;
                    rs.moveToFirst();

                    String sbjct = rs.getString(rs.getColumnIndex(DBHelper.HOMETASK_COLUMN_SUBJECT));
                    String dt = rs.getString(rs.getColumnIndex(DBHelper.HOMETASK_COLUMN_DATE));
                    String decr = rs.getString(rs.getColumnIndex(DBHelper.HOMETASK_COLUMN_DESCRIPTION));
                    String teach = rs.getString(rs.getColumnIndex(DBHelper.HOMETASK_COLUMN_TEACHER));

                    if (!rs.isClosed()){
                        rs.close();
                    }

                    Button b = (Button)rootView.findViewById(R.id.button_save_task);
                    b.setVisibility(View.INVISIBLE);
                    subject.setText((CharSequence)sbjct);
                    subject.setFocusable(false);
                    subject.setClickable(false);

                    date.setText((CharSequence)dt);
                    date.setFocusable(false);
                    date.setClickable(false);

                    description.setText((CharSequence)decr);
                    description.setFocusable(false);
                    description.setClickable(false);

                    teacher.setText((CharSequence)teach);
                    teacher.setFocusable(false);
                    teacher.setClickable(false);
                }
            }


            // SAVE button click listener:
            Button btnSave = (Button) rootView.findViewById(R.id.button_save_task);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    run();
                }
            });
            return rootView;
        }

        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
            // Do something that differs the Activity's menu here
            super.onCreateOptionsMenu(menu, inflater);

            Bundle extras = getActivity().getIntent().getExtras();
            if(extras != null){
                int Value = extras.getInt("id");
                if(Value > 0){
                    getActivity().getMenuInflater().inflate(R.menu.menu_hometask_activity_detail, menu);
                }
                else{
                    getActivity().getMenuInflater().inflate(R.menu.menu_hometask, menu);
                }
            }
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()){
                case R.id.Edit_Task:
                    Button b = (Button)getActivity().findViewById(R.id.button_save_task);
                    b.setVisibility(View.VISIBLE);
                    subject.setEnabled(true);
                    subject.setFocusableInTouchMode(true);
                    subject.setClickable(true);

                    date.setEnabled(true);
                    date.setFocusableInTouchMode(true);
                    date.setClickable(true);

                    description.setEnabled(true);
                    description.setFocusableInTouchMode(true);
                    description.setClickable(true);

                    teacher.setEnabled(true);
                    teacher.setFocusableInTouchMode(true);
                    teacher.setClickable(true);

                    return true;

                case R.id.Delete_Task:

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage(R.string.deleteTask)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    mydb.deleteContact(id_To_Update);
                                    Toast.makeText(getActivity(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity(),HometaskActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                }
                            });
                    AlertDialog d = builder.create();
                    d.setTitle("Are you sure?");
                    d.show();

                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }
        }

        public void run(){
            Bundle extras = getActivity().getIntent().getExtras();
            if(extras != null){
                int Value = extras.getInt("id");
                if(Value > 0){
                    if(mydb.updateContact(  id_To_Update,
                            subject.getText().toString(),
                            date.getText().toString(),
                            description.getText().toString(),
                            teacher.getText().toString())){
                        Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(),HometaskActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getActivity(), "not Updated", Toast.LENGTH_SHORT).show();
                    }
                }else{

                    if(mydb.insertContact( subject.getText().toString(),
                            date.getText().toString(),
                            description.getText().toString(),
                            teacher.getText().toString())){
                        Toast.makeText(getActivity(), "done", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "not done", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(getActivity(),HometaskActivity.class);
                    startActivity(intent);
                }
            }
        }


    }






}
