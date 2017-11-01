package com.example.mat.smartpillbox;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.AnalogClock;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE;


public class MainActivity extends AppCompatActivity {


    AlarmHandler alarmHandler;
    Clock clock;
    //List<Integer> alarmList = Arrays.asList( 7, 8, 9, 11, 14, 15, 17 );
    List<Integer> alarmList = Arrays.asList( 18, 19, 20, 21, 22, 22, 22 );

    /*
    int hour = 15;
    int minuteBase = 00;
    int[][] alarmListFine = { {hour, minuteBase + 2}, {hour, minuteBase + 4}, {hour,minuteBase + 6},
                              {hour,minuteBase + 8}, {hour, minuteBase + 10}, {hour,minuteBase + 12},
                              {hour, minuteBase + 14}};
    int alarmReset = 16;
    */


    private SoundPool soundPool;
    private int[] streamID = new int[10];
    private int[] soundID = new int[10];



    boolean loaded = false;

    //Button button1 = (Button)findViewById(R.id.button1);
    public void brCallback(String param, Intent intent) {

        final Button button0 = (Button) findViewById(R.id.button0);
        final Button button1 = (Button) findViewById(R.id.button1);
        final Button button2 = (Button) findViewById(R.id.button2);
        final Button button3 = (Button) findViewById(R.id.button3);
        final Button button4 = (Button) findViewById(R.id.button4);
        final Button button5 = (Button) findViewById(R.id.button5);
        final Button button6 = (Button) findViewById(R.id.button6);
        Log.i("brCallback()", param);

        button0.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        button0.setText("07:00");
        button0.clearAnimation();
        button1.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        button1.setText("08:00");
        button1.clearAnimation();
        button2.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        button2.setText("09:00");
        button2.clearAnimation();
        button3.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        button3.setText("11:00");
        button3.clearAnimation();
        button4.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        button4.setText("14:00");
        button4.clearAnimation();
        button5.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        button5.setText("15:00");
        button5.clearAnimation();
        //button6.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        button6.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        button6.setText("17:00");
        button6.clearAnimation();

        soundPool.stop(streamID[4]);

        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        //((PowerManager)getSystemService(POWER_SERVICE)).newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "TAG").acquire();
        alarmHandler.completeWakefulIntent(intent);
    }

    //PowerManager.WakeLock keepScreenOn;
    public void brCallback_0(String param, Intent intent) {
        final Button button0 = (Button)findViewById(R.id.button0);
        Log.i("brCallback_0()", param);
        animateAlarm(button0, 0);
        alarmHandler.completeWakefulIntent(intent);

    }

    public void brCallback_1(String param, Intent intent) {
        final Button button1 = (Button)findViewById(R.id.button1);
        Log.i("brCallback_1()", param);
        animateAlarm(button1, 1);
        alarmHandler.completeWakefulIntent(intent);

    }

    public void brCallback_2(String param, Intent intent) {
        final Button button2 = (Button)findViewById(R.id.button2);
        Log.i("brCallback_2()", param);
        animateAlarm(button2, 2);
        alarmHandler.completeWakefulIntent(intent);
    }

    public void brCallback_3(String param, Intent intent) {
        final Button button3 = (Button)findViewById(R.id.button3);
        Log.i("brCallback_3()", param);
        animateAlarm(button3, 3);
        alarmHandler.completeWakefulIntent(intent);
    }

    public void brCallback_4(String param, Intent intent) {
        final Button button4 = (Button)findViewById(R.id.button4);
        Log.i("brCallback_4()", param);
        animateAlarm(button4, 4);
        alarmHandler.completeWakefulIntent(intent);
    }

    public void brCallback_5(String param, Intent intent) {
        final Button button5 = (Button)findViewById(R.id.button5);
        Log.i("brCallback_5()", param);
        animateAlarm(button5, 5);
        alarmHandler.completeWakefulIntent(intent);
    }

