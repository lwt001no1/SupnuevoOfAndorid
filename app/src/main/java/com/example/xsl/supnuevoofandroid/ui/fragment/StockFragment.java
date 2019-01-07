package com.example.xsl.supnuevoofandroid.ui.fragment;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xsl.supnuevoofandroid.R;
import com.example.xsl.supnuevoofandroid.ui.adapter.SaleAdapter;
import com.example.xsl.supnuevoofandroid.ui.adapter.StockAdapter;
import com.example.xsl.supnuevoofandroid.widget.CustomDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StockFragment extends Fragment {

    private ListView listView;
    private TextView textView;
    private ArrayList<HashMap> arr = new ArrayList<HashMap>();
    private StockAdapter adapter;
    private ArrayList<String> mArr = new ArrayList<String>();
    private TextView wait;

    public StockFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stock, container, false);
//        initBind(view);
        textView = (TextView) view.findViewById(R.id.stock_TextView);
        listView = (ListView) view.findViewById(R.id.stock_ListView);
        wait = (TextView) view.findViewById(R.id.stock_waitDialog);
        adapter = new StockAdapter(arr);
        listView.setAdapter(adapter);
        Runnable run = new Runnable() {
            @Override
            public void run() {
                getPhoneContacts();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        wait.setVisibility(View.GONE);
                        CustomDialog.hideWaitingDialo();
                        adapter.notifyDataSetChanged();
//                adapter = new StockAdapter(arr);
//                listView.setAdapter(adapter);
                    }
                });
            }
        };
        new Thread(run).start();
        CustomDialog.showWaitingDialog(getContext(),"查询中。。。");
        return view;
    }

    private void initBind(View view) {
        textView = (TextView) view.findViewById(R.id.stock_TextView);
//        listView = (ListView) view.findViewById(R.id.stock_ListView);
//        mAdapter = new ArrayAdapter(getContext(), R.layout.item_stock, arr);
        HashMap mMap = new HashMap();
        mMap.put("name", "wangwu");
        mMap.put("phoneNumber", "11233");
        arr.add(mMap);
        adapter = new StockAdapter(arr);
        listView.setAdapter(adapter);
    }

    private void getPhoneContacts() {
        ContentResolver cr = getContext().getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            HashMap map = new HashMap();
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            map.put("name", name);
            map.put("contactId", contactId);
            Cursor phones = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);
            ArrayList<String> detail = new ArrayList<String>();
            while (phones.moveToNext()) {
                String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                detail.add(phoneNumber);
            }
            map.put("phoneNumber", detail);
            arr.add(map);
            phones.close();
        }
        cursor.close();
    }
}
