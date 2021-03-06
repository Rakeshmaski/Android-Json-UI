package com.qurater.pivotal.rest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.loopj.android.http.RequestParams;
import com.qurater.pivotal.R;
import com.qurater.pivotal.api.PivotalConstants;
import com.qurater.pivotal.api.Story;
import com.qurater.pivotal.api.URLMap;
import com.qurater.pivotal.interfaces.IStoriesConsumer;


public class StoriesApi implements IDataSink {
	
	private IStoriesConsumer activity;
	/**
	 * Constructor
	 */
	public StoriesApi(IStoriesConsumer activity) {
		this.activity = activity;
	}
	
	/**
	 * Stories
	 */
	public void getStories(Long projectId) {
		String url = URLMap.getUrl("project_stories_url");
		url = url.replace(PivotalConstants.PROJECT_ID, String.valueOf(projectId));
		RequestParams params = new RequestParams();
		params.put(PivotalConstants.FIELDS, "id,project_id,name,description,story_type,current_state,estimate,requested_by_id,owner_ids,label_ids,follower_ids,comment_ids,created_at,updated_at");
		RemoteDataFetcher.get(url, params, activity.getApplicationContext(), this);
	}

	/**
	 * Stories for owner
	 */
	public void getIceboxStories(Long projectId) {
		String url = URLMap.getUrl("project_stories_url");
		url = url.replace(PivotalConstants.PROJECT_ID, String.valueOf(projectId));
		RequestParams params = new RequestParams();
		params.put(PivotalConstants.FIELDS, "id,project_id,name,description,story_type,current_state,estimate,requested_by_id,owner_ids,label_ids,follower_ids,comment_ids");
		params.put(PivotalConstants.WITH_STATE, PivotalConstants.UNSCHEDULED);
		RemoteDataFetcher.get(url, params, activity.getApplicationContext(), this);
	}


	@Override
	public void receive(String response) {
		try {
			System.err.println(response);
			List<Story> stories = new ArrayList<Story>();
			JSONArray jStories= new JSONArray(response);
			for (int i = 0; i < jStories.length(); i++) {
				stories.add(Story.fromJson(jStories.getJSONObject(i)));
			}
			activity.onStoriesLoadSuccess(stories);
		} catch (JSONException e) {
			e.printStackTrace();
			activity.onStoriesLoadFailure(activity.getApplicationContext().getResources().getString(R.string.stories_failure));
		}
	}

	@Override
	public void error(Throwable e, String response) {
		if (response != null) {
		    System.err.println(response);
		}
		activity.onStoriesLoadFailure(activity.getApplicationContext().getResources().getString(R.string.stories_failure));
	}
}