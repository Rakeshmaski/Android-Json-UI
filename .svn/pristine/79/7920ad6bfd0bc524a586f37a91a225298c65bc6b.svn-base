package com.qurater.pivotal.rest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.loopj.android.http.RequestParams;
import com.qurater.pivotal.R;
import com.qurater.pivotal.api.PivotalActivity;
import com.qurater.pivotal.api.PivotalConstants;
import com.qurater.pivotal.api.QuraterConstants;
import com.qurater.pivotal.api.URLMap;
import com.qurater.pivotal.interfaces.IActivitiesConsumer;


public class ProjectPivotalActivityApi implements IDataSink {
	
	private IActivitiesConsumer consumer;
	private Long projectId;
	/**
	 * Constructor
	 */
	public ProjectPivotalActivityApi(IActivitiesConsumer consumer) {
		this.consumer = consumer;
	}
	
	/**
	 * Login
	 */
	public void getActivities(final Long projectId) {
		getActivities(projectId, 0);
	}

	/**
	 * Login
	 */
	public void getActivities(final Long projectId, int pgno) {
		String url = URLMap.getUrl("project_activities_url");
		this.projectId = projectId;
		url = url.replace(PivotalConstants.PROJECT_ID, String.valueOf(projectId));
		RequestParams params = new RequestParams();
		int offset = QuraterConstants.PAGE_SIZE*pgno;
		params.add(PivotalConstants.OFFSET, String.valueOf(offset));
		params.add(PivotalConstants.LIMIT, String.valueOf(QuraterConstants.PAGE_SIZE));
		RemoteDataFetcher.get(url, params, consumer.getApplicationContext(), this);
		System.err.println(url + " " + params);
	}
	
	@Override
	public void receive(String response) {
		try {
			//System.err.println(response);
			List<PivotalActivity> activities = new ArrayList<PivotalActivity>();
			JSONArray jActivities = new JSONArray(response);
			for (int idx = 0; idx < jActivities.length(); idx++) {
				PivotalActivity pivotalActivity = PivotalActivity.fromJson(jActivities.getJSONObject(idx));
				pivotalActivity.setProjectId(Integer.valueOf(String.valueOf(projectId)));
				activities.add(pivotalActivity);
			}
			consumer.onActivitiesLoadSuccess(activities);
		} catch (JSONException e) {
			e.printStackTrace();
			consumer.onActivitiesLoadFailure(consumer.getApplicationContext().getResources().getString(R.string.activities_failure));
		}
	}

	@Override
	public void error(Throwable e, String response) {
		System.err.println(response);
		consumer.onActivitiesLoadFailure(consumer.getApplicationContext().getResources().getString(R.string.activities_failure));
	}
}