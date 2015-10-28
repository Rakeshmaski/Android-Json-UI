package com.qurater.pivotal.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.qurater.pivotal.R;
import com.qurater.pivotal.activity.SearchProjectStoriesActivity;
import com.qurater.pivotal.api.Font;
import com.qurater.pivotal.api.QuraterConstants;

/**
 * Bunch of methods each ui uses
 * @author mishras
 *
 */
public class ProjectUIHelper {
	
	private Activity activity;
	private Long projectId;
	
	public ProjectUIHelper(Activity activity, Long projectId) {
		this.activity = activity;
		this.projectId = projectId;
	}
	public void init() {
        //
        View goBack = getActivity().findViewById(R.id.menu_go_back);
        goBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				activity.onBackPressed();
			}
		});
        
        final DrawerUtils drawerUtils = new DrawerUtils(getActivity());
        drawerUtils.initDrawer();
        drawerUtils.initProjectOptions();
        ImageView drawer = (ImageView) getActivity().findViewById(R.id.drawer);
        drawer.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) {
              if (drawerUtils.isDrawerOpen()) {
            	  drawerUtils.closeDrawer();
              } else {
            	  drawerUtils.openDrawer();
              }
          }
        });
        
        Typeface typefaceLatoLight = Font.getFont(getActivity(), "Lato-Light.ttf");
        final EditText et = (EditText)getActivity().findViewById(R.id.input_search);
        et.setTypeface(typefaceLatoLight);
        String hint = "<i>" + getActivity().getResources().getString(R.string.search) + "</i>";
        et.setHint(Html.fromHtml(hint));
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                	getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
                    getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });
        ImageView btnSearchTrigger = (ImageView) getActivity().findViewById(R.id.btn_search_trigger);
        btnSearchTrigger.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				View vSearchContainer = getActivity().findViewById(R.id.search_container);
				vSearchContainer.setVisibility(View.VISIBLE);
				View vDrawerContainer = getActivity().findViewById(R.id.drawer_container);
				vDrawerContainer.setVisibility(View.GONE);
			}
		});
        ImageView btnCancelSearch = (ImageView) getActivity().findViewById(R.id.btn_close_search);
        btnCancelSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				View vSearchContainer = getActivity().findViewById(R.id.search_container);
				vSearchContainer.setVisibility(View.GONE);
				View vDrawerContainer = getActivity().findViewById(R.id.drawer_container);
				vDrawerContainer.setVisibility(View.VISIBLE);
			}
		});
        ImageView btnSearch = (ImageView) getActivity().findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String searchTerm = et.getText().toString();
				if (searchTerm == null || searchTerm.trim().length() == 0) {
					Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.empty_search), Toast.LENGTH_LONG).show();
				} else {
					Intent intent = new Intent(getActivity(), SearchProjectStoriesActivity.class);
	                intent.putExtra(QuraterConstants.INTENT_PROJECT_ID, projectId);
	                intent.putExtra(QuraterConstants.INTENT_SEARCH_TERM, searchTerm);
	                activity.startActivity(intent);
	                activity.overridePendingTransition(R.anim.slide_left_detail, R.anim.stay_in_place_detail);
				}
			}
		});
	}
	
	public Activity getActivity() {
		return this.activity;
	}
	
}