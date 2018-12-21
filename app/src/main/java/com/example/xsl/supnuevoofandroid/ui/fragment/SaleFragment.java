package com.example.xsl.supnuevoofandroid.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.xsl.supnuevoofandroid.R;
import com.example.xsl.supnuevoofandroid.object.SerializableMap;
import com.example.xsl.supnuevoofandroid.object.sale_itemObject;
import com.example.xsl.supnuevoofandroid.ui.activity.Sale_ListViewActivity;
import com.example.xsl.supnuevoofandroid.ui.presenter.Presenter_Post;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import static com.example.xsl.supnuevoofandroid.R.drawable.common_button_disable;

public class SaleFragment extends Fragment implements View.OnClickListener, TextWatcher {

    Button sale_Button_query;
    EditText sale_EditText_query;
    private TextView textView;

    public SaleFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sale, container, false);
        initBind(v);
        sale_EditText_query.addTextChangedListener(this);
        sale_Button_query.setOnClickListener(this);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.sale_chaxun_button:

                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        String ss = sale_EditText_query.getText().toString();
                        ArrayList<Map> res = new ArrayList();
                        final ArrayList mid = new ArrayList();
                        try {
                            res = Presenter_Post.sale_Query_Post(ss);
                            mid.addAll(res);
                            Bundle bundle = new Bundle();
                            Intent intent = new Intent(getActivity(), Sale_ListViewActivity.class);
                            bundle.putParcelableArrayList("list", mid);
                            intent.putExtras(bundle);
                            startActivityForResult(intent, 1);
//                            startActivity(intent);
                        } catch (Exception e) {
                            Log.e("SaleFragment_Err", e.getMessage());
                        }
                    }
                };
                new Thread(run).start();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 1) {
            Bundle bundle = data.getBundleExtra("bundle");
            SerializableMap serializableMap = (SerializableMap) bundle.get("backdata");
            Map map = serializableMap.getMapMap();
            textView.setText(map.get("object").toString());
            textView.setMovementMethod(ScrollingMovementMethod.getInstance());
            textView.setVisibility(View.VISIBLE);
        }
    }

    private void initBind(View v) {
        sale_Button_query = (Button) v.findViewById(R.id.sale_chaxun_button);
        sale_EditText_query = (EditText) v.findViewById(R.id.sale_editText);
        textView = (TextView) v.findViewById(R.id.sale_TextView);
        textView.setVisibility(View.GONE);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Resources resources = getResources();
        String ss = sale_EditText_query.getText().toString();
        if (ss.length() < 4) {
            sale_Button_query.setEnabled(false);
            sale_Button_query.setBackground(resources.getDrawable(R.drawable.common_button_disable));
            sale_Button_query.setTextColor(resources.getColor(R.color.text_gray));
        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Resources resources = getResources();
        String ss = sale_EditText_query.getText().toString();
        if (ss.length() > 3) {
            sale_Button_query.setEnabled(true);
            sale_Button_query.setBackground(resources.getDrawable(R.drawable.common_button));
            sale_Button_query.setTextColor(resources.getColor(R.color.themeColor));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
