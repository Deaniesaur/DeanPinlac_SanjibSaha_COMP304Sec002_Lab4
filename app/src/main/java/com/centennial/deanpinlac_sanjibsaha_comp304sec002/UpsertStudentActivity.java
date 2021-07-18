package com.centennial.deanpinlac_sanjibsaha_comp304sec002;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Student;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.utils.Common;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.viewModel.StudentViewModel;

public class UpsertStudentActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private StudentViewModel studentViewModel;
    private Student student;
    private String professorId;

    private EditText editStudentId;
    private EditText editProfessorId;
    private EditText editFirstName;
    private EditText editLastName;
    private EditText editDepartment;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upsert_student);

        editStudentId = findViewById(R.id.editStudentId);
        editProfessorId = findViewById(R.id.editStudentProfessor);
        editFirstName = findViewById(R.id.editStudentFirstName);
        editLastName = findViewById(R.id.editStudentLastName);
        editDepartment = findViewById(R.id.editStudentDepartment);
        buttonAdd = findViewById(R.id.confirmStudent);

        sharedPreferences = getSharedPreferences("",
                Context.MODE_PRIVATE);
        professorId = sharedPreferences.getString("professorId","");

        String studentJson = sharedPreferences.getString("editStudent", "");
        if(!studentJson.equals("")){
            //Edit
            student = Common.convertJsonToObject(studentJson, Student.class);
            String displayStudentId = "ID: " + student.getStudentId();

            editStudentId.setVisibility(View.VISIBLE);
            editStudentId.setText(displayStudentId);
            editFirstName.setText(student.getFirstName());
            editLastName.setText(student.getLastName());
            editDepartment.setText(student.getDepartment());
        }else{
            //Add
            editStudentId.setVisibility(View.GONE);
        }
        String displayProfessorId = "ProfessorID: " + professorId;
        editProfessorId.setText(displayProfessorId);

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
            if(student == null){
                insertStudent();
            }else{
                editStudent();
            }
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.edit().remove("editStudent").apply();
    }

    private void insertStudent(){
        Student student = new Student();
        student.setProfessorId(professorId);
        student.setFirstName(editFirstName.getText().toString());
        student.setLastName(editLastName.getText().toString());
        student.setDepartment(editDepartment.getText().toString());

        studentViewModel.insert(student);
    }

    private void editStudent(){
        studentViewModel.updateById(
                student.getStudentId(),
                editFirstName.getText().toString(),
                editLastName.getText().toString(),
                editDepartment.getText().toString());
    }

    private void showMessage(String message){
        Toast.makeText(UpsertStudentActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}