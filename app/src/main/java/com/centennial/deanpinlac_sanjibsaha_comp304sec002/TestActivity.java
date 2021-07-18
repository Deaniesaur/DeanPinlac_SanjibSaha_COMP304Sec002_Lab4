package com.centennial.deanpinlac_sanjibsaha_comp304sec002;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Classroom;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Student;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.viewModel.ClassroomViewModel;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    EditText floor,isAirconditioned;
    Button submit;
    Spinner spin;
    Classroom classroom;
    ClassroomViewModel classroomViewModel;
    ArrayList<String> par;
    SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setTitle("Add Test");
        floor=findViewById(R.id.floor);
        isAirconditioned=findViewById(R.id.isAirconditioned);
        submit=findViewById(R.id.submit);
        spin=findViewById(R.id.spin);
        classroom=new Classroom();
        sp=getSharedPreferences("shrf",MODE_PRIVATE);
        classroomViewModel= ViewModelProviders.of(this).get(ClassroomViewModel.class);
        par=new ArrayList<>();
        par.add("Select Patient");
        classroomViewModel.getAllStudent().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> result) {
                par.clear();
                par.add("Select Student");
                for(Student person : result) {
                    par.add(person.getStudentId()+"."+person.getFirstName());

                }
            }
        });
        @SuppressWarnings("unchecked")
        ArrayAdapter ad=new ArrayAdapter(TestActivity.this,android.R.layout.simple_list_item_1,par);
        spin.setAdapter(ad);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val;
                if(spin.getSelectedItemPosition()==0) {
                    Toast.makeText(TestActivity.this, "Select the student first", Toast.LENGTH_SHORT).show();
                }
                if(floor.getText().toString().isEmpty())
                {
                    floor.setError("Floor input Required");
                }
                else if(floor.getText().toString().isEmpty())
                {
                    isAirconditioned.setError("Aircondition input Required");
                }
                else {
                    val=spin.getSelectedItem().toString().substring(0,spin.getSelectedItem().toString().indexOf("."));
                    classroom.setProfessorId(sp.getInt("pid",0));
                    classroom.setStudentId(Integer.parseInt(val));
                    classroom.setFloor(floor.getText().toString());
                    classroom.setAirConditioned(isAirconditioned.getText().toString());

                    classroomViewModel.insertClassroom(classroom);

                    classroomViewModel.getStudentClassroomData().observe(TestActivity.this, new Observer<Integer>() {
                        @Override
                        public void onChanged(@Nullable Integer integer) {
                            if(integer==1)
                            {
                                Toast.makeText(TestActivity.this, " Test Added Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else
                            {
                                Toast.makeText(TestActivity.this, "Error in Add Test", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(TestActivity.this,StudentActivity.class);
        startActivity(i);
    }
}