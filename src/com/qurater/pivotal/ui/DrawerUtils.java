package com.qurater.pivotal.ui;

import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qurater.pivotal.R;
import com.qurater.pivotal.activity.BacklogStoriesFragment;
import com.qurater.pivotal.activity.CurrentStoriesFragment;
import com.qurater.pivotal.activity.IceboxStoriesFragment;
import com.qurater.pivotal.activity.IterationsFragment;
import com.qurater.pivotal.activity.LabelsFragment;
import com.qurater.pivotal.activity.PivotalActivitiesFragment;
import com.qurater.pivotal.activity.ProjectActivity;
import com.qurater.pivotal.activity.UserStoriesFragment;
import com.qurater.pivotal.api.Font;
import com.qurater.pivotal.api.PivotalConstants;
import com.qurater.pivotal.storage.LoggedInUserStore;


public class DrawerUtils {
	
    //Drawer
    //http://stackoverflow.com/questions/20057084/how-to-move-main-content-with-drawer-layout-left-side
	protected DrawerLayout mDrawerLayout;
    protected View mDrawerLayoutFrame;
    private ActionBarDrawerToggle mDrawerToggle;
    private FrameLayout frame;
    private float lastTranslate = 0.0f;
	protected Activity activity;
	
	public DrawerUtils(Activity activity) {
		this.activity = activity;
	}
	
	public void initDrawer() {
        //Drawer
        mDrawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        mDrawerLayoutFrame = activity.findViewById(R.id.drawer_list);
        frame = (FrameLayout) activity.findViewById(R.id.list_main);
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
       
        mDrawerToggle = new ActionBarDrawerToggle(
                activity,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                //getActionBar().setTitle(mTitle);
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
    
            public void onDrawerOpened(View drawerView) {
                activity.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
            
            @SuppressLint("NewApi")
            public void onDrawerSlide(View drawerView, float slideOffset) {
                float moveFactor = (-1)*(mDrawerLayoutFrame.getWidth() * slideOffset);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    frame.setTranslationX(moveFactor);
                }
                else {
                    TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
                    anim.setDuration(0);
                    anim.setFillAfter(true);
                    frame.startAnimation(anim);
                    lastTranslate = moveFactor;
                }
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }
	
	public void openDrawer(){
        mDrawerLayout.openDrawer(mDrawerLayoutFrame);
    }
    
	public void closeDrawer(){
        mDrawerLayout.closeDrawer(mDrawerLayoutFrame);
    }
	
    public boolean isDrawerOpen(){
        return mDrawerLayout.isDrawerOpen(mDrawerLayoutFrame);
    }
    
    public void initProjectOptions() {
    	Typeface tf = Font.getFont(activity.getApplicationContext(), "Lato-Bold.ttf");
    	View v = activity.findViewById(R.id.container_my_work);
    	v.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ProjectActivity projectActivity = (ProjectActivity)activity;
				FragmentManager fragmentManager = projectActivity.getSupportFragmentManager();
		        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		        Long id = null;
				try {
					id = LoggedInUserStore.getLoggedInUser(activity).getLong(PivotalConstants.ID);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				UserStoriesFragment stories = new UserStoriesFragment(projectActivity, id);
	            fragmentTransaction.replace(R.id.fragment_container, stories);
	            fragmentTransaction.commit();
	            closeDrawer();
			}
		});
    	TextView tv = (TextView)activity.findViewById(R.id.my_work);
    	tv.setTypeface(tf);
    	
    	v = activity.findViewById(R.id.container_current);
    	v.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ProjectActivity projectActivity = (ProjectActivity)activity;
				FragmentManager fragmentManager = projectActivity.getSupportFragmentManager();
		        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				CurrentStoriesFragment stories = new CurrentStoriesFragment(projectActivity);
	            fragmentTransaction.replace(R.id.fragment_container, stories);
	            fragmentTransaction.commit();
	            closeDrawer();
			}
		});
    	tv = (TextView)activity.findViewById(R.id.current);
    	tv.setTypeface(tf);
    	
    	v = activity.findViewById(R.id.container_backlog);
    	v.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ProjectActivity projectActivity = (ProjectActivity)activity;
				FragmentManager fragmentManager = projectActivity.getSupportFragmentManager();
		        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				BacklogStoriesFragment stories = new BacklogStoriesFragment(projectActivity);
	            fragmentTransaction.replace(R.id.fragment_container, stories);
	            fragmentTransaction.commit();
	            closeDrawer();
			}
		});
    	tv = (TextView)activity.findViewById(R.id.backlog);
    	tv.setTypeface(tf);
    	
    	v = activity.findViewById(R.id.container_icebox);
    	v.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ProjectActivity projectActivity = (ProjectActivity)activity;
				FragmentManager fragmentManager = projectActivity.getSupportFragmentManager();
		        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				IceboxStoriesFragment stories = new IceboxStoriesFragment(projectActivity);
	            fragmentTransaction.replace(R.id.fragment_container, stories);
	            fragmentTransaction.commit();
	            closeDrawer();
			}
		});
    	tv = (TextView)activity.findViewById(R.id.icebox);
    	tv.setTypeface(tf);
    	
    	v = activity.findViewById(R.id.container_done);
    	v.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ProjectActivity projectActivity = (ProjectActivity)activity;
				FragmentManager fragmentManager = projectActivity.getSupportFragmentManager();
		        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				//DoneStoriesFragment stories = new DoneStoriesFragment(projectActivity);
		        IterationsFragment iterations = new IterationsFragment(projectActivity);
	            fragmentTransaction.replace(R.id.fragment_container, iterations);
	            fragmentTransaction.commit();
	            closeDrawer();
			}
		});
    	tv = (TextView)activity.findViewById(R.id.done);
    	tv.setTypeface(tf);
    	tv = (TextView)activity.findViewById(R.id.epics);
    	tv.setTypeface(tf);
    	
    	v = activity.findViewById(R.id.container_labels);
    	v.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ProjectActivity projectActivity = (ProjectActivity)activity;
				FragmentManager fragmentManager = projectActivity.getSupportFragmentManager();
		        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				LabelsFragment labels = new LabelsFragment(projectActivity);
	            fragmentTransaction.replace(R.id.fragment_container, labels);
	            fragmentTransaction.commit();
	            closeDrawer();
			}
		});
    	tv = (TextView)activity.findViewById(R.id.labels);
    	tv.setTypeface(tf);
    	
    	tv = (TextView)activity.findViewById(R.id.charts);
    	tv.setTypeface(tf);
    	
    	v = activity.findViewById(R.id.container_project_history);
    	v.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ProjectActivity projectActivity = (ProjectActivity)activity;
				FragmentManager fragmentManager = projectActivity.getSupportFragmentManager();
		        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				PivotalActivitiesFragment activities = new PivotalActivitiesFragment(projectActivity);
	            fragmentTransaction.replace(R.id.fragment_container, activities);
	            fragmentTransaction.commit();
	            closeDrawer();
			}
		});
    	tv = (TextView)activity.findViewById(R.id.project_history);
    	tv.setTypeface(tf);
    	tv = (TextView)activity.findViewById(R.id.logout);
    	tv.setTypeface(tf);
    	
    }
}