package com.haspau.KimsLek;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends Activity {

    private static final String TAG = StartActivity.class.getSimpleName();
    private Context context = this;
    private DBAdapter dbAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        initWidgets();
    }

    private void initWidgets() {

        Button btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(listener);

        Button btnHighScore = (Button) findViewById(R.id.btnHigh);
        btnHighScore.setOnClickListener(listener);

        Button btnQuit = (Button) findViewById(R.id.btnQuit);
        btnQuit.setOnClickListener(listener);


        dbAdapter = new DBAdapter(this);
        /* DEBUG
        dbAdapter.open();
        dbAdapter.delete2();
        dbAdapter.close();
        */
        initMovies();

    }



    private void initMovies() {
        //INSERT TITLES INTO DB.
        Movie movie1 = new Movie("Prince Caspian");
        Movie movie2 = new Movie("Inception");
        Movie movie3 = new Movie("The Matrix");
        Movie movie4 = new Movie("Ice Age3");
        Movie movie5 = new Movie("Blues Brothers");
        Movie movie6 = new Movie("Sharks");
        Movie movie7 = new Movie("Braveheart");
        Movie movie8 = new Movie("Titanic");
        Movie movie9 = new Movie("E.T");
        Movie movie10 = new Movie("Hook");
        Movie movie11 = new Movie("Narnia");
        Movie movie12 = new Movie("Runaway Bride");
        Movie movie13 = new Movie("Treasure Island");
        Movie movie14 = new Movie("Shutter Island");
        Movie movie15 = new Movie("Romeo&Juliet");
        Movie movie16 = new Movie("True Lies");
        Movie movie17 = new Movie("Home Alone1");
        Movie movie18 = new Movie("Astrix");
        Movie movie19 = new Movie("Sniper");
        Movie movie20 = new Movie("Taken");
        Movie movie21 = new Movie("Terminator");
        Movie movie22 = new Movie("Blade Runner");
        Movie movie23 = new Movie("High Fidelity");
        Movie movie24 = new Movie("Rush");
        Movie movie25 = new Movie("Avengers");
        Movie movie26 = new Movie("DareDevil");
        Movie movie27 = new Movie("James Bond");
        Movie movie28 = new Movie("Rocky");
        Movie movie29 = new Movie("Fantastic Four");
        Movie movie30 = new Movie("El Critico");
        Movie movie31 = new Movie("Batman");
        Movie movie32 = new Movie("Jurassic Park");
        Movie movie33 = new Movie("Mad Max");
        Movie movie34 = new Movie("La French");
        Movie movie35 = new Movie("Home");
        Movie movie36 = new Movie("Avatar");
        Movie movie37 = new Movie("Max Payn");
        Movie movie38 = new Movie("Deer Hunter");
        Movie movie39 = new Movie("Forest Gump");
        Movie movie40 = new Movie("Pitch Dark");
        Movie movie41 = new Movie("Peter Pan");


        dbAdapter.open();
        dbAdapter.insert(movie1);
        dbAdapter.insert(movie2);
        dbAdapter.insert(movie3);
        dbAdapter.insert(movie4);
        dbAdapter.insert(movie6);
        dbAdapter.insert(movie5);
        dbAdapter.insert(movie7);
        dbAdapter.insert(movie8);
        dbAdapter.insert(movie9);
        dbAdapter.insert(movie10);
        dbAdapter.insert(movie11);
        dbAdapter.insert(movie12);
        dbAdapter.insert(movie13);
        dbAdapter.insert(movie14);
        dbAdapter.insert(movie15);
        dbAdapter.insert(movie16);
        dbAdapter.insert(movie17);
        dbAdapter.insert(movie18);
        dbAdapter.insert(movie19);
        dbAdapter.insert(movie20);
        dbAdapter.insert(movie21);
        dbAdapter.insert(movie22);
        dbAdapter.insert(movie23);
        dbAdapter.insert(movie24);
        dbAdapter.insert(movie25);
        dbAdapter.insert(movie26);
        dbAdapter.insert(movie27);
        dbAdapter.insert(movie28);
        dbAdapter.insert(movie29);
        dbAdapter.insert(movie30);
        dbAdapter.insert(movie31);
        dbAdapter.insert(movie32);
        dbAdapter.insert(movie33);
        dbAdapter.insert(movie34);
        dbAdapter.insert(movie35);
        dbAdapter.insert(movie36);
        dbAdapter.insert(movie37);
        dbAdapter.insert(movie38);
        dbAdapter.insert(movie39);
        dbAdapter.insert(movie40);
        dbAdapter.insert(movie41);
        dbAdapter.close();

    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.btnPlay:
                    Intent intent = new Intent(context, StartActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btnHigh:
                    Intent intent1 = new Intent(context, HighscoreActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.btnQuit:
                    finish();
                    System.exit(0);
                    break;
                default:
                  break;
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Destroying");
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Stopping..");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Resume..    ");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Restart..    ");
        Intent mStartActivity = new Intent(context, MainActivity.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        System.exit(0);

    }

}
