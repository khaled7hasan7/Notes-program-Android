package com.example.assgiment1note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity     {

    private ArrayList<Task> taskList = new ArrayList<>();
    private TasksAdapter tasksAdapter;
    private Button btn_addTask ,donetask ;
    private EditText ed_task ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView tasksListView = findViewById(R.id.tasksListView);
        btn_addTask=findViewById(R.id.btn_add) ;
        ed_task= findViewById(R.id.ed_task) ;
        donetask = findViewById(R.id.taskisdone) ;

        loadTasksFromSharedPreferences();
        tasksAdapter = new TasksAdapter(this, taskList);
        tasksListView.setAdapter(tasksAdapter);

        donetask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, maintwo.class) ;
                startActivity(intent);


            }
        });


        btn_addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_task ;
                str_task =ed_task.getText().toString() ;
                if (!str_task.equals("")) {


                    Task new_task = new Task();
                    new_task.setName(str_task);

                    taskList.add(new_task);
                    tasksAdapter.notifyDataSetChanged();
                    saveTasksToSharedPreferences();

                    ed_task.setText("");
                }

            }
        });






    }



        Gson gson = new Gson();
        public void saveTasksToSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("TasksPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        String tasksJson = gson.toJson(taskList);

        editor.putString("tasks", tasksJson);
        editor.apply();
    }


    private void loadTasksFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("TasksPref", Context.MODE_PRIVATE);
        String tasksJson = sharedPreferences.getString("tasks", "");


        Type type = new TypeToken<ArrayList<Task>>() {}.getType();
        taskList = gson.fromJson(tasksJson, type);

        if (taskList == null) {
            taskList = new ArrayList<>();
        }
    }



    public void deleteTask(Task deletedTask) {
        taskList.remove(deletedTask);
        tasksAdapter.notifyDataSetChanged();
        saveTasksToSharedPreferences();
    }


    public void moveTaskToCompleted(Task completedTask) {
        taskList.remove(completedTask);
        tasksAdapter.notifyDataSetChanged();
        Intent intent = new Intent(MainActivity.this, maintwo.class);
        intent.putExtra("completedTask", completedTask);
        startActivity(intent);
        saveTasksToSharedPreferences();
    }




}