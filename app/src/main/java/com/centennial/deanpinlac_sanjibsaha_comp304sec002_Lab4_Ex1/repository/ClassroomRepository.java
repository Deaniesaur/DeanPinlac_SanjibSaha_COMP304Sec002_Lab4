package com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.AppDatabase;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.dao.ClassroomDao;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.model.Classroom;

import java.util.List;

public class ClassroomRepository {
    private final ClassroomDao classroomDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Classroom>> allClasrooms;

    public ClassroomRepository(Context context, int studentId){
        //Create a database object
        AppDatabase db = AppDatabase.getInstance(context);
        //Create an interface object
        classroomDao = db.classroomDao();
        allClasrooms = classroomDao.getAllClassrooms();
    }

    public LiveData<List<Classroom>> getAllClassrooms(){
        return allClasrooms;
    }

    public LiveData<List<Classroom>> getClassroomsByStudentId(int studentId){
        return classroomDao.getClassroomsByStudentId(studentId);
    }

    public LiveData<Integer>getInsertResult(){
        return insertResult;
    }

    public void insert(Classroom classroom){
        insertAsync(classroom);
    }

    private void insertAsync(final Classroom classroom){
        new Thread(() -> {
            try {
                classroomDao.insert(classroom);
                insertResult.postValue(1);
            }catch(Exception e){
                insertResult.postValue(0);
            }
        }).start();
    }

    public void removeClassroomById(int classroomId){
        new Thread(() -> {
            classroomDao.removeClassroomById(classroomId);
        }).start();
    }

    public void updateById(int classroomId, String floor, boolean airConditioned){
        new Thread(() -> {
            classroomDao.updateById(classroomId, floor, airConditioned);
        }).start();
    }

    public void update(Classroom classroom){
        new Thread(() -> {
            classroomDao.update(classroom);
        }).start();
    }
}
