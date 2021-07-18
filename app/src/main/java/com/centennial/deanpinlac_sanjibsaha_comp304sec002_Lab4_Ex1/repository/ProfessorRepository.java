package com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.AppDatabase;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.dao.ProfessorDao;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002_Lab4_Ex1.model.Professor;

import java.util.List;

public class ProfessorRepository {
    private final ProfessorDao professorDao;
    private MutableLiveData<Integer> insertResult = new MutableLiveData<>();
    private LiveData<List<Professor>> professorList;
    private MutableLiveData<Professor> professorResult = new MutableLiveData<>();

    public ProfessorRepository(Context context){
        //Create a database object
        AppDatabase db = AppDatabase.getInstance(context);
        //Create an interface object
        professorDao = db.professorDao();
        //Call interface method
        professorList = professorDao.getAllProfessors();
    }

    //returns query results as LiveData object
    public LiveData<List<Professor>> getAllProfessors(){
        return professorList;
    }

    public LiveData<Integer>getInsertResult(){
        return insertResult;
    }

    public void insert(Professor professor){
        insertAsync(professor);
    }

    private void insertAsync(final Professor professor){
        new Thread(() -> {
            try {
                professorDao.insert(professor);
                insertResult.postValue(1);
            }catch(Exception e){
                insertResult.postValue(0);
            }
        }).start();
    }

    public LiveData<Professor> getProfessorResult(){
        return professorResult;
    }

    public void login(String username, String password){
        new Thread(() -> {
            try {
                Professor professor = professorDao.getProfessorByIdAndPass(username, password);
                professorResult.postValue(professor);
            }catch(Exception e){
                professorResult.postValue(null);
            }
        }).start();
    }
}
