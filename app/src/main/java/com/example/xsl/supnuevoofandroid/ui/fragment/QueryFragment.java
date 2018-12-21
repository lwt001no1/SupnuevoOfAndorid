package com.example.xsl.supnuevoofandroid.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.xsl.supnuevoofandroid.R;

import java.util.ArrayList;

public class QueryFragment extends Fragment implements View.OnClickListener, TextWatcher {

    private static QueryFragment instance = null;
    Activity context;
    private EditText editText;
    private Button btn_query;
    private String[] arr1;
    private ListView listOfQuery;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    public QueryFragment() {
        // TODO Auto-generated constructor stub

    }

    public static QueryFragment getInstence() {
        if (instance == null) {
            instance = new QueryFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View v = inflater.inflate(R.layout.query, container, false);
        initControl(v);
        editText.addTextChangedListener(this);
        btn_query.setOnClickListener(this);
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.item_query, arr1);
        listOfQuery.setAdapter(adapter);
        return v;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.query_Button_Find:
                arrayList.add(editText.getText().toString());
                adapter = new ArrayAdapter<String>(getActivity(), R.layout.item_query, arrayList);
                listOfQuery.setAdapter(adapter);
                break;
        }
    }

    private void initControl(View v) {//初始化View控件
        Resources res = getResources();//获取Xml里面的数组
        arr1 = res.getStringArray(R.array.list_Query);
        btn_query = (Button) v.findViewById(R.id.query_Button_Find);
        editText = (EditText) v.findViewById(R.id.query_editText);
        listOfQuery = (ListView) v.findViewById(R.id.list_Query);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        btn_query.setEnabled(false);
        btn_query.setBackgroundColor(Color.parseColor("#CCCCCC"));
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!TextUtils.isEmpty(editText.getText().toString())) {
            btn_query.setEnabled(true);
            btn_query.setBackgroundColor(Color.parseColor("#1C86EE"));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
//        if(!TextUtils.isEmpty(editText.getText().toString())){
//            btn_query.setEnabled(true);
//            btn_query.setBackgroundColor(Color.parseColor("#CCCCCC"));
//        }
    }
}
