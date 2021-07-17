package com.centennial.deanpinlac_sanjibsaha_comp304sec002.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Classroom {
    @PrimaryKey(autoGenerate = true)
    private int classroomId;
    private int studentId;
    private String professorId;
    private String floor;
    private boolean airConditioned;

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public boolean isAirConditioned() {
        return airConditioned;
    }

    public void setAirConditioned(boolean airConditioned) {
        this.airConditioned = airConditioned;
    }
}
