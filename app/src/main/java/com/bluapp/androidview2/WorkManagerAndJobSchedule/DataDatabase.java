package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = { WorkData11.class }, version = 1, exportSchema = false)
public abstract class DataDatabase extends RoomDatabase {
    private static final String DB_NAME ="DataDb";
    private static DataDatabase instance;
    public abstract DataDAO dataDAO();

    public synchronized static DataDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, DataDatabase.class, DB_NAME)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }
}