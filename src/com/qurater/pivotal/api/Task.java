package com.qurater.pivotal.api;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;



public class Task {
    private Long id;
    private Long storyId;
    private String description;
    private Integer position;
    private boolean complete;
    private Date createDate;
    private Date updateDate;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getStoryId() {
        return storyId;
    }
    public void setStoryId(Long storyId) {
        this.storyId = storyId;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean getComplete() {
        return complete;
    }
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public Integer getPosition() {
        return position;
    }
    public void setPosition(Integer position) {
        this.position = position;
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
        if (o != null && o instanceof Comment) {
            return ((Comment)o).getId().equals(this.getId());
        }
        return false;
    }

    public static Task fromJson(JSONObject json) throws JSONException {
    	Task task = new Task();
    	task.setId(json.getLong("id"));
        if (json.has("story_id")) {
        	task.setStoryId(json.getLong("story_id"));
        }
        if (json.has("description")) {
            task.setDescription(json.getString("description"));
        }
        if (json.has("complete")) {
            task.setComplete(json.getBoolean("complete"));
        }
        if (json.has("position")) {
            task.setPosition(json.getInt("position"));
        }
        return task;
    }
}
