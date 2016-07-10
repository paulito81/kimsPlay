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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by paul on 11.05.2015.
 */
public class HighscoreActivity extends Activity {


    private static final String TAG = StartActivity.class.getSimpleName();
    private Context context = this;
    HashMap<Integer, String> sortedListOfPlayers = new HashMap<>();


    //Listview
    private List<String> listItems = new ArrayList<>();
    private List<String> listItems2 = new ArrayList<>(100);
    private List<Player> playerList = new ArrayList<>();
    private PlayerController player = new PlayerController();
    //init widgets
    private Button btnMenu;
    ListView listView;
    public DBAdapter dbAdapter;
    //Player details
    String name;
    String date;
    int playerscore;


    @Override
    public void onCreate(Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        getApplicationContext();
        setContentView(R.layout.highscore);
        initWidgets();

        dbAdapter = new DBAdapter(this);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        playerscore = intent.getIntExtra("playerscore", playerscore);
        date = intent.getStringExtra("date");

        insertPlayerIntoHighScore(name, playerscore, date);
        getAllPlayers();
        sortMap(getSortedListOfPlayers());
        listView.getContext();

    }

    public void initWidgets() {
        listView = (ListView) findViewById(R.id.listView);
        btnMenu = (Button) findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(listener);


    }
    private void insertPlayerIntoHighScore(String name, int playerscore, String date) {

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();
        dbAdapter.insert2(new Player(name, playerscore, date));
        dbAdapter.close();
    }
    private void getAllPlayers() throws NullPointerException{

        dbAdapter.open();
        Cursor cursor = dbAdapter.getAllP();
        if(cursor.getCount() >0){
            cursor.moveToFirst();
            int id;
            String name;
            int playerpoints;
            String date ;
            Player player;

            //INSERT A NEW PLAYER INTO THE HIGHSCORE
            do{
                id = cursor.getInt( cursor.getColumnIndex(DBAdapter.P_ID));
                name = cursor.getString(cursor.getColumnIndex(DBAdapter.P_NAME));
                playerpoints = cursor.getInt( cursor.getColumnIndex(DBAdapter.P_PLAYERPOINTS));
                date = cursor.getString( cursor.getColumnIndex(DBAdapter.P_Date));
                player = new Player( id, name, playerpoints,date);

                if(player.getDate() != null || player.getName() != null || player.getPlayerpoints() !=0 ) {
                        sortedListOfPlayers.put(playerpoints, name + " :  " + date);
                }
               /* if(player.getDate() != null || player.getName() != null || player.getPlayerpoints() !=0 ) {
                    insertDataIntoListView(player.getName(), player.getPlayerpoints(), player.getDate());
                }
*/
            }while ( cursor.moveToNext()  );
        }

        dbAdapter.close();
    }
    private void sortMap(Map<Integer, String > map) {
        for(Map.Entry<Integer, String> entry : map.entrySet()){
            insertSortedDataIntoList(entry.getKey(), entry.getValue());
            listItems2.add(entry.getValue()+ " Poeng: " + entry.getKey() );
            String[] items;
            items = listItems2.toArray(new String[listItems2.size()]);
            listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));
        }
    }
    public void insertSortedDataIntoList(int score, String name){
        listItems.add(name + "( "  + score +": points )"   );
        String[] items;
        items = listItems.toArray(new String[listItems.size()]);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));

    }
    public HashMap<Integer, String> getSortedListOfPlayers() {
        return sortedListOfPlayers;
    }

    public void setSortedListOfPlayers(HashMap<Integer, String> sortedListOfPlayers) {
        this.sortedListOfPlayers = sortedListOfPlayers;
    }

    public void insertDataIntoListView( String name, int points, String date) {

        listItems.add(name + "\t\t\t\t ( "  + points +") points\t\t\t " + date  );

        String[] items;
        items = listItems.toArray(new String[listItems.size()]);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));

    }



    public void getListView() {
        listView.getContext();

    }

    private void insertIntoListView() {

        listItems.add(name + "\t\t: " + playerscore + " points!\t\t\t " + getTimeNow());
        String[] items;
        items = listItems.toArray(new String[listItems.size()]);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));

    }

    public String getTimeNow() {
        String dates;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault());
        dates = df.format(new Date(System.currentTimeMillis()));
        return dates;
    }


    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.btnMenu:
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
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
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Restart..");
        Intent mStartActivity = new Intent(context, HighscoreActivity.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(context, mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        System.exit(0);

    }

}