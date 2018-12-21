package com.example.xsl.supnuevoofandroid.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.xsl.supnuevoofandroid.R;
import com.example.xsl.supnuevoofandroid.object.SerializableMap;
import com.example.xsl.supnuevoofandroid.object.sale_itemObject;
import com.example.xsl.supnuevoofandroid.ui.adapter.SaleAdapter;
import com.example.xsl.supnuevoofandroid.ui.presenter.Presenter_Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sale_ListViewActivity extends Activity implements View.OnClickListener {

    private ListView listView;
    private ArrayAdapter adapter;
    private sale_itemObject item;
    private ArrayList<Map> arr = new ArrayList();
    private Map commodityInfo = new HashMap();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sale_listview);
        initBind();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        arr = (ArrayList<Map>) bundle.get("list");
        listView.setAdapter(new SaleAdapter(arr, this));
    }

    private void initBind() {
        listView = (ListView) findViewById(R.id.sale_listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                final Map param = arr.get(pos);
                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            commodityInfo = Presenter_Post.sale_GetCommodity_Post((String) param.get("codigo"));
                            Intent intent = new Intent();
                            SerializableMap ser = new SerializableMap();
                            ser.setMapMap(commodityInfo);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("backdata", ser);
                            intent.putExtra("bundle",bundle);
                            setResult(1, intent);
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                new Thread(run).start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}
