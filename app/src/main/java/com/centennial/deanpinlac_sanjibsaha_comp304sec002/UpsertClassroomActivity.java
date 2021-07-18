package com.centennial.deanpinlac_sanjibsaha_comp304sec002;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Classroom;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Student;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.utils.Common;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.viewModel.ClassroomViewModel;

public class UpsertClassroomActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private Classroom classroom;
    private String professorId;
    private int studentId;

    private EditText editFloor;
    private EditText editAir;
    private Button buttonConfirmAdd;
    private ClassroomViewModel classroomViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upsert_classroom);

        editFloor = findViewById(R.id.editFloor);
        editAir = findViewById(R.id.editAirConditioned);

        sharedPreferences = getSharedPreferences("", MODE_PRIVATE);
        professorId = sharedPreferences.getString("professorId", "");
        studentId = sharedPreferences.getInt("studentId", -1);

        String classroomJson = sharedPreferences.getString("editClassroom", "");
        if(!classroomJson.equals("")){
            classroom = Common.convertJsonToObject(classroomJson, Classroom.class);
            editFloor.setText(classroom.getFloor());
            editAir.setText(String.valueOf(classroom.isAirConditioned()));
        }

        classroomViewModel = new ViewModelProvider(this).get(ClassroomViewModel.class);
        classroomViewModel.getInsertResult().observe(this, result -> {
            if(result == 1) {
                finish();
            }else{
                showMessage("Insert Failed");
            }
        });
        buttonConfirmAdd = findViewById(R.id.confirmAddClassroom);
        buttonConfirmAdd.setOnClickListener(v -> {
            if(classroom == null){
                insertClassroom();
            }else{
                editClassroom();
            }
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.edit().remove("editClassroom").apply();
    }

    private void insertClassroom(){
        Classroom classroom = new Classroom();
        classroom.setStudentId(studentId);
        classroom.setProfessorId(professorId);
        classroom.setFloor(editFloor.getText().toString());
        classroom.setAirConditioned(true);

        classroomViewModel.insert(classroom);
    }

    private void editClassroom(){
        classroom.setFloor(editFloor.getText().toString());
        classroom.setAirConditioned(true);
        classroomViewModel.update(classroom);
    }

    private void showMessage(String message){
        Toast.makeText(UpsertClassroomActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}