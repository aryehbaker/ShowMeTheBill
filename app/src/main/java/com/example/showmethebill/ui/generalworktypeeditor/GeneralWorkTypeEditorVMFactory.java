package com.example.showmethebill.ui.generalworktypeeditor;

import android.app.Application;

import com.example.showmethebill.AppDatabase;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class GeneralWorkTypeEditorVMFactory extends ViewModelProvider.NewInstanceFactory {

    //  two member variables. One for the database and one for the taskId
    private final Application mApplication;
    private final AppDatabase mDb;
    private final int mTaskId;

    public GeneralWorkTypeEditorVMFactory(Application application, int taskId) {
        mApplication = application;
        mDb = AppDatabase.getInstance(mApplication.getApplicationContext());
        mTaskId = taskId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new GeneralWorkTypeEditorViewModel(mApplication,mDb, mTaskId);
    }
}
