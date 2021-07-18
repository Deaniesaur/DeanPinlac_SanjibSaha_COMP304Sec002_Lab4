package com.centennial.deanpinlac_sanjibsaha_comp304sec002.viewModel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Student;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.repository.StudentRepository;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {

    private StudentRepository studentRepository;
    private LiveData<Integer> insertResult;
    private LiveData<List<Student>> allStudents;
    private LiveData<List<Student>> studentsByProfessorId;

    public StudentViewModel(@NonNull Application application) {
        super(application);

        SharedPreferences sharedPref = application.getSharedPreferences("",
                Context.MODE_PRIVATE);
        String professorId = sharedPref.getString("professorId", "");
        studentRepository = new StudentRepository(application, professorId);
        insertResult = studentRepository.getInsertResult();
        allStudents = studentRepository.getAllStudents();
        studentsByProfessorId = studentRepository.getStudentsByProfessorId();
    }

    public void insert(Student student){
        studentRepository.insert(student);
    }

    public LiveData<Integer>getInsertResult(){
        return insertResult;
    }

    public LiveData<List<Student>>getAllStudents(){
        return allStudents;
    }

    public LiveData<List<Student>> getStudentsByProfessorId(){
        return studentsByProfessorId;
    }

    public void removeById(int studentId){
        studentRepository.removeById(studentId);
    }

    public void updateById(int studentId, String firstName, String lastName, String department){
        studentRepository.updateById(studentId, firstName, lastName, department);
    }
}
