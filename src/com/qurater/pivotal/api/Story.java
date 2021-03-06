package com.qurater.pivotal.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Story {
    private Long id;
    private Long projectId;
    private String name;
    private String description;
    private Long estimate;
    private String storyType;
    private String currentState;
    private Date createDate;
    private Date updateDate;
    private Long requestedById;
    private List<Long> ownerIds;
    private List<Long> followerIds;
    private List<Label> labels;
    private List<Comment> comments;
    private List<Long> commentIds;
    private List<Task> tasks;
    
	

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
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Long getEstimate() {
        return estimate;
    }
    public void setEstimate(Long estimate) {
        this.estimate = estimate;
    }
    
    public String getStoryType() {
        return storyType;
    }
    public void setStoryType(String storyType) {
        this.storyType = storyType;
    }
    
    public String getCurrentState() {
        return currentState;
    }
    public void setCurrentState(String currentState) {
        this.currentState = currentState;
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
    
    public Long getRequestedById() {
    	return requestedById;
    }
    public void setRequestedById(Long requestedById) {
    	this.requestedById = requestedById;
    }
    
    public List<Long> getOwnerIds() {
    	return ownerIds;
    }
    public void setOwnerIds(List<Long> ownerIds) {
    	this.ownerIds = ownerIds;
    }
    
    public List<Long> getFollowerIds() {
    	return followerIds;
    }
    public void setFollowerIds(List<Long> followerIds) {
    	this.followerIds = followerIds;
    }
    
    public List<Long> getCommentIds() {
    	return commentIds;
    }
    public void setCommentIds(List<Long> commentIds) {
    	this.commentIds = commentIds;
    }
    
    public List<Label> getLabels() {
    	return labels;
    }
    public void setLabels(List<Label> labels) {
    	this.labels = labels;
    }
    
    public List<Comment> getComments() {
    	return comments;
    }
    public void setComments(List<Comment> comments) {
    	this.comments = comments;
    }
    
    public List<Task> getTasks() {
    	return tasks;
    }
    public void setTasks(List<Task> tasks) {
    	this.tasks = tasks;
    }
    
    public boolean equals(Object o) {
        if (o != null && o instanceof Story) {
            return ((Story)o).getId().equals(this.getId());
        }
        return false;
    }

    public static Story fromJson(JSONObject json) throws JSONException {
    	Story s = new Story();
        s.setId(json.getLong("id"));
        if (json.has("project_id")) {
            s.setProjectId(json.getLong("project_id"));
        }
        if (json.has("name")) {
            s.setName(json.getString("name"));
        }
        if (json.has("description")) {
            s.setDescription(json.getString("description"));
        }
        if (json.has("estimate")) {
            s.setEstimate(json.getLong("estimate"));
        }
        if (json.has("story_type")) {
            s.setStoryType(json.getString("story_type"));
        }
        if (json.has("current_state")) {
            s.setCurrentState(json.getString("current_state"));
        }
        if (json.has("requested_by_id")) {
            s.setRequestedById(json.getLong("requested_by_id"));
        }
        if (json.has("owner_ids")) {
        	List<Long> ownerIds = new ArrayList<Long>();
        	JSONArray jOwnerIds = json.getJSONArray("owner_ids");
        	for (int i = 0; i < jOwnerIds.length(); i++) {
        		ownerIds.add(jOwnerIds.getLong(i));
        	}
            s.setOwnerIds(ownerIds);
        }
        if (json.has("follower_ids")) {
        	List<Long> followerIds = new ArrayList<Long>();
        	JSONArray jFollowerIds = json.getJSONArray("follower_ids");
        	for (int i = 0; i < jFollowerIds.length(); i++) {
        		followerIds.add(jFollowerIds.getLong(i));
        	}
            s.setFollowerIds(followerIds);
        }
        if (json.has("comment_ids")) {
        	List<Long> commentIds = new ArrayList<Long>();
        	JSONArray jCommentIds = json.getJSONArray("comment_ids");
        	for (int i = 0; i < jCommentIds.length(); i++) {
        		commentIds.add(jCommentIds.getLong(i));
        	}
            s.setCommentIds(commentIds);
        }
        if (json.has("labels")) {
        	List<Label> labels = new ArrayList<Label>();
        	JSONArray jLabels = json.getJSONArray("labels");
        	for (int i = 0; i < jLabels.length(); i++) {
        		labels.add(Label.fromJson(jLabels.getJSONObject(i)));
        	}
            s.setLabels(labels);
        }
        if (json.has("comments")) {
        	List<Comment> comments = new ArrayList<Comment>();
        	JSONArray jComments = json.getJSONArray("comments");
        	for (int i = 0; i < jComments.length(); i++) {
        		comments.add(Comment.fromJson(jComments.getJSONObject(i)));
        	}
            s.setComments(comments);
        }
        if (json.has("tasks")) {
        	List<Task> tasks = new ArrayList<Task>();
        	JSONArray jTasks = json.getJSONArray("tasks");
        	for (int i = 0; i < jTasks.length(); i++) {
        		tasks.add(Task.fromJson(jTasks.getJSONObject(i)));
        	}
            s.setTasks(tasks);
        }
        if (json.has("created_at")) {
            s.setCreateDate(Utils.parseISO8601(json.getString("created_at")));
        }
        if (json.has("updated_at")) {
            s.setUpdateDate(Utils.parseISO8601(json.getString("updated_at")));
        }
        return s;
    }
}
