package com.haspau.KimsLek;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paul on 14.05.2015.
 */
public class PlayerController extends Activity {

    DBAdapter dbAdapter;
    public static final String PLAYER_TABLE = "player";
    public static final String P_PLAYERPOINTS = "playerpoints";
    public static final String P_ID = "p_id";
    public static final String P_NAME = "name";

    List<Player> listOfPlayers = new ArrayList<>();

    public PlayerController() {
        getListOfPlayers();
    }

    public List<Player> getListOfPlayers() {
        return listOfPlayers;
    }

    public void setListOfPlayers(List<Player> listOfPlayers) {

        this.listOfPlayers = listOfPlayers;
    }

    public void getAPlayer() {
        dbAdapter.open();
        Cursor cursor = dbAdapter.getAllP();
        try {

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                String name;
                int points;
                Player player;
                do {

                    name = cursor.getString(cursor.getColumnIndex(DBAdapter.P_NAME));
                    points = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBAdapter.P_PLAYERPOINTS)));
                    player = new Player( name, points);
                    Toast.makeText(this, player.toString(), Toast.LENGTH_SHORT).show();
                    listOfPlayers.add(player);
                } while (cursor.moveToNext());

                dbAdapter.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }



}
