package com.centennial.deanpinlac_sanjibsaha_comp304sec002.dao;

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
    void insert(Classroom classroom);

    //Monitoring Query using Live Data
    @Query("select * from Classroom")
    LiveData<List<Classroom>> getAllClassrooms();

    //Query Classrooms using StudentId
    @Query("select * from Classroom where studentId = :studentId order by classroomId")
    LiveData<List<Classroom>> getClassroomsByStudentId(int studentId);
}
