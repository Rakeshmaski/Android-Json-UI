package com.qurater.pivotal.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qurater.pivotal.R;
import com.qurater.pivotal.api.Font;
import com.qurater.pivotal.api.Person;


public class UserAdapter extends BaseAdapter {
 
    protected List<Person> persons;
    protected Activity activity;
 
    public UserAdapter(Activity activity) {
        this.activity = activity;
    }
       
    @Override
    public int getCount() {
        return (persons != null)? persons.size(): 0;
    }
 
    @Override
    public Object getItem(int position) {
        return (persons != null)? persons.get(position): null;
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
                     R.layout.one_user, 
                     parent, 
                     false);
        } else {
            layout = (RelativeLayout) convertView;
        }
          
        final Person person = persons.get(position); 
        Typeface typefaceLatoRegular = Font.getFont(getActivity().getApplicationContext(), "Lato-Regular.ttf");
        TextView tv = (TextView)layout.findViewById(R.id.name);
        tv.setTypeface(typefaceLatoRegular);
        String name = person.getName();
        tv.setText(name);
        

        return layout;
    }
 
    public void clear() {
        persons.clear();
        notifyDataSetChanged();
    }
       
    public void update(List<Person> persons) {
    	persons = (persons == null)? new ArrayList<Person>(): persons; 
        if (this.persons != null) {     
            this.persons.addAll(persons);
        } else {
            this.persons = persons;
        }
        notifyDataSetChanged();
    } 
}