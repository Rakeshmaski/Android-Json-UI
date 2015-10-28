package com.qurater.pivotal.activity;

import android.graphics.Typeface;
import android.widget.TextView;

import com.qurater.pivotal.R;
import com.qurater.pivotal.api.Font;
import com.qurater.pivotal.rest.SearchApi;

public class UserStoriesFragment extends CurrentStoriesFragment {
    
	protected Long userId;
	
    public UserStoriesFragment() {
    	super();
    }
    
    public UserStoriesFragment(ProjectActivity activity, Long userId) {
    	super(activity);
    	this.userId = userId;
    }
    
    @Override
	protected void getStories(Long projectId) {
        SearchApi api = new SearchApi(this);
        api.getStoriesForOwner(projectId, userId);
	}
    
    @Override
    protected void setTitle() {
        TextView tvTitle = ((TextView)getActivity().findViewById(R.id.title));
        tvTitle.setText(getActivity().getResources().getString(R.string.my_work));
        Typeface typeface = Font.getFont(getActivity().getApplicationContext(), "Lato-Bold.ttf");
        tvTitle.setTypeface(typeface);
	}
} 