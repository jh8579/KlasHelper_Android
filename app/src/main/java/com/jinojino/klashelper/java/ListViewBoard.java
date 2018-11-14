package com.jinojino.klashelper.java;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jinojino.klashelper.R;

public class ListViewBoard extends LinearLayout {
    TextView Text1;
    TextView Text2;
    TextView Text3;

    public ListViewBoard(Context context, Board aItem) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listview_board, this, true);

        Text1 = (TextView) findViewById(R.id.dataItem01);
        Text1.setText(aItem.getClassName());

        Text2 = (TextView) findViewById(R.id.dataItem02);
        Text2.setText(aItem.getInstructor());

        Text3 = (TextView) findViewById(R.id.dataItem03);
        Text3.setText(aItem.getClassCode());
    }

    public void setText(int index, String data) {
        if (index == 0) {
            Text1.setText(data);
        } else if (index == 1) {
            Text2.setText(data);
        } else if (index == 2) {
            Text3.setText(data);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
