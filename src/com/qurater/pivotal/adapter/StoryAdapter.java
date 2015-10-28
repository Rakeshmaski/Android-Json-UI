package com.qurater.pivotal.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qurater.pivotal.R;
import com.qurater.pivotal.activity.CurrentStoriesFragment;
import com.qurater.pivotal.activity.StoryActivity;
import com.qurater.pivotal.api.Font;
import com.qurater.pivotal.api.Label;
import com.qurater.pivotal.api.Person;
import com.qurater.pivotal.api.PivotalConstants;
import com.qurater.pivotal.api.Project;
import com.qurater.pivotal.api.QuraterConstants;
import com.qurater.pivotal.api.Story;
import com.qurater.pivotal.api.Utils;
import com.qurater.pivotal.dialog.FollowerDialog;
import com.qurater.pivotal.rest.ProjectsApi;
import com.qurater.pivotal.ui.PointsCircleAsyncTask;
import com.qurater.pivotal.ui.StoryTypeAsyncTask;
import com.qurater.pivotal.ui.TwoWayView;
import com.qurater.pivotal.ui.UserClickableSpan;


public class StoryAdapter extends BaseAdapter {
 
	public static int MAX_LENGTH=200;
    protected List<Story> stories;
    protected CurrentStoriesFragment activity;
 
    public StoryAdapter() {
    }
    
    public StoryAdapter(CurrentStoriesFragment activity) {
        this.activity = activity;
    }
       
    @Override
    public int getCount() {
        return (stories != null)? stories.size(): 0;
    }
 
