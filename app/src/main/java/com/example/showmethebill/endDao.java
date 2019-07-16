package com.example.showmethebill;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface endDao {
    @Query("SELECT id, middleId, workType, cost FROM endWorkType"
            + " WHERE MIDDLEID = :id")
    LiveData<List<endWorkType>>getMatchingMiddleEndTypes(int id);

    @Query("SELECT id, middleId, workType, cost FROM endWorkType WHERE ID = :id")
    LiveData<endWorkType> getMatchingEndType(int id);

    @Query("SELECT id, middleId, workType, cost FROM endWorkType")
    List<endWorkType>getAllEndTypes();

    @Insert
    void insertEndType(endWorkType... endWorkType);

    @Update
    void updateEndType(endWorkType... endWorkType);

    @Delete
    void deleteEndType(endWorkType... endWorkType);

}
