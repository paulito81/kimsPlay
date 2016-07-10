package com.haspau.KimsLek;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by paul on 12.05.2015.
 */
public class DBAdapter {

    private static final String TAG = StartActivity.class.getSimpleName();

    //DB VER
    public static final String DB_NAME = "kims_play_db";
    public static final int DB_VERSION = 1;
    //PLAYER
    public static final String PLAYER_TABLE = "player";
    public static final String P_PLAYERPOINTS = "playerpoints";
    public static final String P_ID = "p_id";
    public static final String P_NAME = "name";
    public static final String P_Date ="p_created";
    //MOVIE
    public static final String MOVIE_TABLE_NAME = "movie";
    public static final String M_ID = "m_id";
    public static final String M_NAME = "movie_title";
    //SQL DB
    private static final String CREATE_PLAYER_SQL = "CREATE TABLE " + PLAYER_TABLE + "(" + P_ID +" integer primary key autoincrement, " +P_NAME + " text, " + P_PLAYERPOINTS + " text,  " +  P_Date + " DEFAULT CURRENT_TIMESTAMP)";
    private static final String CREATE_MOVIE_TABLE_SQL = "CREATE TABLE " + MOVIE_TABLE_NAME + "(" + M_ID +" integer primary key autoincrement,  " + M_NAME + " text)";

    //DELETE EMPTY
    private static final String DELETE_EMPTY_SQL = "DELETE FROM "+ PLAYER_TABLE + "\n" + " WHERE MY_COLUMN IS NULL\n" + " OR TRIM(MY_COLUMN) = ''";
    private static final String DELETE_WHERE_SQL = " WHERE MY_COLUMN IS NULL\n";
    private static final String DELETE_TRIM_SQL = " OR TRIM(MY_COLUMN) = ''";


    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public DBAdapter(Context context) {
        this.context = context;
        dbHelper = new DBHelper();
    }

    /**
     * Open connection to DB
     */
    public void open() {
        try {

            database = dbHelper.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Close connection to DB
     */
    public void close() {
        try {



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.close();
        }
    }
    //
    //MOVIES
    //
    /**
     * Insert into listOfAllMovies DB
     * @param movie new movie
     * @return listOfAllMovies
     */
    public long insert(Movie movie){
        ContentValues values =new ContentValues();
        values.put(M_NAME, movie.getName());
        return database.insert(MOVIE_TABLE_NAME, null, values);
    }


    /**
     * Get all Movies
     * @return cursor
     */
    public Cursor getAllM() {
        return database.query(MOVIE_TABLE_NAME, null, null, null, null, null, null);
    }


    /**
     * Delete a movie
     * @param movieId delete by id
     * @return a empty movie
     */
    public int deleteM(long movieId) {
        return database.delete(MOVIE_TABLE_NAME, M_ID + " = ?", new String[]{movieId + ""});
    }

    /**
     * Get a movie by ID
     * @param movieId get by ID
     * @return matching movies
     */
    public Cursor getMovieById(long movieId) {
        Cursor cursor = database.query(MOVIE_TABLE_NAME, null, M_ID + "= ?", new String[]{movieId + ""}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    //
    //PLAYERS
    //
    /**
     * Get all Players
     *
     * @return a database query with the result
     */
    public Cursor getAllP() {
        return database.query(PLAYER_TABLE, null, null, null, null, null, null);
    }

    /**
     * Update a player
     *
     * @param p_NAME playername
     * @param player Player
     * @return int
     */
    public int update(String p_NAME, Player player) {
        ContentValues values = new ContentValues();
        values.put(P_NAME, player.getName());
        values.put(P_PLAYERPOINTS, player.getPlayerpoints());
        values.put(P_Date, player.getDate());
        return database.update(PLAYER_TABLE, values, P_NAME + " = ?" , new String[]{p_NAME + ""});
    }

    /**
     * Delete a player
     * @param p_NAME delete by name
     * @return a empty player
     */

    public int deleteP(String p_NAME) {
        return database.delete(PLAYER_TABLE, P_NAME + " = ?", new String[]{P_NAME + ""});
    }
    public int delete2() {
        return database.delete(PLAYER_TABLE, null, null);
    }

    /**
     * Insert a player
     * @param player name and player points
     * @return values
     */
    public long insert(Player player, String name, int points, String p_Date) {
        ContentValues values = new ContentValues();
        values.put(P_NAME, player.getName());
        values.put(P_PLAYERPOINTS, player.getPlayerpoints());
        values.put(P_Date, player.getDate());
        return database.insert(PLAYER_TABLE, null, values);
    }

    public long insert(Player player){
        ContentValues values =new ContentValues();
        values.put(P_NAME, player.getName());
        values.put(P_PLAYERPOINTS, player.getPlayerpoints());
     //   values.put(P_Date, player.getDate());
        return database.insert(PLAYER_TABLE, null, values);
    }
    public long insert2(Player player){
        ContentValues values =new ContentValues();
        values.put(P_NAME, player.getName());
        values.put(P_PLAYERPOINTS, player.getPlayerpoints());
        values.put(P_Date, player.getDate());
        return database.insert(PLAYER_TABLE, null, values);
    }


    /**
     * Get a player by ID
     * @param p_NAME get by name
     * @return matching players
     */
    public Cursor getPlayerByName(String p_NAME) {
        Cursor cursor = database.query(PLAYER_TABLE, null, P_NAME + "=?", new String[]{p_NAME + ""}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor getPlayerByName3(String p_NAME) {
        Cursor cursor = database.query(PLAYER_TABLE, null, P_NAME + "=?", new String[]{p_NAME + ""}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public String getPlayerByName2(String p_NAME){
        Cursor cursor = database.query(PLAYER_TABLE, null, P_NAME + "=?", new String[]{p_NAME + ""}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        assert cursor != null;
        cursor.close();
        return p_NAME;
    }


    //INTERN HELPER CLASS
    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper() {

            super(context, DB_NAME, null, DB_VERSION);
        }


        /**
         * CREATE new tables
         * @param db create tables
         */
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_MOVIE_TABLE_SQL);
            db.execSQL(CREATE_PLAYER_SQL);
        }

        /**
         * Upgrade tables
         * @param db update database
         * @param oldVersion previous database version
         * @param newVersion a new database version
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + PLAYER_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + MOVIE_TABLE_NAME);
            onCreate(db);
        }
    }

}
