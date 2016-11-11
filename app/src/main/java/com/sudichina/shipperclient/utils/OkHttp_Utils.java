package com.sudichina.shipperclient.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class OkHttp_Utils {
	// ��ʼ��
	public static final OkHttpClient okHttpClient = new OkHttpClient();

	// ��ȡ ��������Request
	public static Request getRequestFromUrl(String urlString) {
		Request request = new Request.Builder().url(urlString).build();
		return request;
	}

	/**
	 * ��ȡ Response ��Ӧ����
	 * 
	 * @param urlString
	 * @return
	 * @throws IOException
	 */
	private static final Response getResopnseFromUrl(String urlString)
			throws IOException {
		Request request = getRequestFromUrl(urlString);
		Response response = okHttpClient.newCall(request).execute();

		return response;
	}

	/**
	 * �Զ��壺 ��ȡResponseBody����
	 * 
	 * @param urlString
	 * @return
	 * @throws IOException
	 */
	private static ResponseBody getResponseBodyFromUrl(String urlString)
			throws IOException {
		Response response = getResopnseFromUrl(urlString);
		// isSuccessful ��Ӧ���
		if (response.isSuccessful()) {
			return response.body();
		}
		return null;
	}

	/**
	 * ͨ���������� ��ȡ�ַ�
	 * 
	 * @param urlString
	 * @return
	 * @throws IOException
	 */
	public static String loadStringFromUrl(String urlString) throws IOException {
		ResponseBody responseBody = getResponseBodyFromUrl(urlString);
		if (responseBody != null) {
			return responseBody.string();
		}
		return null;
	}

	/**
	 * ��ȡ�������󷵻ص�����
	 * 
	 * @param urlString
	 * @return
	 * @throws IOException
	 */
	public static byte[] loadByteFromUrl(String urlString) throws IOException {
		ResponseBody responseBody = getResponseBodyFromUrl(urlString);
		if (responseBody != null) {
			return responseBody.bytes();
		}
		return null;
	}

	/**
	 * ��ȡ�������󷵻�������
	 * 
	 * @param urlString
	 * @return
	 * @throws IOException
	 */
	public static InputStream loadStreamFromUrl(String urlString)
			throws IOException {
		ResponseBody responseBody = getResponseBodyFromUrl(urlString);
		if (responseBody != null) {
			return responseBody.byteStream();
		}
		return null;
	}

	// ///////////////////////////////////////////////////
	//
	// //////////////////////////////////////////////////

	// ///////////////////////////////////////////////////////
	// Post 同步 网络访问
	// ///////////////////////////////////////////////////////

	/**
	 * 构建Post请求方式
	 * 
	 * @param urlString
	 * @param requestBody
	 * @return
	 */
	private static Request buildPostRequest(String urlString,
			RequestBody requestBody) {

		Request.Builder builder = new Request.Builder();
		builder.url(urlString).post(requestBody);
		return builder.build();
	}

	/**
	 * @param urlString
	 * @param requestBody
	 * 
	 * @return
	 * @throws IOException
	 */
	private static String postRequestBody(String urlString,
										  RequestBody requestBody) throws IOException {
		Request request = buildPostRequest(urlString, requestBody);
		Response response = okHttpClient.newCall(request).execute();
		if (response.isSuccessful()) {
			return response.body().string();

		}
		return null;
	}

	/**
	 * Psot 网络请求发送键值对。获取RequestBody对象
	 * 
	 * @param map
	 * @return
	 */
//	private static RequestBody buildRequestBody(Map<String, String> map) {
//
//		FormBody builder = new FormBody();
//		if (map != null && !map.isEmpty()) {
//			// 遍历Map集合
//			for (Map.Entry<String, String> entry : map.entrySet()) {
//
//				builder.add(entry.getKey(), entry.getValue());
//			}
//		}
//		return builder.build();
//	}

	/**
	 * Post 网络访问 提交键值对
	 * 
	 * @param urlString
	 * @param map
	 * @return
	 * @throws IOException
	 */
//	public static String postKeyValuesPair(String urlString,
//			Map<String, String> map) throws IOException {
//
//		RequestBody requestBody = buildRequestBody(map);
//
//		return postRequestBody(urlString, requestBody);
//	}

	/**
	 * Post 上传文件 urlString 网络地址 map 键值对
	 * 
	 * files 提交的文件
	 * 
	 * 
	 * formFileName 每个需要提交的文件对应的文件input的name；
	 * 
	 * @throws IOException
	 */

//	public static String postUPloadFiles(String urlString,
//			Map<String, Object> map, File[] files, String[] formFileName)
//			throws IOException {
//
////		RequestBody requestBody = builRequestBody(map, files, formFileName);
//
////		return postRequestBody(urlString, requestBody);
//	}

	/**
	 * 生成提交分块请求的RequestBody对象
	 * 
//	 * @param map
//	 * @param files
//	 * @param formFileName
//	 * @return
//	 */
//	private static RequestBody builRequestBody(Map<String, Object> map,
//			File[] files, String[] formFileName) {
//		MultipartBuilder builder = new MultipartBuilder()
//				.type(MultipartBuilder.FORM);
//		// 第一部分
//		if (map != null) {
//			for (Map.Entry<String, String> entry : map.entrySet()) {
//				builder.addPart(
//						Headers.of("Content-Disposition", "from-data;name=\""
//								+ entry.getKey() + "\""),
//						RequestBody.create(null, entry.getValue()));
//			}
//		} 
//		// 第二部分
//		if (files != null) {
//			for (int i = 0; i < files.length; i++) {
//				File file = files[i];
//				String fileName = file.getName();
//
//				RequestBody requestBody = RequestBody.create(
//						MediaType.parse(getMimeType(fileName)), file);
//				// 添加file inpurt 数据块
//				builder.addPart(
//						Headers.of("Content-Disposition", "from-data;name=\""
//								+ formFileName[i] + "\";filenname=\""
//								+ fileName + "\""), requestBody);
//			}
//		} 
//		return builder.build();
//	}

	private static String getMimeType(String filename) {
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String contentTypeFor = fileNameMap.getContentTypeFor(filename);
		if (contentTypeFor == null) {
			contentTypeFor = "application/octet-stream";
		}
		return contentTypeFor;
	}
	
	
	public static String File(String url, Map<String, Object> map){
		
		

		return url;
	}
}
