package com.qurater.pivotal.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qurater.pivotal.R;
import com.qurater.pivotal.api.Attachment;
import com.qurater.pivotal.api.Font;
import com.qurater.pivotal.dialog.ImageViewerDialog;
import com.squareup.picasso.Picasso;


public class AttachmentAdapter extends BaseAdapter {
 
	protected List<Attachment> attachments;
	protected Activity activity;
	private BaseAdapter parentAdapter;
	
    public AttachmentAdapter(Activity activity, BaseAdapter parentAdapter) {
        this.activity = activity;
        this.parentAdapter = parentAdapter;
    }
       
    @Override
    public int getCount() {
        return (attachments != null)? attachments.size(): 0;
    }
 
    @Override
    public Object getItem(int position) {
        return (attachments != null)? attachments.get(position): null;
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    public Activity getActivity() {
        return this.activity;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {                                           
        View layout;
        if (convertView == null) {
            layout = getActivity().getLayoutInflater().inflate(
                     R.layout.one_attachment, 
                     parent, 
                     false);
        } else {
            layout = (RelativeLayout) convertView;
        }
          
        final Attachment attachment = attachments.get(position); 
        Typeface typefaceLatoBold = Font.getFont(getActivity().getApplicationContext(), "Lato-Bold.ttf");
        
        String contentType = attachment.getContentType();
        ImageView ico_attachment = (ImageView)layout.findViewById(R.id.ico_attachment);
        if (contentType != null && contentType.indexOf("image") != -1) {
        	Picasso.with(this.activity)
            .load(attachment.getBigUrl())
             .into(ico_attachment);
        	
        	ico_attachment.setOnClickListener(new View.OnClickListener() {
    			@Override
    			public void onClick(View v) {
    	        	ImageViewerDialog dlg = new ImageViewerDialog(activity, attachment.getBigUrl());
    		    	dlg.show(((FragmentActivity)activity).getSupportFragmentManager(), "");
    			}
    		});
        } else {
	        ico_attachment.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String download_uri = "https://www.pivotaltracker.com/file_attachments/{file_attachment_id}/download";
					download_uri = download_uri.replace("{file_attachment_id}", attachment.getId() + "");
					Intent intent = new Intent(Intent.ACTION_VIEW);
					intent.setData(Uri.parse(download_uri));//, attachment.getContentType());
					activity.startActivity(intent);	
				}
			});
        }
        TextView tv = (TextView)layout.findViewById(R.id.name);
        tv.setTypeface(typefaceLatoBold);
        String name = attachment.getFileName();
        tv.setText(name);
        return layout;
    }
 
    public void clear() {
    	attachments.clear();
        notifyDataSetChanged();
    }
       
    public void update(List<Attachment> attachments) {
    	attachments = (attachments == null)? new ArrayList<Attachment>(): attachments; 
        if (this.attachments != null) {     
            this.attachments.addAll(attachments);
        } else {
            this.attachments = attachments;
        }
        notifyDataSetChanged();
        parentAdapter.notifyDataSetChanged();
    } 
}