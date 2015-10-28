package com.qurater.pivotal.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.qurater.pivotal.R;
import com.qurater.pivotal.activity.UserStoriesActivity;
import com.qurater.pivotal.api.Font;
import com.qurater.pivotal.api.QuraterConstants;


public class UserClickableSpan extends ClickableSpan{
    protected Typeface typeface; 
    protected int color = Color.rgb(0, 89, 159);
    protected Long projectId;
    protected Long userId;
    protected Activity activity;
   
    public UserClickableSpan(Activity activity, Long projectId, Long userId) {
    	typeface = Font.getFont(activity.getApplicationContext(), "DroidSerif-BoldItalic.ttf");
        this.activity = activity;
        this.projectId = projectId;
        this.userId = userId;
    }
   
    @Override
    public void onClick(View textView) {
	    Intent intent = new Intent(activity, UserStoriesActivity.class);
        intent.putExtra(QuraterConstants.INTENT_PROJECT_ID, projectId);
        intent.putExtra(QuraterConstants.INTENT_USER_ID, userId);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_left_detail, R.anim.stay_in_place_detail);
    }
    
    @Override
    public void updateDrawState(TextPaint ds) {// override updateDrawState
        super.updateDrawState(ds);
        ds.setColor(color);
        ds.setTypeface(typeface);
        ds.setUnderlineText(false); // set to false to remove underline
    }
    
    public void setColor(int color) {
    	this.color = color;
    }
}   