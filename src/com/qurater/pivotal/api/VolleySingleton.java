package com.qurater.pivotal.api;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

//http://arnab.ch/blog/2013/08/asynchronous-http-requests-in-android-using-volley/
public class VolleySingleton {
    
    private static VolleySingleton instance;
    private RequestQueue requestQueue;
 
    private VolleySingleton(Context context) {
        requestQueue = Volley.newRequestQueue(context);
 
    }
 
    public static VolleySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new VolleySingleton(context);
        }
        return instance;
    }
 
    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}