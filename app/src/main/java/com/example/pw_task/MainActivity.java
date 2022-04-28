package com.example.pw_task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Teacher_Details_Fragment()).commit();
    }

    @Override
    public void onBackPressed() {
        //Ensuring no back button quit
    }
}