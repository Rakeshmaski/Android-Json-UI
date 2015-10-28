package com.qurater.pivotal.activity;

import java.util.List;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qurater.pivotal.R;
import com.qurater.pivotal.adapter.ProjectAdapter;
import com.qurater.pivotal.api.Font;
import com.qurater.pivotal.api.Project;
import com.qurater.pivotal.interfaces.IProjectsConsumer;
import com.qurater.pivotal.rest.ProjectsApi;


public class ProjectListActivity extends FragmentActivity implements IProjectsConsumer{
    protected ProgressBar progressBar;
    protected RelativeLayout noResultsMessageView;
    protected ProjectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        setContentView(R.layout.activity_projects);
        ListView projects = (ListView) findViewById(R.id.projects);
        
        //
        Typeface typefaceLatoBold = Font.getFont(getApplicationContext(), "Lato-Bold.ttf");
        TextView tvTitle = (TextView) findViewById(R.id.title);
        tvTitle.setTypeface(typefaceLatoBold);
        tvTitle.setText(getResources().getString(R.string.my_projects));
        
        adapter = new ProjectAdapter(this);
        projects.setAdapter(adapter);
        ProjectsApi api = new ProjectsApi(this);
        api.getProjects();
    }
    
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.slide_right_detail);   
    }
    
	@Override
	public void onProjectsLoadSuccess(List<Project> projects) {
		findViewById(R.id.progress).setVisibility(View.GONE);
		adapter.update(projects);
	}

	@Override
	public void onProjectsLoadFailure(String reason) {
		findViewById(R.id.progress).setVisibility(View.GONE);
		findViewById(R.id.ico_no_results).setVisibility(View.VISIBLE);
	}
}
