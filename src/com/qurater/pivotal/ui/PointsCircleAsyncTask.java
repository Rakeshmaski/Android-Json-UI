package com.qurater.pivotal.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.RelativeLayout;

public class PointsCircleAsyncTask extends AsyncTask<String, Void, Void> {
    protected RelativeLayout parentLayout;
    protected Context context;
    protected PointsCircle pointsCircle;
    protected float radius;

    
    public PointsCircleAsyncTask(RelativeLayout parentLayout, Context context, float radius) {
        this.parentLayout = parentLayout;
        this.context = context;
        this.radius = radius;
    }

    @Override
    protected Void doInBackground(String... params) {
    	pointsCircle = new PointsCircle(context, radius);
        return null;
    }
    
    @Override
    protected void onPostExecute(Void v) {
        parentLayout.addView(pointsCircle);
    }
}