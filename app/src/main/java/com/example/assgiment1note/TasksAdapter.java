package com.example.assgiment1note;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.ArrayList;

public class TasksAdapter extends ArrayAdapter<Task> {

    private ArrayList<Task> tasksList;

    private Context context;

    public TasksAdapter(Context context, ArrayList<Task> tasksList) {
        super(context, 0, tasksList);
        this.context = context;
        this.tasksList = tasksList;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list, parent, false);
        }

        TextView taskTextView = convertView.findViewById(R.id.taskTextView);
        Button doneButton = convertView.findViewById(   R.id.doneButton);
        Button deleteButton = convertView.findViewById(R.id.deleteButton);
        Task currentTask = tasksList.get(position);
        taskTextView.setText(currentTask.getName());


        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Task completedTask = tasksList.get(position);

                if (context instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) context;
                    mainActivity.moveTaskToCompleted(completedTask);
                }

            }


        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task deletedTask = tasksList.get(position);
                if (context instanceof MainActivity) {
                    ((MainActivity) context).deleteTask(deletedTask);
                }



            }
        });

        return convertView;
    }


}