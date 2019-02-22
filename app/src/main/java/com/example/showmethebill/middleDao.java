package com.example.showmethebill;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface middleDao {

    @Query("SELECT id, workType, cost, generalId FROM MIDDLEWORKTYPE WHERE GENERALID = :id")
    List<middleWorkType>getMatchingGeneralMiddleTypes(int id);

    @Query("SELECT id, workType, cost, generalId FROM MIDDLEWORKTYPE WHERE ID = :id")
    middleWorkType getMatchingMiddleTypes(int id);

    @Query("SELECT id, workType, cost, generalId FROM MIDDLEWORKTYPE")
    List<middleWorkType>getAllMiddleTypes();

    @Insert
    void insertMiddleType(middleWorkType... middleWorkType);

    @Update
    void updateMiddleType(middleWorkType... middleWorkType);

    @Delete
    void deleteMiddleType(middleWorkType... middleWorkType);

}
