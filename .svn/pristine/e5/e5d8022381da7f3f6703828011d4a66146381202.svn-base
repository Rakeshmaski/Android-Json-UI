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
import com.qurater.pivotal.adapter.LabelAdapter;
import com.qurater.pivotal.adapter.LabelInProjectAdapter;
import com.qurater.pivotal.api.Font;
import com.qurater.pivotal.api.Label;
import com.qurater.pivotal.api.QuraterConstants;
import com.qurater.pivotal.interfaces.ILabelsConsumer;
import com.qurater.pivotal.rest.LabelApi;
import com.qurater.pivotal.ui.ProjectUIHelper;

public class LabelsFragment extends Fragment implements ILabelsConsumer {
    protected View rootView;
    protected LabelAdapter adapter;
    protected ProjectActivity activity;
    
    public LabelsFragment() {
    }
    
    public LabelsFragment(ProjectActivity activity) {
    	this.activity = activity;
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.rootView = getRootView(inflater, container);
        getActivity().getActionBar().hide();
        return rootView;
    }

    protected View getRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.activity_labels, container, false);
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView labels = (ListView) getActivity().findViewById(R.id.all_labels);
        adapter = new LabelInProjectAdapter(this.getActivity());
        labels.setAdapter(adapter);
        Long projectId = activity.getIntent().getLongExtra(QuraterConstants.INTENT_PROJECT_ID, Long.valueOf(-1));
        LabelApi api = new LabelApi(this);
        api.getLabels(projectId);

        setTitle();
        
        //
        ProjectUIHelper helper = new ProjectUIHelper(getActivity(), projectId);
        helper.init();

    }
    
	@Override
	public void onLabelsLoadSuccess(List<Label> labels) {
		getActivity().findViewById(R.id.progress).setVisibility(View.GONE);
		adapter.update(labels);
	}

	@Override
	public void onLabelsLoadFailure(String reason) {
		getActivity().findViewById(R.id.progress).setVisibility(View.GONE);
	}

	@Override
	public Context getApplicationContext() {
		return activity;
	}
	
	protected void setTitle() {
        TextView tvTitle = ((TextView)getActivity().findViewById(R.id.title));
        tvTitle.setText(getActivity().getResources().getString(R.string.labels));
        Typeface typeface = Font.getFont(getActivity().getApplicationContext(), "Lato-Bold.ttf");
        tvTitle.setTypeface(typeface);
	}
} 