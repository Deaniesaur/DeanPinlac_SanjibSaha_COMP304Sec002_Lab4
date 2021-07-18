package com.centennial.deanpinlac_sanjibsaha_comp304sec002;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Classroom;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.utils.Common;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.viewModel.ClassroomViewModel;

public class UpsertClassroomActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private Classroom classroom;
    private String professorId;
    private int studentId;

    private EditText editId;
    private EditText editStudentId;
    private EditText editProfessorId;
    private EditText editFloor;
    private Spinner spinnerAirConditioned;
    private Button buttonConfirm;
    private ClassroomViewModel classroomViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upsert_classroom);

        editId = findViewById(R.id.editClassroomId);
        editStudentId = findViewById(R.id.editClassroomStudent);
        editProfessorId = findViewById(R.id.editClassroomProfessor);
        editFloor = findViewById(R.id.editFloor);
        spinnerAirConditioned = findViewById(R.id.spinnerAirConditioned);
        buttonConfirm = findViewById(R.id.confirmClassroom);

        sharedPreferences = getSharedPreferences("", MODE_PRIVATE);
        professorId = sharedPreferences.getString("professorId", "");
        studentId = sharedPreferences.getInt("studentId", -1);

        String classroomJson = sharedPreferences.getString("editClassroom", "");
        if(!classroomJson.equals("")){
            classroom = Common.convertJsonToObject(classroomJson, Classroom.class);

            String displayId = "ID: " + classroom.getClassroomId();
            editId.setVisibility(View.VISIBLE);
            editId.setText(displayId);
            editFloor.setText(classroom.getFloor());
            if(classroom.isAirConditioned()){
                spinnerAirConditioned.setSelection(0);
            }else{
                spinnerAirConditioned.setSelection(1);
            }

            buttonConfirm.setText("Confirm Edit");
        }else{
            editId.setVisibility(View.GONE);
            buttonConfirm.setText("Confirm Add");
        }

        String displayStudentId = "Student ID: " + studentId;
        String displayProfessorId = "Professor ID: " + professorId;
        editStudentId.setText(displayStudentId);
        editProfessorId.setText(displayProfessorId);

        classroomViewModel = new ViewModelProvider(this).get(ClassroomViewModel.class);
        classroomViewModel.getInsertResult().observe(this, result -> {
            if(result == 1) {
                finish();
            }else{
                showMessage("Insert Failed");
            }
        });

        buttonConfirm.setOnClickListener(v -> {
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
        int position = spinnerAirConditioned.getSelectedItemPosition();
        if(position == 0)
            classroom.setAirConditioned(true);
        else
            classroom.setAirConditioned(false);
        classroomViewModel.update(classroom);
    }

    private void showMessage(String message){
        Toast.makeText(UpsertClassroomActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}