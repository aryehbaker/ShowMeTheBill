package com.example.showmethebill;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;
@Entity (foreignKeys = @ForeignKey(entity = middleWorkType.class,
        parentColumns = "id",
        childColumns = "middleId",
        onDelete = CASCADE))
public class endWorkType {
    @PrimaryKey(autoGenerate = true)
   public int id;
   public int middleId;
   public String workType;
   public float cost;


    public endWorkType(int id, int middleId, String workType, float cost) {
        this.id = id;
        this.middleId = middleId;
        this.workType = workType;
        this.cost = cost;

    }
    @Ignore
    public endWorkType( int middleId,String workType,Float cost){
        this.middleId = middleId;
        this.workType = workType;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMiddleId() {
        return middleId;
    }

    public void setMiddleId(int middleId) {
        this.middleId = middleId;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }


    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getCost() {
        return cost;
    }

 }
