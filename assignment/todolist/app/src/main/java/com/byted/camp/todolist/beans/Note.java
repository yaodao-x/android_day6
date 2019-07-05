package com.byted.camp.todolist.beans;

import java.util.Date;

/**
 * Created on 2019/1/23.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public class Note {

    private String content;
    private Date date;
    public final long id;
    private Priority priority;
    private State state;

    public Note(long paramLong)
    {
        this.id = paramLong;
    }

    public String getContent()
    {
        return this.content;
    }

    public Date getDate()
    {
        return this.date;
    }

    public Priority getPriority()
    {
        return this.priority;
    }

    public State getState()
    {
        return this.state;
    }

    public void setContent(String paramString)
    {
        this.content = paramString;
    }

    public void setDate(Date paramDate)
    {
        this.date = paramDate;
    }

    public void setPriority(Priority paramPriority)
    {
        this.priority = paramPriority;
    }

    public void setState(State paramState)
    {
        this.state = paramState;
    }
}
