package com.example.rtodo.strt3;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.rtodo.strt3.Model.ListItems;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public DatabaseHandler db;
    private Context context;
    private List<ListItems> listItems;
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
        private ListItems alarmItem1;
        public List<AlarmItem> alarmItemList;
        public TextView alarmringtone;
        public TextView alarmvibrate;
        public ImageView imageringtone;
        public ImageView imagevibrate;
        public TextView alarmhour;
        public TimePicker mTimePicker;
        public Button ConfirmButton;
        public Button CancelButton;
        private int alarm_hour;
        private int alarm_minute;
        private PendingIntent mPendingIntent;
        public AlarmManager mAlarmManager;

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



            alarmswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        Toast.makeText(context, "Start Alarm", Toast.LENGTH_SHORT).show();
                        mDay = getAdapterPosition();
                        if (mDay == 0){
                            sDay = "Monday";
                        }else if (mDay == 1){
                            sDay = "Tuesday";
                        }else if (mDay == 2){
                            sDay = "Wednesday";
                        }else if (mDay == 3){
                            sDay = "Thursday";
                        }else if (mDay == 4){
                            sDay = "Friday";
                        }else if (mDay == 5){
                            sDay = "Saturday";
                        }else if (mDay == 6){
                            sDay = "Sunday";
                        }
                        int alarm_day = (mDay + 1)%7 + 1;
                        db = new DatabaseHandler(context);
                        alarm_hour = db.getAlarmItem(mDay).getAlarmHour();
                        alarm_minute = db.getAlarmItem(mDay).getAlarmMinute();
                        Calendar nowCalendar = new GregorianCalendar();
                        int now_day = nowCalendar.get(Calendar.DAY_OF_WEEK);
                        int difference_days;
                        if (alarm_day>now_day){
                            difference_days = alarm_day - now_day;
                        }else if (alarm_day<now_day){
                            difference_days = alarm_day - now_day + 7;
                        }else{
                            int now_hour = nowCalendar.get(Calendar.HOUR_OF_DAY);
                            int now_minute = nowCalendar.get(Calendar.MINUTE);
                            if (alarm_hour*60 + alarm_minute > now_hour*60 + now_minute){
                                difference_days = 0;
                            }else {
                                difference_days = 7;
                            }
                        }
                        nowCalendar.add(Calendar.DAY_OF_YEAR, difference_days);
                        nowCalendar.set(Calendar.HOUR_OF_DAY, alarm_hour);
                        nowCalendar.set(Calendar.MINUTE, alarm_minute);
                        nowCalendar.set(Calendar.SECOND, 0);


                        Log.d("Calendar", nowCalendar.getTime().toString());
                        Intent mIntent = new Intent(context, AlarmReceiver.class);
                        mPendingIntent = PendingIntent.getBroadcast(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                        mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP, nowCalendar.getTimeInMillis(),7*24*3600*1000, mPendingIntent);


                    }else {
                        Toast.makeText(context, "Stop alarm", Toast.LENGTH_SHORT).show();
                        Intent mIntent = new Intent(context, AlarmReceiver.class);
                        mPendingIntent = PendingIntent.getBroadcast(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                        mAlarmManager.cancel(mPendingIntent);
                        mPendingIntent.cancel();
                    }
                }
            });
            

        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.hourID || view.getId() == R.id.dayID){
                int position = getAdapterPosition();
                ListItems item = listItems.get(position);
                startpopup();
                Log.d("Test", "After popup");
            }



        }

        public void startpopup(){
            mAlertDialogBuilder = new AlertDialog.Builder(context);
            mInflater = LayoutInflater.from(context);
            View view = mInflater.inflate(R.layout.popup, null);
            mTimePicker = view.findViewById(R.id.TimePickerID);
            ConfirmButton = view.findViewById(R.id.ConfirmButtonID);
            CancelButton = view.findViewById(R.id.CancelButtonID);
            mAlertDialogBuilder.setView(view);
            mDialog = mAlertDialogBuilder.create();
            mDialog.show();

            CancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialog.dismiss();
                }
            });

            ConfirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mDay = getAdapterPosition();
                    if (mDay == 0){
                        sDay = "Monday";
                    }else if (mDay == 1){
                        sDay = "Tuesday";
                    }else if (mDay == 2){
                        sDay = "Wednesday";
                    }else if (mDay == 3){
                        sDay = "Thursday";
                    }else if (mDay == 4){
                        sDay = "Friday";
                    }else if (mDay == 5){
                        sDay = "Saturday";
                    }else if (mDay == 6){
                        sDay = "Sunday";
                    }

                    alarm_hour = mTimePicker.getCurrentHour();
                    alarm_minute = mTimePicker.getCurrentMinute();

                    String sHour = String.valueOf(alarm_hour);
                    String sMinute = String.valueOf(alarm_minute);
                    if (alarm_minute < 10){
                        sMinute = "0" + sMinute;
                    }

                    String sHM = sHour + ":" + sMinute;

                    AlarmItem alarmItem = new AlarmItem(mDay, sDay, alarm_minute, alarm_hour,sHM);

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


                    Log.d("isChecked switch", "Initial " + alarmswitch.isChecked());

                    alarmswitch.setChecked(false);
                    Log.d("isChecked switch","after first togle" + alarmswitch.isChecked());
                    alarmswitch.setChecked(true);
                    Log.d("isChecked switch","after second togle" + alarmswitch.isChecked());



                    Log.d("Test", "Before dismiss");
                    mDialog.dismiss();
                    Log.d("Test", "After dismiss");
                }
            });
        }
    }
}
