package com.qurater.pivotal.rest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.loopj.android.http.RequestParams;
import com.qurater.pivotal.R;
import com.qurater.pivotal.api.PivotalConstants;
import com.qurater.pivotal.api.QuraterConstants;
import com.qurater.pivotal.api.Story;
import com.qurater.pivotal.api.URLMap;
import com.qurater.pivotal.interfaces.IStoriesConsumer;


public class IterationStoriesApi implements IDataSink {
	
	private IStoriesConsumer activity;
	/**
	 * Constructor
	 */
	public IterationStoriesApi(IStoriesConsumer activity) {
		this.activity = activity;
	}
	
	/**
	 * Login
	 */
	public void getStories(Long projectId, String scope) {
		getStories(projectId, scope, 0);
	}

	/**
	 * Login
	 */
	public void getStories(Long projectId, String scope, int pgno) {
		String url = URLMap.getUrl("project_iterations_url");
		url = url.replace(PivotalConstants.PROJECT_ID, String.valueOf(projectId));
		RequestParams params = new RequestParams();
		int offset = QuraterConstants.PAGE_SIZE*pgno;
		params.add(PivotalConstants.OFFSET, String.valueOf(offset));
		params.add(PivotalConstants.LIMIT, String.valueOf(QuraterConstants.PAGE_SIZE));
		params.put(PivotalConstants.SCOPE, scope);
		params.put(PivotalConstants.FIELDS, 
				"number,project_id,length,team_strength,stories(id,project_id,name,description,story_type,current_state,estimate,requested_by_id,owner_ids,label_ids,follower_ids,comment_ids,created_at,updated_at)");
		RemoteDataFetcher.get(url, params, activity.getApplicationContext(), this);
	}
	
	/**
	 * Login
	 */
	public void getDoneStoriesForIteration(Integer projectId, int iteration) {
		getDoneStoriesForIteration(projectId, iteration, 0);
	}

	/**
	 * Login
	 */
	public void getDoneStoriesForIteration(Integer projectId, int iteration, int pgno) {
		String url = URLMap.getUrl("project_iterations_url");
		url = url.replace(PivotalConstants.PROJECT_ID, String.valueOf(projectId));
		RequestParams params = new RequestParams();
		params.add(PivotalConstants.OFFSET, String.valueOf(iteration));
		params.add(PivotalConstants.LIMIT, String.valueOf(QuraterConstants.PAGE_SIZE));
		params.put(PivotalConstants.SCOPE, PivotalConstants.DONE_SCOPE);
		params.put(PivotalConstants.FIELDS, 
				"number,project_id,length,team_strength,stories(id,project_id,name,description,story_type,current_state,estimate,requested_by_id,owner_ids,labels,follower_ids,comment_ids)");
		RemoteDataFetcher.get(url, params, activity.getApplicationContext(), this);
	}
	@Override
	public void receive(String response) {
		try {
			System.err.println(response);
			List<Story> stories = new ArrayList<Story>();
			JSONArray jIterations = new JSONArray(response);
			for (int idx = 0; idx < jIterations.length(); idx++) {
				JSONArray jStories= jIterations.getJSONObject(idx).getJSONArray("stories");
				for (int i = 0; i < jStories.length(); i++) {
					stories.add(Story.fromJson(jStories.getJSONObject(i)));
				}
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