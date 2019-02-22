package com.example.showmethebill;

import androidx.room.Entity;
import androidx.room.ForeignKey;
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
   public float partsCost;
   public float laborCost;


    public endWorkType(int id, int middleId, String workType, float partsCost, float laborCost) {
        this.id = id;
        this.middleId = middleId;
        this.workType = workType;
        this.partsCost = partsCost;
        this.laborCost = laborCost;
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

    public float getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(float partsCost) {
        this.partsCost = partsCost;
    }

    public float getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(float laborCost) {
        this.laborCost = laborCost;
    }
}
