package com.qurater.pivotal.ui;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.qurater.pivotal.api.PivotalConstants;
import com.qurater.pivotal.api.Utils;

public class StoryTypeCircle extends View {  
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);  
    private Context context;     
    private float radius;
    private String storyType;
    
    public StoryTypeCircle(Context context, float radius, String storyType) {  
        super(context);  
        this.context = context;
        this.radius = radius;
        this.storyType = storyType;
    }
       
    @Override  
    protected void onDraw(Canvas canvas) {  
        super.onDraw(canvas);  
        canvas.setDensity(Bitmap.DENSITY_NONE);
        int color = Color.argb(255, 255, 255, 255);    
        paint.setStyle(Paint.Style.FILL);
        if (PivotalConstants.FEATURE.equals(storyType)) {
        	color = Color.rgb(220, 196, 87);
        } else if (PivotalConstants.BUG.equals(storyType)) {
        	color = Color.rgb(216, 115, 80);
        } else if (PivotalConstants.CHORE.equals(storyType)) {
        	color = Color.rgb(150, 150, 150);
        } else if (PivotalConstants.RELEASE.equals(storyType)) {
        	color = Color.rgb(243, 243, 209);
        }
        paint.setColor(color);
        canvas.drawCircle(Utils.getPixels(context, radius), Utils.getPixels(context, radius), Utils.getPixels(context, radius), paint);
    }

}
   