package com.qurater.pivotal.api;

import org.json.JSONException;
import org.json.JSONObject;



public class Person {
    private Long id;
    private String name;
    private String email;
    private String initials;
    private String username;
	

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getInitials() {
        return initials;
    }
    public void setInitials(String initials) {
        this.initials = initials;
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    public boolean equals(Object o) {
        if (o != null && o instanceof Person) {
            return ((Person)o).getId().equals(this.getId());
        }
        return false;
    }

    public static Person fromJson(JSONObject json) throws JSONException {
    	Person p = new Person();
        p.setId(json.getLong("id"));
        p.setName(json.getString("name"));
        if (json.has("email")) {
        	p.setEmail(json.getString("email"));
        }
        if (json.has("initials")) {
        	p.setInitials(json.getString("initials"));
        }
        if (json.has("username")) {
        	p.setUsername(json.getString("username"));
        }
        return p;
    }
}
