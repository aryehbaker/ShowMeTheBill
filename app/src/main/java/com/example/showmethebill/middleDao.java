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

    @Query("SELECT id, workType, cost, generalId FROM MIDDLEWORKTYPE WHERE GENERALID = :id")
    LiveData<List<com.example.showmethebill.middleWorkType>>getMatchingGeneralMiddleTypes(int id);

    @Query("SELECT id, workType, cost, generalId FROM MIDDLEWORKTYPE WHERE ID = :id")
    LiveData<com.example.showmethebill.middleWorkType> getMatchingMiddleTypes(int id);

    @Query("SELECT id, workType, cost, generalId FROM MIDDLEWORKTYPE")
    LiveData<List<com.example.showmethebill.middleWorkType>>getAllMiddleTypes();

    @Insert
    void insertMiddleType(com.example.showmethebill.middleWorkType... middleWorkType);

    @Update
    void updateMiddleType(com.example.showmethebill.middleWorkType... middleWorkType);

    @Delete
    void deleteMiddleType(com.example.showmethebill.middleWorkType... middleWorkType);

}
