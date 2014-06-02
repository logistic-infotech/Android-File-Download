package com.logistic.restclient;
import com.loopj.android.http.*;

public class LIRestClient {
	
  private static final String BASE_URL = "http://www.logisticinfotech.com/";
  
  private static AsyncHttpClient client = new AsyncHttpClient();

  public static void downloadFile(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
      client.get(url, params, responseHandler);
  }

  private static String getAbsoluteUrl(String relativeUrl) {
      return BASE_URL + relativeUrl;
  }
  
}
