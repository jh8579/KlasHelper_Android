package com.jinojino.klashelper.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jinojino.klashelper.R;
import com.jinojino.klashelper.Thread.BoardThread;
import com.jinojino.klashelper.activity.BoardActivity;
import com.jinojino.klashelper.adapter.BoardAdapter;
import com.jinojino.klashelper.java.Board;
import com.jinojino.klashelper.java.ListViewAlarm;
import com.jinojino.klashelper.java.Work;

import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class BoardFragment extends Fragment {

    SharedPreferences data;
    ArrayList<Board> BoardList = new ArrayList<Board>();

    ListView listView;
    BoardAdapter adapter;

    private OnFragmentInteractionListener mListener;

    public BoardFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static BoardFragment newInstance() {
        BoardFragment fragment = new BoardFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // ID 가져오기
        data = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String id = data.getString("id", "default value");

        // View 생성
        View view = inflater.inflate(R.layout.fragment_board, null);

        //BoardThread로부터 강의정보 가져오기
        BoardThread ht = new BoardThread(id);
        ht.start();

        try {
            ht.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 서버 응답 가져오기
        String response = ht.getResult();

        try {
            JSONObject AllBoardObject = new JSONObject(response);
            JSONArray BoardArray = AllBoardObject.getJSONArray("board");
            for (int i = 0; i < BoardArray.length(); i++) {
                JSONObject BoardObject = BoardArray.getJSONObject(i);
                String class_name = BoardObject.getString("class_name");
                String instructor = BoardObject.getString("instructor");
                String class_code = BoardObject.getString("class_code");

                BoardList.add(new Board(class_name, instructor, class_code));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // 리스트뷰 객체 참조
        listView = (ListView) view.findViewById(R.id.listView);
        // 어댑터 객체 생성
        adapter = new BoardAdapter(this.getActivity());
        for (int i = 0; i < BoardList.size(); i++) {
            adapter.addItem(BoardList.get(i));
        }

        // 리스트뷰에 어댑터 설정
        listView.setAdapter(adapter);

        //리스트뷰에서 한 항목이 클릭 되면 Acticity로 넘어간다
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent myIntent = new Intent(v.getContext(), BoardActivity.class);
                myIntent.putExtra("class_name", BoardList.get(position).getClassName());
                myIntent.putExtra("class_code", BoardList.get(position).getClassCode());
                myIntent.putExtra("instructor", BoardList.get(position).getInstructor());
                // convert to new activity
                startActivityForResult(myIntent, 0);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
