package com.jinojino.klashelper.java;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jinojino.klashelper.R;

import org.w3c.dom.Text;

public class ListViewPost extends LinearLayout {
    TextView Text1;
    TextView Text2;
    TextView Text3;
    TextView Text4;
    TextView Text5;

    public ListViewPost(Context context, Post aPost) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listview_post, this, true);

        Text1 = (TextView) findViewById(R.id.PostInfo01);
        Text1.setText(String.valueOf(aPost.getPost_id()));

        Text2 = (TextView) findViewById(R.id.PostInfo02);
        Text2.setText(aPost.getTitle());

        Text3 = (TextView) findViewById(R.id.PostInfo03);
        Text3.setText(aPost.getAuthor_id());

        Text4 = (TextView) findViewById(R.id.PostInfo04);
        Text4.setText(aPost.getCreate_date());

        Text5 = (TextView) findViewById(R.id.PostInfo05);
        Text5.setText(String.valueOf(aPost.getHit()));
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
        } else if (index == 4) {
            Text5.setText(data);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
