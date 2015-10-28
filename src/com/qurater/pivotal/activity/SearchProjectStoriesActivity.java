package com.qurater.pivotal.activity;
import java.util.List;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.qurater.pivotal.R;
import com.qurater.pivotal.adapter.SearchProjectStoryAdapter;
import com.qurater.pivotal.adapter.StoryAdapter;
import com.qurater.pivotal.api.Font;
import com.qurater.pivotal.api.QuraterConstants;
import com.qurater.pivotal.api.Story;
import com.qurater.pivotal.interfaces.IStoriesConsumer;
import com.qurater.pivotal.rest.SearchApi;
import com.qurater.pivotal.ui.ExitWarning;

public class SearchProjectStoriesActivity extends FragmentActivity implements IStoriesConsumer {

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
        findViewById(R.id.btn_search_trigger).setVisibility(View.GONE);
        
        ListView stories = (ListView) findViewById(R.id.stories);
        adapter = new SearchProjectStoryAdapter(this);
        stories.setAdapter(adapter);
        
        SearchApi api = new SearchApi(this);
        Bundle extras = getIntent().getExtras();
        Long projectId = extras.getLong(QuraterConstants.INTENT_PROJECT_ID);
        String  searchTerm = extras.getString(QuraterConstants.INTENT_SEARCH_TERM);
        api.searchStories(projectId, searchTerm);
        
        TextView tvTitle = ((TextView)findViewById(R.id.title));
        tvTitle.setText(searchTerm);
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
