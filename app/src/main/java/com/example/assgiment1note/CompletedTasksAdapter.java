package com.example.assgiment1note;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CompletedTasksAdapter extends ArrayAdapter<Task> {

    private ArrayList<Task> completedTasksList;
    private Context context;

    public CompletedTasksAdapter(Context context, ArrayList<Task> completedTasksList) {
        super(context, 0, completedTasksList);
        this.context = context;
        this.completedTasksList = completedTasksList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list2, parent, false);
        }

        TextView completedTaskTextView = convertView.findViewById(R.id.task_comp);

        Task currentTask = completedTasksList.get(position);
        String tas = currentTask.getName().toString() ;
        completedTaskTextView.setText(tas);

        return convertView;
    }
}
