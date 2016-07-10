package com.haspau.KimsLek;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by paul on 12.05.2015.
 */
public class Player  {

    private int playerId;
    private int playerpoints;
    private String name;
    private String date;

    public String getDate() {

        return date;
    }
    public String getTimeNow(Long date) {
        String dates;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault());
        dates= df.format(new Date(date));
        return dates;
    }

    public void setDate(String date) {
        this.date = date;
    }




    public Player(){

    }

    public Player(int playerId, String name, int playerpoints, String date){
        super();
        this.playerId = playerId;
        this.name = name;
        this.playerpoints = playerpoints;
        this.date = date;
    }
    public Player( String name, int playerpoints, String date){
        super();
        this.name = name;
        this.playerpoints = playerpoints;
        this.date = date;
    }

    public Player(int playerId, String name,int playerpoints ){
        super();
        this.playerId =playerId;
        this.name = name;
        this.playerpoints = playerpoints;
    }

    public Player(String name, int playerpoints){
        super();
        this.name = name;
        this.playerpoints = playerpoints;
    }

    public int getPlayerId() {
        return playerId;
    }
    public String getName(){
        return name;
    }
    public int getPlayerpoints(){
        return playerpoints;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPlayerpoints(int playerpoints){
        this.playerpoints = playerpoints;
    }
    @Override
    public String toString(){
        return "Player [ name " + name + ", score: = " + playerpoints + "]";
    }
}
