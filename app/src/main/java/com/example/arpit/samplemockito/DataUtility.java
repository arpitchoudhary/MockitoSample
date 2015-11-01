package com.example.arpit.samplemockito;


import android.content.Context;
import android.content.SharedPreferences;

public class DataUtility {

    private Context context;
    private StoredData storedData;

    public DataUtility(Context context){
        storedData = new StoredData();
        this.context = context;
    }

    public String reverseInputString(String input){
        return storedData.revereTheInputString(input);
    }

    public void storeCurrentTime(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Storage",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("CurrentTime", System.currentTimeMillis());
        editor.apply();
    }

    public long getStoredTime(){
        long storedTime = 0;
        SharedPreferences sharedPreferences = context.getSharedPreferences("Storage",Context.MODE_PRIVATE);
        if(sharedPreferences.contains("CurrentTime")){
            storedTime = sharedPreferences.getLong("CurrentTime",0l);
        }
        return storedTime;
    }

}
