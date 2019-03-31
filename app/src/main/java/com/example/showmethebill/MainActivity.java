package com.example.showmethebill;

import android.content.Intent;
import android.os.Bundle;

import com.example.showmethebill.databinding.ActivityMainBinding;
//import com.example.showmethebill.databinding.ContentMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GeneralAdapter.OnGeneralClickListener {
    private static final String TAG = "main";
    ActivityMainBinding binding;
    private AppDatabase mDb;
    RecyclerView generalRecyclerView,middleRecyclerView,endRecyclerView;
   GeneralAdapter generalAdapter;
   GeneralAdapter.OnGeneralClickListener onGeneralClickListener;
   MiddleAdapter middleAdapter;

   Observer GeneralObserver;
   Observer MiddleObserver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        generalRecyclerView = binding.mcontent.reGeneral;
        generalAdapter = new GeneralAdapter();
        generalAdapter.setOnItemClickListener(onGeneralClickListener);
        middleRecyclerView = binding.mcontent.reMiddle;
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
        generalRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        middleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
         new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
             @Override
             public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                 return false;
             }

             @Override
             public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                 AppExecutors.getInstance().diskIO().execute(new Runnable() {
                     @Override
                     public void run() {
                         int position = viewHolder.getAdapterPosition();
                         List<generalWorkType> tasks = generalAdapter.getGeneralWorkTypeList();
                         mDb.generalDao().deleteGeneralType(tasks.get(position));
                     }
                 });
             }
         }).attachToRecyclerView(generalRecyclerView);
        mDb = AppDatabase.getInstance(getApplicationContext());
        final GeneralWorkTypeViewModel generalWorkTypeViewModel =
                ViewModelProviders.of(this).get(GeneralWorkTypeViewModel.class);
       GeneralObserver = (Observer<List<generalWorkType>>) generalWorkTypes -> {
            if(generalWorkTypes != null && !generalWorkTypes.isEmpty()){
                generalAdapter.setAdaptorList(generalWorkTypes);
                generalRecyclerView.setAdapter(generalAdapter);
            }
        };
        generalWorkTypeViewModel.getGeneralWorkType().observe(this, GeneralObserver);
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

    @Override
    public void onItemClick(View view, generalWorkType item) {
        Log.d(TAG, "onItemClick: "+ item.WorkType);
    }
}
