package com.example.showmethebill;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface generalDao {
    @Query("SELECT id, WorkType FROM GENERALWORKTYPE")
    List<generalWorkType> loadGeneralTypes();

    @Query("SELECT id, WorkType FROM GENERALWORKTYPE WHERE ID = :id ")
    LiveData<generalWorkType> getAGeneralWorkType(int id);
    @Insert
    void insertGeneralType(generalWorkType... generalWorkType);

    @Update
    void updateGeneralType(generalWorkType... generalWorkType);

    @Delete
    void  deleteGeneralType(generalWorkType... generalWorkType);
}
