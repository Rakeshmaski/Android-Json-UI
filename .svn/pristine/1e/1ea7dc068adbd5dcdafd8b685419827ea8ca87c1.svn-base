package com.qurater.pivotal.activity;

import android.graphics.Typeface;
import android.widget.TextView;

import com.qurater.pivotal.R;
import com.qurater.pivotal.api.Font;
import com.qurater.pivotal.api.PivotalConstants;
import com.qurater.pivotal.rest.IterationStoriesApi;

public class BacklogStoriesFragment extends CurrentStoriesFragment {
    
    public BacklogStoriesFragment() {
    	super();
    }
    
    public BacklogStoriesFragment(ProjectActivity activity) {
    	super(activity);
    }
    
    @Override
	protected void getStories(Long projectId) {
    	IterationStoriesApi api = new IterationStoriesApi(this);
        api.getStories(projectId, PivotalConstants.BACKLOG_SCOPE);

	}
    
    @Override
    protected void setTitle() {
        TextView tvTitle = ((TextView)getActivity().findViewById(R.id.title));
        tvTitle.setText(getActivity().getResources().getString(R.string.backlog));
        Typeface typeface = Font.getFont(getActivity().getApplicationContext(), "Lato-Bold.ttf");
        tvTitle.setTypeface(typeface);
	}
} 