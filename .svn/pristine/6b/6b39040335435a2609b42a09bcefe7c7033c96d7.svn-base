package com.qurater.pivotal.rest;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.RequestParams;
import com.qurater.pivotal.R;
import com.qurater.pivotal.api.Iteration;
import com.qurater.pivotal.api.PivotalConstants;
import com.qurater.pivotal.api.QuraterConstants;
import com.qurater.pivotal.api.URLMap;
import com.qurater.pivotal.interfaces.IIterationsConsumer;


public class IterationsApi implements IDataSink {
	
	private IIterationsConsumer activity;
	/**
	 * Constructor
	 */
	public IterationsApi(IIterationsConsumer activity) {
		this.activity = activity;
	}
	
	/**
	 * Login
	 */
	public void getIterations(Long projectId) {
		getIterations(projectId, 0);
	}

	/**
	 * Login
	 */
	public void getIterations(Long projectId, int pgno) {
		String url = URLMap.getUrl("project_iterations_url");
		url = url.replace(PivotalConstants.PROJECT_ID, String.valueOf(projectId));
		RequestParams params = new RequestParams();
		int offset = QuraterConstants.PAGE_SIZE*pgno;
		params.add(PivotalConstants.OFFSET, String.valueOf(offset));
		params.add(PivotalConstants.LIMIT, String.valueOf(QuraterConstants.PAGE_SIZE));
		params.put(PivotalConstants.FIELDS, 
				"number,project_id,length,team_strength,start,finish");
		RemoteDataFetcher.get(url, params, activity.getApplicationContext(), this);
	}
	
	@Override
	public void receive(String response) {
		try {
			System.err.println(response);
			List<Iteration> iterations = new ArrayList<Iteration>();
			JSONArray jIterations = new JSONArray(response);
			for (int idx = 0; idx < jIterations.length(); idx++) {
				JSONObject jIteration = jIterations.getJSONObject(idx);
				iterations.add(Iteration.fromJson(jIteration));
			}
			activity.onIterationsLoadSuccess(iterations);
		} catch (JSONException e) {
			e.printStackTrace();
			activity.onIterationsLoadFailure(activity.getApplicationContext().getResources().getString(R.string.iterations_failure));
		}
	}

	@Override
	public void error(Throwable e, String response) {
		if (response != null) {
		    System.err.println(response);
		}
		activity.onIterationsLoadFailure(activity.getApplicationContext().getResources().getString(R.string.iterations_failure));
	}
}