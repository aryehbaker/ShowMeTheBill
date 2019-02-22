package com.example.showmethebill.ui.generalworktypeeditor;

import com.example.showmethebill.AppDatabase;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class GeneralWorkTypeEditorVMFactory extends ViewModelProvider.NewInstanceFactory {

    //  two member variables. One for the database and one for the taskId
    private final AppDatabase mDb;
    private final int mTaskId;

    public GeneralWorkTypeEditorVMFactory(AppDatabase database, int taskId) {
        mDb = database;
        mTaskId = taskId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new GeneralWorkTypeEditorViewModel(mDb, mTaskId);
    }
}
