package com.qurater.pivotal.adapter;

import java.util.LinkedHashMap;
import java.util.Map;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;


public class MergedAdapter extends BaseAdapter {
    protected Map<String, Adapter> sections = new LinkedHashMap<String, Adapter>();

    
    /**
     * constructor
     * @author mishras
     *
     */
    public  MergedAdapter() {
    }
    
    /**
     * Add an adapter
     * @param key
     * @param value
     */
    public void addSection(String key, Adapter value) {
        sections.put(key, value);
    }

    @Override
    public int getCount() {
        int total = 0;
        for (Adapter adapter: sections.values()) {
            total += adapter.getCount();
        }
        return total;
    }

    @Override
    public int getViewTypeCount() {
        int total = 0;
        for (Adapter adapter: sections.values()) {
            total += adapter.getViewTypeCount();
        }
        return total;
    }
    
    @Override
    public int getItemViewType(int position) {
        int type = 0; 
        for (String section: sections.keySet()) {
            Adapter adapter = sections.get(section);
            int size = adapter.getCount();
            //check if position is inside this section
            if (position < size) {
                return type + adapter.getItemViewType(position);
            }
            position -= size;
            type += adapter.getViewTypeCount();  
        }
        return -1;  
    }
    
    
    @Override
    public Object getItem(int position) {
        for (String section: sections.keySet()) {
            Adapter adapter = sections.get(section);
            int size = adapter.getCount();
            //check if position is inside this section
            if (position < size) {
                return adapter.getItem(position);
            }
            position -= size;
        }
        return null;  
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        for(Object section : this.sections.keySet()) {  
            Adapter adapter = sections.get(section);  
            int size = adapter.getCount();
            // check if position inside this section   
            if(position < size) {
                return adapter.getView(position, convertView, parent);  
            }
            // otherwise jump into next section  
            position -= size;  
        }  
        return null;  
    }
}