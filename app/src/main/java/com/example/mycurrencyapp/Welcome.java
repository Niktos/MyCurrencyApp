package com.example.mycurrencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                {
                    try {
                        sleep(3000);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    } finally {
                        Intent Home = new Intent(Welcome.this, MainActivity.class);
                        startActivity(Home);
                    }
                }
            }
        };

        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }
}
