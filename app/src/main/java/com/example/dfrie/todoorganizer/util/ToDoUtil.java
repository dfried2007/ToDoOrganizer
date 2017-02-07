package com.example.dfrie.todoorganizer.util;

import com.example.dfrie.todoorganizer.model.ToDoTask;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dfrie on 2/5/2017.
 */

public class ToDoUtil {

    private static final SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy");

    private static final String DELIMITER = "\u0000";

    public static String asString(ToDoTask task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getTaskName()).append(DELIMITER);
        if (task.getTaskDate()==null) {
            sb.append(new Date().getTime());
        } else {
            sb.append(task.getTaskDate().getTime());
        }
        sb.append(DELIMITER);
        if (task.getTaskDetail()==null || task.getTaskDetail().length()==0) {
            sb.append(" ");
        } else {
            sb.append(task.getTaskDetail());
        }
        sb.append(DELIMITER);
        if (task.getTaskPriority()<1 || task.getTaskPriority()>9) {
            sb.append(9);
        } else {
            sb.append(task.getTaskPriority());
        }
        sb.append(DELIMITER);
        if (task.getTaskStatus()<0 || task.getTaskStatus()>2) {
            sb.append(0);
        } else {
            sb.append(task.getTaskStatus());
        }
        sb.append(DELIMITER);
        if (task.getTaskAlertLeadtime()<1 || task.getTaskAlertLeadtime()>99) {
            sb.append(1);
        } else {
            sb.append(task.getTaskAlertLeadtime());
        }
        sb.append(DELIMITER);

        return sb.toString();
    }

    public static ToDoTask fromString(String row) {
        ToDoTask task = new ToDoTask();
        String[] parts = row.split(DELIMITER);
        for (int i=0; i<parts.length; i++) {
            switch (i) {
                case 0:
                    task.setTaskName(parts[i]);
                    break;
                case 1:
                    long time = Long.parseLong(parts[i]);
                    task.setTaskDate(new Date(time));
                    break;
                case 2:
                    task.setTaskDetail(parts[i]);
                    break;
                case 3:
                    int priority = Integer.parseInt(parts[i]);
                    task.setTaskPriority(priority);
                    break;
                case 4:
                    int status = Integer.parseInt(parts[i]);
                    task.setTaskStatus(status);
                    break;
                case 5:
                    int alert = Integer.parseInt(parts[i]);
                    task.setTaskAlertLeadtime(alert);
                    break;
            }
        }
        return task;
    }

    public static Date parseDate(String s) {
        return SDF.parse(s, new ParsePosition(0));
    }

    public static String formatDate(Date date) {
        return SDF.format(date);
    }


}
