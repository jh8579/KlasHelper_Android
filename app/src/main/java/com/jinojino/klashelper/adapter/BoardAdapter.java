package com.jinojino.klashelper.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jinojino.klashelper.java.Board;
import com.jinojino.klashelper.java.ListViewBoard;

import java.util.ArrayList;

public class BoardAdapter extends BaseAdapter {
    Context mContext;
    private ArrayList<Board> BoardList = new ArrayList<Board>();

    public BoardAdapter(Context context){ mContext = context; }

    public void addItem(Board board) { BoardList.add(board); }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return BoardList.size();
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) { return BoardList.get(position); }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) { return position; }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ListViewBoard dataView;

        if (convertView == null){
            dataView = new ListViewBoard(mContext, BoardList.get(position));
        } else {
            dataView = (ListViewBoard) convertView;
            dataView.setText(0, BoardList.get(position).getClassName());
            dataView.setText(1, BoardList.get(position).getInstructor());
            dataView.setText(2, BoardList.get(position).getClassCode());
        }
        return dataView;
    }




}
