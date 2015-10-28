package com.qurater.pivotal.rest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.loopj.android.http.RequestParams;
import com.qurater.pivotal.R;
import com.qurater.pivotal.api.Label;
import com.qurater.pivotal.api.PivotalConstants;
import com.qurater.pivotal.api.URLMap;
import com.qurater.pivotal.interfaces.ILabelsConsumer;


public class LabelApi implements IDataSink {
	
	private ILabelsConsumer activity;
	/**
	 * Constructor
	 */
	public LabelApi(ILabelsConsumer activity) {
		this.activity = activity;
	}
	
	/**
	 * Login
	 */
	public void getLabels(Long projectId) {
		String url = URLMap.getUrl("labels_url");
		url = url.replace(PivotalConstants.PROJECT_ID, String.valueOf(projectId));
		RequestParams params = new RequestParams();
		RemoteDataFetcher.get(url, params, activity.getApplicationContext(), this);
	}

	@Override
	public void receive(String response) {
		try {
			System.err.println(response);
			List<Label> labels = new ArrayList<Label>();
			JSONArray jLabels = new JSONArray(response);
			for (int i = 0; i < jLabels.length(); i++) {
				labels.add(Label.fromJson(jLabels.getJSONObject(i)));
			}
			activity.onLabelsLoadSuccess(labels);
		} catch (JSONException e) {
			e.printStackTrace();
			activity.onLabelsLoadFailure(activity.getApplicationContext().getResources().getString(R.string.labels_failure));
		}
	}

	@Override
	public void error(Throwable e, String response) {
		System.err.println(response);
		activity.onLabelsLoadFailure(activity.getApplicationContext().getResources().getString(R.string.labels_failure));
	}
}