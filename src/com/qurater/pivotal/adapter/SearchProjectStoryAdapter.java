package com.qurater.pivotal.adapter;

import android.app.Activity;

import com.qurater.pivotal.activity.SearchProjectStoriesActivity;


public class SearchProjectStoryAdapter extends StoryAdapter {
 
    protected SearchProjectStoriesActivity activity;
 
    public SearchProjectStoryAdapter(SearchProjectStoriesActivity activity) {
        this.activity = activity;
    }
       
    @Override
    public Activity getActivity() {
    	return activity;
    }
    
}