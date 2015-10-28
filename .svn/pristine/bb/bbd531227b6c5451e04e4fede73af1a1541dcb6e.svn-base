package com.qurater.pivotal.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Typeface;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qurater.pivotal.R;
import com.qurater.pivotal.activity.StoryActivity;
import com.qurater.pivotal.api.Font;
import com.qurater.pivotal.api.Task;


public class TaskAdapter extends BaseAdapter {
 
	protected List<Task> tasks;
	protected Activity activity;
	private BaseAdapter parentAdapter;
	
    public TaskAdapter(StoryActivity activity, BaseAdapter parentAdapter) {
        this.activity = activity;
        this.parentAdapter = parentAdapter;
    }
       
    @Override
    public int getCount() {
        return (tasks != null)? tasks.size(): 0;
    }
 
    @Override
    public Object getItem(int position) {
        return (tasks != null)? tasks.get(position): null;
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
                     R.layout.one_task, 
                     parent, 
                     false);
        } else {
            layout = convertView;
        }
          
        final Task task = tasks.get(position); 
        Typeface typefaceLatoRegular = Font.getFont(getActivity().getApplicationContext(), "Lato-Regular.ttf");
        Typeface typefaceLatoBold = Font.getFont(getActivity().getApplicationContext(), "Lato-Bold.ttf");
        
        TextView tv = (TextView)layout.findViewById(R.id.description);
        tv.setTypeface(typefaceLatoRegular);
        String text = task.getDescription();
        tv.setText(text);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        Linkify.addLinks(tv, Linkify.WEB_URLS);
        
        View sectionHeader = layout.findViewById(R.id.task_section_header);
        if (position == 0) {
        	sectionHeader.setVisibility(View.VISIBLE);
        	TextView sectionHeaderTitle = (TextView)layout.findViewById(R.id.title_section_header);
        	sectionHeaderTitle.setTypeface(typefaceLatoBold);
        	sectionHeaderTitle.setText(activity.getResources().getString(R.string.tasks));
        } else {
        	sectionHeader.setVisibility(View.GONE);
        }
        
        ImageView icoCompleted = (ImageView)layout.findViewById(R.id.chk_complete);
        if (task.getComplete()) {
        	icoCompleted.setImageResource(R.drawable.checkchecked_xhdpi);
        } else {
        	icoCompleted.setImageResource(R.drawable.checkunchecked_xhdpi);
        }
        return layout;
    }
 
    public void clear() {
    	tasks.clear();
        notifyDataSetChanged();
    }
       
    public void update(List<Task> tasks) {
    	tasks = (tasks == null)? new ArrayList<Task>(): tasks; 
        if (this.tasks != null) {     
            this.tasks.addAll(tasks);
        } else {
            this.tasks = tasks;
        }
        notifyDataSetChanged();
        parentAdapter.notifyDataSetChanged();
    } 
}