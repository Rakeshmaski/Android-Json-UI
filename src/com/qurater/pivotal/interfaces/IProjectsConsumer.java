package com.qurater.pivotal.interfaces;

import java.util.List;

import android.content.Context;

import com.qurater.pivotal.api.Project;



/**
 * Projects callback
 */
public interface IProjectsConsumer {
	/**
	 * Callback for projects
	 */
	public void onProjectsLoadSuccess(List<Project> project);
	
	/**
	 * Callback for failure
	 */
	public void onProjectsLoadFailure(String reason);
	
	/**
	 * Application Context
	 */
	public Context getApplicationContext();
}