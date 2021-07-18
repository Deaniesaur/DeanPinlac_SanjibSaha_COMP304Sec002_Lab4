package com.centennial.deanpinlac_sanjibsaha_comp304sec002.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Student;

import java.util.List;

@Dao
public interface StudentDao {
    @Insert
    void insert(Student student);

    //Monitoring Query using Live Data
    @Query("select * from Student order by lastName, firstName")
    LiveData<List<Student>> getAllStudents();

    //Query by professor Id
    @Query("select * from Student where professorId = :professorId order by lastName, firstName")
    LiveData<List<Student>> getStudentsByProfessorId(String professorId);

    //Remove by StudentId
    @Query("delete from Student where studentId = :studentId")
    void removeById(int studentId);

    //Update by StudentId
    @Query("update Student set firstName = :firstName, lastName = :lastName, " +
            "department = :department where studentId = :studentId")
    void updateById(int studentId, String firstName, String lastName, String department);
}
