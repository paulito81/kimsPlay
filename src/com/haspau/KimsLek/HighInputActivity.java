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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by paul on 18.05.2015.
 */
public class HighInputActivity extends Activity {

    private static final String TAG = StartActivity.class.getSimpleName();
    private Context context = this;
    EditText edtName;
    TextView txtScore;
    Button btnSend;
    DBAdapter dbAdapter;
    int playerscore;
    String dates;
    Player player = new Player();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playerscore);
        Intent intent = getIntent();
        playerscore = getIntent().getExtras().getInt("playerscore");
        initWidgets();
    }

    private void initWidgets() {
        txtScore = (TextView)findViewById(R.id.txtScore);
        edtName =(EditText) findViewById(R.id.edtName);
        btnSend =(Button) findViewById(R.id.btnSend);
        btnSend.setOnClickListener(listener);

        txtScore.setText("Player score: " +playerscore + " points!") ;
        setPlayer();
    }
    private void setPlayer(){
        Intent intent =getIntent();
        playerscore = intent.getIntExtra("playerscore", playerscore);
        dates = intent.getStringExtra("date");


    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

               case R.id.btnSend:

                    Intent intent = new Intent(context,HighscoreActivity.class);
                    intent.putExtra( "name", edtName.getText().toString().toUpperCase());
                    intent.putExtra( "playerscore", playerscore);
                    intent.putExtra( "date", dates);
                    startActivity(intent);
                break;

                default:
                    throw new IllegalArgumentException("Error reported:");


            }
        }
    };
    @Override
    public void onDestroy() {
        super.onDestroy();
        android.os.Debug.stopMethodTracing();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Resume..");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Stopping..");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Pause..");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "Restart..");
        Intent mStartActivity = new Intent(context, HighInputActivity.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId,    mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        System.exit(0);

    }

}
