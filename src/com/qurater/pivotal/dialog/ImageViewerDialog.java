package com.qurater.pivotal.dialog;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.qurater.pivotal.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ImageViewerDialog extends DialogFragment {
    
    protected Activity activity;
    protected String imgUrl;
    
    public ImageViewerDialog(Activity activity, String imgUrl) {
        super();
        this.activity = activity;
        this.imgUrl = imgUrl;
    }
    /** The system calls this to get the DialogFragment's layout, regardless
        of whether it's being displayed as a dialog or an embedded fragment. */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout to use as dialog or embedded fragment
        View view = inflater.inflate(R.layout.dialog_image_viewer, container, false);
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
    		
    		ImageView img = (ImageView)getView().findViewById(R.id.img);
    		Picasso.with(this.activity).load(imgUrl).into(img);
    		Picasso.with(this.activity).load(imgUrl).into(img, new Callback() {
                    @Override
                    public void onError() {
                    	getView().findViewById(R.id.progress).setVisibility(View.GONE);
                    	getView().findViewById(R.id.ico_no_results).setVisibility(View.VISIBLE);
                    }
                    @Override
                    public void onSuccess() {
                    	getView().findViewById(R.id.progress).setVisibility(View.GONE);
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
}