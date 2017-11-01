package com.example.mat.smartpillbox;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Vibrator;
import android.widget.Toast;


/**
 * Created by mat on 18.07.17.
 */

public class AlarmHandlerStatic extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        System.err.println("--->>> AlarmHandlerStatic.onReceive: context: " + context.toString() + " intent: " + intent.toString());
        Toast.makeText (context, "Static: Tabletten einnehmen !", Toast.LENGTH_LONG).show();

        ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, (int)(ToneGenerator.MAX_VOLUME * 0.85));
        tg.startTone(ToneGenerator.TONE_SUP_RINGTONE, 5000);

        Vibrator vibrator = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        //vibrator.vibrate(1000);
        //Button button1 = MainActivity.getButton1();
    }
}
