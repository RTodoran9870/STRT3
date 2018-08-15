package com.example.rtodo.strt3;

public class AlarmItem {

    private int id;
    private String AlarmDay;
    private int AlarmMinute;
    private int AlarmHour;
    private String AlarmHM;

    public AlarmItem(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AlarmItem(int id, String alarmDay, int alarmMinute, int alarmHour, String alarmHM) {
        this.id = id;
        AlarmDay = alarmDay;
        AlarmMinute = alarmMinute;
        AlarmHour = alarmHour;
        AlarmHM = alarmHM;
    }

    public AlarmItem(String alarmDay, int alarmMinute, int alarmHour) {
        AlarmDay = alarmDay;
        AlarmMinute = alarmMinute;
        AlarmHour = alarmHour;
    }

    public AlarmItem(String alarmDay, int alarmMinute, int alarmHour, String alarmHM) {
        AlarmDay = alarmDay;
        AlarmMinute = alarmMinute;
        AlarmHour = alarmHour;
        AlarmHM = alarmHM;
    }

    public String getAlarmDay() {
        return AlarmDay;
    }

    public void setAlarmDay(String alarmDay) {
        AlarmDay = alarmDay;
    }

    public int getAlarmMinute() {
        return AlarmMinute;
    }

    public void setAlarmMinute(int alarmMinute) {
        AlarmMinute = alarmMinute;
    }

    public int getAlarmHour() {
        return AlarmHour;
    }

    public void setAlarmHour(int alarmHour) {
        AlarmHour = alarmHour;
    }

    public String getAlarmHM() {
        return AlarmHM;
    }

    public void setAlarmHM(String alarmHM) {
        AlarmHM = alarmHM;
    }
}