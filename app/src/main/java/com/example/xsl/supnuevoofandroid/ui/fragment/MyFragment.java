package com.example.xsl.supnuevoofandroid.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xsl.supnuevoofandroid.R;

public class MyFragment extends Fragment implements View.OnClickListener {

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my, container, false);
        initBind(view);

        return view;
    }

    private void initBind(View v) {
        editText1 = (EditText) v.findViewById(R.id.my_edText_1);
        editText2 = (EditText) v.findViewById(R.id.my_edText_2);
        editText3 = (EditText) v.findViewById(R.id.my_edText_3);
        editText4 = (EditText) v.findViewById(R.id.my_edText_4);
        button1 = (Button) v.findViewById(R.id.my_Bt_1);
        button2 = (Button) v.findViewById(R.id.my_Bt_2);
        button3 = (Button) v.findViewById(R.id.my_Bt_3);
        button4 = (Button) v.findViewById(R.id.my_Bt_4);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_Bt_1:
                Looper.prepare();
                Toast.makeText(getContext(), "bt1", Toast.LENGTH_SHORT);
                Looper.loop();
                break;
            case R.id.my_Bt_2:
                Looper.prepare();
                Toast.makeText(getContext(), "bt2", Toast.LENGTH_SHORT);
                Looper.loop();
                break;
            case R.id.my_Bt_3:
                Looper.prepare();
                Toast.makeText(getContext(), "bt3", Toast.LENGTH_SHORT);
                Looper.loop();
                break;
            case R.id.my_Bt_4:
                Looper.prepare();
                Toast.makeText(getContext(), "bt4", Toast.LENGTH_SHORT);
                Looper.loop();
                break;

        }
    }
}
