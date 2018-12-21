package com.example.xsl.supnuevoofandroid.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xsl.supnuevoofandroid.R;
import com.example.xsl.supnuevoofandroid.ui.presenter.Presenter_Post;
import com.example.xsl.supnuevoofandroid.util.util_Http;
import com.example.xsl.supnuevoofandroid.widget.CustomDialog;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class LoginActivity extends Activity {
    EditText userName, passWord;
    String baseUrl = "http://192.168.176.1:8080/supnuevo/func/auth/webLogin";

    private Dialog loginWaitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {
                Toast.makeText(this, "quanxian", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "quanxian", Toast.LENGTH_SHORT).show();
            }
        }
        userName = (EditText) findViewById(R.id.edit_user);
        passWord = (EditText) findViewById(R.id.edit_password);
        Button btn_login = (Button) findViewById(R.id.login_btn);

        btn_login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                CustomDialog.showWaitingDialog(LoginActivity.this, "dengluzhong");
                Post("supnuevo", "1");
            }
        });
    }

    private void Post(final String user, final String pass) {
        new Thread() {
            public void run() {
                Looper.prepare();
                String res = Presenter_Post.post_Login(user, pass, LoginActivity.this);
                if (res != "") {
                    CustomDialog.hideWaitingDialo();
                    Intent in = new Intent();
                    in.setClass(LoginActivity.this, MainActivity.class);
                    startActivity(in);
                    finish();
                } else {
                    CustomDialog.hideWaitingDialo();
                    Toast.makeText(LoginActivity.this, "LoginFail", Toast.LENGTH_LONG).show();
                }
                Looper.loop();
                Looper.myLooper().quit();
            }
        }.start();

    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    CustomDialog.hideWaitingDialo();
                    break;
            }

        }

        ;
    };

    private String sendHttpLogin(final String username, final String password) throws Exception {
        // TODO Auto-generated method stub
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {

            @Override
            public String call() throws Exception {
                // TODO Auto-generated method stub
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(baseUrl);
                httpPost.setHeader("Content-Type", "application/json");
                List<NameValuePair> pairList = new ArrayList<NameValuePair>();
                NameValuePair pair1 = new BasicNameValuePair("username", username);
                NameValuePair pair2 = new BasicNameValuePair("password", password);
                pairList.add(pair1);
                pairList.add(pair2);
                httpPost.setEntity(new UrlEncodedFormEntity(pairList));
                HttpResponse httpResponse = httpClient.execute(httpPost);
                System.out.println("**************************" + httpResponse.getStatusLine().getStatusCode());
                Log.e("HttpPost", "**************************" + httpResponse.getStatusLine().getStatusCode());
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    Log.e("HttpPost", "**************************" + httpResponse.getStatusLine().getStatusCode());
                    String result = EntityUtils.toString(httpResponse.getEntity());
                    return result;
                } else {
                    Log.e("HttpPost", "**************************" + httpResponse.getStatusLine().getStatusCode());
                }
                return null;
            }

        });
        new Thread(task).start();
        return task.get();

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Log.d("onStart", "onStart");
    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        Log.d("onRestart", "onRestart");
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.d("onResume", "onResume");
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Log.d("onPause", "onPause");
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        Log.d("onStop", "onStop");
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.d("onDestroy", "onDestroy");
    }

}
