package com.centennial.deanpinlac_sanjibsaha_comp304sec002.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Professor;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Student;

import java.util.List;

@Dao
public interface ProfessorDao {
    @Insert
    void insert(Professor professor);

    //Monitoring Query using Live Data
    @Query("select * from Professor order by lastName, firstName")
    LiveData<List<Professor>> getAllProfessors();

    //Query by ProfessorId
    @Query("select * from Professor where professorId = :professorId and password = :password")
    Professor getProfessorByIdAndPass(String professorId, String password);
}
