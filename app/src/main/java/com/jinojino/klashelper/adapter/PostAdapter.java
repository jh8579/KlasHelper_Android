package com.jinojino.klashelper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jinojino.klashelper.java.Post;
import com.jinojino.klashelper.java.ListViewPost;

import java.util.ArrayList;

public class PostAdapter extends BaseAdapter {
    Context mContext;
    private ArrayList<Post> PostList = new ArrayList<Post>();

    public PostAdapter(Context context){ mContext = context; }

    public void addItem(Post post) { PostList.add(post); }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return PostList.size();
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) { return PostList.get(position); }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) { return position; }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    // TODO : EditText로 작성자, 조회수 정보 띄워주기
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ListViewPost dataView;

        if (convertView == null){
            dataView = new ListViewPost(mContext, PostList.get(position));
        } else {
            dataView = (ListViewPost) convertView;
            dataView.setText(0, PostList.get(position).getPost_id());
            dataView.setText(1, PostList.get(position).getTitle());
            dataView.setText(2, PostList.get(position).getAuthor_id());
            //dataView.setText(2, "작성자 : " + PostList.get(position).getAuthor_id());
            dataView.setText(3, PostList.get(position).getCreate_date());
            dataView.setText(4, String.valueOf(PostList.get(position).getHit()));
            //dataView.setText(4, "조회수 : " + String.valueOf(PostList.get(position).getHit()));
        }
        return dataView;
    }




}
