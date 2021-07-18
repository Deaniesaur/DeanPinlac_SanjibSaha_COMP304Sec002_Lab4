package com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.AppDatabase;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.dao.StudentDao;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.model.Student;

import java.util.List;

public class StudentRepository {
    private final StudentDao studentDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Student>> studentList;
    private LiveData<List<Student>> studentsListByProfessorId;

    public StudentRepository(Context context, String professorId){
        //Create a database object
        AppDatabase db = AppDatabase.getInstance(context);
        //Create an interface object
        studentDao = db.studentDao();
        //Call interface method
        studentList = studentDao.getAllStudents();
        studentsListByProfessorId = studentDao.getStudentsByProfessorId(professorId);
    }

    //returns query results as LiveData object
    public LiveData<List<Student>> getAllStudents(){
        return studentList;
    }

    public LiveData<Integer>getInsertResult(){
        return insertResult;
    }

    public void insert(Student student){
        insertAsync(student);
    }

    private void insertAsync(final Student student){
        new Thread(() -> {
            try {
                studentDao.insert(student);
                insertResult.postValue(1);
            }catch(Exception e){
                insertResult.postValue(0);
            }
        }).start();
    }

    public LiveData<List<Student>> getStudentsByProfessorId(){
        return studentsListByProfessorId;
    }

    public void removeById(int studentId){
        new Thread(() -> {
            studentDao.removeById(studentId);
        }).start();
    }

    public void updateById(int studentId, String firstName, String lastName, String department){
        new Thread(() -> {
            studentDao.updateById(studentId, firstName, lastName, department);
        }).start();
    }
}
