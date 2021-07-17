package com.centennial.deanpinlac_sanjibsaha_comp304sec002;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Professor;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Student;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.viewModel.ProfessorViewModel;

public class LoginActivity extends AppCompatActivity {
    private String LOGIN_LABEL;
    private String REGISTER_LABEL;

    private ConstraintLayout layoutLogin;
    private ConstraintLayout layoutRegister;
    private EditText editProfessorId;
    private EditText editFirstName;
    private EditText editLastName;
    private EditText editDepartment;
    private EditText editUsername;
    private EditText editPassword;
    private EditText editConfirmPass;

    private ProfessorViewModel professorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LOGIN_LABEL = getString(R.string.buttonLoginLabel);
        REGISTER_LABEL = getString(R.string.buttonRegisterLabel);

        layoutLogin = findViewById(R.id.layoutLogin);
        layoutRegister = findViewById(R.id.layoutRegister);
        editProfessorId = findViewById(R.id.editProfessorId);
        editFirstName = findViewById(R.id.editFirstName);
        editLastName = findViewById(R.id.editLastName);
        editDepartment = findViewById(R.id.editDepartment);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        editConfirmPass = findViewById(R.id.editConfirmPass);

        Button buttonLogin = findViewById(R.id.buttonLogin);
        TextView labelAlter = findViewById(R.id.textAlter);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layoutLogin);

        labelAlter.setOnClickListener(v -> {
            if(buttonLogin.getText().toString().equals(LOGIN_LABEL)){
                constraintSet.connect(
                        editUsername.getId(),
                        ConstraintSet.TOP,
                        layoutRegister.getId(),
                        ConstraintSet.BOTTOM);
                constraintSet.applyTo(layoutLogin);
                layoutRegister.setVisibility(View.VISIBLE);
                editUsername.setVisibility(View.GONE);
                editConfirmPass.setVisibility(View.VISIBLE);
                buttonLogin.setText(REGISTER_LABEL);
                labelAlter.setText(R.string.alterHaveAccount);
            }else{
                constraintSet.connect(
                        editUsername.getId(),
                        ConstraintSet.TOP,
                        R.id.guideline4,
                        ConstraintSet.BOTTOM);
                constraintSet.applyTo(layoutLogin);
                layoutRegister.setVisibility(View.GONE);
                editUsername.setVisibility(View.VISIBLE);
                editConfirmPass.setVisibility(View.GONE);
                buttonLogin.setText(LOGIN_LABEL);
                labelAlter.setText(R.string.alterNoAccount);
            }
        });

        professorViewModel = new ViewModelProvider(this).get(ProfessorViewModel.class);
        professorViewModel.getInsertResult().observe(this, integer -> {
            if(integer == 1){
                showMessage("Professor successfully saved");
            }else{
                showMessage("Error saving professor");
            }
        });

        professorViewModel.getProfessorResult().observe(this, professor -> {
            if(professor == null){
                showMessage("Authentication Failed");
            }else{
                showMessage("Logging in...");
                Intent intent = new Intent(this, StudentActivity.class);

                SharedPreferences.Editor editor = getSharedPreferences("",
                        Context.MODE_PRIVATE).edit();
                editor.putString("professorId", professor.getProfessorId());
                editor.apply();

                startActivity(intent);
                finish();
            }
        });

        buttonLogin.setOnClickListener(v -> {
            if(buttonLogin.getText().toString().equals(LOGIN_LABEL)){
                //Login
                String username = editUsername.getText().toString();
                String password = editPassword.getText().toString();

                professorViewModel.login(username, password);
            }else{
                //Register
                Professor professor = new Professor();
                professor.setProfessorId(editProfessorId.getText().toString());
                professor.setFirstName(editFirstName.getText().toString());
                professor.setLastName(editLastName.getText().toString());
                professor.setDepartment(editDepartment.getText().toString());
                professor.setPassword(editPassword.getText().toString());

                //Insert to Database
                professorViewModel.insert(professor);
            }
        });

        professorViewModel.getAllProfessors().observe(this, professors -> {
            String professorNames = "";
            for(Professor professor: professors){
                professorNames  += professor.getFirstName() + "\n";
            }

            showMessage(professorNames);
        });
    }

    private void showMessage(String message){
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}