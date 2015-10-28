package com.qurater.pivotal.adapter;

import java.util.List;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qurater.pivotal.R;
import com.qurater.pivotal.activity.StoryActivity;
import com.qurater.pivotal.api.Comment;
import com.qurater.pivotal.api.Story;


public class StoryDetailAdapter extends StoryAdapter {
    private StoryActivity activity;
    private BaseAdapter parentAdapter;
    
    public StoryDetailAdapter() {
    }
    
    public StoryDetailAdapter(StoryActivity activity, BaseAdapter parentAdapter) {
        this.activity = activity;
        this.parentAdapter = parentAdapter;
    }
    
    @Override
    public void update(List<Story> stories) {
        super.update(stories);
        parentAdapter.notifyDataSetChanged();
    } 

  
    @Override
    public int getItemViewType(int position) {
    	//Define a way to determine which layout to use, here it's just evens and odds.
    	return 0;
    }

    @Override
    public int getViewTypeCount() {
    	return 1; // Count of different layouts
    }
    
    @Override
    public Activity getActivity() {
    	return activity;
    }
    
    @Override
    public String getStoryDescription(Story story) {
    	String description = story.getDescription();
        description = (description == null)? "": description;
        return description;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	View v = super.getView(position, convertView, parent);
    	TextView tv = (TextView)v.findViewById(R.id.tv_answer_count);
    	final Story story = getStory(position); 
    	List<Comment> comments = story.getComments();
    	int numComments = (comments == null)? 0: comments.size();
    	tv.setText(String.valueOf(numComments));
    	return v;
    }
    
    @Override 
    protected void setClickListener(View layout, final Story story) {
    	//do nothing in detail
    }
    
}