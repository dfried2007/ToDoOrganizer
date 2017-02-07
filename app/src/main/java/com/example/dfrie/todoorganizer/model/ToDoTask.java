package com.example.dfrie.todoorganizer.model;

import java.util.Date;

/**
 * Created by dfrie on 2/4/2017.
 */

public class ToDoTask implements Comparable {

    private String taskName;
    private Date taskDate;
    private String taskDetail;
    private int taskPriority;
    private int taskStatus;
    private int taskAlertLeadtime;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(int taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskDetail() {
        return taskDetail;
    }

    public void setTaskDetail(String taskDetail) {
        this.taskDetail = taskDetail;
    }

    public int getTaskAlertLeadtime() {
        return taskAlertLeadtime;
    }

    public void setTaskAlertLeadtime(int taskAlertLeadtime) {
        this.taskAlertLeadtime = taskAlertLeadtime;
    }

    @Override
    public int compareTo(Object o) {
        ToDoTask other = (ToDoTask)o;
        long diff = taskDate.getTime() - other.getTaskDate().getTime();
        return diff > 0 ? 1 : (diff < 0 ? -1 : 0);
    }
}
