package com.comp4905.jobarc.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.comp4905.jobarc.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnJobseeker).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, AccountTypeRegisterActivity.class)));

        findViewById(R.id.btnEmployer).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, LoginActivity.class)));

    }

}