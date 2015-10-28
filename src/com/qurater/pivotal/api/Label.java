package com.qurater.pivotal.api;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;



public class Label {
    private Long id;
    private Long projectId;
    private String name;
    private Date createDate;
    private Date updateDate;
    
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
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public Date getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    public boolean equals(Object o) {
        if (o != null && o instanceof Label) {
            return ((Label)o).getId().equals(this.getId());
        }
        return false;
    }

    public static Label fromJson(JSONObject json) throws JSONException {
    	Label label = new Label();
    	label.setId(json.getLong("id"));
        if (json.has("project_id")) {
        	label.setProjectId(json.getLong("project_id"));
        }
        if (json.has("name")) {
        	label.setName(json.getString("name"));
        }
        return label;
    }
}