    public void brCallback_6(String param, Intent intent) {
        final Button button6 = (Button)findViewById(R.id.button6);
        Log.i("brCallback_6()", param);
        animateAlarm(button6, 6);
        alarmHandler.completeWakefulIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | SYSTEM_UI_FLAG_IMMERSIVE ;
        decorView.setSystemUiVisibility(uiOptions);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        Log.i("onCreate()", "");

        for ( int i = 0; i < alarmList.size(); i++ )
        {
            Log.i( "AlarmTime: ", alarmList.get(i).toString() );
            //Log.i( "AlarmTimeFine: ", Integer.toString(alarmListFine[i][0]) + "." + Integer.toString(alarmListFine[i][1]));
        }


        final Button[] button = new Button[ alarmList.size() ];

        button[0] = (Button)findViewById(R.id.button0);
        button[1] = (Button)findViewById(R.id.button1);
        button[2] = (Button)findViewById(R.id.button2);
        button[3] = (Button)findViewById(R.id.button3);
        button[4] = (Button)findViewById(R.id.button4);
        button[5] = (Button)findViewById(R.id.button5);
        button[6] = (Button)findViewById(R.id.button6);


        Calendar calendar = Calendar.getInstance();
        //calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 5);



        //AlarmManager alarmManager = (AlarmManager) getSystemService(WakefulReceiver);

        alarmHandler = new AlarmHandler(this); //passing context
        //clock = new Clock(); //passing context

        AnalogClock analog = (AnalogClock) findViewById(R.id.analog_clock);
        analog.bringToFront();

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // reset for next day
        String name = "custom-action_0";
        Intent intent = new Intent(name);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        //calendar.set(Calendar.MINUTE, alarmReset);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        IntentFilter filter = new IntentFilter(name);
        getApplicationContext().registerReceiver(alarmHandler, filter);


        String name1 = "custom-action_7";
        Intent intent1 = new Intent(name1);
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(getApplicationContext(), 1, intent1, 0);
        calendar.set(Calendar.HOUR_OF_DAY, alarmList.get(0));
        //calendar.set(Calendar.HOUR_OF_DAY, alarmListFine[0][0]);
        //calendar.set(Calendar.MINUTE, alarmListFine[0][1]);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent1);
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, pendingIntent1);
        IntentFilter filter1 = new IntentFilter(name1);
        getApplicationContext().registerReceiver(alarmHandler, filter1);

