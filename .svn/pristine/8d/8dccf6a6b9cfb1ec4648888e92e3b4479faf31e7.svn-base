package com.qurater.pivotal.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qurater.pivotal.R;
import com.qurater.pivotal.activity.ProjectListActivity;
import com.qurater.pivotal.api.User;
import com.qurater.pivotal.interfaces.ILoginUser;
import com.qurater.pivotal.rest.LoginApi;

public class LoginDialog extends DialogFragment implements ILoginUser{
    
    protected Activity activity;
    
    public LoginDialog(Activity activity) {
        super();
        this.activity = activity;
    }
    /** The system calls this to get the DialogFragment's layout, regardless
        of whether it's being displayed as a dialog or an embedded fragment. */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment
        View view = inflater.inflate(R.layout.dialog_login, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        int style = DialogFragment.STYLE_NORMAL, theme = android.R.style.Theme_Holo_Light_Dialog;
        setStyle(style, theme);
        return view;
    }  
    
    @Override
    public void onStart() {
    	super.onStart();
    	// safety check
    	if (getDialog() == null) {
    		return;
    	}
    	getDialog().getWindow().setWindowAnimations(
              R.style.dialog_animation_fade);

    	try {
    		RelativeLayout layout = (RelativeLayout)this.getView().findViewById(R.id.dlg_header);
    		layout.setAlpha(1.0f);
    		final DialogFragment dlg = this;
	      
    		ImageView btnCloseDialog = (ImageView)getView().findViewById(R.id.ico_close);
    		btnCloseDialog.setOnClickListener(new View.OnClickListener() {
    			@Override
    			public void onClick(View arg0) {
    				dlg.dismiss();
    			}
    		});
    		
	        //
            Typeface tfLatoNormal = Typeface.createFromAsset(activity.getAssets(), "Lato-Regular.ttf");
            Typeface tfLatoBold = Typeface.createFromAsset(activity.getAssets(), "Lato-Bold.ttf");
            
            final EditText email = (EditText) getView().findViewById(R.id.email);
            email.setTypeface(tfLatoNormal);
            String hint = "<i>" + activity.getResources().getString(R.string.email) + "</i>";
            email.setHint(Html.fromHtml(hint));
            
            final EditText password = (EditText) getView().findViewById(R.id.password);
            password.setTypeface(tfLatoNormal);
            hint = "<i>" + activity.getResources().getString(R.string.password) + "</i>";
            password.setHint(Html.fromHtml(hint));
            
            final TextView loginError = (TextView)getView().findViewById(R.id.login_error);
            loginError.setTypeface(tfLatoNormal);
            
            TextView btnLogin = (TextView)getView().findViewById(R.id.btn_login);
            btnLogin.setTypeface(tfLatoBold);
            
            final ILoginUser activity = this;
            btnLogin.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                	loginError.setText("");
                	loginError.setVisibility(View.GONE);
                	boolean errors = false;
                	email.setError(null);
	                if (email.getText().toString().trim().equalsIgnoreCase("")) {
	                	email.setError("This field can not be blank");
	                	errors = true;
	                }
	                password.setError(null);
	                if (password.getText().toString().trim().equalsIgnoreCase("")) {
	                	password.setError("This field can not be blank");
	                	errors = true;
	                }
	                if (!errors) {
	                	LoginApi login = new LoginApi(activity);
	                	login.doLogin(email.getText().toString(), password.getText().toString());
	                }
                }
            });
	        
      } catch(Exception e) {
    	  e.printStackTrace();
      }
      WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
      lp.dimAmount=0.0f;
      DisplayMetrics displaymetrics = new DisplayMetrics();
      getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
      Window window = getDialog().getWindow();
      lp.width = WindowManager.LayoutParams.MATCH_PARENT;
      lp.height = WindowManager.LayoutParams.MATCH_PARENT;
      window.setAttributes(lp);
      getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
      
      AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 0.85f);
      fadeIn.setDuration(1500);
      fadeIn.setInterpolator(new AccelerateInterpolator());
      fadeIn.setFillAfter(true);
      fadeIn.setFillEnabled(true);
      fadeIn.setInterpolator(new DecelerateInterpolator());
      View vBg = getView().findViewById(R.id.bg_dlg);
      vBg.setAnimation(fadeIn);
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    

	@Override
	public Context getApplicationContext() {
		return activity;
	}
	
	@Override
	public void onLogin(User u) {
		Intent intent = new Intent(activity, ProjectListActivity.class);
        startActivity(intent);
        activity.finish();
	}
	
	@Override
	public void onLoginFailure(String reason) {
		TextView loginError = (TextView)getView().findViewById(R.id.login_error);
		loginError.setVisibility(View.VISIBLE);
		loginError.setText(reason);
	}	

}