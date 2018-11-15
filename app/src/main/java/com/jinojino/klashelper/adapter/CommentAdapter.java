package com.jinojino.klashelper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jinojino.klashelper.java.Comment;
import com.jinojino.klashelper.java.ListViewComment;
import com.jinojino.klashelper.java.Post;
import com.jinojino.klashelper.java.ListViewPost;

import java.util.ArrayList;

public class CommentAdapter extends BaseAdapter {
    Context mContext;
    private ArrayList<Comment> CommentList = new ArrayList<Comment>();

    public CommentAdapter(Context context){ mContext = context; }

    public void addItem(Comment comment) { CommentList.add(comment); }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return CommentList.size();
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) { return CommentList.get(position); }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) { return position; }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    // TODO : EditText로 작성자, 조회수 정보 띄워주기
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ListViewComment dataView;

        if (convertView == null){
            dataView = new ListViewComment(mContext, CommentList.get(position));
        } else {
            dataView = (ListViewComment) convertView;
            dataView.setText(0, String.valueOf(CommentList.get(position).getComment_id()));
            dataView.setText(1, CommentList.get(position).getContent());
            dataView.setText(2, CommentList.get(position).getAuthor_id());
            dataView.setText(3, CommentList.get(position).getCreate_date());
        }
        return dataView;
    }




}
