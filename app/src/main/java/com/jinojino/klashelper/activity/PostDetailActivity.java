package com.jinojino.klashelper.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.jinojino.klashelper.R;
import com.jinojino.klashelper.Thread.PostDetailThread;
import com.jinojino.klashelper.adapter.CommentAdapter;
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

            JSONObject CurrentPost = PostArray.getJSONObject(0);

            String create_date = CurrentPost.getString("create_date");
            String post_id = CurrentPost.getString("post_id");
            String content = CurrentPost.getString("content");
            int hit = CurrentPost.getInt("hit");
            String author_id = CurrentPost.getString("author_id");
            String title = CurrentPost.getString("title");
            CurrentStatus = new Post(post_id, title, content, author_id, create_date, hit);

            String status = PostObject.getString("status");

            if(status.equals("Success")) {
                JSONArray CommentsArray = PostObject.getJSONArray("comments");
                for (int i = 0; i < CommentsArray.length(); i++) {
                    JSONObject Comments = CommentsArray.getJSONObject(i);

                    int comment_id = Comments.getInt("comment_id");
                    String c_content = Comments.getString("content");
                    String c_create_date = Comments.getString("create_date");
                    String c_author_id = Comments.getString("author_id");

                    CommentList.add(new Comment(c_create_date, c_author_id, c_content, comment_id));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(CurrentStatus.getTitle());
        TextView author = (TextView) findViewById(R.id.author);
        author.setText("작성자 : " + CurrentStatus.getAuthor_id());
        TextView post_detail = (TextView) findViewById(R.id.post_detail);
        post_detail.setText(CurrentStatus.getContent());

        CommentAdapter Cadapter = new CommentAdapter(this.getApplicationContext());
        ListView listView = (ListView) findViewById(R.id.comment_list);
        listView.setAdapter(Cadapter);

        for (int i = 0; i < CommentList.size(); i++) {
            Cadapter.addItem(CommentList.get(i));
        }


    }
}
