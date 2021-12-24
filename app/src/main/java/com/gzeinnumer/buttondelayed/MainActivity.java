package com.gzeinnumer.buttondelayed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gzeinnumer.buttondelayed.ui.FullExampleActivity;
import com.gzeinnumer.buttondelayed.ui.SimpleExampleActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_full).setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), FullExampleActivity.class));
        });

        findViewById(R.id.btn_simple).setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), SimpleExampleActivity.class));
        });
    }
}