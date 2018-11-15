package com.jinojino.klashelper.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jinojino.klashelper.R;
import com.jinojino.klashelper.Thread.PostListThread;
import com.jinojino.klashelper.adapter.PostAdapter;
import com.jinojino.klashelper.java.Board;
import com.jinojino.klashelper.java.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {
    SharedPreferences data;
    Board CurrentStatus = new Board();
    ArrayList<Post> PostList = new ArrayList<Post>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        Intent intent = getIntent(); // 보내온 Intent를 얻는다
        CurrentStatus.setClassName(intent.getStringExtra("class_name"));
        CurrentStatus.setClassCode(intent.getStringExtra("class_code"));
        CurrentStatus.setInstructor(intent.getStringExtra("instructor"));

        //PostThread로부터 강의정보 가져오기
        PostListThread ht = new PostListThread(CurrentStatus.getClassCode());

        //test용 클래스 코드
        //PostListThread ht = new PostListThread("SWCON22100");

        ht.start();

        try {
            ht.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 서버 응답 가져오기
        String response = ht.getResult();

        try {
            JSONObject AllPostObject = new JSONObject(response);
            String status = AllPostObject.getString("status");
            if(status.compareTo("Success") == 0) {
                JSONArray PostListArray = AllPostObject.getJSONArray("posts");
                for (int i = 0; i < PostListArray.length(); i++) {
                    JSONObject PostObject = PostListArray.getJSONObject(i);
                    String title = PostObject.getString("title");
                    int hit = PostObject.getInt("hit");
                    String post_id = PostObject.getString("post_id");
                    String author_id = PostObject.getString("author_id");
                    String content = PostObject.getString("content");
                    String create_date = PostObject.getString("create_date");

                    PostList.add(new Post(post_id, title, content, author_id, create_date, hit));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        //class_name.setText(PostList.get(0).getTitle());
        PostAdapter adapter = new PostAdapter(this.getApplicationContext());
        ListView listview = (ListView) findViewById(R.id.listView02);
        listview.setAdapter(adapter);

        Post Null_Post = new Post("N","생성된 게시물이 없습니다","N/A","N/A","N/A",-1);
        if(PostList.size() == 0){
          adapter.addItem(Null_Post);
        }
        else {
            for (int i = 0; i < PostList.size(); i++) {
                adapter.addItem(PostList.get(i));
            }
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent myIntent = new Intent(view.getContext(), PostDetailActivity.class);
                    myIntent.putExtra("post_id", PostList.get(i).getPost_id());
                    // convert to new activity
                    startActivityForResult(myIntent, 0);
                }
            });
        }
    }
}
