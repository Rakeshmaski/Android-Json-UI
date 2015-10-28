package com.qurater.pivotal.activity;



import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.TextView;

import com.qurater.pivotal.R;
import com.qurater.pivotal.api.Font;
import com.qurater.pivotal.dialog.LoginDialog;
import com.qurater.pivotal.storage.LoggedInUserStore;


/**
 * Splash Activity
 * @author mishras
 *
 */
public class SplashActivity extends FragmentActivity {

	public static final int LOGIN_DISPLAY_TIMEOUT = 1200;
    public static final int SPLASH_DISPLAY_LENGTH = 1000;
    
	protected View rl_login;
    protected TextView btnLogin;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Crashlytics.start(this);
        Context context = getApplicationContext();
        setContentView(R.layout.splashscreen);
        CookieSyncManager.createInstance(context);
        CookieManager.getInstance().acceptCookie();
        
        //Hide the action bar
        if (getActionBar() != null) {
            getActionBar().hide();
        }
        Typeface latoBold = Font.getFont(getApplicationContext(), "Lato-Bold.ttf");
        //Container
        rl_login = this.findViewById(R.id.rl_login);
        //Login button
        btnLogin = (TextView)this.findViewById(R.id.btn_login);
        btnLogin.setTypeface(latoBold);
        btnLogin.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	LoginDialog dlg = new LoginDialog(SplashActivity.this);
		    	dlg.show(getSupportFragmentManager(), "");
            }
        });
        //Login
        if (!LoggedInUserStore.isLoggedIn(getApplicationContext())) {
        	showLoginScreen();
        } else {
	        new Handler().postDelayed(new Runnable(){
	            @Override
	            public void run() {
	                Intent intent = new Intent(SplashActivity.this, ProjectListActivity.class);
	                startActivity(intent);
	                SplashActivity.this.finish();
	            }
	        }, SplashActivity.SPLASH_DISPLAY_LENGTH);
        }
    }
    
    protected void showLoginScreen() {
		new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                new Handler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                    	btnLogin.setVisibility(View.VISIBLE);
                        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
                        //fadeIn.setInterpolator(new DecelerateInterpolator());
                        fadeIn.setDuration(1000);
                        fadeIn.setFillAfter(true);
                        fadeIn.setFillEnabled(true);
                        btnLogin.startAnimation(fadeIn);
                    }
                }, 500);
            }
        }, LOGIN_DISPLAY_TIMEOUT);
    }
}