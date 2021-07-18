package com.centennial.deanpinlac_sanjibsaha_comp304sec002.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.AppDatabase;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.dao.ClassroomDao;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Classroom;

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

}
