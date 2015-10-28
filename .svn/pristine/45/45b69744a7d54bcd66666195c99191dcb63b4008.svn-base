package com.qurater.pivotal.api;

import org.json.JSONException;
import org.json.JSONObject;



public class User {
    private Long id;
    private String name;
    
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
    
    public boolean equals(Object o) {
        if (o != null && o instanceof User) {
            return ((User)o).getId().equals(this.getId());
        }
        return false;
    }
    

    public static User fromJson(JSONObject json) throws JSONException {
        User u = new User();
        u.setId(json.getLong("id"));
        u.setName(json.getString("name"));
        return u;
    }
}
