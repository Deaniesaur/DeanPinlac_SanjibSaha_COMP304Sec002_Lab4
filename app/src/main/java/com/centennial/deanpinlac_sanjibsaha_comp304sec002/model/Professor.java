package com.centennial.deanpinlac_sanjibsaha_comp304sec002.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Professor {
    @PrimaryKey(autoGenerate = true)
    private int professorId;
    private String firstName;
    private String lastName;
    private String department;
    private String password;

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
