package com.example.showmethebill;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.showmethebill.ui.endworktypeeditor.EndWorkTypeEditorFragment;
import com.example.showmethebill.ui.middleworktypeeditor.MiddleWorkTypeEditorFragment;

public class EndWorkTypeEditor extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_work_type_editor_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, EndWorkTypeEditorFragment.newInstance())
                    .commitNow();
        }
    }
}
