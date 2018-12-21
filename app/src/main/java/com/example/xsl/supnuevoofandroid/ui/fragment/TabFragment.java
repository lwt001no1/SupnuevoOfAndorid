package com.example.xsl.supnuevoofandroid.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xsl.supnuevoofandroid.R;

public class TabFragment extends Fragment {

	private String content;

	public TabFragment(){

	}

	public TabFragment(String content) {
		// TODO Auto-generated constructor stub
		this.content = content;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.tab_content, container, false);
		TextView tv = (TextView) v.findViewById(R.id.txt_content);
		tv.setText(content);
		return v;
	}

}
