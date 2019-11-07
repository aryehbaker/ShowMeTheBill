package com.example.showmethebill;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity (indices = {@Index("id")})
public class GeneralWorkType {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String WorkType;
    @ColumnInfo(name = "updated_at")
    public Date updatedAt;

    public GeneralWorkType(int id, String WorkType, Date updatedAt) {
        this.id = id;
        this.WorkType = WorkType;
        this.updatedAt = updatedAt;
    }
    @Ignore
    public GeneralWorkType(String WorkType, Date updatedAt) {
        this.WorkType = WorkType;
        this.updatedAt = updatedAt;
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
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
