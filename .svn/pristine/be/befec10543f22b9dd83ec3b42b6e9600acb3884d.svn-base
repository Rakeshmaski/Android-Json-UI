package com.qurater.pivotal.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.qurater.pivotal.R;
import com.qurater.pivotal.ui.DrawerUtils;
import com.qurater.pivotal.ui.ExitWarning;


public class BaseDrawerActivity extends FragmentActivity {
    
    
    protected DrawerUtils drawerUtils;
    protected String subActivity;
    
    public BaseDrawerActivity() {
    	drawerUtils = new DrawerUtils(this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action buttons
        switch(item.getItemId()) {
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    public void initDrawer(Bundle savedInstanceState) {
        drawerUtils.initDrawer();
    }
    
    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    
    public void openDrawer() {
        drawerUtils.openDrawer();
    }
    

    public void closeDrawer() {
    	drawerUtils.closeDrawer();
    }

    
    @Override
    public void onRestart() {
        super.onRestart();
    }
    
    @Override
    public void onStop() {
        super.onStop();
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    
    @Override
    public void onPause() {
        super.onPause();
    }
    
    
    @Override
    public void startActivity(Intent intent) {
        //closeDrawer();
        super.startActivity(intent);
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