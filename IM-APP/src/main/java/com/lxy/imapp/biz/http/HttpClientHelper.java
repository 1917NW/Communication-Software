package com.lxy.imapp.biz.http;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpClientHelper {
 public static String sendPost(String urlParam, Object requestBody) throws HttpException, IOException {
  // 创建httpClient实例对象
  HttpClient httpClient = new HttpClient();
  // 设置httpClient连接主机服务器超时时间：15000毫秒
  httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
  // 创建post请求方法实例对象
  PostMethod postMethod = new PostMethod(urlParam);
  // 设置post请求超时时间
  postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
  postMethod.addRequestHeader("Content-Type", "application/json");

  postMethod.setRequestEntity(new StringRequestEntity(JSON.toJSONString(requestBody),"application/json", "utf-8"));

  httpClient.executeMethod(postMethod);

  String result = postMethod.getResponseBodyAsString();
  postMethod.releaseConnection();
  return result;
 }

 public static String sendGet(String urlParam, Map<String, String> requestParams) throws HttpException, IOException {
  // 创建httpClient实例对象
  HttpClient httpClient = new HttpClient();
  // 设置httpClient连接主机服务器超时时间：15000毫秒
  httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);

  // 拼接字符串
  if(requestParams!=null && requestParams.size() > 0){
    urlParam = urlParam + "?";
    Set<Map.Entry<String, String>> entries = requestParams.entrySet();
    for(Map.Entry<String, String> entry : entries){
     urlParam += (entry.getKey() + "=" + entry.getValue());
    }
  }
  // 创建GET请求方法实例对象
  GetMethod getMethod = new GetMethod(urlParam);
  // 设置post请求超时时间
  getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
  getMethod.addRequestHeader("Content-Type", "application/json");
  httpClient.executeMethod(getMethod);

  String result = getMethod.getResponseBodyAsString();
  getMethod.releaseConnection();
  return result;
 }
 public static void main(String[] args) throws HttpException, IOException {
  String url ="http://localhost:8888/imServer/serverUrl?userPhone=115053827183";


 }
}
