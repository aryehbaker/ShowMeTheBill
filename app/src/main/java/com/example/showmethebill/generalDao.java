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
    @Query("SELECT id, WorkType,updated_at FROM GeneralWorkType")
    LiveData<List<GeneralWorkType>> loadGeneralTypes();

    @Query("SELECT id, WorkType,updated_at FROM GeneralWorkType WHERE ID = :id ")
    LiveData<GeneralWorkType> getAGeneralWorkType(int id);
    @Insert
    void insertGeneralType(GeneralWorkType... generalWorkType);

    @Update
    void updateGeneralType(GeneralWorkType... generalWorkType);

    @Delete
    void  deleteGeneralType(GeneralWorkType... generalWorkType);
}
