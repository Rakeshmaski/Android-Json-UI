package com.qurater.pivotal.ui;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.qurater.pivotal.api.Utils;

public class PointsCircle extends View {  
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);  
    private Context context;     
    private float radius;
    
    public PointsCircle(Context context, float radius) {  
        super(context);  
        this.context = context;
        this.radius = radius;
    }
       
    @Override  
    protected void onDraw(Canvas canvas) {  
        super.onDraw(canvas);  
        canvas.setDensity(Bitmap.DENSITY_NONE);
        int color = Color.argb(255, 255, 255, 255);    
        
        //int px = Utils.getPixels(context, 1.0f);
        //paint.setStrokeWidth(px);
        //paint.setStyle(Paint.Style.STROKE);
        //color = Color.argb(255,  204, 204,  204);    
        //paint.setColor(color);
        //canvas.drawCircle(Utils.getPixels(context, radius), Utils.getPixels(context, radius), Utils.getPixels(context, radius), paint);
        paint.setStyle(Paint.Style.FILL);
        color = Color.argb(60,  99, 190,  187);    	
        paint.setColor(color);
        canvas.drawCircle(Utils.getPixels(context, radius), Utils.getPixels(context, radius), Utils.getPixels(context, radius), paint);
    }

}
   