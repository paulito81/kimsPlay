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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by paul on 11.05.2015.
 */
public class StartActivity extends Activity {

    private Context context = this;
    private static final String TAG = StartActivity.class.getSimpleName();
    //DB connection
    private DBAdapter dbAdapter;
    // Textview connection
    private TextViewController textViewController = new TextViewController();

    //Round logic
    int roundNumber = 1;
    int roundExit = 1;
    int playerpoints = 0;

    //BUTTONS
    Button btnPlay;
    Button btnQuit;


    public void onCreate(Bundle instanceSavedState) {
        super.onCreate(instanceSavedState);
        setContentView(R.layout.startplay);

        initWidgets();
        roundCounter();
    }

    private void initWidgets() {

        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(listener);

        btnQuit = (Button) findViewById(R.id.btnQuit);
        btnQuit.setOnClickListener(listener);

        textViewController.txtViewR0 = (TextView) findViewById(R.id.txtViewR0);
        textViewController.txtViewR = (TextView) findViewById(R.id.txtViewR);
        textViewController.txtView1 = (TextView) findViewById(R.id.txtView1);
        textViewController.txtView2 = (TextView) findViewById(R.id.txtView2);
        textViewController.txtView3 = (TextView) findViewById(R.id.txtView3);
        textViewController.txtView4 = (TextView) findViewById(R.id.txtView4);
        textViewController.txtView5 = (TextView) findViewById(R.id.txtView5);
        textViewController.txtView6 = (TextView) findViewById(R.id.txtView6);
        textViewController.txtView7 = (TextView) findViewById(R.id.txtView7);
        textViewController.txtView8 = (TextView) findViewById(R.id.txtView8);
        textViewController.txtView9 = (TextView) findViewById(R.id.txtView9);

    }
    private void roundCounter(){
        Intent intent = getIntent();
        // get a new round to play
        roundExit = intent.getIntExtra("roundexit", roundExit);
        setRoundExit(roundExit);

        // The first round
        if (roundExit == 1) {

            textViewController.txtViewR0.setText("Roundnumber: " + getRoundNumber());
            textViewController.txtViewR.setText("Points: " + getPlayerpoints());
            initTextViews();

            // Round 2 -30
        } else if (roundExit > 1 || roundExit < 30) {

            playerpoints = intent.getIntExtra("playerpoints", playerpoints);
            roundNumber = intent.getIntExtra("roundnumber", roundNumber);

            textViewController.txtViewR0.setText("Roundnumber: " + getRoundNumber());
            textViewController.txtViewR.setText("Points: " + getPlayerpoints());
            initTextViews2(roundExit);

            // Round 30-60
        } else if (roundExit == 30) {

            playerpoints = intent.getIntExtra("playerpoints", playerpoints);
            roundNumber = intent.getIntExtra("roundnumber", roundNumber);

            setPlayerpoints(playerpoints);
            setRoundNumber(roundNumber);

            textViewController.txtViewR0.setText("Roundnumber: " + getRoundNumber());
            textViewController.txtViewR.setText("Points: " + getPlayerpoints());
            roundExit--;

            initTextViews2(roundExit);

            // Round 60-> error at round 90...
            if (roundExit == 1) {
                setPlayerpoints(getIntent().getExtras().getInt("playerpoints"));
                setRoundExit(getRoundNumber());
                textViewController.txtViewR0.setText("Round: " + getRoundNumber());
                textViewController.txtViewR.setText("Points: " + getPlayerpoints());
                initTextViews2(roundExit);
                roundExit++;
            }
        }
    }

    /**
     * First play init movies to the nine textviews.
     * Collect the movie titles from the local DB.
     */
    private void initTextViews() {

        textViewController.txtView1.setText(getAMovie(1));
        textViewController.txtView2.setText(getAMovie(2));
        textViewController.txtView3.setText(getAMovie(3));
        textViewController.txtView4.setText(getAMovie(4));
        textViewController.txtView5.setText(getAMovie(5));
        textViewController.txtView6.setText(getAMovie(6));
        textViewController.txtView7.setText(getAMovie(7));
        textViewController.txtView8.setText(getAMovie(8));
        textViewController.txtView9.setText(getAMovie(9));

        Toast.makeText(this, "Click the 'I am ready button' to start a new game", Toast.LENGTH_SHORT).show();

    }

    /**
     * Get a new "random" movie title +1 jump in the movielist per round
     * @param random integer value that pick a movie from the local DB.
     */
    private void initTextViews2(int random) {

        textViewController.txtView1.setText(getAMovie(1 + random));
        textViewController.txtView2.setText(getAMovie(2 + random));
        textViewController.txtView3.setText(getAMovie(3 + random));
        textViewController.txtView4.setText(getAMovie(4 + random));
        textViewController.txtView5.setText(getAMovie(5 + random));
        textViewController.txtView6.setText(getAMovie(6 + random));
        textViewController.txtView7.setText(getAMovie(7 + random));
        textViewController.txtView8.setText(getAMovie(8 + random));
        textViewController.txtView9.setText(getAMovie(9 + random));

    }

    /**
     * GET A MOVIE BY A INTEGER NUM FROM DB
     * @param NUM Integer value of the movie
     * @return a string value with the movie-title
     */
    private String getAMovie(int NUM) {
        dbAdapter = new DBAdapter(this);
        try {

            dbAdapter.open();
            Cursor cursor = dbAdapter.getMovieById(NUM);

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                int movieId;
                String name;
                Movie movie;
                do {
                    movieId = cursor.getInt(cursor.getColumnIndex(DBAdapter.M_ID));
                    name = cursor.getString(cursor.getColumnIndex(DBAdapter.M_NAME));
                    movie = new Movie(movieId, name);

                    textViewController.setText = movie.getName();

                } while (cursor.moveToNext());

                dbAdapter.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return textViewController.setText;
    }

    public void setTextView() {

        //Init a send items method to next activity
        Intent intent = new Intent(context, PlayActivity.class);

        // Set all fields +1 per round.
        setPlayerpoints(playerpoints++);
        setRoundNumber(roundNumber++);
        setRoundExit(roundExit++);

        //init values to be passed to the next activity
        intent.putExtra("roundnumber", roundNumber);
        intent.putExtra("playerpoints", playerpoints);
        intent.putExtra("roundexit", roundExit);

        // Send values to next activity
        intent.putExtra("txt1", textViewController.getTxtView1().getText().toString());
        intent.putExtra("txt2", textViewController.getTxtView2().getText().toString());
        intent.putExtra("txt3", textViewController.getTxtView3().getText().toString());
        intent.putExtra("txt4", textViewController.getTxtView4().getText().toString());
        intent.putExtra("txt5", textViewController.getTxtView5().getText().toString());
        intent.putExtra("txt6", textViewController.getTxtView6().getText().toString());
        intent.putExtra("txt7", textViewController.getTxtView7().getText().toString());
        intent.putExtra("txt8", textViewController.getTxtView8().getText().toString());
        intent.putExtra("txt9", textViewController.getTxtView9().getText().toString());

        startActivity(intent);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.btnPlay:
                    setTextView();
                    break;
                case R.id.btnQuit:
                    finish();
                    System.exit(0);
                    break;
                default:
                    throw new IllegalArgumentException("Illegal input ");

            }
        }
    };

    public int getRoundExit() {
        return roundExit;
    }

    public void setPlayerpoints(int playerpoints) {
        this.playerpoints = playerpoints;
    }

    public void setRoundExit(int roundExit) {
        this.roundExit = roundExit;
    }

    public int getPlayerpoints() {
        return playerpoints;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Destroying");
        finish();
        System.exit(0);
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