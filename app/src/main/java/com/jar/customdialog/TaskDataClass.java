package com.jar.customdialog;

public class TaskDataClass{
    //String taskName, taskDate, taskStartTime, taskEndTime, taskNote;
    String tname, tdate, tstart, tend, tnote;
    public TaskDataClass(){}

    public TaskDataClass(String tname, String tdate, String tstart, String tend, String tnote) {
        this.tname = tname;
        this.tdate = tdate;
        this.tstart = tstart;
        this.tend = tend;
        this.tnote = tnote;
    }

    public String getTname() {
        return tname;
    }

    public String getTdate() {
        return tdate;
    }

    public String getTstart() {
        return tstart;
    }

    public String getTend() {
        return tend;
    }

    public String getTnote() {
        return tnote;
    }
//    public TaskDataClass(String taskName, String taskDate, String taskStartTime, String taskEndTime, String taskNote) {
//        this.taskName = taskName;
//        this.taskDate = taskDate;
//        this.taskStartTime = taskStartTime;
//        this.taskEndTime = taskEndTime;
//        this.taskNote = taskNote;
//    }
//
//    public String getTaskName() {
//        return taskName;
//    }
//
//    public String getTaskDate() {
//        return taskDate;
//    }
//
//    public String getTaskStartTime() {
//        return taskStartTime;
//    }

//    public String getTaskEndTime() {
//        return taskEndTime;
//    }
//
//    public String getTaskNote() {
//        return taskNote;
//    }
}
