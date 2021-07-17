package com.centennial.deanpinlac_sanjibsaha_comp304sec002;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.adapter.StudentAdapter;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Student;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.viewModel.StudentViewModel;

public class StudentActivity extends AppCompatActivity {
    private StudentViewModel studentViewModel;

    private Button buttonAddStudent;
    private RecyclerView recyclerStudents;

    String professorId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        buttonAddStudent = findViewById(R.id.buttonAddStudent);
        recyclerStudents = findViewById(R.id.recyclerStudents);

        SharedPreferences sharedPreferences = getSharedPreferences("",
                Context.MODE_PRIVATE);
        professorId = sharedPreferences.getString("professorId","");

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        //Retrieve Student by Professor ID
        studentViewModel.getStudentsByProfessorId().observe(this, students -> {
            for(Student student: students){
                showMessage(student.getFirstName());
            }

            recyclerStudents.setAdapter(new StudentAdapter(students, this));
            recyclerStudents.setLayoutManager(new LinearLayoutManager(this));
        });

        buttonAddStudent.setOnClickListener((v) -> {
            Intent intent = new Intent(this, UpsertStudentActivity.class);
            startActivity(intent);
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