        String name2 = "custom-action_8";
        Intent intent2 = new Intent(name2);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(getApplicationContext(), 1, intent2, 0);
        calendar.set(Calendar.HOUR_OF_DAY, alarmList.get(1));
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent2);
        IntentFilter filter2 = new IntentFilter(name2);
        getApplicationContext().registerReceiver(alarmHandler, filter2);

        String name3 = "custom-action_9";
        Intent intent3 = new Intent(name3);
        PendingIntent pendingIntent3 = PendingIntent.getBroadcast(getApplicationContext(), 1, intent3, 0);
        calendar.set(Calendar.HOUR_OF_DAY, alarmList.get(2));
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent3);
        IntentFilter filter3 = new IntentFilter(name3);
        getApplicationContext().registerReceiver(alarmHandler, filter3);

        String name4 = "custom-action_11";
        Intent intent4 = new Intent(name4);
        PendingIntent pendingIntent4 = PendingIntent.getBroadcast(getApplicationContext(), 1, intent4, 0);
        calendar.set(Calendar.HOUR_OF_DAY, alarmList.get(3));
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, pendingIntent4);
        IntentFilter filter4 = new IntentFilter(name4);
        getApplicationContext().registerReceiver(alarmHandler, filter4);

        String name5 = "custom-action_14";
        Intent intent5 = new Intent(name5);
        PendingIntent pendingIntent5 = PendingIntent.getBroadcast(getApplicationContext(), 1, intent5, 0);
        calendar.set(Calendar.HOUR_OF_DAY, alarmList.get(4));
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, pendingIntent5);
        IntentFilter filter5 = new IntentFilter(name5);
        getApplicationContext().registerReceiver(alarmHandler, filter5);

        String name6 = "custom-action_15";
        Intent intent6 = new Intent(name6);
        PendingIntent pendingIntent6 = PendingIntent.getBroadcast(getApplicationContext(), 1, intent6, 0);
        calendar.set(Calendar.HOUR_OF_DAY, alarmList.get(5));
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, pendingIntent6);
        IntentFilter filter6 = new IntentFilter(name6);
        getApplicationContext().registerReceiver(alarmHandler, filter6);

        String name7 = "custom-action_17";
        Intent intent7 = new Intent(name7);
        PendingIntent pendingIntent7 = PendingIntent.getBroadcast(getApplicationContext(), 1, intent7, 0);
        calendar.set(Calendar.HOUR_OF_DAY, alarmList.get(6));
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_HOUR, pendingIntent7);
        IntentFilter filter7 = new IntentFilter(name7);
        getApplicationContext().registerReceiver(alarmHandler, filter7);


        abstract class ClickListener implements View.OnClickListener {
            final int mCounter;

            public ClickListener(final int counter) {
                mCounter = counter;
            }
        }

        for ( int counter = 0; counter < button.length; counter++ )
        {
            button[counter].setOnClickListener(new ClickListener(counter) {

                public void onClick(View v) {
                    String alarm = button[mCounter].getText().toString();
                    Log.i("button text: ", alarm);
                    if (alarm.contains("Alarm") ) {
                        Log.i("Alarm ",  "identified !");
                        button[mCounter].setBackgroundColor(Color.GREEN);
                        button[mCounter].clearAnimation();
                        soundPool.stop(streamID[4]);
                        switch (mCounter) {
                            case 0: button[mCounter].setText( "07:00 OK");
                                break;
                            case 1: button[mCounter].setText( "08:00 OK");
                                break;
                            case 2: button[mCounter].setText( "09:00 OK");
                                break;
                            case 3: button[mCounter].setText( "11:00 OK");
                                break;
                            case 4: button[mCounter].setText( "14:00 OK");
                                break;
                            case 5: button[mCounter].setText( "15:00 OK");
                                break;
                            case 6: button[mCounter].setText( "17:00 OK");
                                break;
                        }
                        try {
                            Log.i("email", alarm);
                            GMailSender sender = new GMailSender("Matthias.Griese@gmail.com", "!HistEST216411");
                            sender.sendMail("test Tabletten Box Hubertus - v1.0.2",
                                    "OK: " + alarm,
                                    "Matthias.Griese@yahoo.de",
                                    "matthias.griese@yahoo.de");
                        } catch (Exception e) {
                            Log.e("SendMail", e.getMessage(), e);
                        }
                    }



                }
            });
        }

        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,
                                       int status) {
                loaded = true;
            }
        });

        soundID[0] = soundPool.load(this, R.raw.horncall_beethoven3_eflatmajor_mono, 1);
        soundID[1] = soundPool.load(this, R.raw.horncall_bozza_enforet1_fmajor_mono, 1);
        soundID[2] = soundPool.load(this, R.raw.horncall_beethoven7_amajor_mono, 1);
        soundID[3] = soundPool.load(this, R.raw.horncall_bozza_enforet2_fmajor_mono, 1);
        soundID[4] = soundPool.load(this, R.raw.horncall_bruckner4_bbminor_mono, 1);
        soundID[5] = soundPool.load(this, R.raw.horncall_haydn31_hornsignal_dmajor_mono, 1);
        soundID[6] = soundPool.load(this, R.raw.horncall_shostakovich5_bflatmajor_mono, 1);
        soundID[7] = soundPool.load(this, R.raw.horncall_strauss1_eflatmajor_mono, 1);
        soundID[8] = soundPool.load(this, R.raw.horncall_strauss_donjuan_emajor_mono, 1);
        soundID[9] = soundPool.load(this, R.raw.horncall_wagner_siegfriedcall_fmajor_mono, 1);

    }

    private void animateAlarm(Button button, int number) {

        button.setBackgroundColor(Color.RED);

        /*
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String timeLabel = formatter.format(timestamp);
        */

        switch (number) {
            case 0:
                button.setText( "07:00 Pillen Alarm\n");
                break;
            case 1:
                button.setText( "08:00 Pillen Alarm\n");
                break;
            case 2:
                button.setText( "09:00 Pillen Alarm\n");
                break;
            case 3:
                button.setText( "11:00 Pillen Alarm\n");
                break;
            case 4:
                button.setText( "14:00 Pillen Alarm\n");
                break;
            case 5:
                button.setText( "15:00 Pillen Alarm\n");
                break;
            case 6:
                button.setText( "17:00 Pillen Alarm\n" +
                        "");
                break;
        }

        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setDuration(500); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in

        button.startAnimation(animation);

        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        float actualVolume = (float) audioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC);
        float maxVolume = (float) audioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volume = actualVolume / maxVolume;
        // Is the sound loaded already?
        if (loaded) {
            int loop = 5;
            //streamID[number] = soundPool.play(soundID[number], volume, volume, 1, loop, 1f);
            //if (streamID[4] != 0) soundPool.stop(streamID[4]);
            soundPool.stop(streamID[4]);
            streamID[4] = soundPool.play(soundID[4], volume, volume, 1, loop, 1f);
            Log.e("Test", "Played sound");
        }


    }


    @Override
    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | SYSTEM_UI_FLAG_IMMERSIVE ;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus) {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | SYSTEM_UI_FLAG_IMMERSIVE ;
        decorView.setSystemUiVisibility(uiOptions);
    }

}
