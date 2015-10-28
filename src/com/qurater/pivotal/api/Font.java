package com.qurater.pivotal.api;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Typeface;

public class Font {
    private static Map<String, Typeface> smTypefaces = new HashMap<String, Typeface>();
    public static synchronized Typeface getFont (Context context, String font) {
        if (smTypefaces.get(font) == null) {
            try {
                Typeface tf = Typeface.createFromAsset(context.getAssets(), font);
                if (null != tf) {
                    // TODO use maybe a weak reference?
                    smTypefaces.put(font, tf);
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    
        return smTypefaces.get(font);
    }
}