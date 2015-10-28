package com.qurater.pivotal.ui;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

import com.qurater.pivotal.interfaces.IScrollable;

public class ScrollListener implements OnScrollListener {

    private int visibleThreshold = 6;
    private int currentPage = 0;
    private int previousTotal = 0;
    private boolean loading = true;
    private IScrollable scrollable = null;
    
    public ScrollListener(IScrollable scrollable) {
    	this.scrollable = scrollable;
    }
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
            int visibleItemCount, int totalItemCount) {
    	
    	if (visibleItemCount > 1) {
	        if (loading) {
	            if (totalItemCount > previousTotal) {
	                loading = false;
	                previousTotal = totalItemCount;
	                currentPage++;
	            }
	        }
	        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
	        	scrollable.getPage(currentPage);
	            loading = true;
	        }
    	}
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }
    
}