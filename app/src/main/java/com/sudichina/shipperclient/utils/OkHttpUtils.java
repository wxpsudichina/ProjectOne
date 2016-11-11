package com.sudichina.shipperclient.utils;

import android.os.Environment;

import com.google.gson.Gson;
import com.sudichina.shipperclient.biz.IURL;

import java.io.File;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * OkHttpUtils
 */
public class OkHttpUtils {
    private static final String TAG = "OkHttpUtils";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType FILE = MediaType.parse("application/octet-stream");

    private static OkHttpClient mOkHttpClient = new OkHttpClient();
    private static Gson mGson = new Gson();

    public static void jsonPost(String url, Object object, Callback callback) {
        String json = mGson.toJson(object);
        System.out.println(json);

        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static void formPost(String url, Callback callback) {

        File dcim = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        System.out.println(dcim.getAbsolutePath());
        File file = new File(dcim.getAbsolutePath() + "/Screenshots/S60825-19271962.jpg");
        if (file.exists()) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }

        RequestBody r1 = RequestBody.create(FILE, file);
//
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", "37")
                .addFormDataPart("idCard", "142730199510261519")
                .addFormDataPart("realname", "武晓鹏")
                .addFormDataPart("drivingLicense", "P60824-110400.jpg", r1)
                .build();

        Request request = new Request.Builder()
                .url(IURL.ID_VERIFY)
                .post(requestBody)
                .build();

        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);


    }

    public static void formPost(String url, Map<String, Object> data, Map<String, String> file, Callback callback) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            builder.addFormDataPart(entry.getKey(), entry.getValue().toString());
        }

        for (Map.Entry<String, String> entry : file.entrySet()) {
            File f = new File(entry.getValue());
            if (f.exists()) {
                System.out.println("文件存在，上传文件");
                String path = entry.getValue();
                int last = path.lastIndexOf("/");
                String name = path.substring(last + 1);
                System.out.println(name);
                builder.addFormDataPart(entry.getKey(), name, RequestBody.create(FILE, new File(path)));
            } else {
                System.out.println("文件不存在，不变");
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            }

        }

        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();

        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);

    }

    public static void get(String url, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static void getjson(String getHostCity, Callback callback) {

    }

//    public static void post(String url, Map<String, String> data, Callback callback) {
//        FormBody.Builder builder = new FormBody.Builder();
//        for (Map.Entry<String, String> entry : data.entrySet()) {
//            builder.add(entry.getKey(), entry.getValue());
//            System.out.println(entry.getKey() + " = " + entry.getValue());
//        }
//
//        Request request = new Request.Builder()
//                .url(url)
//                .post(builder.build())
//                .build();
//        Call call = mOkHttpClient.newCall(request);
//        call.enqueue(callback);
//    }
}
