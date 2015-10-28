package com.qurater.pivotal.rest;

import android.content.Context;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.qurater.pivotal.api.RestClient;
import com.qurater.pivotal.storage.LoggedInUserStore;


public class RemoteDataFetcher {
    
	public static void login(String url, String username, String password, Context context, final IDataSink sink) {
		final PersistentCookieStore myCookieStore = new PersistentCookieStore(context);
        AsyncHttpResponseHandler asyncHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                sink.receive(response);
            }
            @Override
            public void onFailure(Throwable e, String response) {
                sink.error(e, response);
            }
        };
        
        RestClient.setCookieStore(myCookieStore);
        RestClient.login(url, username, password, new RequestParams(), asyncHandler);
    }
	
    public static void get(String url, Context context, final IDataSink sink) {
        RemoteDataFetcher.get(url, new RequestParams(), context, sink);
    }
    
    public static void get(String url, RequestParams params, Context context, final IDataSink sink) {
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(context);
        AsyncHttpResponseHandler asyncHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                sink.receive(response);
            }
            @Override
            public void onFailure(Throwable e, String response) {
                sink.error(e, response);
            }
        };
        
        RestClient.setCookieStore(myCookieStore);
        String token = LoggedInUserStore.getLoggedInUserToken(context);
        RestClient.get(url, token, params, asyncHandler);
    }
    
    public static void put(String url, Context context, final IDataSink sink) {
        RemoteDataFetcher.put(url, new RequestParams(), context, sink);
    }
    
    public static void put(String url, RequestParams params, Context context, final IDataSink sink) {
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(context);
        AsyncHttpResponseHandler asyncHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                sink.receive(response);
            }
            @Override
            public void onFailure(Throwable e, String response) {
                sink.error(e, response);
            }
        };
        params.put("_method", "PUT");
        RestClient.setCookieStore(myCookieStore);
        RestClient.post(url, params, asyncHandler);
    }
    
    public static void delete(String url, Context context, final IDataSink sink) {
        RemoteDataFetcher.delete(url, new RequestParams(), context, sink);
    }
    
    public static void delete(String url, RequestParams params, Context context, final IDataSink sink) {
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(context);
        AsyncHttpResponseHandler asyncHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                sink.receive(response);
            }
            @Override
            public void onFailure(Throwable e, String response) {
                sink.error(e, response);
            }
        };
        params.put("_method", "DELETE");
        RestClient.setCookieStore(myCookieStore);
        RestClient.post(url, params, asyncHandler);
    }
}