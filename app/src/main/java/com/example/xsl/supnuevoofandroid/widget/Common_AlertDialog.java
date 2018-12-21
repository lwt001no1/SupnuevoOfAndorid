package com.example.xsl.supnuevoofandroid.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.xsl.supnuevoofandroid.R;

public class Common_AlertDialog extends Dialog {

    private String title;
    private String content;
    private Context context;

    public Common_AlertDialog(@NonNull Context context, String title, String content) {
        super(context);
        this.context = context;
        this.title = title;
        this.content = content;
    }

    public Common_AlertDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        this.title = title;
        this.content = content;
    }

    protected Common_AlertDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
        this.title = title;
        this.content = content;
    }

    public static void alertDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context.getApplicationContext())
                .setTitle("错误信息").setMessage("登录超时");
        builder.setIcon(R.drawable.icon);
        builder.setNegativeButton("登录失败", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog1 = builder.create();
        alertDialog1.show();
//        setNegativeButton(alertDialog).create().show();
    }

    private AlertDialog.Builder setNegativeButton(AlertDialog.Builder buider) {

        return buider.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }
}
