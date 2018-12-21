package com.example.xsl.supnuevoofandroid.ui.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xsl.supnuevoofandroid.R;
import com.example.xsl.supnuevoofandroid.ui.base.CrashHandler;
import com.example.xsl.supnuevoofandroid.ui.fragment.QueryFragment;
import com.example.xsl.supnuevoofandroid.ui.fragment.SaleFragment;
import com.example.xsl.supnuevoofandroid.ui.fragment.StockFragment;
import com.example.xsl.supnuevoofandroid.ui.fragment.TabFragment;

public class MainActivity extends Activity implements OnClickListener {
    // UI Object
    private TextView tab_query;
    private TextView tab_sale;
    private TextView tab_stock;
    private TextView tab_my;
    private TextView tab_information;
    private FrameLayout tab_content;
    private QueryFragment queryFragment;
    private QueryFragment fg1;
    private SaleFragment fg2;
    private StockFragment fg3;
    // Fragment Object
    private TabFragment fg4, fg5;
    private FragmentManager fManager;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fManager = getFragmentManager();
        bindViews();
        CrashHandler.getInstance().init(this);
        tab_query.performClick();
    }

    private void bindViews() {
        // TODO Auto-generated method stub
        tab_query = (TextView) findViewById(R.id.tab_query);
        tab_sale = (TextView) findViewById(R.id.tab_sale);
        tab_stock = (TextView) findViewById(R.id.tab_stock);
        tab_my = (TextView) findViewById(R.id.tab_my);
        tab_information = (TextView) findViewById(R.id.tab_information);
        tab_content = (FrameLayout) findViewById(R.id.tab_content);

        tab_query.setOnClickListener(this);
        tab_sale.setOnClickListener(this);
        tab_stock.setOnClickListener(this);
        tab_my.setOnClickListener(this);
        tab_information.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        FragmentTransaction fTeansaction = fManager.beginTransaction();
        hideAllFragment(fTeansaction);
        switch (v.getId()) {
            case R.id.tab_query:
                setSelected();
                tab_query.setSelected(true);
                if (fg1 == null) {
                    fg1 = new QueryFragment();
                    fTeansaction.add(R.id.tab_content, fg1);
                } else {
                    fTeansaction.show(fg1);
                }
                break;

            case R.id.tab_sale:
                setSelected();
                tab_sale.setSelected(true);
                if (fg2 == null) {
                    fg2 = new SaleFragment();
                    fTeansaction.add(R.id.tab_content, fg2);
                } else {
                    fTeansaction.show(fg2);
                }
                break;
            case R.id.tab_stock:
                setSelected();
                tab_stock.setSelected(true);
                if (fg3 == null) {
                    fg3 = new StockFragment();
                    fTeansaction.add(R.id.tab_content, fg3);
                } else {
                    fTeansaction.show(fg3);
                }
                break;
            case R.id.tab_my:
                setSelected();
                tab_my.setSelected(true);
                if (fg4 == null) {
                    fg4 = new TabFragment("��4��");
                    fTeansaction.add(R.id.tab_content, fg4);
                } else {
                    fTeansaction.show(fg4);
                }
                break;
            case R.id.tab_information:
                setSelected();
                tab_information.setSelected(true);
                if (fg5 == null) {
                    fg5 = new TabFragment("�����");
                    fTeansaction.add(R.id.tab_content, fg5);
                } else {
                    fTeansaction.show(fg5);
                }
            default:
                break;
        }
        fTeansaction.commit();
    }

    private void setSelected() {
        // TODO Auto-generated method stub
        tab_query.setSelected(false);
        tab_sale.setSelected(false);
        tab_stock.setSelected(false);
        tab_my.setSelected(false);
        tab_information.setSelected(false);
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        // TODO Auto-generated method stub
        if (fg1 != null)
            fragmentTransaction.hide(fg1);
        if (fg2 != null)
            fragmentTransaction.hide(fg2);
        if (fg3 != null)
            fragmentTransaction.hide(fg3);
        if (fg4 != null)
            fragmentTransaction.hide(fg4);
        if (fg5 != null)
            fragmentTransaction.hide(fg5);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                fManager.popBackStack();
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
//				finish();
                FragmentTransaction fTeansaction = fManager.beginTransaction();
                hideAllFragment(fTeansaction);
                System.exit(0);
            }
        }
        return true;
    }
}
