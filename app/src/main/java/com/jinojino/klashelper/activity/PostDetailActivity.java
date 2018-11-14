package com.jinojino.klashelper.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jinojino.klashelper.R;
import com.jinojino.klashelper.Thread.PostDetailThread;
import com.jinojino.klashelper.java.Board;
import com.jinojino.klashelper.java.Comment;
import com.jinojino.klashelper.java.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PostDetailActivity extends AppCompatActivity {
    SharedPreferences data;
    Post CurrentStatus = new Post();
    ArrayList<Comment> CommentList = new ArrayList<Comment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        Intent intent = getIntent();
        CurrentStatus.setPost_id(intent.getStringExtra("post_id"));

        PostDetailThread pt = new PostDetailThread(CurrentStatus.getPost_id());
        pt.start();

        try {
            pt.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 서버 응답 가져오기
        String response = pt.getResult();

        try {
            JSONObject PostObject = new JSONObject(response);
            JSONArray PostArray = PostObject.getJSONArray("post");
            for (int i = 0; i < PostArray.length(); i++) {
                JSONObject CurrentPost = PostArray.getJSONObject(i);

                String create_date = CurrentPost.getString("create_date");
                String post_id = CurrentPost.getString("post_id");
                String content = CurrentPost.getString("content");
                String title = CurrentPost.getString("title");
                String author_id = CurrentPost.getString("author_id");
                int hit = CurrentPost.getInt("hit");

                CurrentStatus = new Post(post_id, title, content, author_id, create_date, hit);
            }

            JSONArray CommentsArray = PostObject.getJSONArray("comments");
            for (int i = 0; i < CommentsArray.length(); i++) {
                JSONObject Comments = CommentsArray.getJSONObject(i);

                String create_date = Comments.getString("create_date");
                String author_id = Comments.getString("author_id");
                String content = Comments.getString("content");
                int comment_id = Comments.getInt("comment_id");

                CommentList.add(new Comment(create_date, author_id, content, comment_id));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
}
