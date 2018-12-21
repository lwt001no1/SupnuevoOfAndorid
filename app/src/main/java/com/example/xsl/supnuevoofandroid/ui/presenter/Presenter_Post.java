package com.example.xsl.supnuevoofandroid.ui.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.xsl.supnuevoofandroid.object.sale_itemObject;
import com.example.xsl.supnuevoofandroid.ui.activity.LoginActivity;
import com.example.xsl.supnuevoofandroid.util.util_Http;
import com.example.xsl.supnuevoofandroid.widget.Common_AlertDialog;
import com.example.xsl.supnuevoofandroid.widget.CustomDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Presenter_Post {


    public static String post_Login(String use, String pas, Context context) {
        String res = "";
        String url = "auth/webLogin";
        Map m = new HashMap();
        m.put("loginName", use);
        m.put("password", pas);
        m.put("loginType", "1");
        try {
            res = util_Http.post_Asyn(m, url);
        } catch (Exception e) {
            Common_AlertDialog.alertDialog(context);
//            Log.e("post_Login", e.getMessage());
        }

        return res;
    }

    public static ArrayList sale_Query_Post(String codigo) throws Exception {
        String url = "commodity/getQueryDataListByInputStringMobile";
        ArrayList<Map> commodityList = new ArrayList();
        String res = "";
        Map m = new HashMap();
        m.put("codigo", codigo);
        res = util_Http.post_Asyn(m, url);
        JSONObject jsonObject = new JSONObject(res);
        JSONArray arr = new JSONArray();
        if (jsonObject.has("array")) {
            arr = jsonObject.getJSONArray("array");
            Gson mGson = new Gson();
            Type listType = new TypeToken<ArrayList>() {
            }.getType();
            commodityList = mGson.fromJson(arr.toString(), listType);
        }
        return commodityList;
    }

    public static Map sale_GetCommodity_Post(String codigo) throws Exception {
        String url = "commodity/getSupnuevoBuyerPriceFormByCodigoMobile";
        ArrayList<Map> commodityList = new ArrayList();
        String res = "";
        Map m = new HashMap();
        m.put("codigo", codigo);
        res = util_Http.post_Asyn(m, url);
        Gson mGson = new Gson();
        Map map = new HashMap();
        map = mGson.fromJson(res, map.getClass());
        return map;
    }


}
