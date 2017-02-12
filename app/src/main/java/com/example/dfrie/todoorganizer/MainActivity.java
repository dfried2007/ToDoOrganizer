package com.example.dfrie.todoorganizer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dfrie.todoorganizer.model.ToDoTask;
import com.example.dfrie.todoorganizer.util.ToDoUtil;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    protected static final String FILENAME = "todos.txt";
    public static final String EXTRA_DATE = "com.example.dfrie.todoorganizer.DATE";
    public static final String EXTRA_PAYLOAD = "com.example.dfrie.todoorganizer.PAYLOAD";
    public static final String EXTRA_POSITION = "com.example.dfrie.todoorganizer.POSITION";

    private ArrayList<ToDoTask> items;
    protected ToDoItemAdapter itemsAdapter;
    private ListView lvItems;
    private EditText textEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textEntry = (EditText)findViewById(R.id.tvEditText);

        readItems();
        itemsAdapter = new ToDoItemAdapter(this, items);

        lvItems = (ListView)findViewById(R.id.lvItems);
        lvItems.setAdapter(itemsAdapter);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                items.remove(position);
                                writeItems();
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        itemsAdapter.notifyDataSetChanged();
                                    }
                                });
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                String message = getString(R.string.delete_item_label);
                String taskName = items.get(position).getTaskName();
                builder.setMessage(MessageFormat.format(message, taskName))
                    .setPositiveButton(getString(android.R.string.yes), dialogClickListener)
                    .setNegativeButton(getString(android.R.string.no), dialogClickListener).show();
                return true;
            }
        });
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Intent i = new Intent(getApplicationContext(), ItemEditActivity.class);
                i.putExtra(EXTRA_PAYLOAD, ToDoUtil.asString(items.get(position)));
                i.putExtra(EXTRA_POSITION, ""+position);
                startActivity(i);
            }
        });
    }

    public void addNewItem(View view) {
        String str = textEntry.getText().toString();
        if (str.length()<1) {
            Toast.makeText(this, R.string.enter_text_label, Toast.LENGTH_LONG).show();
        } else {
            ToDoTask task = new ToDoTask();
            task.setTaskName(str);
            task.setTaskDate(new Date());
            task.setTaskPriority(5);
            itemsAdapter.add(task);
            textEntry.setText("");
            writeItems();
            runOnUiThread(new Runnable() {
                public void run() {
                    itemsAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File file = new File(filesDir, FILENAME);
        ArrayList<String> rows;
        try {
            rows =  new ArrayList<String>(FileUtils.readLines(file));
        } catch (IOException e) {
            rows =  new ArrayList<String>();
        }
        ToDoTask task;
        items = new  ArrayList<ToDoTask>();
        for (String row: rows) {
            task = ToDoUtil.fromString(row);
            items.add(task);
        }
    }

    private void writeItems() {
        Collections.sort(items);
        ArrayList<String> rows =  new ArrayList<String>();
        for (ToDoTask task: items) {
            rows.add(ToDoUtil.asString(task));
        }

        File filesDir = getFilesDir();
        File file = new File(filesDir, FILENAME);
        try {
            FileUtils.writeLines(file, rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
