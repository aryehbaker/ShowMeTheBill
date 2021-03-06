package com.example.showmethebill;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = GeneralWorkType.class,
        parentColumns = "id",
        childColumns = "generalId",
        onDelete = CASCADE),
        indices = {@Index("id"), @Index("generalId")}
)

public class MiddleWorkType {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String WorkType;
    public float cost;
    public int generalId;

    @Ignore
    public MiddleWorkType(String WorkType, float cost, int generalId) {
        this.WorkType = WorkType;
        this.cost = cost;
        this.generalId = generalId;
    }

    public MiddleWorkType(int id, String WorkType, float cost, int generalId) {
        this.id = id;
        this.WorkType = WorkType;
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

