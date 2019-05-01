package com.example.showmethebill;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.showmethebill.databinding.ActivityMainBinding;
//import com.example.showmethebill.databinding.ContentMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity
        {
    private static final String TAG = MainActivity.class.getSimpleName();
    Context context;
    private enum FabStyle{GENERAL,MIDDLE,END};

    ActivityMainBinding binding;
    private AppDatabase mDb;
    FloatingActionButton fab;
    RecyclerView generalRecyclerView,middleRecyclerView,endRecyclerView;
   GeneralAdapter generalAdapter;
   MiddleAdapter middleAdapter;

   Observer GeneralObserver;
   Observer MiddleObserver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        generalRecyclerView = binding.reGeneral;
        generalAdapter = new GeneralAdapter(this, new GeneralAdapter.OnGeneralClickListener() {
            @Override
            public void onItemClick(View view, generalWorkType item) {
                Log.d(TAG, "onItemClick: "+ item.WorkType);
                Toast.makeText(getApplicationContext(),"Hello Javatpoint", Toast.LENGTH_LONG).show();
                onGeneralItemClick(view,item);
            }
        });
        middleRecyclerView = binding.reMiddle;
       // Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        setFab(FabStyle.GENERAL);

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
                        List<middleWorkType> tasks = middleAdapter.getMiddleWorkTypeList();
                        mDb.middleDao().deleteMiddleType(tasks.get(position));
                    }
                });
            }
        }).attachToRecyclerView(middleRecyclerView);
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


    public void onGeneralItemClick(View view, generalWorkType item) {
        Log.d(TAG, "onItemClick: "+ item.WorkType);
        Toast.makeText(getApplicationContext(),"you clicked "+item.WorkType, Toast.LENGTH_LONG).show();
        setFab(FabStyle.MIDDLE);
        MiddleWorkTypeIdVmFactory factory = new MiddleWorkTypeIdVmFactory(mDb,item.id);
        final MiddleWorkTypeIdViewModel viewModel =
                ViewModelProviders.of(this,factory).get(MiddleWorkTypeIdViewModel.class);
        MiddleObserver = (Observer<List<middleWorkType>>) middleWorkTypes -> {
            if(middleWorkTypes != null && !middleWorkTypes.isEmpty()){
                middleAdapter.setAdaptorList(middleWorkTypes);
                middleRecyclerView.setAdapter(generalAdapter);
            }
            else{
                Intent intent1 = new Intent(MainActivity.this,
                        MiddleWorkTypeEditor.class);
                startActivity(intent1);
            }
        };
        viewModel.getMiddleWorkTypeOfGeneralId().observe(this, MiddleObserver);

    }
    private void setFab(FabStyle FAB){

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String classType;
                switch(FAB){
                    case GENERAL:Intent intent = new Intent(MainActivity.this,
                            GeneralWorkTypeEditor.class);
                    startActivity(intent);
                    break;
                    case MIDDLE:Intent intent1 = new Intent(MainActivity.this,
                            MiddleWorkTypeEditor.class);
                    startActivity(intent1);
                    break;
                    default:Intent intent2 = new Intent(MainActivity.this,
                            GeneralWorkTypeEditor.class);
                        startActivity(intent2);
                }


            }
        });
    }
}