    @Override
    public Object getItem(int position) {
        return (stories != null)? stories.get(position): null;
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
                     R.layout.one_story, 
                     parent, 
                     false);
        } else {
            layout = (RelativeLayout) convertView;
        }
          
        final Story story = getStory(position); 
        Typeface typefaceLatoRegular = Font.getFont(getActivity().getApplicationContext(), "Lato-Regular.ttf");
        Typeface typefaceLatoBold = Font.getFont(getActivity().getApplicationContext(), "Lato-Bold.ttf");
        Typeface typefaceLatoBlack = Font.getFont(getActivity().getApplicationContext(), "Lato-Black.ttf");
        Typeface typefaceOpenSans = Font.getFont(getActivity().getApplicationContext(), "OpenSans-CondBold.ttf");
        
        TextView tv = (TextView)layout.findViewById(R.id.name);
        tv.setTypeface(typefaceLatoBold);
        String name = story.getName();
        tv.setText(name);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        Linkify.addLinks(tv, Linkify.WEB_URLS);
        
        tv = (TextView)layout.findViewById(R.id.story_type);
        tv.setTypeface(typefaceOpenSans);
        String storyType = story.getStoryType();
        storyType = (storyType == null)? PivotalConstants.FEATURE: storyType;
        tv.setText(String.valueOf(storyType.charAt(0)).toUpperCase());
        RelativeLayout rl = (RelativeLayout)layout.findViewById(R.id.story_type_circle);
        rl.removeAllViews();
        StoryTypeAsyncTask asyncStoryType = new StoryTypeAsyncTask(
                rl, 
                getActivity().getApplicationContext(),
                13f,
                storyType
                );
        asyncStoryType.execute();
        
        tv = (TextView)layout.findViewById(R.id.description);
        tv.setTypeface(typefaceLatoRegular);
        String description = getStoryDescription(story);
        tv.setText(description);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        Linkify.addLinks(tv, Linkify.WEB_URLS);
        
        tv = (TextView)layout.findViewById(R.id.owners);
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        Project currentProject = null;
		Map<Long, Project> m_projects = ProjectsApi.getStoredProjectsMap(getActivity());
        if (m_projects != null) {
        	currentProject = m_projects.get(story.getProjectId());
        }
    	if (currentProject != null) {
    		List<Long> ownerIds = story.getOwnerIds();
    		for (Long ownerId: ownerIds) {
                Person person = currentProject.getMember(ownerId);
                SpannableString ss = new SpannableString(person.getUsername() + " ");
                UserClickableSpan ucs = new UserClickableSpan(this.getActivity(), story.getProjectId(), person.getId());
                ss.setSpan(ucs, 0, ss.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ssb.append(ss);
    		}
    	}
    	String s = ssb.toString();
        if (s.endsWith(QuraterConstants.DELIMITER + " ")) {
        	ssb = ssb.delete(s.length()-2, s.length());
        }
        tv.setText(ssb);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        
        tv = (TextView)layout.findViewById(R.id.tv_answer_count);
        tv.setTypeface(typefaceLatoRegular);
        List<Long> commentIds = story.getCommentIds();
        int count = (commentIds == null)? 0: commentIds.size();
        tv.setText(String.valueOf(count));
        
        ImageView ico_followers = (ImageView)layout.findViewById(R.id.ico_followers);
        ico_followers.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
	        	FollowerDialog dlg = new FollowerDialog(getActivity(), story.getProjectId(), story.getFollowerIds());
		    	dlg.show(((FragmentActivity)getActivity()).getSupportFragmentManager(), "");
			}
		});
        tv = (TextView)layout.findViewById(R.id.tv_follower_count);
        tv.setTypeface(typefaceLatoRegular);
        List<Long> followerIds = story.getFollowerIds();
        int followerCount = (followerIds == null)? 0: followerIds.size();
        tv.setText(String.valueOf(followerCount));
        
        Long estimate = story.getEstimate();
        tv = (TextView)layout.findViewById(R.id.tv_estimate);
        tv.setTypeface(typefaceLatoBlack);
        s = getActivity().getResources().getString(R.string.unestimated_prefix);
        if (estimate != null) {
        	//s = activity.getActivity().getResources().getString(R.string.estimate).replace("{estimate}", String.valueOf(estimate));
        	s = String.valueOf(estimate);
        }
        tv.setText(s);
        
        rl = (RelativeLayout)layout.findViewById(R.id.points_circle);
        rl.removeAllViews();
        PointsCircleAsyncTask async = new PointsCircleAsyncTask(
                rl, 
                getActivity().getApplicationContext(),
                13f
                );
        async.execute();
        /*
        GraphView graph = (GraphView) layout.findViewById(R.id.graph);
        Long estimate = story.getEstimate();
        if (estimate == null || estimate == 0) {
        	graph.setVisibility(View.GONE);
        } else {
        	//int width = Utils.getPixels(activity.getApplicationContext(), estimate*30);
        	//graph.getLayoutParams().width = width;
        	graph.setVisibility(View.VISIBLE);
            graph.removeAllSeries();

        	List<DataPoint> points = new ArrayList<DataPoint>();
        	for (int i = 0; i <= estimate; i++) {
        		points.add(new DataPoint(i, i));
        	}
        	Long maxScale = 3L;
        	if (currentProject != null) {
        		List<Long> scale = currentProject.getScale();
        		if (scale != null) {
        		    maxScale = scale.get(scale.size() -1);
        		}
        	}
        	for (int i = estimate.intValue(); i < maxScale+1; i++) {
        		points.add(new DataPoint(i, 0));
        	}
	        BarGraphSeries<DataPoint> series = new BarGraphSeries<DataPoint>(points.toArray(new DataPoint[points.size()]));
	        series.setSpacing(50);
	        graph.addSeries(series);
	        graph.getGridLabelRenderer().setGridStyle(GridStyle.NONE);
	        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
	        graph.getGridLabelRenderer().setVerticalLabelsVisible(false);
	        graph.getLegendRenderer().setVisible(false);
	        graph.onDataChanged(true, true);
	        
        }*/
        TextView tvCreatedBy = (TextView)layout.findViewById(R.id.created_by);
        tvCreatedBy.setTypeface(typefaceLatoRegular);
        if (story.getRequestedById() != null) {
        	Person person = currentProject.getMember(story.getRequestedById());
        	ssb = new SpannableStringBuilder();
            SpannableString ss = new SpannableString(person.getUsername() + " ");
        	UserClickableSpan ucs = new UserClickableSpan(this.getActivity(), story.getProjectId(), story.getRequestedById());
            ss.setSpan(ucs, 0, ss.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.append(ss);
            tvCreatedBy.setText(ssb);
            tvCreatedBy.setMovementMethod(LinkMovementMethod.getInstance());
        }
        
        TextView tvCreateDate = (TextView)layout.findViewById(R.id.create_date);
        tvCreateDate.setTypeface(typefaceLatoRegular);
        if (story.getCreateDate() != null) {
        	String createDate = getActivity().getResources().getString(R.string.created_on);
        	createDate = createDate.replace(QuraterConstants.DATE_PLACEHOLDER, Utils.getFormattedDate(story.getCreateDate()));
        	tvCreateDate.setText(createDate);
        }
        TextView tvUpdateDate = (TextView)layout.findViewById(R.id.update_date);
        tvUpdateDate.setTypeface(typefaceLatoRegular);
        if (story.getUpdateDate() != null) {
        	String updateDate = getActivity().getResources().getString(R.string.updated_on);
        	updateDate = updateDate.replace(QuraterConstants.DATE_PLACEHOLDER, Utils.getFormattedDate(story.getUpdateDate()));
        	tvUpdateDate.setText(updateDate);
        }
        
        String currentState = story.getCurrentState();
        TextView tvAccept = (TextView)layout.findViewById(R.id.btn_accept);
        tvAccept.setTypeface(typefaceOpenSans);
        tvAccept.setVisibility(View.GONE);
        
        TextView tvReject = (TextView)layout.findViewById(R.id.btn_reject);
        tvReject.setTypeface(typefaceOpenSans);
        tvReject.setVisibility(View.GONE);
        
        TextView tvDeliver = (TextView)layout.findViewById(R.id.btn_deliver);
        tvDeliver.setTypeface(typefaceOpenSans);
        tvDeliver.setVisibility(View.GONE);
        
        TextView tvFinish = (TextView)layout.findViewById(R.id.btn_finish);
        tvFinish.setTypeface(typefaceOpenSans);
        tvFinish.setVisibility(View.GONE);
        
        TextView tvAccepted = (TextView)layout.findViewById(R.id.accepted);
        tvAccepted.setTypeface(typefaceOpenSans);
        tvAccepted.setVisibility(View.GONE);
        
        TextView tvStart = (TextView)layout.findViewById(R.id.btn_start);
        tvStart.setTypeface(typefaceOpenSans);
        tvStart.setVisibility(View.GONE);
        
        TextView tvRestart = (TextView)layout.findViewById(R.id.btn_restart);
        tvRestart.setTypeface(typefaceOpenSans);
        tvRestart.setVisibility(View.GONE);
        
        if (PivotalConstants.DELIVERED.equals(currentState)) {
        	tvAccept.setVisibility(View.VISIBLE);
        	tvReject.setVisibility(View.VISIBLE);
        }
        if (PivotalConstants.FINISHED.equals(currentState)) {
        	tvDeliver.setVisibility(View.VISIBLE);
        }
        if (PivotalConstants.STARTED.equals(currentState)) {
        	tvFinish.setVisibility(View.VISIBLE);
        }
        if (PivotalConstants.ACCEPTED.equals(currentState)) {
        	tvAccepted.setVisibility(View.VISIBLE);
        }
        if (PivotalConstants.REJECTED.equals(currentState)) {
        	tvRestart.setVisibility(View.VISIBLE);
        }
        if (PivotalConstants.PLANNED.equals(currentState)) {
        	tvStart.setVisibility(View.VISIBLE);
        }
        //
        List<Label> labels = story.getLabels();
        LabelAdapter labelAdapter = new LabelAdapter(getActivity());
        TwoWayView listview = (TwoWayView) layout.findViewById(R.id.labels);
        if (labels != null && labels.size() > 0) {
        	listview.setVisibility(View.VISIBLE);
        	listview.setAdapter(labelAdapter);
        	labelAdapter.update(labels);
        } else {
        	listview.setVisibility(View.GONE);
        }
        
        setClickListener(layout, story);
        return layout;
    }
 
    public void clear() {
        stories.clear();
        notifyDataSetChanged();
    }
       
    public void update(List<Story> stories) {
        stories = (stories == null)? new ArrayList<Story>(): stories; 
        if (this.stories != null) {     
            this.stories.addAll(stories);
        } else {
            this.stories = stories;
        }
        notifyDataSetChanged();
    }
    
    public Activity getActivity() {
    	return activity.getActivity();
    }
    
    public Story getStory(int position) {
    	return stories.get(position);
    }
    
    public String getStoryDescription(Story story) {
    	String description = story.getDescription();
        description = (description == null)? "": description;
        description = (description.length() > StoryAdapter.MAX_LENGTH? description.substring(0, StoryAdapter.MAX_LENGTH-1) + "..": description);
        return description;
    }
    
    protected void setClickListener(View layout, final Story story) {
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
        	public void onClick(View v) {
        		Intent intent = new Intent(getActivity(), StoryActivity.class);
        		intent.putExtra(QuraterConstants.INTENT_PROJECT_ID, story.getProjectId());
                intent.putExtra(QuraterConstants.INTENT_STORY_ID, story.getId());
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_left_detail, R.anim.stay_in_place_detail);
			 }
		});
         

    }
}