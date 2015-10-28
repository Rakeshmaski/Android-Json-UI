package com.qurater.pivotal.api;


import org.apache.http.client.params.ClientPNames;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;


public class RestClient{
    
    private final static int DEFAULT_TIMEOUT = 20 * 1000;
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void login(String url, String username, String password, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        try {
            client.getHttpClient().getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
            client.setBasicAuth(username, password);
        } catch(Exception e) {
            //Added later - dont know what issues this might cause -not needed anywhere except for logoutoodja user 
        }
        client.setTimeout(DEFAULT_TIMEOUT);
        client.get(url, params, responseHandler);
    }
    
    public static void get(String url, String token, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        try {
            client.getHttpClient().getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
        } catch(Exception e) {
            //Added later - dont know what issues this might cause -not needed anywhere except for logoutoodja user 
        }
        client.addHeader("X-TrackerToken", token);
        client.setTimeout(DEFAULT_TIMEOUT);
        client.get(url, params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setTimeout(DEFAULT_TIMEOUT);
        client.setMaxRetriesAndTimeout(3, DEFAULT_TIMEOUT);
        client.post(url, params, responseHandler);
    }
    
    public static void setCookieStore(PersistentCookieStore cookieStore) {
        client.setCookieStore(cookieStore);
    }
}