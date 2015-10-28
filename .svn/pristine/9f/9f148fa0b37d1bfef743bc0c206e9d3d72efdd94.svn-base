package com.qurater.pivotal.activity;

import android.graphics.Typeface;
import android.widget.TextView;

import com.qurater.pivotal.R;
import com.qurater.pivotal.api.Font;
import com.qurater.pivotal.rest.StoriesApi;

public class IceboxStoriesFragment extends CurrentStoriesFragment {
    
    public IceboxStoriesFragment() {
    	super();
    }
    
    public IceboxStoriesFragment(ProjectActivity activity) {
    	super(activity);
    }
    
    @Override
	protected void getStories(Long projectId) {
    	StoriesApi api = new StoriesApi(this);
        api.getIceboxStories(projectId);
	}
    
    @Override
    protected void setTitle() {
        TextView tvTitle = ((TextView)getActivity().findViewById(R.id.title));
        tvTitle.setText(getActivity().getResources().getString(R.string.icebox));
        Typeface typeface = Font.getFont(getActivity().getApplicationContext(), "Lato-Bold.ttf");
        tvTitle.setTypeface(typeface);
	}
} 