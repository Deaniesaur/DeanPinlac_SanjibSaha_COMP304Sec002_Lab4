package com.centennial.deanpinlac_sanjibsaha_comp304sec002.viewModel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Classroom;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Student;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.repository.ClassroomRepository;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.repository.StudentRepository;

import java.util.List;

public class ClassroomViewModel extends AndroidViewModel {

    private ClassroomRepository classroomRepository;
    private LiveData<Integer> insertResult;
    private LiveData<List<Classroom>> allClassrooms;
    private LiveData<List<Classroom>> classroomsByStudentId;

    public ClassroomViewModel(@NonNull Application application) {
        super(application);

        SharedPreferences sharedPref = application.getSharedPreferences("",
                Context.MODE_PRIVATE);
        int studentId = sharedPref.getInt("studentId", -1);
        classroomRepository = new ClassroomRepository(application, studentId);
        insertResult = classroomRepository.getInsertResult();
        allClassrooms = classroomRepository.getAllClassrooms();
    }

    public LiveData<List<Classroom>>getAllClassrooms(){
        return allClassrooms;
    }

    public void insert(Classroom classroom){
        classroomRepository.insert(classroom);
    }

    public LiveData<Integer>getInsertResult(){
        return insertResult;
    }

    public LiveData<List<Classroom>> getClassroomsByStudentId(int studentId){
        return classroomRepository.getClassroomsByStudentId(studentId);
    }

    public void removeClassroomById(int classroomId){
        classroomRepository.removeClassroomById(classroomId);
    }

    public void updateClassroomById(int classroomId, String floor, boolean airConditioned){
        classroomRepository.updateById(classroomId, floor, airConditioned);
    }

    public void update(Classroom classroom){
        classroomRepository.update(classroom);
    }
}
