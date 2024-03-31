package com.example.assgiment1note;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class maintwo extends AppCompatActivity {



    Button back  ;
    private CompletedTasksAdapter completedTasksAdapter;

   // private ArrayAdapter<Task> completedTasksAdapter;
    private ArrayList<Task> taskListcomp = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitytwo);

        ListView completedTasksListView = findViewById(R.id.listview_done);
        back = findViewById(R.id.btn_back) ;

        loadTasksFromSharedPreferences();
        completedTasksAdapter = new CompletedTasksAdapter(this, taskListcomp);
        completedTasksListView.setAdapter(completedTasksAdapter);

        Task completedTask = (Task) getIntent().getSerializableExtra("completedTask");
        if (completedTask != null) {
            taskListcomp.add(completedTask);
            completedTasksAdapter.notifyDataSetChanged();
            saveTasksToSharedPreferences();
        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(maintwo.this, MainActivity.class) ;
                startActivity(intent);
                finish() ;
            }


        });





    }


    Gson gsoN = new Gson();
    public void saveTasksToSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("TasksdonePref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        String tasksJson = gsoN.toJson(taskListcomp);

        editor.putString("task", tasksJson);
        editor.apply();
    }


    private void loadTasksFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("TasksdonePref", Context.MODE_PRIVATE);
        String tasksJson = sharedPreferences.getString("task", "");


        Type type = new TypeToken<ArrayList<Task>>() {}.getType();
        taskListcomp = gsoN.fromJson(tasksJson, type);


        if (taskListcomp == null) {
            taskListcomp = new ArrayList<>();
        }
    }




}
