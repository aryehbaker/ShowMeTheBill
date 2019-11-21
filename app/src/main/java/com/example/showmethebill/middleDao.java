package com.example.showmethebill;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface middleDao {

    @Query("SELECT id, workType, cost, generalId FROM MiddleWorkType WHERE GENERALID = :id")
    LiveData<List<MiddleWorkType>>getMatchingGeneralMiddleTypes(int id);

    @Query("SELECT id, workType, cost, generalId FROM MiddleWorkType WHERE ID = :id")
    LiveData<MiddleWorkType> getMatchingMiddleType(int id);

    @Query("SELECT id, workType, cost, generalId FROM MiddleWorkType")
    LiveData<List<MiddleWorkType>>getAllMiddleTypes();

    @Insert
    void insertMiddleType(MiddleWorkType... middleWorkType);

    @Update
    void updateMiddleType(MiddleWorkType... middleWorkType);

    @Delete
    void deleteMiddleType(MiddleWorkType... middleWorkType);

}
