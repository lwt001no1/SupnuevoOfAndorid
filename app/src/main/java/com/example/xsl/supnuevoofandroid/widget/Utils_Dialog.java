package com.example.xsl.supnuevoofandroid.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xsl.supnuevoofandroid.R;

public class Utils_Dialog {

	/*
	 * ��ʾDialog
	 * 
	 * @param context ������
	 * 
	 * @param msg ��ʾ����
	 * 
	 * @param isTransBg �Ƿ�͸��
	 * 
	 * @param isCancelable �Ƿ���Ե��ȡ��
	 */
	public static Dialog showWaitDialog(Context context, String msg, boolean isTeansBg, boolean isCancel) {
//		LayoutInflater inflater = LayoutInflater.from(context);
//		View v = inflater.inflate(R.layout.dialog_waiting, null);// ����View
//
//		RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.dialog_waiting);// ���ز���
//		TextView tipTextView = (TextView) v.findViewById(R.id.tvTip);
//		tipTextView.setText(msg);
//
//		Dialog loadingDialog = new Dialog(context, R.style.MyDialog);
//		loadingDialog.setCancelable(true);
//		loadingDialog.setCanceledOnTouchOutside(false);
//		loadingDialog.setContentView(R.layout.dialog_waiting);
//
//		/**
//		 * ����ʾDialog�ķ�����װ��������
//		 */
//		Window window = loadingDialog.getWindow();
//		WindowManager.LayoutParams lp = window.getAttributes();
//		lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//		lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//		window.setGravity(Gravity.CENTER);
//		window.setAttributes(lp);
//		window.setWindowAnimations(R.style.PopWindowAnimStyle);
//		loadingDialog.show();
//
//		return loadingDialog;
		return null;
	}

	public static void closeDialog(Dialog mDialogUtils) {
		if (mDialogUtils != null && mDialogUtils.isShowing()) {
			mDialogUtils.dismiss();
		}
	}
}
