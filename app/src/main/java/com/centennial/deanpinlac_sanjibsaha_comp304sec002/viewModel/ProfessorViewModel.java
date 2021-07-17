package com.centennial.deanpinlac_sanjibsaha_comp304sec002.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Professor;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.repository.ProfessorRepository;

import java.util.List;

public class ProfessorViewModel extends AndroidViewModel {

    private ProfessorRepository professorRepository;
    private LiveData<Integer> insertResult;
    private LiveData<List<Professor>> allProfessors;

    public ProfessorViewModel(@NonNull Application application) {
        super(application);
        professorRepository = new ProfessorRepository(application);
        insertResult = professorRepository.getInsertResult();
        allProfessors = professorRepository.getAllProfessors();
    }

    public void insert(Professor professor){
        professorRepository.insert(professor);
    }

    public LiveData<Integer>getInsertResult(){
        return insertResult;
    }

    public LiveData<List<Professor>>getAllProfessors(){
        return allProfessors;
    }
}
