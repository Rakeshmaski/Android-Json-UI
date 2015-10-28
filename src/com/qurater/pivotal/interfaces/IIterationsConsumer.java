package com.qurater.pivotal.interfaces;

import java.util.List;

import android.content.Context;

import com.qurater.pivotal.api.Iteration;



/**
 * Stories callback
 */
public interface IIterationsConsumer {
	/**
	 * Callback for success
	 */
	public void onIterationsLoadSuccess(List<Iteration> stories);
	
	/**
	 * Callback for failure
	 */
	public void onIterationsLoadFailure(String reason);
	
	/**
	 * Application Context
	 */
	public Context getApplicationContext();
}