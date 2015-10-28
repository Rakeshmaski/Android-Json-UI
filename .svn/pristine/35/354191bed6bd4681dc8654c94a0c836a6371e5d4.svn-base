package com.qurater.pivotal.activity;
import java.util.List;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.qurater.pivotal.R;
import com.qurater.pivotal.adapter.IterationStoryAdapter;
import com.qurater.pivotal.adapter.StoryAdapter;
import com.qurater.pivotal.api.Font;
import com.qurater.pivotal.api.QuraterConstants;
import com.qurater.pivotal.api.Story;
import com.qurater.pivotal.interfaces.IStoriesConsumer;
import com.qurater.pivotal.rest.IterationStoriesApi;
import com.qurater.pivotal.ui.ExitWarning;

public class IterationStoriesActivity extends FragmentActivity implements IStoriesConsumer {

	protected StoryAdapter adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        setContentView(R.layout.activity_stories);
        
        //
        View goBack = findViewById(R.id.menu_go_back);
        goBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});
        
        findViewById(R.id.drawer).setVisibility(View.GONE);
        
        
        ListView stories = (ListView) findViewById(R.id.stories);
        adapter = new IterationStoryAdapter(this);
        stories.setAdapter(adapter);
        
        IterationStoriesApi api = new IterationStoriesApi(this);
        Bundle extras = getIntent().getExtras();
        Integer projectId = extras.getInt(QuraterConstants.INTENT_PROJECT_ID);
        Integer iteration = extras.getInt(QuraterConstants.INTENT_ITERATION);
        api.getDoneStoriesForIteration(projectId, iteration-1);
        
        TextView tvTitle = ((TextView)findViewById(R.id.title));
        String s = getResources().getString(R.string.iteration_i_done);
        s = s.replace("{number}", String.valueOf(iteration));
        tvTitle.setText(s);
        Typeface typeface = Font.getFont(getApplicationContext(), "Lato-Bold.ttf");
        tvTitle.setTypeface(typeface);

    }
    
    @Override
    public void onBackPressed() {
        boolean warned = ExitWarning.warn(this);
        if (!warned) {
            super.onBackPressed();
            overridePendingTransition(0, R.anim.slide_right_detail);
        }  
    }
    
    @Override
	public void onStoriesLoadSuccess(List<Story> stories) {
		findViewById(R.id.progress).setVisibility(View.GONE);
		adapter.update(stories);
	}

	@Override
	public void onStoriesLoadFailure(String reason) {
		findViewById(R.id.progress).setVisibility(View.GONE);
		findViewById(R.id.ico_no_results).setVisibility(View.VISIBLE);
	}

}
