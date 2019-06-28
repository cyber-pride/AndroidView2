package com.bluapp.androidview2.WorkManagerAndJobSchedule;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface DataDAO {

    @Insert
    public void insert(WorkData11 workData11);

    @Update
    public void update(WorkData11 workData11);

    @Delete
    public void delete(WorkData11 workData11);

    @Query("select * from datas limit 1")
    LiveData<List<WorkData11>> getlimituserList();

}