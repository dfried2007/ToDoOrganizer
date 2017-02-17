package com.example.dfrie.todoorganizer;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dfrie.todoorganizer.enums.TaskStatus;
import com.example.dfrie.todoorganizer.model.ToDoTask;
import com.example.dfrie.todoorganizer.util.ToDoUtil;

import java.util.ArrayList;

/**
 * Created by dfrie on 2/4/2017.
 */

public class ToDoItemAdapter  extends ArrayAdapter<ToDoTask> {

    private static class ViewHolder {
        ImageView ivPriority;
        ImageView ivStatus;
        TextView tvToDo;
        TextView tvToDoDetail;
    }

    public ToDoItemAdapter(Context context, ArrayList<ToDoTask> todos) {
        super(context, 0, todos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ToDoTask todo = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_todo, parent, false);
            viewHolder.ivPriority = (ImageView)convertView.findViewById(R.id.ivPriority);
            viewHolder.ivStatus = (ImageView)convertView.findViewById(R.id.ivStatus);
            viewHolder.tvToDo = (TextView)convertView.findViewById(R.id.tvToDo);
            viewHolder.tvToDoDetail = (TextView)convertView.findViewById(R.id.tvToDoDetail);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvToDo.setText(todo.getTaskName());

        StringBuilder sb = new StringBuilder();
        sb.append(getContext().getString(R.string.due_label));
        if (todo.getTaskDate()!=null) {
            sb.append(ToDoUtil.formatDate(todo.getTaskDate()));
        }
        if (todo.getTaskDetail()!=null && !todo.getTaskDetail().equals(" ")) {
            sb.append("  (").append(todo.getTaskDetail().trim()).append(")");
        }
        viewHolder.tvToDoDetail.setText(sb.toString());

        if (todo.getTaskStatus() == TaskStatus.ABORTED.getValue()) {
            viewHolder.ivPriority.setVisibility(View.INVISIBLE);
            viewHolder.ivStatus.setVisibility(View.VISIBLE);
            viewHolder.ivStatus.setImageResource(android.R.drawable.ic_delete);
            viewHolder.tvToDo.setTextColor(Color.GRAY);
        } else if (todo.getTaskStatus() == TaskStatus.DONE.getValue()) {
            viewHolder.ivPriority.setVisibility(View.INVISIBLE);
            viewHolder.ivStatus.setVisibility(View.VISIBLE);
            //TODO...
            //viewHolder.ivStatus.setImageResource(android.R.drawable.btn_check_buttonless_on);
            //viewHolder.ivStatus.setImageResource(android.R.drawable.checkbox_on_background);
            viewHolder.ivStatus.setImageResource(R.mipmap.ic_stat_done);
            viewHolder.tvToDo.setTextColor(Color.GRAY);
        } else {
            viewHolder.ivPriority.setVisibility(View.VISIBLE);
            viewHolder.ivStatus.setVisibility(View.INVISIBLE);
            //viewHolder.ivStatus.setImageResource(android.R.drawable.checkbox_off_background);
            if (todo.getTaskPriority() <= 2) {
                viewHolder.ivPriority.setImageResource(android.R.drawable.star_on);
            } else if (todo.getTaskPriority() <= 5) {
                viewHolder.ivPriority.setImageResource(android.R.drawable.btn_star_big_on);
            } else {
                viewHolder.ivPriority.setImageResource(android.R.drawable.btn_star_big_off);
            }
        }
        return convertView;
    }

}
