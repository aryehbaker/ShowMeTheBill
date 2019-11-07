package com.example.showmethebill.ui.endworktypeeditor;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.showmethebill.AppDatabase;
import com.example.showmethebill.EndWorkType;

import java.util.List;

public class EndWorkTypeEditorViewModel extends ViewModel {
    private LiveData<List<EndWorkType>> endWorkTypesLiveData;
    private LiveData<EndWorkType> endWorkTypeLiveData;
    public ObservableField<EndWorkType> oWorkType;
    public EndWorkTypeEditorViewModel(){super();}
    public EndWorkTypeEditorViewModel(AppDatabase database, int id) {
        endWorkTypeLiveData = database.endDao().getMatchingEndType(id);
    }


    public LiveData<List<EndWorkType>> getEndWorkTypes() {
        return endWorkTypesLiveData;
    }
    public LiveData<EndWorkType> getEndWorkType() {
        return endWorkTypeLiveData;
    }
    // TODO: Implement the ViewModel
}
