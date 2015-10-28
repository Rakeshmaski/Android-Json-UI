package com.qurater.pivotal.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qurater.pivotal.R;
import com.qurater.pivotal.activity.ProjectActivity;
import com.qurater.pivotal.activity.ProjectListActivity;
import com.qurater.pivotal.api.Font;
import com.qurater.pivotal.api.Project;
import com.qurater.pivotal.api.QuraterConstants;


public class ProjectAdapter extends BaseAdapter {
 
    protected List<Project> projects;
    protected ProjectListActivity activity;
 
    public ProjectAdapter(ProjectListActivity activity) {
        this.activity = activity;
    }
       
    @Override
    public int getCount() {
        return (projects != null)? projects.size(): 0;
    }
 
    @Override
    public Object getItem(int position) {
        return (projects != null)? projects.get(position): null;
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    public Activity getActivity() {
        return this.activity;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {                                           
        View layout;
        if (convertView == null) {
            layout = getActivity().getLayoutInflater().inflate(
                     R.layout.one_project, 
                     parent, 
                     false);
        } else {
            layout = (RelativeLayout) convertView;
        }
          
        final Project project = projects.get(position); 
        Typeface typefaceLatoBold = Font.getFont(getActivity().getApplicationContext(), "Lato-Bold.ttf");
        Typeface typefaceLatoRegular = Font.getFont(getActivity().getApplicationContext(), "Lato-Regular.ttf");
        
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
        	public void onClick(View v) {
                Activity activity = getActivity();
        		Intent intent = new Intent(getActivity(), ProjectActivity.class);
                intent.putExtra(QuraterConstants.INTENT_PROJECT_ID, project.getId());
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_left_detail, R.anim.stay_in_place_detail);
			 }
		});
         
        TextView tv = (TextView)layout.findViewById(R.id.name);
        tv.setTypeface(typefaceLatoBold);
        String name = project.getName();
        tv.setText(name);
        
        tv = (TextView)layout.findViewById(R.id.current_velocity_label);
        tv.setTypeface(typefaceLatoBold);
        Integer velocity = project.getCurrentVelocity();
        if (velocity != null) {
	        tv = (TextView)layout.findViewById(R.id.current_velocity);
	        tv.setTypeface(typefaceLatoRegular);
	        tv.setText(String.valueOf(velocity));
        }
        
        tv = (TextView)layout.findViewById(R.id.current_volatility_label);
        tv.setTypeface(typefaceLatoBold);
        Double volatility = project.getCurrentVolatility();
        if (volatility != null) {
	        tv = (TextView)layout.findViewById(R.id.current_volatility);
	        tv.setTypeface(typefaceLatoRegular);
	        tv.setText(volatility + "%");
        }
        return layout;
    }
 
    public void clear() {
        projects.clear();
        notifyDataSetChanged();
    }
       
    public void update(List<Project> projects) {
        projects = (projects == null)? new ArrayList<Project>(): projects; 
        if (this.projects != null) {     
        	System.out.println("in ProjectAdapter : update method");
            this.projects.addAll(projects);
        } else {
        	System.out.println("projects null:null");
            this.projects = projects;
        }
        notifyDataSetChanged();
    } 
}