package com.haspau.KimsLek;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by paul on 11.05.2015.
 */
public class PlayActivity extends Activity {


    private Context context = this;
    private static final String TAG = StartActivity.class.getSimpleName();
    private MovieController movieController = new MovieController();

    // Answer the question
    private Button btnAnswer1;
    private Button btnAnswer2;
    private Button btnAnswer3;

    //Button meny /quit
    private Button btnQuit;
    private Button btnMenu;

    //Textviews
    private TextView txtView1;
    private TextView txtView2;
    private TextView txtView3;
    private TextView txtView4;
    private TextView txtView5;
    private TextView txtView6;
    private TextView txtView7;
    private TextView txtView8;
    private TextView txtView9;

    //Input a round Counter & Points
    private TextView txtViewR;
    private TextView txtPoints;

    public String getDates() {
        return dates;
    }

    private void setDates(String dates) {
        this.dates = dates;
    }

    //Rounds
    private String dates;
    private int roundNumber = 1;
    private int roundExit;
    private int playerpoints;
    private String round = "Round: ";



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);

        initWidgets();
    }

    private void initWidgets() {

        //Buttons
        btnMenu = (Button) findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(listener);
        btnQuit = (Button) findViewById(R.id.btnQuit);
        btnQuit.setOnClickListener(listener);
        btnAnswer1 = (Button) findViewById(R.id.btnAnswer1);
        btnAnswer1.setOnClickListener(listener);
        btnAnswer2 = (Button) findViewById(R.id.btnAnswer2);
        btnAnswer2.setOnClickListener(listener);
        btnAnswer3 = (Button) findViewById(R.id.btnAnswer3);
        btnAnswer3.setOnClickListener(listener);

        //TextViews
        txtViewR = (TextView) findViewById(R.id.txtViewR);
        txtPoints =(TextView) findViewById(R.id.txtPoints);
        txtView1 = (TextView) findViewById(R.id.txtView1);
        txtView2 = (TextView) findViewById(R.id.txtView2);
        txtView3 = (TextView) findViewById(R.id.txtView3);
        txtView4 = (TextView) findViewById(R.id.txtView4);
        txtView5 = (TextView) findViewById(R.id.txtView5);
        txtView6 = (TextView) findViewById(R.id.txtView6);
        txtView7 = (TextView) findViewById(R.id.txtView7);
        txtView8 = (TextView) findViewById(R.id.txtView8);
        txtView9 = (TextView) findViewById(R.id.txtView9);

        playerInit();   //set playerpoints, roundnum, gameround.
        initTextContent();
        initTextViews(roundNumber);

    }

    private void playerInit() {

        //reset buttons
        btnAnswer1.setInputType(0);
        btnAnswer2.setInputType(0);
        btnAnswer3.setInputType(0);

        Intent intent = getIntent();
        //get values from previous activity
        playerpoints = intent.getIntExtra("playerpoints", playerpoints);
        roundNumber = intent.getIntExtra("roundnumber", roundNumber);
        roundExit = intent.getIntExtra("roundexit", roundExit);

        // Set round to be played and player points
        setPlayerpoints(playerpoints);
        setRoundNumber(roundNumber);
        setRoundExit(roundExit);
    }

    //Init the first game round.
    private void initTextContent() {

        Intent intent = getIntent();

        //Playscore box, round box
        txtPoints.setText("Points: " + playerpoints);
        txtViewR.setText("Round: " + roundNumber);

        //Multiple movie fields, get value from previous activity
        txtView1.setText(intent.getStringExtra("txt1"));
        txtView2.setText(intent.getStringExtra("txt2"));
        txtView3.setText(intent.getStringExtra("txt3"));
        txtView4.setText(intent.getStringExtra("txt4"));
        txtView5.setText(intent.getStringExtra("txt5"));
        txtView6.setText(intent.getStringExtra("txt6"));
        txtView7.setText(intent.getStringExtra("txt7"));
        txtView8.setText(intent.getStringExtra("txt8"));
        txtView9.setText(intent.getStringExtra("txt9"));

    }

    /**
     * Random movie picker, pick a winner box.
     *
     */
    private void initTextViews(int i) {
        Intent intent = getIntent();
        if (i == 1 || i == 10 || i == 20 || i == 30 || i == 40) {
          //  Toast.makeText(this, "Demo-test\nCorrect answer is" + intent.getStringExtra("txt1"), Toast.LENGTH_LONG).show();
            btnAnswer1.setText(intent.getStringExtra("txt1"));        //correct answer
            btnAnswer2.setText(intent.getStringExtra("txt2"));
            btnAnswer3.setText(intent.getStringExtra("txt3"));
            txtView1.setText("");
            btnAnswer1.setInputType(1);

        } else if (i == 2 || i == 11 || i == 21 || i == 31) {
           // Toast.makeText(this, "Demo-test\nCorrect answer is" + intent.getStringExtra("txt3"), Toast.LENGTH_LONG).show();
            btnAnswer1.setText(intent.getStringExtra("txt4"));
            btnAnswer2.setText(intent.getStringExtra("txt3"));       //correct answer
            btnAnswer3.setText(intent.getStringExtra("txt5"));
            txtView3.setText("");
            btnAnswer2.setInputType(1);

        } else if (i == 3 || i == 12 || i == 22 || i == 32) {
          //  Toast.makeText(this, "Demo-test\nCorrect answer is" + intent.getStringExtra("txt3"), Toast.LENGTH_LONG).show();
            btnAnswer1.setText(intent.getStringExtra("txt4"));
            btnAnswer2.setText(intent.getStringExtra("txt2"));
            btnAnswer3.setText(intent.getStringExtra("txt3"));         //correct answer
            txtView3.setText("");
            btnAnswer3.setInputType(1);

        } else if (i == 4 || i == 13 || i == 23 || i == 33) {
         //   Toast.makeText(this, "Demo-test\nCorrect answer is" + intent.getStringExtra("txt4"), Toast.LENGTH_LONG).show();
            btnAnswer1.setText(intent.getStringExtra("txt4"));      //correct answer
            btnAnswer2.setText(intent.getStringExtra("txt6"));
            btnAnswer3.setText(intent.getStringExtra("txt7"));
            txtView4.setText("");
            btnAnswer1.setInputType(1);

        } else if (i == 5 || i == 14 || i == 24 || i == 34) {
         //   Toast.makeText(this, "Demo-test\nCorrect answer is" + intent.getStringExtra("txt5"), Toast.LENGTH_LONG).show();
            btnAnswer1.setText(intent.getStringExtra("txt4"));
            btnAnswer2.setText(intent.getStringExtra("txt2"));
            btnAnswer3.setText(intent.getStringExtra("txt5"));     //correct answer
            txtView5.setText("");
            btnAnswer3.setInputType(1);

        } else if (i == 6 || i == 15 || i == 25 || i == 35) {
        //    Toast.makeText(this, "Demo-test\nCorrect answer is" + intent.getStringExtra("txt6"), Toast.LENGTH_LONG).show();
            btnAnswer1.setText(intent.getStringExtra("txt8"));
            btnAnswer2.setText(intent.getStringExtra("txt6"));        //correct answer
            btnAnswer3.setText(intent.getStringExtra("txt9"));
            txtView6.setText("");
            btnAnswer2.setInputType(1);

        } else if (i == 7 || i == 16 || i == 26 || i == 36) {
        //    Toast.makeText(this, "Demo-test\nCorrect answer is" + intent.getStringExtra("txt7"), Toast.LENGTH_LONG).show();
            btnAnswer1.setText(intent.getStringExtra("txt7"));     //correct answer
            btnAnswer2.setText(intent.getStringExtra("txt1"));
            btnAnswer3.setText(intent.getStringExtra("txt2"));
            txtView7.setText("");
            btnAnswer1.setInputType(1);

        } else if (i == 8 || i == 17 || i == 27 || i == 37) {
        //    Toast.makeText(this, "Demo-test\nCorrect answer is" + intent.getStringExtra("txt8"), Toast.LENGTH_LONG).show();
            btnAnswer1.setText(intent.getStringExtra("txt3"));
            btnAnswer2.setText(intent.getStringExtra("txt4"));
            btnAnswer3.setText(intent.getStringExtra("txt8"));       //correct answer
            txtView8.setText("");
            btnAnswer3.setInputType(1);

        } else if (i == 9 || i == 18 || i == 28 || i == 38) {
        //    Toast.makeText(this, "Demo-test\nCorrect answer is" + intent.getStringExtra("txt9"), Toast.LENGTH_LONG).show();
            btnAnswer1.setText(intent.getStringExtra("txt5"));
            btnAnswer2.setText(intent.getStringExtra("txt9"));       //correct answer
            btnAnswer3.setText(intent.getStringExtra("txt4"));
            txtView9.setText("");
            btnAnswer2.setInputType(1);
        } else
            try {
                throw new Exception("failure");
            } catch (Exception e) {
                e.printStackTrace();
            }


    }

    private void startANewRound() {

        roundExit++;
        playerpoints++;
        roundNumber++;

        txtPoints.setText(playerpoints + " POENG!");

        txtViewR.setText(round + getRoundNumber());
        Intent intent = new Intent(context, StartActivity.class);
        intent.putExtra("roundnumber", roundNumber);
        intent.putExtra("playerpoints", playerpoints);
        intent.putExtra("roundexit", roundExit);

        startActivity(intent);

    }

    private void gameOver() {

        Toast.makeText(this, "GAME OVER", Toast.LENGTH_SHORT).show();

        txtViewR.setText("GAME OVER!");
        btnAnswer1.setText("GAME OVER");
        btnAnswer2.setText("GAME OVER");
        btnAnswer3.setText("GAME OVER");
        btnMenu.setText("GAME OVER");
        btnQuit.setText("GAME OVER");

        txtPoints.setText("GAME OVER");
        txtView1.setText("GAME OVER");
        txtView3.setText("GAME OVER");
        txtView4.setText("GAME OVER");
        txtView5.setText("GAME OVER");
        txtView6.setText("GAME OVER");
        txtView7.setText("GAME OVER");
        txtView8.setText("GAME OVER");
        txtView9.setText("GAME OVER");

    }

    private boolean highscore() {


        return getPlayerscore() > 3;
    }

    public String getTimeNow() {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault());
        setDates(df.format(new Date(System.currentTimeMillis())));

        return dates;
    }

    private void nextIntent() {
        Player player = new Player();
        player.setDate(getTimeNow());
        player.setPlayerpoints(getPlayerscore());

        Toast.makeText(context, "Congratulations you reached the highscore with a total of: " + player.getPlayerpoints() + " POINTS!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, HighInputActivity.class);
        intent.putExtra("playerscore", player.getPlayerpoints());
        intent.putExtra("date", player.getDate());
        startActivity(intent);
    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.btnMenu:
                    gameOver();
                    highscore();
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    break;

                case R.id.btnAnswer1:
                    if (btnAnswer1.getInputType() == 1) {
                        Toast.makeText(context, "Correct!", Toast.LENGTH_SHORT).show();
                        startANewRound();

                    } else if (btnAnswer1.getInputType() == 0 || btnAnswer2.getInputType() == 0 || btnAnswer3.getInputType() == 0) {
                        gameOver();
                        if (highscore()) {
                            nextIntent();
                        } else {
                            gameOver();
                            Intent intent2 = new Intent(context, MainActivity.class);
                            movieController.getListOfAllMovies();
                            startActivity(intent2);
                        }
                    } else {
                        try {
                            throw new Exception("An failure is reported");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case R.id.btnAnswer2:
                    if (btnAnswer2.getInputType() == 1) {
                        Toast.makeText(context, "Correct!", Toast.LENGTH_SHORT).show();
                        startANewRound();

                    } else if (btnAnswer1.getInputType() == 0 || btnAnswer2.getInputType() == 0 || btnAnswer3.getInputType() == 0) {
                        gameOver();
                        if (highscore()) {
                            nextIntent();
                        } else {
                            gameOver();
                            Intent intent3 = new Intent(context, MainActivity.class);
                            movieController.getListOfAllMovies();
                            startActivity(intent3);
                        }

                    } else {
                        try {
                            throw new Exception("An failure is reported");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case R.id.btnAnswer3:
                    if (btnAnswer3.getInputType() == 1) {
                        Toast.makeText(context, "Correct!", Toast.LENGTH_SHORT).show();
                        startANewRound();

                    } else if (btnAnswer1.getInputType() == 0 || btnAnswer2.getInputType() == 0 || btnAnswer3.getInputType() == 0) {
                        gameOver();
                        if (highscore()) {
                            nextIntent();
                        } else {
                            gameOver();
                            Intent intent3 = new Intent(context, MainActivity.class);
                            movieController.getListOfAllMovies();
                            startActivity(intent3);
                        }
                    } else {
                        try {
                            throw new Exception("An failure is reported");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case R.id.btnQuit:
                    finish();
                    System.exit(0);
                    break;
                default:
                    throw new IllegalArgumentException("Error reported:");

            }
        }
    };
    public int getRoundExit() {
        return roundExit;
    }

    public void setRoundExit(int roundExit) {
        this.roundExit = roundExit;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public int getPlayerscore() {
        return playerpoints;
    }

    public void setPlayerpoints(int playerpoints) {
        this.playerpoints = playerpoints;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

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
        Log.d(TAG, "Resume..");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Restart..");
        Intent mStartActivity = new Intent(context, PlayActivity.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        System.exit(0);

    }
}
