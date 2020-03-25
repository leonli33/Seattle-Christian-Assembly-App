package com.leon.scaapp;

/**
 * Created by Leon on 7/13/2018.
 */

public class Message {
    public String author;
    public String title;
    public String date;

    public Message(String Author, String Title, String Date)
    {
        author = Author;
        title = Title;
        date = Date;
    }
    public String getAuthor()
    {
        return author;
    }
    public String getTitle()
    {
        return title;
    }
    public String getDate()
    {
        return date;
    }
}
