package com.centennial.deanpinlac_sanjibsaha_comp304sec002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonLogin = findViewById(R.id.buttonLoginActivity);
        Button buttonStudent = findViewById(R.id.buttonStudentActivity);
        Button buttonTest = findViewById(R.id.buttonTestActivity);
        Button buttonView = findViewById(R.id.buttonViewClassroomActivity);

        buttonLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        buttonStudent.setOnClickListener(v -> {
            Intent intent = new Intent(this, StudentActivity.class);
            startActivity(intent);
        });

        buttonTest.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestActivity.class);
            startActivity(intent);
        });

        buttonView.setOnClickListener(v -> {
            Intent intent = new Intent(this, ViewClassroomActivity.class);
            startActivity(intent);
        });
    }
}