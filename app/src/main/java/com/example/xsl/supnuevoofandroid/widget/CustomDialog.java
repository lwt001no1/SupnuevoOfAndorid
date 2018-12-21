package com.example.xsl.supnuevoofandroid.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xsl.supnuevoofandroid.R;

import java.util.zip.Inflater;


public class CustomDialog extends Dialog {

    private static CustomDialog cusDialog;

    /**
     * 宽高由布局文件中指定（但是最底层的宽度无效，可以多嵌套一层解决）
     */
    public CustomDialog(Context context, int style) {
        // TODO Auto-generated constructor stub
        super(context, style);
    }

    public CustomDialog(Context context, int width, int height, View layout, int style) {
        super(context, style);

        setContentView(layout);

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();

        float density = getDensity(context);
        params.width = (int) (width * density);
        params.height = (int) (height * density);
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }


    public float getDensity(Context context) {
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        return dm.density;
    }

    public static Dialog showWaitingDialog(Context context, String tips) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_waiting, null);
        TextView tv = (TextView) v.findViewById(R.id.tvTip);

        tv.setText("正在登录。。");

        cusDialog = new CustomDialog(context, R.style.MyDialog);

        if (!TextUtils.isEmpty(tips)) {
            cusDialog.setTitle("登录中！别急");

        }
        cusDialog.setCancelable(true);
        cusDialog.setContentView(R.layout.dialog_waiting);
        cusDialog.setCanceledOnTouchOutside(false);
        cusDialog.show();
        return cusDialog;
    }

    public static void hideWaitingDialo() {
        // TODO Auto-generated method stub
        if (cusDialog != null) {
            cusDialog.dismiss();
            cusDialog = null;
        }
    }
}
