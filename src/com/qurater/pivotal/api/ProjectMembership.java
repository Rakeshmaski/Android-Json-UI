package com.qurater.pivotal.api;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;



public class ProjectMembership {
    private Long id;
    private Long projectId;
    private Date lastViewed;
    private Person person;
	

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getProjectId() {
        return projectId;
    }
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    
    public Date getLastViewed() {
        return lastViewed;
    }
    public void setLastViewed(Date lastViewed) {
        this.lastViewed = lastViewed;
    }
    
    public boolean equals(Object o) {
        if (o != null && o instanceof ProjectMembership) {
            return ((ProjectMembership)o).getId().equals(this.getId());
        }
        return false;
    }

    public static ProjectMembership fromJson(JSONObject json) throws JSONException {
    	ProjectMembership p = new ProjectMembership();
        p.setId(json.getLong("id"));
        if (json.has("project_id")) {
        	p.setProjectId(json.getLong("project_id"));
        } 
        if (json.has("person")) {
        	p.setPerson(Person.fromJson(json.getJSONObject("person")));
        }
        return p;
    }
}
