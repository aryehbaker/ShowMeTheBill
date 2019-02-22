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
    @Query("SELECT id, middleId, workType, partsCost, laborCost FROM endWorkType"
            + " WHERE MIDDLEID = :id")
    List<middleWorkType>getMatchingMiddleEndTypes(int id);

    @Query("SELECT id, middleId, workType, partsCost, laborCost FROM endWorkType WHERE ID = :id")
    middleWorkType getMatchingEndTypes(int id);

    @Query("SELECT id, middleId, workType, partsCost, laborCost FROM endWorkType")
    List<middleWorkType>getAllEndTypes();

    @Insert
    void insertEndType(endWorkType... endWorkType);

    @Update
    void updateEndType(endWorkType... endWorkType);

    @Delete
    void deleteEndType(endWorkType... endWorkType);

}
