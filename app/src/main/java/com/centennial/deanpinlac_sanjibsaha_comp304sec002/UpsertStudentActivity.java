package com.centennial.deanpinlac_sanjibsaha_comp304sec002;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Student;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.viewModel.StudentViewModel;

public class UpsertStudentActivity extends AppCompatActivity {
    private StudentViewModel studentViewModel;
    private Student student;
    private String professorId;

    private EditText editFirstName;
    private EditText editLastName;
    private EditText editDepartment;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upsert_student);

        SharedPreferences sharedPreferences = getSharedPreferences("",
                Context.MODE_PRIVATE);
        professorId = sharedPreferences.getString("professorId","");

        editFirstName = findViewById(R.id.editStudentFirstName);
        editLastName = findViewById(R.id.editStudentLastName);
        editDepartment = findViewById(R.id.editStudentDepartment);
        buttonAdd = findViewById(R.id.confirmAddStudent);

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        studentViewModel.getInsertResult().observe(this, integer -> {
            if(integer == 1){
                showMessage("Student successfully saved");
                finish();
            }else{
                showMessage("Error saving student");
            }
        });

        buttonAdd.setOnClickListener((v) -> {
            Student student = new Student();
            student.setProfessorId(professorId);
            student.setFirstName(editFirstName.getText().toString());
            student.setLastName(editLastName.getText().toString());
            student.setDepartment(editDepartment.getText().toString());

            studentViewModel.insert(student);
        });
    }

    private void showMessage(String message){
        Toast.makeText(UpsertStudentActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}