package com.qurater.pivotal.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qurater.pivotal.R;
import com.qurater.pivotal.api.Font;
import com.qurater.pivotal.api.Label;


public class LabelAdapter extends BaseAdapter {
 
	protected List<Label> labels;
	protected Activity activity;
	
    public LabelAdapter(Activity activity) {
        this.activity = activity;
    }
       
    @Override
    public int getCount() {
        return (labels != null)? labels.size(): 0;
    }
 
    @Override
    public Object getItem(int position) {
        return (labels != null)? labels.get(position): null;
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
                     getViewResource(),
                     parent, 
                     false);
        } else {
            layout = (RelativeLayout) convertView;
        }
          
        final Label label = labels.get(position); 
        Typeface typefaceLatoRegular = Font.getFont(getActivity().getApplicationContext(), "Lato-Regular.ttf");
        

        TextView tv = (TextView)layout.findViewById(R.id.label);
        tv.setTypeface(typefaceLatoRegular);
        String name = label.getName();
        tv.setText(name);
        return layout;
    }
 
    public void clear() {
    	labels.clear();
        notifyDataSetChanged();
    }
       
    public void update(List<Label> labels) {
    	labels = (labels == null)? new ArrayList<Label>(): labels; 
        if (this.labels != null) {     
            this.labels.addAll(labels);
        } else {
            this.labels = labels;
        }
        notifyDataSetChanged();
    }
    
    protected int getViewResource() {
    	return R.layout.one_label;
    }
}