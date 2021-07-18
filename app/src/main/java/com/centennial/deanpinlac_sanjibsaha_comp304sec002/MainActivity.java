package com.centennial.deanpinlac_sanjibsaha_comp304sec002;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("", MODE_PRIVATE);
//        Button buttonLogin = findViewById(R.id.buttonLoginActivity);
//        Button buttonStudent = findViewById(R.id.buttonStudentActivity);
//        Button buttonTest = findViewById(R.id.buttonTestActivity);
//        Button buttonView = findViewById(R.id.buttonViewClassroomActivity);
//
//        buttonLogin.setOnClickListener(v -> {
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
//        });
//
//        buttonStudent.setOnClickListener(v -> {
//            Intent intent = new Intent(this, StudentActivity.class);
//            startActivity(intent);
//        });
//
//        buttonTest.setOnClickListener(v -> {
//            Intent intent = new Intent(this, UpsertClassroomActivity.class);
//            startActivity(intent);
//        });
//
//        buttonView.setOnClickListener(v -> {
//            Intent intent = new Intent(this, ViewClassroomActivity.class);
//            startActivity(intent);
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.optionStudents:
                intent = new Intent(this, StudentActivity.class);
                startActivity(intent);
                break;
            case R.id.optionClassrooms:
                intent = new Intent(this, ViewClassroomActivity.class);
                startActivity(intent);
                break;
            case R.id.optionLogout:
                intent = new Intent(this, LoginActivity.class);
                sharedPreferences.edit().remove("professorId").apply();
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}