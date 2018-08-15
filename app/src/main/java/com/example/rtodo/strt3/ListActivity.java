package com.example.rtodo.strt3;

import android.app.AlertDialog;
import android.app.LauncherActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.rtodo.strt3.Model.ListItems;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    private List<ListItems> listItems;
    private AlertDialog.Builder mDialogBuilder;
    private AlertDialog mAlertDialog;
    private TimePicker mTimePicker;
    private Button mPopupButton;
    private TextView mHourText;
    private DatabaseHandler db;
    public List<AlarmItem> alarmItemList;
    private Context ctxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        db = new DatabaseHandler(this);

        recyclerView = findViewById(R.id.RecyclerViewID);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        listItems = new ArrayList<>();
        alarmItemList = new ArrayList<>();
        alarmItemList = db.getAll();

        for (AlarmItem c : alarmItemList) {
            ListItems alarmItem = new ListItems();
            alarmItem.setAlarmday(c.getAlarmDay());
            alarmItem.setAlarmhour(c.getAlarmHM());

            listItems.add(alarmItem);

        }


        adapter = new RecyclerViewAdapter(this, listItems);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
