package com.example.showmethebill;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity (foreignKeys = @ForeignKey(entity = generalWorkType.class,
                                        parentColumns = "id",
                                        childColumns = "generalId",
                                        onDelete = CASCADE)
        )
public class middleWorkType {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String WorkType;
    public float cost;
    public int generalId;


    public middleWorkType(int id, String workType, float cost, int generalId) {
        this.id = id;
        this.WorkType = workType;
        this.cost = cost;
        this.generalId = generalId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkType() {
        return WorkType;
    }

    public void setWorkType(String workType) {
        WorkType = workType;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getGeneralId() {
        return generalId;
    }

    public void setGeneralId(int generalId) {
        this.generalId = generalId;
    }
}

