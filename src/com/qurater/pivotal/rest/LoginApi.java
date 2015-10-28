package com.qurater.pivotal.rest;

import org.json.JSONException;
import org.json.JSONObject;

import com.qurater.pivotal.R;
import com.qurater.pivotal.api.PivotalConstants;
import com.qurater.pivotal.api.URLMap;
import com.qurater.pivotal.api.User;
import com.qurater.pivotal.interfaces.ILoginUser;
import com.qurater.pivotal.storage.LoggedInUserStore;


public class LoginApi implements IDataSink {
	
	private ILoginUser activity;
	/**
	 * Constructor
	 */
	public LoginApi(ILoginUser activity) {
		this.activity = activity;
	}
	
	/**
	 * Login
	 */
	public void doLogin(String email, String password) {
		String url = URLMap.getUrl("login_url");
		RemoteDataFetcher.login(url, email, password, activity.getApplicationContext(), this);
	}

	@Override
	public void receive(String response) {
		try {
			JSONObject jResponse = new JSONObject(response);
			String kind = jResponse.getString(PivotalConstants.KIND);
			if (PivotalConstants.ME.equals(kind)) {
				LoggedInUserStore.storeLoggedInUser(activity.getApplicationContext(), jResponse);
				activity.onLogin(User.fromJson(jResponse));
			} else {
				activity.onLoginFailure(activity.getApplicationContext().getResources().getString(R.string.login_failure));
			}
			
		} catch (JSONException e) {
			activity.onLoginFailure(activity.getApplicationContext().getResources().getString(R.string.login_failure));
		}
	}

	@Override
	public void error(Throwable e, String response) {
		activity.onLoginFailure(activity.getApplicationContext().getResources().getString(R.string.login_failure));
	}
}