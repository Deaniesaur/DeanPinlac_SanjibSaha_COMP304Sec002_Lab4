package com.centennial.deanpinlac_sanjibsaha_comp304sec002;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Student;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.viewModel.StudentViewModel;

public class StudentActivity extends AppCompatActivity {
    private StudentViewModel studentViewModel;
    private Student student;

    String professorId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        SharedPreferences sharedPreferences = getSharedPreferences("",
                Context.MODE_PRIVATE);
        professorId = sharedPreferences.getString("professorId","");

        EditText edit = findViewById(R.id.editTextTextPersonName);
        Button button = findViewById(R.id.buttonSave);

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        student = new Student();
        studentViewModel.getInsertResult().observe(this, integer -> {
            if(integer == 1){
                showMessage("Student successfully saved");
            }else{
                showMessage("Error saving student");
            }
        });

        studentViewModel.getAllStudents().observe(this, students -> {
            String output="";
            for(Student student: students){
                output += student.getFirstName() + "\n";
            }

            showMessage(output);
        });

        //Retrieve Student by Professor ID
        studentViewModel.getStudentsByProfessorId().observe(this, students -> {
            for(Student student: students){
                showMessage(student.getFirstName());
            }
        });

        button.setOnClickListener((v) -> {
            student.setFirstName(edit.getText().toString());
            studentViewModel.insert(student);
            showMessage("button save clicked");
            edit.setText("");
        });
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        showMessage(item.toString());
        return super.onOptionsItemSelected(item);
    }

    private void showMessage(String message){
        Toast.makeText(StudentActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}