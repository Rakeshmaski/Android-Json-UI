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
import com.qurater.pivotal.activity.IterationStoriesActivity;
import com.qurater.pivotal.activity.IterationsFragment;
import com.qurater.pivotal.api.Font;
import com.qurater.pivotal.api.Iteration;
import com.qurater.pivotal.api.QuraterConstants;
import com.qurater.pivotal.api.Utils;


public class IterationAdapter extends BaseAdapter {
 
    protected List<Iteration> iterations;
    protected IterationsFragment activity;
 
    public IterationAdapter() {
    }
    
    public IterationAdapter(IterationsFragment activity) {
        this.activity = activity;
    }
       
    @Override
    public int getCount() {
        return (iterations != null)? iterations.size(): 0;
    }
 
    @Override
    public Object getItem(int position) {
        return (iterations != null)? iterations.get(position): null;
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
 
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {                                           
        View layout;
        if (convertView == null) {
            layout = getActivity().getLayoutInflater().inflate(
                     R.layout.one_iteration, 
                     parent, 
                     false);
        } else {
            layout = (RelativeLayout) convertView;
        }
          
        final Iteration iteration = getIteration(position); 
        Typeface typefaceLatoRegular = Font.getFont(getActivity().getApplicationContext(), "Lato-Regular.ttf");
        Typeface typefaceLatoBold = Font.getFont(getActivity().getApplicationContext(), "Lato-Bold.ttf");
        
        TextView tv = (TextView)layout.findViewById(R.id.label_iteration);
        tv.setTypeface(typefaceLatoBold);
        
        tv = (TextView)layout.findViewById(R.id.iteration_number);
        tv.setTypeface(typefaceLatoBold);
        tv.setText(String.valueOf(iteration.getNumber()));
        
        tv = (TextView)layout.findViewById(R.id.label_start);
        tv.setTypeface(typefaceLatoBold);
        
        tv = (TextView)layout.findViewById(R.id.start);
        tv.setTypeface(typefaceLatoRegular);
        tv.setText(Utils.getFormattedDate(iteration.getStartDate()));
        
        tv = (TextView)layout.findViewById(R.id.label_end);
        tv.setTypeface(typefaceLatoBold);
        
        tv = (TextView)layout.findViewById(R.id.end);
        tv.setTypeface(typefaceLatoRegular);
        tv.setText(Utils.getFormattedDate(iteration.getFinishDate()));
        
        tv = (TextView)layout.findViewById(R.id.label_team_strength);
        tv.setTypeface(typefaceLatoBold);
        
        tv = (TextView)layout.findViewById(R.id.team_strength);
        tv.setTypeface(typefaceLatoRegular);
        tv.setText(String.valueOf(iteration.getTeamStrength()));
        
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
        	public void onClick(View v) {
                Activity activity = getActivity();
        		Intent intent = new Intent(getActivity(), IterationStoriesActivity.class);
                intent.putExtra(QuraterConstants.INTENT_PROJECT_ID, iteration.getProjectId());
                intent.putExtra(QuraterConstants.INTENT_ITERATION, iteration.getNumber());
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_left_detail, R.anim.stay_in_place_detail);
			 }
		});

        return layout;
    }
 
    public void clear() {
    	iterations.clear();
        notifyDataSetChanged();
    }
       
    public void update(List<Iteration> iterations) {
    	iterations = (iterations == null)? new ArrayList<Iteration>(): iterations; 
        if (this.iterations != null) {     
            this.iterations.addAll(iterations);
        } else {
            this.iterations = iterations;
        }
        notifyDataSetChanged();
    }
    
    public Activity getActivity() {
    	return activity.getActivity();
    }
    
    public Iteration getIteration(int position) {
    	return iterations.get(position);
    }
    
}