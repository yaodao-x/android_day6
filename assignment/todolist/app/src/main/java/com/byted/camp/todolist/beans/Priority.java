package com.byted.camp.todolist.beans;

public class Priority {
    public int color;
    public int priority;

    public Priority(int priority) {
        this.priority = priority;
        if (priority == 0) {
            //low
            this.color = 0xffffff;

        } else if (priority == 1) {
            //normal
            this.color = 0xFF8BC34A;
        } else if (priority == 2) {
            //high
            this.color = 0xFFEB0B58;

        }
    }
}
