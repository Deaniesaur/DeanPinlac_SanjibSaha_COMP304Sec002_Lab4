package com.centennial.deanpinlac_sanjibsaha_comp304sec002.viewModel;

import android.app.Application;

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

    public StudentViewModel(@NonNull Application application) {
        super(application);
        studentRepository = new StudentRepository(application);
        insertResult = studentRepository.getInsertResult();
        allStudents = studentRepository.getAllStudents();
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
}
