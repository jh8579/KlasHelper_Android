package com.jinojino.klashelper.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jinojino.klashelper.DB.DBHelper;
import com.jinojino.klashelper.R;
import com.jinojino.klashelper.Thread.LoginThread;
import com.jinojino.klashelper.receiver.AlarmReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SignInActivity extends AppCompatActivity {

    SharedPreferences data;
    SharedPreferences.Editor editor;

    DBHelper dbHelper;
    String id="";
    String pw="";
    String msg="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        dbHelper= new DBHelper(getApplicationContext(), "Work.db", null, 1);
        data = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //id, pw 받는 칸
        final TextInputEditText idInput = (TextInputEditText) findViewById(R.id.id_input);
        final TextInputEditText pwInput = (TextInputEditText) findViewById(R.id.pw_input);

        // 로그인 성공한 기록 있으면 그 기록으로 로그인인
       if(data.contains("id") && data.contains("pw")){
            id = data.getString("id","");
            pw = data.getString("pw", "");
            idInput.setText(id);
            pwInput.setText(pw);

            msg = "로그인 성공!";

            // 자동으로 메인 액티비티로 넘겨주기
            Intent intent= new Intent(SignInActivity.this, MainActivity.class);
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            startActivity(intent);
        }
        // 버튼 눌렀을때 동작
        Button loginButton = (Button)findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // edittext에 들어가 있는 정보 불러오기
                String id_in = idInput.getText().toString();
                String pw_in = pwInput.getText().toString();

                // 서버로부터 허용된 로그인인지 확인!
                LoginThread l = new LoginThread(id_in, pw_in);
                l.start();
                try {
                    l.join();
                } catch(Exception e) {
                    e.printStackTrace();
                }
                String response = l.getResult();
                int flag = 0;
                try {
                    JSONObject jobject = new JSONObject(response);
                    String m_status = jobject.getString("status");
                    flag = jobject.getInt("flag");
                }catch (JSONException e){
                    e.printStackTrace();
                }
                if(flag == 1){
                    // 이전 로그인 정보와 같은지 비교
                    if(id.compareTo(id_in)==0 && pw.compareTo(pw_in)==0){
                        msg = "로그인 성공!";
                    }
                    else{
                        // 새로운 로그인일때 DB 삭제
                        dbHelper.deleteAll();
                        msg = "화면을 내려 과제 목록을 갱신해주세요.";
                    }

                    // 성공한 로그인 정보 저장
                    editor = data.edit();
                    editor.putString("id", idInput.getText().toString());
                    editor.putString("pw", pwInput.getText().toString());
                    editor.commit();

                    // 메인 메뉴로 넘겨주기
                    Intent intent= new Intent(SignInActivity.this, MainActivity.class);
                    Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();
                    startActivity(intent);

                }else {
                    msg = "로그인 실패!";
                    Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

