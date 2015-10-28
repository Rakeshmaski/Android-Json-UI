package com.qurater.pivotal.adapter;

import android.app.Activity;

import com.qurater.pivotal.activity.UserStoriesActivity;


public class UserStoryAdapter extends StoryAdapter {
 
    protected UserStoriesActivity activity;
 
    public UserStoryAdapter(UserStoriesActivity activity) {
        this.activity = activity;
    }
       
    @Override
    public Activity getActivity() {
    	return activity;
    }
    
}