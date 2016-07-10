package com.haspau.KimsLek;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by paul on 13.05.2015.
 */
public class MovieController extends Activity {

    private static final String TAG = StartActivity.class.getSimpleName();
    private Context context = this;
    List<Movie> listOfAllMovies = new ArrayList<>();
    int randomMovieNumber;
    Movie movie;


    public int getRandomMovieNumber() {

        return randomMovieNumber;
    }


    public int movieControllerInput(int min, int max) {
        randomMovieNumber( min, max);
        return randomMovieNumber;
    }


    public int randomMovieNumber( int min, int max) {

            Random rand = new Random();
            randomMovieNumber = rand.nextInt((max - min) + 1) + min;

            return randomMovieNumber;
    }


    public List<Movie> getListOfAllMovies() {
        return listOfAllMovies;
    }


    @Override
    public String toString() {
       return "Movie [id=" + movie.movieId + ", film: " + movie.name + "]";
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Destroying");
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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
        Log.d(TAG, "Resume..");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Restart..");
        Intent mStartActivity = new Intent(context, StartActivity.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        System.exit(0);

    }


}
