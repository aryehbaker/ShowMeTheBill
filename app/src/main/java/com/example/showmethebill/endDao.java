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
    @Query("SELECT id, middleId, workType, cost FROM EndWorkType"
            + " WHERE MIDDLEID = :id")
    LiveData<List<EndWorkType>>getMatchingMiddleEndTypes(int id);

    @Query("SELECT id, middleId, workType, cost FROM EndWorkType WHERE ID = :id")
    LiveData<EndWorkType> getMatchingEndType(int id);

    @Query("SELECT id, middleId, workType, cost FROM EndWorkType")
    List<EndWorkType>getAllEndTypes();

    @Insert
    void insertEndType(EndWorkType... endWorkType);

    @Update
    void updateEndType(EndWorkType... endWorkType);

    @Delete
    void deleteEndType(EndWorkType... endWorkType);

}
