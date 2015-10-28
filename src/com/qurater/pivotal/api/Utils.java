package com.qurater.pivotal.api;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.TextView;

import com.loopj.android.http.PersistentCookieStore;

public class Utils {
    
	public static Date parseISO8601(String dt) {
		DateFormat m_ISO8601Local = new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss");
		try {
			return m_ISO8601Local.parse(dt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
    public static String getTimeElapsed(String s) {
        try {
            Date dtCreate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(s);
            return Utils.getTimeElapsed(dtCreate);
        } catch(Exception e) {
            return "";
        }
    }
    public static String  getTimeElapsed(Date date) {
        Date ts = new Date();
        long seconds = (ts.getTime() - date.getTime())/1000l;
        String elapsed ="";
        if(seconds<3600l) {
            elapsed = "few min ago";
        }
        else if(seconds<86400l) {
            int hours = (int)(seconds/3600l);
            elapsed = hours + ((hours == 1)? " hour ago": " hours ago");
        }
        else {
            int days = (int)(seconds/(86400l));
            elapsed = days + ((days == 1)? " day ago": " days ago");
        }
        return elapsed;
    }
    
    public static String  getTimeDifference(long ts) {
        long diffDays = ts / (24 * 60 * 60 * 1000);
        long diffHours= (ts - (diffDays * 86400l * 1000)) / (60 * 60 * 1000);
        long diffMinutes = (ts - ((diffDays * 86400l * 1000l) + (diffHours * 3600l * 1000l))) / (60 * 1000);
        String ex = "expires in ";
        if (diffDays > 0) {
            ex += diffDays + ((diffDays == 1)? " day ": " days ");
        } 
        ex += diffHours + ((diffHours == 1)? " hour ": " hours ");
        ex += "and ";
        ex += diffMinutes + ((diffMinutes == 1)? " minute. ": " minutes. ");
        return ex;
    }
    
    public static String  getFormattedDate(Date date) {
    	SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        return sdf.format(date);
    }
    
    public static String getDisplayUrl(String url) {
        if (url.startsWith("http://")) {
            url = url.substring(7);
        }
        if (url.startsWith("www.")) {
            url = url.substring(4);
        }
        if (url.indexOf("/") != -1) {
            url = url.substring(0, url.indexOf("/")); 
        }
        if (url.indexOf(".com") != -1) {
            url = url.substring(0, url.indexOf(".com")); 
        } else 
        if (url.indexOf(".net") != -1) {
            url = url.substring(0, url.indexOf(".net")); 
        } else
        if (url.indexOf(".org") != -1) {
            url = url.substring(0, url.indexOf(".org")); 
        }   
        return url;
    }
    
    public static String getSearchableUrl(String url) {
        if (url.startsWith("http://")) {
            url = url.substring(7);
        }   
        return url;
    }
    
    public static String convertToCamelCase(String s) {
        String[] parts = s.split(" ");
        String camelCaseString = "";
        for (String part : parts){
            camelCaseString = camelCaseString + " " + Character.toUpperCase(part.charAt(0)) + part.substring(1).toLowerCase();
        }
        return camelCaseString;
    }
    
    public static void setTitleBarTitle(Window window, AssetManager assets, ActionBar actionBar, String title) {
        int actionBarTitle = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
        TextView actionBarTitleView = (TextView) window.findViewById(actionBarTitle);
        if (actionBarTitleView != null) { 
            actionBarTitleView.setTextSize(18);
            //385772
            actionBarTitleView.setTextColor(Color.parseColor("#ffffff"));
            Typeface tf = Typeface.createFromAsset(assets, "AverageSans-Regular.ttf");
            if(actionBarTitleView != null){
                actionBarTitleView.setTypeface(tf);
            }
            
            actionBar.setTitle("   " + title);
        }
        
    }
    
    public static String[] parseTagsToStringArray(String tagString) throws JSONException{  
        List<String> lStanfordTags = new ArrayList<String>();
        if(tagString.trim().length()==0)
            return new String[]{};
        JSONObject stanfordTagsJson = new JSONObject(tagString);
        JSONObject org = stanfordTagsJson.getJSONObject("Organzation");
        @SuppressWarnings("rawtypes")
        Iterator keys = org.keys();
        while (keys.hasNext()) {
            lStanfordTags.add((String)keys.next());
        }
        JSONObject person = stanfordTagsJson.getJSONObject("Person");
        keys = person.keys();
        while (keys.hasNext()) {
            //Discard single names
            String key = (String)keys.next();
            if (key.indexOf(" ") != -1) {
                lStanfordTags.add(key);
            }
        }
        JSONObject location = stanfordTagsJson.getJSONObject("Location");
        keys = location.keys();
        while (keys.hasNext()) {
            lStanfordTags.add((String)keys.next());
        }
       return (String[])lStanfordTags.toArray(new String[lStanfordTags.size()]);
    }
    
    public static void printCookies(PersistentCookieStore myCookieStore) {
        //List<Cookie> cookies = myCookieStore.getCookies();
        //for (Cookie c: cookies) {
            //System.err.println(c.getName() + " " + c.getDomain() + " " + c.getPath() + " " + c.getValue() + " " + c.getExpiryDate());
        //}
    }
    /**
     * Parse hashtags from text
     */
    public static String parseHashtags(String text) {
        String patternStr = "(?:\\s|\\A)[##]+([A-Za-z0-9-_]+)";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(text);
        String result = "";
        // Search for Hashtags
        while (matcher.find()) {
            result = matcher.group();
            result = result.replace(" ", "");
            String search = result.replace("#", "");
            String searchHTML="<a href='http://search.twitter.com/search?q=" + search + "'>" + result + "</a>";
            text = text.replace(result,searchHTML);
        }
        return text;
    }
    
    /**
     * parse urls from text
     */
    public static String parseUrls(String text) {
        // Search for URLs
         if (text != null && text.contains("http:")) {
             int indexOfHttp = text.indexOf("http:");
             int endPoint = (text.indexOf(' ', indexOfHttp) != -1) ? text.indexOf(' ', indexOfHttp) : text.length();
             String url = text.substring(indexOfHttp, endPoint);
             String targetUrlHtml=  "<a href='" + url + "' target='_blank'>" + url  + "</a>";
             text = text.replace(url,targetUrlHtml);
         }
         return text;
    }
    
    /**
     * MediaScannerConnection provides a way for applications to pass a newly created or 
     * downloaded media file to the media scanner service. 
     * The media scanner service will read metadata from the file and add the file to the media content provider. 
     * The MediaScannerConnectionClient provides an interface for the media scanner service to return the
     *  Uri for a newly scanned file to the client of the MediaScannerConnection class.
     * @param context
     * @param file
     * @param listener
     */
    public static void scanMediaJpegFile(final Context context, final File file,
            final OnScanCompletedListener listener) {
        MediaScannerConnection
                .scanFile(context, new String[]{file.getAbsolutePath()}, new String[]{"image/jpg"},
                        listener);
    }
    
    public static File getCameraPhotoFile() {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(dir, "pivotal_" + System.currentTimeMillis() + ".jpg");
    }
    
    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();
        float bitmapRatio = (float)width /  (float)height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }   
    
    
    public static int getPixels(Context context, float dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        float fpixels = metrics.density * dp;
        int pixels = (int) (fpixels + 0.5f);
        return pixels;
    }
    
    public static boolean isHdpi(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int dpi = metrics.densityDpi;
        //System.err.println(dpi);
        if (dpi < 300) {
            return true;
        }
        return false;
    }
}