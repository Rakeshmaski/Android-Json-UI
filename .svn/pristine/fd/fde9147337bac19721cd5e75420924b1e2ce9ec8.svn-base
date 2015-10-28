package com.qurater.pivotal.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.RelativeLayout;

public class StoryTypeAsyncTask extends AsyncTask<String, Void, Void> {
    protected RelativeLayout parentLayout;
    protected Context context;
    protected StoryTypeCircle storyTypeCircle;
    protected float radius;
    protected String storyType;
    
    public StoryTypeAsyncTask(RelativeLayout parentLayout, Context context, float radius, String storyType) {
        this.parentLayout = parentLayout;
        this.context = context;
        this.radius = radius;
        this.storyType = storyType;
    }

    @Override
    protected Void doInBackground(String... params) {
    	storyTypeCircle = new StoryTypeCircle(context, radius, storyType);
        return null;
    }
    
    @Override
    protected void onPostExecute(Void v) {
        parentLayout.addView(storyTypeCircle);
    }
}