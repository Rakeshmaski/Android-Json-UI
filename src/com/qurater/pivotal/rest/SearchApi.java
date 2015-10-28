package com.qurater.pivotal.rest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.RequestParams;
import com.qurater.pivotal.R;
import com.qurater.pivotal.api.PivotalConstants;
import com.qurater.pivotal.api.Story;
import com.qurater.pivotal.api.URLMap;
import com.qurater.pivotal.interfaces.IStoriesConsumer;


public class SearchApi implements IDataSink {
	
	private IStoriesConsumer activity;
	/**
	 * Constructor
	 */
	public SearchApi(IStoriesConsumer activity) {
		this.activity = activity;
	}
	
	/**
	 * Stories for owner
	 */
	public void getStoriesForOwner(Long projectId, Long userId) {
		String url = URLMap.getUrl("search_url");
		url = url.replace(PivotalConstants.PROJECT_ID, String.valueOf(projectId));
		//url = "https://www.pivotaltracker.com/services/v5/projects/1046766/search?date_format=millis&fields=:default,epics(:default,epics(:default,follower_ids,past_done_story_estimates,past_done_stories_count,past_done_stories_no_point_count,label_id,label(:default,counts),comments(:default,file_attachments,google_attachments))),stories(:default,stories(:default,follower_ids,tasks,comments(:default,file_attachments,google_attachments)))&query=label%3A%22server%22&envelope=true";
		//url = "https://www.pivotaltracker.com/services/v5/projects/1046766/search?date_format=millis&fields=:default,epics(:default,epics(:default,follower_ids,past_done_story_estimates,past_done_stories_count,past_done_stories_no_point_count,label_id,label(:default,counts),comments(:default,file_attachments,google_attachments))),stories(:default,stories(:default,follower_ids,tasks,comments(:default,file_attachments,google_attachments)))&query=owner%3A%22YK%22&envelope=false";
		RequestParams params = new RequestParams();
		try {
			params.put("envelope", "false");
			//params.put("date_format", "millis");
			params.put("fields", ":default,epics(:default,epics(:default,follower_ids,past_done_story_estimates,past_done_stories_count,past_done_stories_no_point_count,label_id,label(:default,counts),comments(:default,file_attachments,google_attachments))),stories(:default,stories(:default,follower_ids))");
			params.put(PivotalConstants.QUERY, "owner:\"" + userId + "\" OR requester:\"" + userId + "\"");
			//params.put(PivotalConstants.QUERY, URLEncoder.encode("label:" + "android", "UTF-8"));
			//params.put(PivotalConstants.QUERY, "label%3Aandroid");
		//} catch (UnsupportedEncodingException e) {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println(params);
		System.err.println(url);
		//url = url + "?" + params;
		RemoteDataFetcher.get(url, params, activity.getApplicationContext(), this);
	}
	
	/**
	 * Stories for owner
	 */
	public void searchStories(Long projectId, String searchTerm) {
		String url = URLMap.getUrl("search_url");
		url = url.replace(PivotalConstants.PROJECT_ID, String.valueOf(projectId));
		RequestParams params = new RequestParams();
		params.put("envelope", "false");
		params.put("fields", ":default,epics(:default,epics(:default,follower_ids,past_done_story_estimates,past_done_stories_count,past_done_stories_no_point_count,label_id,label(:default,counts),comments(:default,file_attachments,google_attachments))),stories(:default,stories(:default,follower_ids))");
		params.put(PivotalConstants.QUERY, searchTerm);
		System.err.println(params);
		RemoteDataFetcher.get(url, params, activity.getApplicationContext(), this);
	}
	
	@Override
	public void receive(String response) {
		try {
			System.err.println(response);
			List<Story> stories = new ArrayList<Story>();
			JSONObject jSearchResponse = new JSONObject(response);
			JSONArray jStories= jSearchResponse.getJSONObject(PivotalConstants.STORIES).getJSONArray(PivotalConstants.STORIES);
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