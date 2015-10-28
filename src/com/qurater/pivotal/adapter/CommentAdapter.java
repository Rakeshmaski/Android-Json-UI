package com.qurater.pivotal.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qurater.pivotal.R;
import com.qurater.pivotal.activity.StoryActivity;
import com.qurater.pivotal.api.Attachment;
import com.qurater.pivotal.api.Comment;
import com.qurater.pivotal.api.Font;
import com.qurater.pivotal.api.Person;
import com.qurater.pivotal.api.Project;
import com.qurater.pivotal.api.Story;
import com.qurater.pivotal.api.Utils;
import com.qurater.pivotal.rest.ProjectsApi;
import com.qurater.pivotal.ui.TwoWayView;
import com.qurater.pivotal.ui.UserClickableSpan;


public class CommentAdapter extends BaseAdapter {
 
	protected List<Comment> comments;
	protected Activity activity;
	private BaseAdapter parentAdapter;
	private Story story;
	
    public CommentAdapter(StoryActivity activity, BaseAdapter parentAdapter, Story story) {
        this.activity = activity;
        this.parentAdapter = parentAdapter;
        this.story = story;
    }
       
    @Override
    public int getCount() {
        return (comments != null)? comments.size(): 0;
    }
 
    @Override
    public Object getItem(int position) {
        return (comments != null)? comments.get(position): null;
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
                     R.layout.one_comment, 
                     parent, 
                     false);
        } else {
            layout = convertView;
        }
          
        final Comment comment = comments.get(position); 
        Typeface typefaceLatoRegular = Font.getFont(getActivity().getApplicationContext(), "Lato-Regular.ttf");
        Typeface typefaceLatoBold = Font.getFont(getActivity().getApplicationContext(), "Lato-Bold.ttf");
        Typeface typefaceDS = Font.getFont(getActivity().getApplicationContext(), "DroidSerif.ttf");
        
        TextView tv = (TextView)layout.findViewById(R.id.text);
        tv.setTypeface(typefaceLatoRegular);
        String text = comment.getText();
        tv.setText(text);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        Linkify.addLinks(tv, Linkify.WEB_URLS);
        
        View sectionHeader = layout.findViewById(R.id.comment_section_header);
        if (position == 0) {
        	sectionHeader.setVisibility(View.VISIBLE);
        	TextView sectionHeaderTitle = (TextView)layout.findViewById(R.id.title_section_header);
        	sectionHeaderTitle.setTypeface(typefaceLatoBold);
        	sectionHeaderTitle.setText(activity.getResources().getString(R.string.comments));
        } else {
        	sectionHeader.setVisibility(View.GONE);
        }
        
        //
        List<Attachment> attachments = comment.getAttachments();
        AttachmentAdapter attachmentAdapter = new AttachmentAdapter(activity, this);
        TwoWayView listview = (TwoWayView) layout.findViewById(R.id.attachments);
        if (attachments != null && attachments.size() > 0) {
        	listview.setVisibility(View.VISIBLE);
        	listview.setAdapter(attachmentAdapter);
            attachmentAdapter.update(attachments);
        } else {
        	listview.setVisibility(View.GONE);
        }
        
        Long personId = comment.getPersonId();
        Project currentProject = null;
		Map<Long, Project> m_projects = ProjectsApi.getStoredProjectsMap(getActivity());
        if (m_projects != null) {
        	currentProject = m_projects.get(story.getProjectId());
        }
    	if (currentProject != null) {
            Person person = currentProject.getMember(personId);
            if (person != null) {
	            tv = (TextView)layout.findViewById(R.id.owner);
	            SpannableString ss = new SpannableString(person.getName());
                UserClickableSpan ucs = new UserClickableSpan(this.getActivity(), story.getProjectId(), person.getId());
                ss.setSpan(ucs, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv.setText(ss);
                tv.setMovementMethod(LinkMovementMethod.getInstance());
            }
    	}
    	tv = (TextView)layout.findViewById(R.id.create_date);
        tv.setTypeface(typefaceDS, Typeface.ITALIC);
        if (comment.getCreateDate() != null) {
            tv.setText(Utils.getTimeElapsed(comment.getCreateDate()));
        }
        return layout;
    }
 
    public void clear() {
    	comments.clear();
        notifyDataSetChanged();
    }
       
    public void update(List<Comment> comments) {
    	comments = (comments == null)? new ArrayList<Comment>(): comments; 
        if (this.comments != null) {     
            this.comments.addAll(comments);
        } else {
            this.comments = comments;
        }
        notifyDataSetChanged();
        parentAdapter.notifyDataSetChanged();
    } 
}