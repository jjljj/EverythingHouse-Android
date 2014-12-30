package com.crossy.app.everythinghouse.utils;

import android.util.Log;

import com.crossy.app.everythinghouse.utils.api.API_SPF;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by ljj on 2014/12/30.
 */
public class HttpUtil {

    private final String TAG = "HttpUtil";

    private List<NameValuePair> nameValuePairs;
    private String url;
    private String json;
    private String sessionId;

    private HttpPost httpPost;
    private HttpGet httpGet;
    private DefaultHttpClient defaultHttpClient;
    private HttpResponse httpResponse;

    public String post(String u, List<NameValuePair> pairs, boolean withSession){
        url = u;
        nameValuePairs = pairs;

        defaultHttpClient = new DefaultHttpClient();
        defaultHttpClient.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, 10000);//超时10s
        httpPost = new HttpPost(url);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if(withSession){
            sessionId = DataUtil.getString(API_SPF.NAME_USER,API_SPF.USER_SESSION_EVERY_HOUSE);
            httpPost.setHeader("Cookie", sessionId);
        }

        try {
            httpResponse = defaultHttpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                json = EntityUtils.toString(httpResponse.getEntity());
            } else {
                json = "{}";
            }
            Log.i(TAG,"success");
        } catch (Exception e) {
            e.printStackTrace();
            json = "{}";
            Log.i(TAG,"网络错误"+e);
        }

        return json;
    }

    public String get(String u, boolean withSession){
        url = u;

        httpGet = new HttpGet(url);
        defaultHttpClient = new DefaultHttpClient();

        if(withSession){
            sessionId = DataUtil.getString(API_SPF.NAME_USER,API_SPF.USER_SESSION_EVERY_HOUSE);
            httpGet.setHeader("Cookie", sessionId);
        }

        try {
            httpResponse = defaultHttpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                json = EntityUtils.toString(httpResponse.getEntity());
            } else {
                json = "{}";
            }
            Log.i(TAG,"success");
        } catch (Exception e) {
            e.printStackTrace();
            json = "{}";
            Log.i(TAG,"网络错误"+e);
        }
        return json;
    }

    public DefaultHttpClient getDefaultHttpClient(){
        return defaultHttpClient;
    }
    public HttpResponse getHttpResponse(){
        return httpResponse;
    }
}
