package com.jinojino.klashelper.java;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jinojino.klashelper.R;

import org.w3c.dom.Text;

public class ListViewComment extends LinearLayout {
    TextView Text1;
    TextView Text2;
    TextView Text3;
    TextView Text4;

    public ListViewComment(Context context, Comment aItem) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listview_comment, this, true);

        Text1 = (TextView) findViewById(R.id.comment_num);
        Text1.setText(String.valueOf(aItem.getComment_id()));

        Text2 = (TextView) findViewById(R.id.comment_main);
        Text2.setText(aItem.getContent());

        Text3 = (TextView) findViewById(R.id.comment_author);
        Text3.setText(aItem.getAuthor_id());

        Text4 = (TextView) findViewById(R.id.comment_time);
        Text4.setText(aItem.getCreate_date());

    }

    public void setText(int index, String data) {
        if (index == 0) {
            Text1.setText(data);
        } else if (index == 1) {
            Text2.setText(data);
        } else if (index == 2) {
            Text3.setText(data);
        } else if (index == 3) {
            Text4.setText(data);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
