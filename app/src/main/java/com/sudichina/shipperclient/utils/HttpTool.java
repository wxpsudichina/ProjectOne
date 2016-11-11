package com.sudichina.shipperclient.utils;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;

/**
 * 网络请求工具类
 */
public class HttpTool {
    protected static final String TAG = "HttpTool";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    // http://169.254.61.202:8080/MyPaoT/register
    private String result = null;
    private static OkHttpClient client = new OkHttpClient();

//		public String doPost(final String url, final Map<String, String> map,
//		final String charset) {
//
//			HttpClient httpClient = null;
//			HttpPost httpPost = null;
//
//			try {
//				httpClient = new DefaultHttpClient();
//				httpPost = new HttpPost(url);
//				// ���ò���
//			List<NameValuePair> list = new ArrayList<NameValuePair>();
//			Iterator iterator = map.entrySet().iterator();
//			while (iterator.hasNext()) {
//				Entry<String, String> elem = (Entry<String, String>) iterator
//						.next();
//				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
//			}
//			if (list.size() > 0) {
//				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,
//						charset);
//				httpPost.setEntity(entity);
//			}
//			HttpResponse response = httpClient.execute(httpPost);
//			if (response != null) {
//				HttpEntity resEntity = response.getEntity();
//				if (resEntity != null) {
//					result = EntityUtils.toString(resEntity, charset);
//					Log.i(TAG, "-HttpTool这里是 是否注册成功" + result);
//				} else {
//					Log.i(TAG, "-HttpTool这里 是否失败" + result);
//				}
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return result;
//	}

    //	public static String post(final String url, final Map<String, String> map) {
//
//		FormEncodingBuilder formBody = new FormEncodingBuilder();
//		Iterator it = map.entrySet().iterator();
//		System.out.println(map.entrySet().size());
//		String key;
//		String value;
//
//		while (it.hasNext()) {
//			Entry entry = (Entry) it.next();
//			key = entry.getKey().toString();
//			value = entry.getValue().toString();
//			formBody.add(key, value);
//			System.out.println(key + "====" + value);
//		}
//
//
////		Gson gson=new Gson();
////		String str_json=gson.toJson(formBody.build().toString());
////		System.out.println("1213123________"+str_json);
//		Request request = new Request.Builder().url(url).post(formBody.build())
//				.build();
//		Response response = null;
//		try {
//			response = client.newCall(request).execute();
//
//			String string = response.body().string();
//
//			if (string != null) {
//				return string;
//			} else {
//				Log.i(TAG, string + "=======");
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}
