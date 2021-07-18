package com.centennial.deanpinlac_sanjibsaha_comp304sec002.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Classroom;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Student;

import java.util.List;

@Dao
public interface ClassroomDao {
    @Insert
    void AddClassroom(Classroom classroom);

    @Query("Select * from patientmodel where nurseid=:nid")
    LiveData<List<Student>> getStudentData(int sid);

    @Query("select * from Classroom where studentId=:sid and professorId=:pid")
    LiveData<List<Classroom>> getStudentClassroomData();
}
