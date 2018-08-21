package com.example.rtodo.strt3;

import android.app.AlertDialog;
import android.app.Activity;
import android.app.LauncherActivity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.rtodo.strt3.Model.ListItems;

import java.util.Calendar;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public DatabaseHandler db;
    private Context context;
    private List<ListItems> listItems;
    private Calendar mCalendar;
    private String sDay;
    private int mDay;
    private Activity activity;


    public RecyclerViewAdapter(Context context, List listItem) {
        this.context = context;
        this.listItems = listItem;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        ListItems item = listItems.get(position);
        holder.alarmday.setText(item.getAlarmday());
        holder.alarmhour.setText(item.getAlarmhour());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }


    private AlertDialog.Builder mAlertDialogBuilder;
    private AlertDialog mDialog;
    private LayoutInflater mInflater;



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public TextView alarmday;
        public Switch alarmswitch;
        public RecyclerView.Adapter adapter;
        public RecyclerView recyclerView;
        private ListItems alarmItem1;
        public List<AlarmItem> alarmItemList;
        public TextView alarmringtone;
        public TextView alarmvibrate;
        public ImageView imageringtone;
        public ImageView imagevibrate;
        public TextView alarmhour;
        public TimePicker mTimePicker;
        public Button mButton;
        public Button mmButton;

        public ViewHolder(@NonNull View view) {
            super(view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    ListItems item = listItems.get(position);
                    startpopup();
                }
            });

            alarmday = view.findViewById(R.id.dayID);
            alarmhour = view.findViewById(R.id.hourID);
            alarmswitch = view.findViewById(R.id.switchID);
            alarmringtone = view.findViewById(R.id.RingtoneTextID);
            alarmvibrate = view.findViewById(R.id.VibrateTextID);
            imageringtone = view.findViewById(R.id.RingtoneId);
            imagevibrate = view.findViewById(R.id.VibrateID);

        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.hourID || view.getId() == R.id.dayID){
                int position = getAdapterPosition();
                ListItems item = listItems.get(position);
                startpopup();
            }



        }

        public void startpopup(){
            mAlertDialogBuilder = new AlertDialog.Builder(context);
            mInflater = LayoutInflater.from(context);
            View view = mInflater.inflate(R.layout.popup, null);
            mTimePicker = view.findViewById(R.id.TimePickerID);
            mButton = view.findViewById(R.id.PopupButtonID);
            mmButton = view.findViewById(R.id.PopupButtonID2);
            mAlertDialogBuilder.setView(view);
            mDialog = mAlertDialogBuilder.create();
            mDialog.show();

            mmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialog.dismiss();
                }
            });

            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //mCalendar.set(Calendar.HOUR_OF_DAY, mTimePicker.getCurrentHour());
                    //mCalendar.set(Calendar.MINUTE, mTimePicker.getCurrentMinute());
                    //TODO: mcalendar not working

                    mDay = getAdapterPosition();
                    if (mDay == 0){
                        sDay = "Monday";
                    }
                    if (mDay == 1){
                        sDay = "Tuesday";
                    }
                    if (mDay == 2){
                        sDay = "Wednesday";
                    }
                    if (mDay == 3){
                        sDay = "Thursday";
                    }
                    if (mDay == 4){
                        sDay = "Friday";
                    }
                    if (mDay == 5){
                        sDay = "Saturday";
                    }
                    if (mDay == 7){
                        sDay = "Sunday";
                    }

                    int mHour = mTimePicker.getCurrentHour();
                    int mMinute = mTimePicker.getCurrentMinute();

                    String sHour = String.valueOf(mHour);
                    String sMinute = String.valueOf(mMinute);
                    if (mMinute < 10){
                        sMinute = "0" + sMinute;
                    }

                    String sHM = sHour + ":" + sMinute;

                    AlarmItem alarmItem = new AlarmItem(mDay, sDay, mMinute, mHour,sHM);

                    db = new DatabaseHandler(context);

                    //db.updateItem(alarmItem);
                    db.deleteItem(mDay);
                    db.addAlarm(alarmItem);
                    //adapter.notifyItemChanged(getAdapterPosition(),alarmItem);
                    //adapter.notifyDataSetChanged();
                    listItems.clear();
                    alarmItemList = db.getAll();
                    for (AlarmItem c : alarmItemList) {
                        alarmItem1 = new ListItems();
                        alarmItem1.setAlarmday(c.getAlarmDay());
                        alarmItem1.setAlarmhour(c.getAlarmHM());

                        listItems.add(alarmItem1);

                    }
                    notifyItemChanged(getAdapterPosition());




                    mDialog.dismiss();
                }
            });
        }
    }
}
