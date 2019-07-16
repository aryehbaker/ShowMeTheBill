package com.example.showmethebill;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;



@Database(entities = {generalWorkType.class, middleWorkType.class,endWorkType.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static final String LOG_TAG = AppDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "bills";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }


    public abstract generalDao  generalDao();

    public abstract middleDao middleDao();

    public abstract endDao endDao();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    database.execSQL("PRAGMA foreign_keys = OFF;");
                    database.beginTransaction();
                    database.execSQL("DROP TABLE ENDWORKTYPE");
                    database.execSQL("CREATE TABLE ENDWORKTYPE('id' INTEGER NOT NULL PRIMARY KEY,'middleId' INTEGER NOT NULL,'workType'TEXT,'cost'REAL NOT NULL, FOREIGN KEY(middleId)REFERENCES middleWorkType(id)ON DELETE CASCADE )");
                    database.endTransaction();
                    database.execSQL("PRAGMA foreign_keys = ON;");
                }
            });

        }

    };
}
