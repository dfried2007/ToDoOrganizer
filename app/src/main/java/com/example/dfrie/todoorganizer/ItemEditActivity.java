package com.example.dfrie.todoorganizer;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dfrie.todoorganizer.enums.TaskStatus;
import com.example.dfrie.todoorganizer.fragments.DatePickerFragment;
import com.example.dfrie.todoorganizer.model.ToDoTask;
import com.example.dfrie.todoorganizer.util.ToDoUtil;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


public class ItemEditActivity extends AppCompatActivity {

    private static final String[] priorities = new String[] {"1","2","3","4","5","6","7","8","9"};
    private static final String[] alerts = new String[] {"1","2","3","4","5","6","7"};

    private ToDoTask todo;
    private int position;

    private EditText etTaskName;
    private EditText etDueDate;
    private EditText etDetails;
    private Spinner spPriority;
    private Spinner spStatus;
    private Spinner spAlert;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_edit);

        Intent intent = getIntent();
        String posStr = intent.getStringExtra(MainActivity.EXTRA_POSITION);
        position = Integer.parseInt(posStr);
        String serial = intent.getStringExtra(MainActivity.EXTRA_PAYLOAD);
        todo = ToDoUtil.fromString(serial);

        etTaskName = (EditText)findViewById(R.id.etTaskName);
        etDueDate = (EditText)findViewById(R.id.etDueDate);
        etDetails = (EditText)findViewById(R.id.etDetails);
        spPriority = (Spinner)findViewById(R.id.spPriority);
        spStatus = (Spinner)findViewById(R.id.spStatus);
        spAlert = (Spinner)findViewById(R.id.spAlert);
        buttonSave = (Button)findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(
            new View.OnClickListener() {
                public void onClick(View v) {
                    saveTaskData(v);
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
            }
        );

        etTaskName.setText(todo.getTaskName());
        etDueDate.setText(ToDoUtil.formatDate(todo.getTaskDate()));
        etDetails.setText(todo.getTaskDetail());
        spPriority.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, priorities));
        spPriority.setSelection(todo.getTaskPriority()-1);
        spAlert.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, alerts));
        spAlert.setSelection(todo.getTaskAlertLeadtime()-1);
        spStatus.setAdapter(new ArrayAdapter<TaskStatus>(this,
                android.R.layout.simple_spinner_item, TaskStatus.values()));
        spStatus.setSelection(TaskStatus.getEnumFor(todo.getTaskStatus()).getValue());
    }

    public void saveTaskData(View view) {
        File filesDir = getFilesDir();
        File file = new File(filesDir, MainActivity.FILENAME);
        ArrayList<String> rows;
        try {
            rows =  new ArrayList<String>(FileUtils.readLines(file));
        } catch (IOException e) {
            Toast.makeText(this, "Cannot read data file", Toast.LENGTH_LONG).show();
            return;
        }
        if (position > rows.size()-1) {
            Toast.makeText(this, "Position "+position+" beyond data file size", Toast.LENGTH_LONG).show();
            return;
        }

        ArrayList<ToDoTask> items = new  ArrayList<ToDoTask>();
        for (String row: rows) {
            todo = ToDoUtil.fromString(row);
            items.add(todo);
        }

        todo = new ToDoTask();
        todo.setTaskName(etTaskName.getText().toString());
        todo.setTaskDetail(etDetails.getText().toString());
        todo.setTaskPriority(spPriority.getSelectedItemPosition()+1);
        todo.setTaskStatus(spStatus.getSelectedItemPosition());
        todo.setTaskAlertLeadtime(spAlert.getSelectedItemPosition()+1);
        try {
            todo.setTaskDate(ToDoUtil.parseDate(etDueDate.getText().toString()));
        } catch (Exception e) {
            Toast.makeText(this, "Date format is not legal", Toast.LENGTH_LONG).show();
            todo.setTaskDate(new Date());
        }
        items.set(position, todo);

        Collections.sort(items);

        rows =  new ArrayList<String>();
        for (ToDoTask task: items) {
            rows.add(ToDoUtil.asString(task));
        }

        try {
            FileUtils.writeLines(file, rows);
        } catch (Exception e) {
            Toast.makeText(this, "Cannot write data file", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), DatePickerFragment.D_TAG);
    }
}
