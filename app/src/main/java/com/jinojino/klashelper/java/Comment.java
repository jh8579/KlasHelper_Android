package com.jinojino.klashelper.java;

public class Comment {
    String create_date;
    String author_id;
    String content;
    int comment_id;

    public Comment(){
        this.create_date = "null";
        this.author_id = "null";
        this.content = "null";
        this.comment_id = 0;
    }

    public Comment(String create_date, String author_id, String content, int comment_id){
        this.create_date = create_date;
        this.author_id = author_id;
        this.content = content;
        this.comment_id = comment_id;
    }

    public String getCreate_date() { return create_date; }

    public void setCreate_date(String create_date) { this.create_date = create_date; }

    public String getAuthor_id() { return author_id; }

    public void setAuthor_id(String author_id) { this.author_id = author_id; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public int getComment_id() { return comment_id; }

    public void setComment_id(int comment_id) { this.comment_id = comment_id; }
}
