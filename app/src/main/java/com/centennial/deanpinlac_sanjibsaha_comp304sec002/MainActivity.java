package com.centennial.deanpinlac_sanjibsaha_comp304sec002;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Student;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.viewModel.StudentViewModel;

public class MainActivity extends AppCompatActivity {
    private StudentViewModel studentViewModel;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edit = findViewById(R.id.editTextTextPersonName);
        Button button = findViewById(R.id.buttonSave);

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        student = new Student();
        studentViewModel.getInsertResult().observe(this, integer -> {
            if(integer == 1){
                Toast.makeText(MainActivity.this, "Student successfully saved", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Error saving student", Toast.LENGTH_SHORT).show();
            }
        });

        studentViewModel.getAllStudents().observe(this, students -> {
            String output="";
            for(Student student: students){
                output += student.getFirstName() + "\n";
            }

            showMessage(output);
        });

        button.setOnClickListener((v) -> {
            student.setFirstName(edit.getText().toString());
            studentViewModel.insert(student);
            showMessage("button save clicked");
            edit.setText("Changed");
        });
    }

    private void showMessage(String message){
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}