package com.example.rtodo.strt3.Model;

import android.widget.ImageView;
import android.widget.Switch;

public class ListItems {

    private String alarmday;
    private Switch alarmswitch;
    private String alarmringtone;
    private String alarmvibrate;
    private ImageView imageringtone;
    private ImageView imagevibrate;
    private String alarmhour;


    public String getAlarmday() {
        return alarmday;
    }

    public void setAlarmday(String alarmday) {
        this.alarmday = alarmday;
    }

    public String getAlarmhour() {
        return alarmhour;
    }

    public void setAlarmhour(String alarmhour) {
        this.alarmhour = alarmhour;
    }

    public Switch getAlarmswitch() {
        return alarmswitch;
    }

    public void setAlarmswitch(Switch alarmswitch) {
        this.alarmswitch = alarmswitch;
    }

    public String getAlarmringtone() {
        return alarmringtone;
    }

    public void setAlarmringtone(String alarmringtone) {
        this.alarmringtone = alarmringtone;
    }

    public String getAlarmvibrate() {
        return alarmvibrate;
    }

    public void setAlarmvibrate(String alarmvibrate) {
        this.alarmvibrate = alarmvibrate;
    }

    public ImageView getImageringtone() {
        return imageringtone;
    }

    public void setImageringtone(ImageView imageringtone) {
        this.imageringtone = imageringtone;
    }

    public ImageView getImagevibrate() {
        return imagevibrate;
    }

    public void setImagevibrate(ImageView imagevibrate) {
        this.imagevibrate = imagevibrate;
    }

    public ListItems(String alarmday, String alarmhour) {
        this.alarmday = alarmday;
        this.alarmhour = alarmhour;
    }

    public ListItems() {
    }
}
