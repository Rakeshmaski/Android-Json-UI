package com.qurater.pivotal.rest;

import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.RequestParams;
import com.qurater.pivotal.R;
import com.qurater.pivotal.api.PivotalConstants;
import com.qurater.pivotal.api.Story;
import com.qurater.pivotal.api.URLMap;
import com.qurater.pivotal.interfaces.IStoryConsumer;


public class StoryApi implements IDataSink {
	
	private IStoryConsumer activity;
	/**
	 * Constructor
	 */
	public StoryApi(IStoryConsumer activity) {
		this.activity = activity;
	}
	
	/**
	 * Login
	 */
	public void getStory(Long projectId, Long storyId) {
		String url = URLMap.getUrl("story_url");
		//storyId = 90387926L;
		url = url.replace(PivotalConstants.PROJECT_ID, String.valueOf(projectId)).replace(PivotalConstants.STORY_ID, String.valueOf(storyId));
		RequestParams params = new RequestParams();
		params.put(PivotalConstants.FIELDS, "id,name,description,story_type,estimate,current_state,owner_ids,follower_ids,created_at,updated_at,owner_ids,requested_by_id,labels,tasks,project_id,comments(person_id,text,created_at,updated_at,id,file_attachments)");
		RemoteDataFetcher.get(url, params, activity.getApplicationContext(), this);
	}

	@Override
	public void receive(String response) {
		try {
			System.err.println(response);
			JSONObject jStory = new JSONObject(response);
			Story story = Story.fromJson(jStory);
			activity.onStoryLoadSuccess(story);
		} catch (JSONException e) {
			e.printStackTrace();
			activity.onStoryLoadFailure(activity.getApplicationContext().getResources().getString(R.string.story_failure));
		}
	}

	@Override
	public void error(Throwable e, String response) {
	    if (response != null) {
	    	System.err.println(response);
	    }
		activity.onStoryLoadFailure(activity.getApplicationContext().getResources().getString(R.string.story_failure));
	}
}