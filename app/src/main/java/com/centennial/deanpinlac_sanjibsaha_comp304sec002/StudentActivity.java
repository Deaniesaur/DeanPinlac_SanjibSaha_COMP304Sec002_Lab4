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
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.adapter.StudentAdapter;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Student;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.utils.Common;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.viewModel.StudentViewModel;

public class StudentActivity extends MainActivity {
    private SharedPreferences sharedPreferences;
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

        sharedPreferences = getSharedPreferences("", Context.MODE_PRIVATE);
        professorId = sharedPreferences.getString("professorId","");

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        //Retrieve Student by Professor ID
        studentViewModel.getStudentsByProfessorId().observe(this, students -> {
            recyclerStudents.setAdapter(new StudentAdapter(students, this));
            recyclerStudents.setLayoutManager(new LinearLayoutManager(this));
        });

        buttonAddStudent.setOnClickListener((v) -> {
            Intent intent = new Intent(this, UpsertStudentActivity.class);
            startActivity(intent);
        });
    }

    public void editStudent(Student student){
        showMessage("Editing");

        sharedPreferences.edit().putString("editStudent", Common.convertToJson(student)).apply();
        Intent intent = new Intent(this, UpsertStudentActivity.class);
        startActivity(intent);
    }

    public void removeStudent(int studentId){
        showMessage("Removing");
        studentViewModel.removeById(studentId);
    }

    private void showMessage(String message){
        Toast.makeText(StudentActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}