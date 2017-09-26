package com.example.avi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mInfoTextView = (TextView) findViewById(R.id.textView);

        // Запускаем свой IntentService
        Intent intentMyIntentService = new Intent(this, ConnectIntentService.class);

        startService(intentMyIntentService.putExtra("time", 3).putExtra("task",
                "Погладить кота"));
        startService(intentMyIntentService.putExtra("time", 1).putExtra("task",
                "Покормить кота"));
        startService(intentMyIntentService.putExtra("time", 4).putExtra("task",
                "Поиграть с котом"));
    }
}
