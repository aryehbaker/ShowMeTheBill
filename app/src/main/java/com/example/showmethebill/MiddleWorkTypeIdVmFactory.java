package com.example.showmethebill;

import com.example.showmethebill.ui.middleworktypeeditor.MiddleWorkTypeEditorViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MiddleWorkTypeIdVmFactory extends ViewModelProvider.NewInstanceFactory {
    private final AppDatabase mDb;


    public MiddleWorkTypeIdVmFactory(AppDatabase database) {
        mDb = database;

    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MiddleWorkTypeIdViewModel(mDb);
    }

}
