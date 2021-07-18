package com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.model.Classroom;

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

    //Remove Classroom by Id
    @Query("delete from Classroom where classroomId = :classroomId")
    void removeClassroomById(int classroomId);

    //Update Classroom by Id
    @Query("update Classroom set floor = :floor, airConditioned = :airConditioned where classroomId = :classroomId")
    void updateById(int classroomId, String floor, boolean airConditioned);

    //Update
    @Update
    void update(Classroom classroom);
}
