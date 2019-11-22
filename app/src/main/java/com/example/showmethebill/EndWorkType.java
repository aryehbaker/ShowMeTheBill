package com.example.showmethebill;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = MiddleWorkType.class,
        parentColumns = "id",
        childColumns = "middleId",
        onDelete = CASCADE),
        indices = {@Index("middleId")})
public class EndWorkType {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int middleId;
    public String workType;
    public float cost;


    public EndWorkType(int id, int middleId, String workType, float cost) {
        this.id = id;
        this.middleId = middleId;
        this.workType = workType;
        this.cost = cost;

    }

    @Ignore
    public EndWorkType(int middleId, String workType, float cost) {
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
