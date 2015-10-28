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
import com.qurater.pivotal.adapter.IterationAdapter;
import com.qurater.pivotal.api.Font;
import com.qurater.pivotal.api.Iteration;
import com.qurater.pivotal.api.QuraterConstants;
import com.qurater.pivotal.interfaces.IIterationsConsumer;
import com.qurater.pivotal.interfaces.IScrollable;
import com.qurater.pivotal.rest.IterationsApi;
import com.qurater.pivotal.ui.ProjectUIHelper;
import com.qurater.pivotal.ui.ScrollListener;

public class IterationsFragment extends Fragment implements IIterationsConsumer, IScrollable {
    protected View rootView;
    protected IterationAdapter adapter;
    protected ProjectActivity activity;
    protected Long projectId;
    
    public IterationsFragment() {
    }
    
    public IterationsFragment(ProjectActivity activity) {
    	this.activity = activity;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.rootView = inflater.inflate(R.layout.activity_iterations, container, false);
        getActivity().getActionBar().hide();
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        ListView iterations = (ListView) getActivity().findViewById(R.id.iterations);
        adapter = new IterationAdapter(this);
        iterations.setAdapter(adapter);
        iterations.setOnScrollListener(new ScrollListener(this));
        
        this.projectId = activity.getIntent().getLongExtra(QuraterConstants.INTENT_PROJECT_ID, Long.valueOf(-1));
        IterationsApi api = new IterationsApi(this);
        api.getIterations(projectId);

        setTitle();
        
        ProjectUIHelper helper = new ProjectUIHelper(getActivity(), projectId);
        helper.init();
    }
    
	@Override
	public void onIterationsLoadSuccess(List<Iteration> iterations) {
		getActivity().findViewById(R.id.progress).setVisibility(View.GONE);
		adapter.update(iterations);
	}

	@Override
	public void onIterationsLoadFailure(String reason) {
		getActivity().findViewById(R.id.progress).setVisibility(View.GONE);
	}

	@Override
	public Context getApplicationContext() {
		return activity;
	}
	
	protected void setTitle() {
        TextView tvTitle = ((TextView)getActivity().findViewById(R.id.title));
        tvTitle.setText(getActivity().getResources().getString(R.string.done));
        Typeface typeface = Font.getFont(getActivity().getApplicationContext(), "Lato-Bold.ttf");
        tvTitle.setTypeface(typeface);
	}

	@Override
	public void getPage(int page) {
		IterationsApi api = new IterationsApi(this);
        api.getIterations(projectId, page);
	}
} 