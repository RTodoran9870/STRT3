package com.example.rtodo.strt3;

import android.app.AlertDialog;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
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
    private DrawerLayout mDrawerLayout;
    private TextView mHourText;
    private DatabaseHandler db;
    public List<AlarmItem> alarmItemList;
    private Context ctxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawerlayout);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        db = new DatabaseHandler(this);
        ctxt = this;

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
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24px);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        int id = menuItem.getItemId();
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        if (id==R.id.nav_account){
                            Intent intent = new Intent(ctxt, MyProfile.class);
                            startActivity(intent);
                        }

                        if (id==R.id.simulate){
                            Intent intent = new Intent(ctxt, Ring.class);
                            startActivity(intent);
                        }


                        return true;
                    }
                });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
