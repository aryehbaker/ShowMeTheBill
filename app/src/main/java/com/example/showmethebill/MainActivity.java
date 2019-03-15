package com.example.showmethebill;

import android.content.Intent;
import android.os.Bundle;

import com.example.showmethebill.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    RecyclerView recyclerView;
   GeneralAdapter adapter;
   Observer observer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        recyclerView = binding.reGeneral;
        adapter = new GeneralAdapter();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addGeneralIntent = new Intent(MainActivity.this,
                        GeneralWorkTypeEditor.class);
                startActivity(addGeneralIntent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final GeneralWorkTypeViewModel viewModel =
                ViewModelProviders.of(this).get(GeneralWorkTypeViewModel.class);
        observer = new Observer<List<generalWorkType>>() {
            @Override
            public void onChanged(List<generalWorkType> generalWorkTypes) {
                if(generalWorkTypes != null && !generalWorkTypes.isEmpty()){
                    adapter.setAdaptorList(generalWorkTypes);
                    recyclerView.setAdapter(adapter);
                }
            }
        };
        viewModel.getGeneralWorkType().observe(this, observer);
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
