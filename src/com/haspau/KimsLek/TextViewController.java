package com.haspau.KimsLek;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by paul on 14.05.2015.
 */
public class TextViewController {

    private Player player = new Player();
    List<String> listOfViews = Collections.emptyList();
    String movietitle;

    // POINTS
    String setText;
    TextView txtPoints;
    TextView txtViewR;

    TextView txtViewR0;

    //Movie choices
    TextView txtView1;
    TextView txtView2;
    TextView txtView3;
    TextView txtView4;
    TextView txtView5;
    TextView txtView6;
    TextView txtView7;
    TextView txtView8;
    TextView txtView9;
    // HIGHSCORE
    private ListView listView;
    private List<String> listItems = new ArrayList<>();


    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public List<String> getListItems() {
        return listItems;
    }

    public void setListItems(List<String> listItems) {
        this.listItems = listItems;
    }

    public void insertDataIntoListView(String name, int playerscore, Long date) {

        listItems.add(name + "\t\t: " + playerscore + " points!\t\t\t " + player.getTimeNow(date));
        String[] items;
        items = listItems.toArray(new String[listItems.size()]);
        //listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));

    }


    public TextViewController() {
        super();
    }


    public List<String> getListOfViews() {
        return listOfViews;
    }

    public TextView getTxtView9() {
        return txtView9;
    }

    public void setTxtView9(TextView txtView9) {
        this.txtView9 = txtView9;
    }

    public TextView getTxtPoints() {
        return txtPoints;
    }

    public void setTxtPoints(TextView txtPoints) {
        this.txtPoints = txtPoints;
    }

    public TextView getTxtViewR() {
        return txtViewR;
    }

    public void setTxtViewR(TextView txtViewR) {
        this.txtViewR = txtViewR;
    }

    public TextView getTxtView1() {
        return txtView1;
    }

    public void setTxtView1(TextView txtView1) {
        this.txtView1 = txtView1;
    }

    public TextView getTxtView2() {
        return txtView2;
    }

    public void setTxtView2(TextView txtView2) {
        this.txtView2 = txtView2;
    }

    public TextView getTxtView3() {
        return txtView3;
    }

    public void setTxtView3(TextView txtView3) {
        this.txtView3 = txtView3;
    }

    public TextView getTxtView4() {
        return txtView4;
    }

    public void setTxtView4(TextView txtView4) {
        this.txtView4 = txtView4;
    }

    public TextView getTxtView5() {
        return txtView5;
    }

    public void setTxtView5(TextView txtView5) {
        this.txtView5 = txtView5;
    }

    public TextView getTxtView6() {
        return txtView6;
    }

    public void setTxtView6(TextView txtView6) {
        this.txtView6 = txtView6;
    }

    public TextView getTxtView7() {
        return txtView7;
    }

    public void setTxtView7(TextView txtView7) {
        this.txtView7 = txtView7;
    }

    public TextView getTxtView8() {
        return txtView8;
    }

    public void setTxtView8(TextView txtView8) {
        this.txtView8 = txtView8;
    }

    public TextViewController(List<String> listOfViews) {
        this.listOfViews = listOfViews;
    }


    public String getSetText() {
        return setText;
    }

    public void setSetText(String setText) {
        this.setText = setText;
    }

    public void initTextViews() {

    }


}
