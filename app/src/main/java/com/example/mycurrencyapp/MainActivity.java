package com.example.mycurrencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Document document;
    private Thread secondThread;
    private Runnable runnable;
    private ListView listView;
    private CustomArrayAdapter arrayAdapter;
    private List<ListItemClass> listItemClasses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        listView = findViewById(R.id.listView);
        listItemClasses = new ArrayList<>();
        arrayAdapter = new CustomArrayAdapter(this, R.layout.list_item, listItemClasses, getLayoutInflater());
        listView.setAdapter(arrayAdapter);
        runnable = new Runnable() {
            @Override
            public void run() {

                GetWeb();
            }
        };

        secondThread = new Thread(runnable);
        secondThread.start();



    }

    private void GetWeb() {

        try {
            document = Jsoup.connect("https://coinmarketcap.com/").get();
            Elements tables = document.getElementsByTag("tbody");
            Element OurTableFull = tables.get(0);




            for (int i = 0; i < 10; i++) {

                ListItemClass itemClass = new ListItemClass();
                itemClass.setData_1(OurTableFull.children().get(i).child(2).text());
                itemClass.setData_2(OurTableFull.children().get(i).child(3).text());
                itemClass.setData_3(OurTableFull.children().get(i).child(4).text());
                itemClass.setData_4(OurTableFull.children().get(i).child(5).text());



                listItemClasses.add(itemClass);

            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    arrayAdapter.notifyDataSetChanged();
                }
            });





            Log.d("MyLog", "Tbody size: " +  OurTableFull.children().get(0).text());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}