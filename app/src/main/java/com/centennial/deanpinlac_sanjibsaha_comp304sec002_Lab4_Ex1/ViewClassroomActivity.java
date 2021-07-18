package com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.R;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.adapter.ClassroomAdapter;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.model.Classroom;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.model.Student;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.utils.Common;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.viewModel.ClassroomViewModel;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.viewModel.StudentViewModel;

import java.util.List;

public class ViewClassroomActivity extends MainActivity {
    private SharedPreferences sharedPreferences;
    private StudentViewModel studentViewModel;
    private ClassroomViewModel classroomViewModel;

    private Spinner spinnerStudents;
    private RecyclerView recyclerClassrooms;
    private List<Student> students;
    private String professorId;

    private Button buttonAddClassroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_classroom);

        sharedPreferences = getSharedPreferences("", MODE_PRIVATE);
        professorId = sharedPreferences.getString("professorId", "");

        spinnerStudents = findViewById(R.id.spinnerStudent);
        spinnerStudents.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Student student = (Student) spinnerStudents.getSelectedItem();
                int studentId = student.getStudentId();
                sharedPreferences.edit().putInt("studentId", studentId).apply();

                classroomViewModel.getClassroomsByStudentId(studentId).observe( ViewClassroomActivity.this, classrooms -> {
                    for(Classroom classroom: classrooms){

                    };

                    recyclerClassrooms.setAdapter(new ClassroomAdapter(classrooms, ViewClassroomActivity.this ));
                    recyclerClassrooms.setLayoutManager(new LinearLayoutManager(ViewClassroomActivity.this));
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        studentViewModel.getStudentsByProfessorId().observe(this, students -> {
            this.students = students;
            ArrayAdapter<Student> spinnerAdapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_spinner_dropdown_item, this.students);
            spinnerStudents.setAdapter(spinnerAdapter);
        });

        recyclerClassrooms = findViewById(R.id.recyclerClassrooms);

        classroomViewModel = new ViewModelProvider(this).get(ClassroomViewModel.class);

        buttonAddClassroom = findViewById(R.id.buttonAddClassroom);
        buttonAddClassroom.setOnClickListener(v -> {
            Intent intent = new Intent(this, UpsertClassroomActivity.class);
            startActivity(intent);
        });
    }

    public void editClassroom(Classroom classroom){
        sharedPreferences.edit().putString("editClassroom", Common.convertToJson(classroom)).apply();
        Intent intent = new Intent(this, UpsertClassroomActivity.class);
        startActivity(intent);
    }

    public void removeClassroom(int classroomId){
        classroomViewModel.removeClassroomById(classroomId);
    }

    private void showMessage(String message){
        Toast.makeText(ViewClassroomActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}