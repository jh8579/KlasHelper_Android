package com.jinojino.klashelper.Thread;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class PostDetailThread extends Thread{
    String result;
    String post_id;
    String json;
    public void run(){
        try {
            HttpResponse response;
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("post_id", post_id);
            json = jsonObject.toString();
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://ryulth.com:9999/post_detail/" + post_id);
            httpPost.setEntity(new StringEntity(json, "UTF-8"));
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Accept-Encoding", "application/json");
            httpPost.setHeader("Accept-Language", "ko");
            response = httpClient.execute(httpPost);
            String sresponse = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
            result = sresponse;
        }
        catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());

        } finally {
            /* nothing to do here */
        }

    }
    public String getResult(){
        return result;
    }

    public PostDetailThread(String post_id){
        this.post_id = post_id;
    }

}
