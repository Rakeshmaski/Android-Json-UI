package com.qurater.pivotal.storage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.qurater.pivotal.api.QuraterConstants;


public class LoggedInUserStore {
    
    /**
     * Store recent documents
     */
    public static void storeLoggedInUser(Context context, JSONObject user) {
        if (context != null) {
            final SharedPreferences prefs = context.getSharedPreferences(QuraterConstants.USER_PREFERENCES, Context.MODE_PRIVATE);
            Editor editor = prefs.edit();
            editor.putString(QuraterConstants.USER_PREFERENCES_USER, user.toString());
            editor.apply();
        }
    }
    
    /**
     * Store recent documents
     */
    public static void clearLoggedInUser(Context context) {
        if (context != null) {
            final SharedPreferences prefs = context.getSharedPreferences(QuraterConstants.USER_PREFERENCES, Context.MODE_PRIVATE);
            Editor editor = prefs.edit();
            editor.putString(QuraterConstants.USER_PREFERENCES_USER, null);
            editor.apply();
        }
    }
    
	public static JSONObject getLoggedInUser(Context context) {
        if (context != null) {
            final SharedPreferences prefs = context.getSharedPreferences(QuraterConstants.USER_PREFERENCES, Context.MODE_PRIVATE);
            String sUser = prefs.getString(QuraterConstants.USER_PREFERENCES_USER, null);
            try {
                return new JSONObject(sUser);
            } catch(Exception e) {
                return null;
            }
        }
        return null;
    }
    
    public static Long getLoggedInUserId(Context context) {
        return getUserLongProperty(context, "id");
    }
        
    public static String getLoggedInUserName(Context context) {
    	return getUserProperty(context, "name");
    }
    
    public static String getLoggedInUserEmail(Context context) {
    	return getUserProperty(context, "email");
    }
    
    public static String getLoggedInUserToken(Context context) {
    	return getUserProperty(context, "api_token");
    }
    
    public static boolean isLoggedIn(Context context) {
        if (context != null) {
            return (getLoggedInUser(context) != null);
        }
        return false;
    }
    
    /**
     * Store recent documents
     */
    public static void storeLoggedInUserProjects(Context context, JSONArray projects) {
        if (context != null) {
            final SharedPreferences prefs = context.getSharedPreferences(QuraterConstants.USER_PREFERENCES, Context.MODE_PRIVATE);
            Editor editor = prefs.edit();
            editor.putString(QuraterConstants.USER_PREFERENCES_PROJECTS, projects.toString());
            editor.apply();
        }
    }

    public static JSONArray getLoggedInUserProjects(Context context) {
        if (context != null) {
            final SharedPreferences prefs = context.getSharedPreferences(QuraterConstants.USER_PREFERENCES, Context.MODE_PRIVATE);
            String sProjects = prefs.getString(QuraterConstants.USER_PREFERENCES_PROJECTS, null);
            try {
                return new JSONArray(sProjects);
            } catch(Exception e) {
                return null;
            }
        }
        return null;
    }
    
    private static String getUserProperty(Context context, String property) {
        JSONObject user = getLoggedInUser(context);
        String result = null;
        try {
        	if(user != null){	        	
				result = user.getString(property);
        	}
		} catch (JSONException e) {}
        return result;
    }
    
    private static Long getUserLongProperty(Context context, String property) {
        JSONObject user = getLoggedInUser(context);
        Long result = null;
        try {
        	if(user != null){
        		result = user.getLong(property);
        	}
		} catch (JSONException ignnore) {}
        return result;
    }
}