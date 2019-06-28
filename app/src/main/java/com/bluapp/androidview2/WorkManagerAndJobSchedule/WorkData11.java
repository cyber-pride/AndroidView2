package com.bluapp.androidview2.WorkManagerAndJobSchedule;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "datas")
public class WorkData11 {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "data")
    private String data;

    public WorkData11(int id, String data){
        this.id = id;
        this.data = data;
    }

    @Ignore
    public WorkData11(String data){
        this.data = data;
    }
    public int getId() {
        return id;
    }
    public String getData() {
        return data;
    }
}