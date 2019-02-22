package com.example.showmethebill.ui.generalworktypeeditor;

import com.example.showmethebill.AppDatabase;
import com.example.showmethebill.generalWorkType;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class GeneralWorkTypeEditorViewModel extends ViewModel {
    private LiveData<generalWorkType> workType;

       public GeneralWorkTypeEditorViewModel(AppDatabase database, int Id) {
        workType = database.myDao().getAGeneralWorkType(Id);
    }

    public LiveData<generalWorkType> getGeneralWorkType() {
        return workType;
    }
}
