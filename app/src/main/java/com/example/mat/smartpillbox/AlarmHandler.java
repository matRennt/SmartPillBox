package com.example.mat.smartpillbox;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.Toast;
import android.os.Vibrator;


/**
 * Created by mat on 18.07.17.
 */

public class AlarmHandler extends WakefulBroadcastReceiver {

    MainActivity mainContext; //a reference to activity's context


    public AlarmHandler(MainActivity context){
        System.err.println("--->>> Constructor: AlarmHandler");
        mainContext = context;
    }

    @Override
    public void onReceive(Context context, Intent intent){
        System.err.println("--->>> AlarmHandler.onReceive: context: " + context.toString() + " intent: " + intent.toString());
        Log.i("AlarmHandler", "Dynamic handle");

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = pm.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
        wakeLock.acquire();

        Toast.makeText (context, "Bitte jetzt Tabletten einnehmen.", Toast.LENGTH_LONG).show();

        Integer time = Integer.parseInt(intent.getAction().split("_")[1]);

        switch ( time ) {
            case 0:
                Log.i("Switch", "default");
                mainContext.brCallback("(nightly) reset of state", intent); //calling activity method
                break;
            case 7:
                Log.i("Switch", "7");
                mainContext.brCallback_0("Alarm 7", intent); //calling activity method
                break;
            case 8:
                Log.i("Switch", "8");
                mainContext.brCallback_1("Alarm 8", intent); //calling activity method
                break;
            case 9:
                Log.i("Switch", "9");
                mainContext.brCallback_2("Alarm 9", intent); //calling activity method
                break;
            case 11:
                Log.i("Switch", "11");
                mainContext.brCallback_3("Alarm 11", intent); //calling activity method
                break;
            case 14:
                Log.i("Switch", "14");
                mainContext.brCallback_4("Alarm 14", intent); //calling activity method
                break;
            case 15:
                Log.i("Switch", "15");
                mainContext.brCallback_5("Alarm 15", intent); //calling activity method
                break;
            case 17:
                Log.i("Switch", "17");
                mainContext.brCallback_6("Alarm 17", intent); //calling activity method
                break;

        }


        //ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, (int)(ToneGenerator.MAX_VOLUME * 0.85));
        //tg.startTone(ToneGenerator.TONE_SUP_RINGTONE, 5000);

        //Vibrator vibrator = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        //vibrator.vibrate(1000);
        //Button button1 = MainActivity.getButton1();

        wakeLock.release();

    }
}
