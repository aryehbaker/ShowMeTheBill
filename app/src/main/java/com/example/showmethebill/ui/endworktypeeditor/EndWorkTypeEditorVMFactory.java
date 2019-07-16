package com.example.showmethebill.ui.endworktypeeditor;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.showmethebill.AppDatabase;
import com.example.showmethebill.ui.middleworktypeeditor.MiddleWorkTypeEditorViewModel;

public class EndWorkTypeEditorVMFactory extends ViewModelProvider.NewInstanceFactory {
    private final AppDatabase mDb;
    private final int mTaskId;

    public EndWorkTypeEditorVMFactory(AppDatabase database, int taskId) {
        mDb = database;
        mTaskId = taskId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MiddleWorkTypeEditorViewModel(mDb, mTaskId);
    }

}
