package com.qurater.pivotal.activity;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.qurater.pivotal.R;
import com.qurater.pivotal.adapter.StoryAdapter;
import com.qurater.pivotal.api.Font;
import com.qurater.pivotal.api.PivotalConstants;
import com.qurater.pivotal.api.QuraterConstants;
import com.qurater.pivotal.api.Story;
import com.qurater.pivotal.interfaces.IScrollable;
import com.qurater.pivotal.interfaces.IStoriesConsumer;
import com.qurater.pivotal.rest.IterationStoriesApi;
import com.qurater.pivotal.ui.ProjectUIHelper;
import com.qurater.pivotal.ui.ScrollListener;

public class CurrentStoriesFragment extends Fragment implements IStoriesConsumer , IScrollable {
    protected View rootView;
    protected StoryAdapter adapter;
    protected ProjectActivity activity;
    protected Long projectId;
    
    public CurrentStoriesFragment() {
    	System.out.println("CurrentStoriesFragment empty constructor");
    }
    
    public CurrentStoriesFragment(ProjectActivity activity) {
    	this.activity = activity;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	System.out.println("CurrentStoriesFragment:onCreateView");
        super.onCreate(savedInstanceState);
        this.rootView = getRootView(inflater, container);
        getActivity().getActionBar().hide();
        return rootView;
    }

    protected View getRootView(LayoutInflater inflater, ViewGroup container) {
    	System.out.println("CurrentStoriesFragment:getRootView");
        return inflater.inflate(R.layout.activity_stories, container, false);
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	System.out.println("CurrentStoriesFragment:onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        ListView stories = (ListView) getActivity().findViewById(R.id.stories);
        adapter = new StoryAdapter(this);
        stories.setAdapter(adapter);
        stories.setOnScrollListener(new ScrollListener(this));
        
        this.projectId = activity.getIntent().getLongExtra(QuraterConstants.INTENT_PROJECT_ID, Long.valueOf(-1));
        getStories(projectId);

        setTitle();
        ProjectUIHelper helper = new ProjectUIHelper(getActivity(), projectId);
        helper.init();

    }
    
	@Override
	public void onStoriesLoadSuccess(List<Story> stories) {
		getActivity().findViewById(R.id.progress).setVisibility(View.GONE);
		adapter.update(stories);
	}

	@Override
	public void onStoriesLoadFailure(String reason) {
		getActivity().findViewById(R.id.progress).setVisibility(View.GONE);
		getActivity().findViewById(R.id.ico_no_results).setVisibility(View.VISIBLE);
	}

	@Override
	public Context getApplicationContext() {
		return activity;
	}
	
	protected void getStories(Long projectId) {
		System.out.println("CurrentStoriesFragment:getStories");
		IterationStoriesApi api = new IterationStoriesApi(this);
        api.getStories(projectId, PivotalConstants.CURRENT_SCOPE);

	}
	
	protected void setTitle() {
        TextView tvTitle = ((TextView)getActivity().findViewById(R.id.title));
        tvTitle.setText(getActivity().getResources().getString(R.string.current_stories));
        Typeface typeface = Font.getFont(getActivity().getApplicationContext(), "Lato-Bold.ttf");
        tvTitle.setTypeface(typeface);
	}
	
	@Override
	public void getPage(int page) {
		IterationStoriesApi api = new IterationStoriesApi(this);
		api.getStories(projectId, PivotalConstants.CURRENT_SCOPE, page);
	}
} 