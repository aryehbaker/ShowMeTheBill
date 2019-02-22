package com.example.showmethebill;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class generalWorkType {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String WorkType;

    public generalWorkType(int id, String workType) {
        this.id = id;
        this.WorkType = workType;
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
}
