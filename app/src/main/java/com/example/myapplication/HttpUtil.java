package com.example.myapplication;

import android.os.Handler;
import android.os.Message;


import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {

    private static String TAG="UPLOADFILE";

    //参数头部有放入access_Token的请求
    public static void sendRequestWithokHttp(final String json, final String url,
                                       final Handler handler,final String access_token) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10,TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .build();

                RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                        , json);

                Request request=new Request.Builder()
                        .addHeader("Authorization",access_token)
                        .url(url)
                        .post(requestBody)
                        .build();

                //创建/Call
                Call call = client.newCall(request);
                //加入队列 异步操作
                call.enqueue(new Callback() {
                    //请求错误回调方法
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            //判断超时异常
                            handler.sendEmptyMessage(-999);
                    }
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String resStr=response.body().string();
                        try{
                            JSONObject jsonObject=new JSONObject(resStr);
                            Message msg=new Message();
                            msg.what=jsonObject.getInt("status");
                            msg.obj=resStr;
                            handler.sendMessage(msg);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }

    //参数头部无放入access_Token的请求
    public static void sendRequestWithokHttp(final String json, final String url,
                                             final Handler handler) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10,TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .build();

                RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                        , json);

                Request request=new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();

                //创建/Call
                Call call = client.newCall(request);
                //加入队列 异步操作
                call.enqueue(new Callback() {
                    //请求错误回调方法
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            //判断超时异常
                            handler.sendEmptyMessage(-999);
                    }
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String resStr=response.body().string();
                        try{
                            JSONObject jsonObject=new JSONObject(resStr);
                            Message msg=new Message();
                            msg.what=jsonObject.getInt("status");
                            msg.obj=resStr;
                            handler.sendMessage(msg);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }

    //旧接口请求方法
    public static void oldSendRequestWithokHttp(final String json, final String url,
                                             final Handler handler) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10,TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .build();

                RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                        , json);

                Request request=new Request.Builder()
                        .url(url)
                        .post(requestBody)
                        .build();

                //创建/Call
                Call call = client.newCall(request);
                //加入队列 异步操作
                call.enqueue(new Callback() {
                    //请求错误回调方法
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            //判断超时异常
                            handler.sendEmptyMessage(-999);
                    }
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String resStr=response.body().string();
                        try{
                            JSONObject jsonObject=new JSONObject(resStr);
                            Message msg=new Message();
                            msg.what=jsonObject.getInt("errCode");
                            msg.obj=resStr;
                            handler.sendMessage(msg);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }

    //get请求,加入token
    public static void getRequestWithokHttp(final String url, final String access_token,
                                            final Handler handler){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient.Builder()
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10,TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .build();
                    Request request=new Request.Builder()
                            .addHeader("Authorization",access_token)
                            .url(url)
                            .build();

                    //创建/Call
                    Call call = client.newCall(request);
                    //加入队列 异步操作
                    call.enqueue(new Callback() {
                        //请求错误回调方法
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            //if (e instanceof SocketTimeoutException) {
                                //判断超时异常
                                handler.sendEmptyMessage(-999);
                            //}
                        }
                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String resStr=response.body().string();
                            try{
                                JSONObject json=new JSONObject(resStr);
                                Message message=new Message();
                                message.what=json.getInt("status");
                                message.obj=json;
                                handler.sendMessage(message);
                            } catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    });

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //get请求,无token
    public static void getRequestWithokHttp(final String url, final Handler handler){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient.Builder()
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10,TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .build();
                    Request request=new Request.Builder()
                            .url(url)
                            .build();

                    //创建/Call
                    Call call = client.newCall(request);
                    //加入队列 异步操作
                    call.enqueue(new Callback() {
                        //请求错误回调方法
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            //if (e instanceof SocketTimeoutException) {
                            //判断超时异常
                            handler.sendEmptyMessage(-999);
                            //}
                        }
                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            String resStr=response.body().string();
                            try{
                                JSONObject json=new JSONObject(resStr);
                                Message message=new Message();
                                message.what=json.getInt("skipResults");
                                message.obj=json;
                                handler.sendMessage(message);
                            } catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    });

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //put请求，部分更新
    public static void patchRequestWithokHttp(final String json, final String url,
                                             final Handler handler,final String access_token) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10,TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .build();

                RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                        , json);

                Request request=new Request.Builder()
                        .addHeader("Authorization",access_token)
                        .url(url)
                        .put(requestBody)
                        .build();

                //创建/Call
                Call call = client.newCall(request);
                //加入队列 异步操作
                call.enqueue(new Callback() {
                    //请求错误回调方法
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        //if (e instanceof SocketTimeoutException) {
                            //判断超时异常
                            handler.sendEmptyMessage(-999);
                        //}
                    }
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String resStr=response.body().string();
                        try{
                            JSONObject jsonObject=new JSONObject(resStr);
                            Message msg=new Message();
                            msg.what=jsonObject.getInt("status");
                            msg.obj=resStr;
                            handler.sendMessage(msg);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }

    //使用表单传值,type为0用post，type为1用put
    public static void formRequestWithokHttp(final Map<String,String> map, final String url,
                                            final Handler handler, final String access_token,final int type) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10,TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .build();

                FormBody.Builder formBodyBuilder = new FormBody.Builder();

                Set<String> keys=map.keySet();
                for (Object key : keys) {
                    String value = map.get(key);
                    formBodyBuilder.add((String)key,value);
                }

                FormBody formBody=formBodyBuilder.build();
                Request request;

                if(!"".equals(access_token)){
                    if(type==1)
                        request=new Request.Builder()
                                .addHeader("Authorization",access_token)
                                .url(url)
                                .put(formBody)
                                .build();
                    else
                        request=new Request.Builder()
                                .addHeader("Authorization",access_token)
                                .url(url)
                                .post(formBody)
                                .build();
                }
                else{
                    request=new Request.Builder()
                            .addHeader("Authorization",access_token)
                            .url(url)
                            .post(formBody)
                            .build();
                }

                //创建/Call
                Call call = client.newCall(request);
                //加入队列 异步操作
                call.enqueue(new Callback() {
                    //请求错误回调方法
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        //if (e instanceof SocketTimeoutException) {
                        //判断超时异常
                        handler.sendEmptyMessage(-999);
                        //}
                    }
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String resStr=response.body().string();
                        try{
                            JSONObject jsonObject=new JSONObject(resStr);
                            Message msg=new Message();
                            msg.what=jsonObject.getInt("status");
                            msg.obj=resStr;
                            handler.sendMessage(msg);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }

    //删除请求
    public static void deleteRequestWithokHttp(final String url,
                                              final Handler handler,final String access_token) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10,TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS)
                        .build();

                Request request=new Request.Builder()
                        .addHeader("Authorization",access_token)
                        .url(url)
                        .delete()
                        .build();

                //创建/Call
                Call call = client.newCall(request);
                //加入队列 异步操作
                call.enqueue(new Callback() {
                    //请求错误回调方法
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        //if (e instanceof SocketTimeoutException) {
                        //判断超时异常
                        handler.sendEmptyMessage(-999);
                        //}
                    }
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        String resStr=response.body().string();
                        try{
                            JSONObject jsonObject=new JSONObject(resStr);
                            Message msg=new Message();
                            msg.what=jsonObject.getInt("status");
                            msg.obj=resStr;
                            handler.sendMessage(msg);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }

//    //上传图片,type=0代表普通图片，1代表封面图片
//    public static void uploadFile(final String filePath, final Handler handler,
//                                  final String access_token,final int type) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                File file = new File(filePath);
//                RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file);
//                RequestBody requestBody = new MultipartBody.Builder()
//                        .setType(MultipartBody.FORM)
//                        .addFormDataPart("file", "test.jpg", fileBody)
//                        .build();
//                final Request request = new Request.Builder()
//                        .url(URLStr.uploadImage)
//                        .addHeader("Authorization",access_token)
//                        .post(requestBody)
//                        .build();
//
//
//                final okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
//                OkHttpClient okHttpClient  = httpBuilder
//                        //设置超时
//                        .connectTimeout(10, TimeUnit.SECONDS)
//                        .writeTimeout(15, TimeUnit.SECONDS)
//                        .build();
//                okHttpClient.newCall(request).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                        //if (e instanceof SocketTimeoutException) {
//                            //判断超时异常
//                            handler.sendEmptyMessage(-999);
//                        //}
//                    }
//
//                    @Override
//                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                        Message message=new Message();
//                        message.what=200+type;//图片专属码
//                        message.obj=response.body().string();
//                        handler.sendMessage(message);
//                    }
//                });
//            }
//        }).start();
//    }
}
