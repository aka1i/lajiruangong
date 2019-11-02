package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FirstPageActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private List<ShopBean> shopBeans = new ArrayList<>();
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        init();
    }

    private void init(){
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("正在重置中...");
        progressDialog.show();
        recyclerView = findViewById(R.id.layout1_rv);
        String url = "http://www.dianping.com/mylist/ajax/shoprank?rankId=63e27f60bce99dbebf1b5da58856513471862f838d1255ea693b953b1d49c7c0";

        HttpUtil.getRequestWithokHttp(url,handler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(progressDialog!=null)
                progressDialog.cancel();
            switch (msg.what){
                case 0:
                    try {
                        JSONArray array = ((JSONObject)msg.obj).getJSONArray("shopBeans");
                        Toast.makeText(getApplicationContext(),"" + array.length(),Toast.LENGTH_SHORT).show();
                        for (int i = 0; i< array.length(); i++){
                            JSONObject object = array.getJSONObject(i);
                            ShopBean bean = new ShopBean();
                            bean.setShopName(object.getString("shopName"));
                            bean.setAvgPrice(object.getInt("avgPrice"));
                            bean.setMainRegionName(object.getString("mainRegionName"));
                            bean.setRefinedScore1(object.getString("refinedScore1"));
                            shopBeans.add(bean);
//                            Toast.makeText(getApplicationContext(),bean.getShopName(),Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    Toast.makeText(getApplicationContext(),"成功",Toast.LENGTH_SHORT).show();
                    Layout1Adapter layout1Adapter = new Layout1Adapter(shopBeans,FirstPageActivity.this);
                    recyclerView.setAdapter(layout1Adapter);
                    break;
                default:
                    Toast.makeText(getApplicationContext(),"发生错误"+msg.obj.toString(),Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
