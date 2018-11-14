package com.jinojino.klashelper.java;

import java.sql.Date;

public class Post {
    String post_id;
    String title;
    String content;
    String author_id;
    String create_date;
    int hit;

    public Post(){
        this.post_id = null;
        this.title = null;
        this.content = null;
        this.author_id = null;
        this.create_date = null;
        this.hit = 0;
    }
    public Post(String post_id, String title, String content, String author_id, String create_date, int hit){
        this.post_id = post_id;
        this.title = title;
        this.content = content;
        this.author_id = author_id;
        this.create_date = create_date;
        this.hit = hit;
    }

    public String getPost_id() { return post_id; }

    public String getTitle() { return title; }

    public String getContent() { return content; }

    public String getAuthor_id() { return author_id; }

    public String getCreate_date() { return create_date; }

    public int getHit() { return hit; }

    public void setPost_id(String post_id) { this.post_id = post_id; }

    public void setTitle(String title) { this.title = title; }

    public void setContent(String content) { this.content = content; }

    public void setAuthor_id(String author_id) { this.author_id = author_id; }

    public void setCreate_date(String create_date) { this.create_date = create_date; }

    public void setHit(int hit) { this.hit = hit; }
}


