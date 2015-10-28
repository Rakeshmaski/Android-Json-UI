package com.qurater.pivotal.interfaces;

import java.util.List;

import android.content.Context;

import com.qurater.pivotal.api.Story;



/**
 * Stories callback
 */
public interface IStoriesConsumer {
	/**
	 * Callback for success
	 */
	public void onStoriesLoadSuccess(List<Story> stories);
	
	/**
	 * Callback for failure
	 */
	public void onStoriesLoadFailure(String reason);
	
	/**
	 * Application Context
	 */
	public Context getApplicationContext();
}