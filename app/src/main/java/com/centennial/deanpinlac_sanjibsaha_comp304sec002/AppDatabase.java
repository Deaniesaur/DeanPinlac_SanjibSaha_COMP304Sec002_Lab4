package com.centennial.deanpinlac_sanjibsaha_comp304sec002;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.centennial.deanpinlac_sanjibsaha_comp304sec002.dao.ClassroomDao;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.dao.ProfessorDao;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.dao.StudentDao;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Classroom;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Professor;
import com.centennial.deanpinlac_sanjibsaha_comp304sec002.model.Student;

@Database(entities = {Student.class, Classroom.class, Professor.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
    private static final String DATABASE_NAME = "ClassroomDB";

    public abstract ProfessorDao professorDao();
    public abstract StudentDao studentDao();
    public abstract ClassroomDao classroomDao();

    public static synchronized AppDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase.class,
                    DATABASE_NAME
            ).build();
        }

        return INSTANCE;
    }
}
