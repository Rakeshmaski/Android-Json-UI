package com.qurater.pivotal.activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.qurater.pivotal.R;
import com.qurater.pivotal.api.QuraterConstants;
import com.qurater.pivotal.ui.ExitWarning;

public class ProjectActivity extends FragmentActivity implements AnimationListener {

	protected String subActivity;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	System.out.println("in projectActivity onCreate()");
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_project_detail);
        Bundle extras = getIntent().getExtras();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        this.subActivity = (extras != null)? (extras.getString("com.qurater.main_sub_activity")): null;
        if (subActivity == null){
            subActivity = QuraterConstants.CURRENT_STORIES;
            System.out.println("projectActivity subActivity is null:"+subActivity);
        }
        
        if (QuraterConstants.CURRENT_STORIES.equals(subActivity)) {
        	System.out.println("projectActivity subActivity is equal to currentstories:"+subActivity);
            CurrentStoriesFragment currentStories = new CurrentStoriesFragment(this);
            fragmentTransaction.replace(R.id.fragment_container, currentStories);
            fragmentTransaction.commit();
        } else if (QuraterConstants.CURRENT_STORIES.equals(subActivity)) {
        	System.out.println("projectActivity subActivity is :not equal to currentstories:else part :"+subActivity);
            CurrentStoriesFragment currentStories = new CurrentStoriesFragment(this);
            fragmentTransaction.replace(R.id.fragment_container, currentStories);
            fragmentTransaction.commit();
        } 
    }
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
    	System.out.println("projectActivity:onPostCreate");
        super.onPostCreate(savedInstanceState);
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public void onAnimationEnd(Animation arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onAnimationRepeat(Animation arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onAnimationStart(Animation arg0) {
        // TODO Auto-generated method stub
    }
    
    @Override
    public void onBackPressed() {
        boolean warned = ExitWarning.warn(this);
        if (!warned) {
            super.onBackPressed();
            overridePendingTransition(0, R.anim.slide_right_detail);
        }  
    }

}
