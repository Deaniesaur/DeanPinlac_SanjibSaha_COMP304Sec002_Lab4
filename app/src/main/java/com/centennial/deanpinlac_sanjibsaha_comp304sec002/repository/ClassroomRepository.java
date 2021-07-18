package com.centennial.deanpinlac_sanjibsaha_comp304sec002.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.AppDatabase;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.dao.ClassroomDao;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.dao.StudentDao;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Classroom;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Student;

import java.util.List;

public class ClassroomRepository {
    private final StudentDao studentDao;
    private LiveData<List<Student>> studentList;
    private MutableLiveData<Integer> addClassroomData=new MutableLiveData<>();

    public ClassroomRepository(Context context)
    {
        AppDatabase db = AppDatabase.getInstance(context);
        studentDao = db.studentDao();
        studentList=classroomDao.getStudentData(context.getSharedPreferences("shrf",Context.MODE_PRIVATE).getInt("nid",0));
    }

    public  LiveData<List<Student>> getAllStudent() {
        return studentList;
    }

    public void AddClassroom(final Classroom classroom)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    classroomDao.AddClassroom(classroom);
                    addClassroom.postValue(1);
                }
                catch (Exception e)
                {
                    addClassroom.postValue(0);
                }
            }
        }).start();
    }
    public LiveData<Integer> AddClassroomData(){
        return addClassroomData;
    }

    public LiveData<List<Classroom>> getStudentClassroomData(int sid, int pid)
    {
        return classroomDao.getStudentClassroomData(sid,pid);
    }


}