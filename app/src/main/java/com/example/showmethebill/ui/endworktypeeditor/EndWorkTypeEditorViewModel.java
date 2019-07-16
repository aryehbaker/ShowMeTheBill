package com.example.showmethebill.ui.endworktypeeditor;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.showmethebill.AppDatabase;
import com.example.showmethebill.endWorkType;
import com.example.showmethebill.middleWorkType;

import java.util.List;

public class EndWorkTypeEditorViewModel extends ViewModel {
    private LiveData<List<endWorkType>> endWorkTypesLiveData;
    private LiveData<endWorkType> endWorkTypeLiveData;
    public ObservableField<endWorkType> oWorkType;
    public EndWorkTypeEditorViewModel(){super();}
    public EndWorkTypeEditorViewModel(AppDatabase database, int id) {
        endWorkTypeLiveData = database.endDao().getMatchingEndType(id);
    }


    public LiveData<List<endWorkType>> getEndWorkTypes() {
        return endWorkTypesLiveData;
    }
    public LiveData<endWorkType> getEndWorkType() {
        return endWorkTypeLiveData;
    }
    // TODO: Implement the ViewModel
}
