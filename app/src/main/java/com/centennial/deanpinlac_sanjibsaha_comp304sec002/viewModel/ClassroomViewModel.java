package com.centennial.deanpinlac_sanjibsaha_comp304sec002.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Student;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.repository.ClassroomRepository;

import java.util.List;

public class ClassroomViewModel {

        private ClassroomRepository classroomRepository;
        private LiveData<List<Student>> studentlist;
        private LiveData<Integer> classroomData;

    public ClassroomViewModel(@NonNull Application application) {
        super(application);
        classroomRepository=new ClassroomRepository(application);
        studentlist=classroomRepository.getAllStudent();
        classroomData=classroomRepository.AddClassroomData();
    }

        public LiveData<List<Student>> getAllStudent(){ return studentlist; }

        public void insertClassroom(Classroom classroom)
        {
            classroomRepository.AddClassroom(classroom);
        }
        public LiveData<Integer> getStudentClassroomData()
        {
            return classroomData;
        }
        public LiveData<List<Classroom>> getStudentClassroomData(int sid, int pid)
        {
            return classroomRepository.getStudentClassroomData(sid,pid);
        }
}
