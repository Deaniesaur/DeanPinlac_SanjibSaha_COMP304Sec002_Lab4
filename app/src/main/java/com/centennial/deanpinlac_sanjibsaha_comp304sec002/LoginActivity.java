package com.centennial.deanpinlac_sanjibsaha_comp304sec002;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.res.Resources;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    }

    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}