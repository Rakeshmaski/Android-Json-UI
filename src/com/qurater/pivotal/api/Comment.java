package com.qurater.pivotal.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Comment {
    private Long id;
    private Long storyId;
    private Long epicId;
    private Long personId;
    private String text;
    private Date createDate;
    private Date updateDate;
    private List<Attachment> attachments;
    
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
    
    public Long getEpicId() {
        return epicId;
    }
    public void setEpicId(Long epicId) {
        this.epicId = epicId;
    }
    
    public Long getPersonId() {
        return personId;
    }
    public void setPersonId(Long personId) {
        this.personId = personId;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
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
    
    public List<Attachment> getAttachments() {
        return attachments;
    }
    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
    public boolean equals(Object o) {
        if (o != null && o instanceof Comment) {
            return ((Comment)o).getId().equals(this.getId());
        }
        return false;
    }

    public static Comment fromJson(JSONObject json) throws JSONException {
    	Comment comment = new Comment();
        comment.setId(json.getLong("id"));
        if (json.has("story_id")) {
            comment.setStoryId(json.getLong("story_id"));
        }
        if (json.has("epic_id")) {
            comment.setEpicId(json.getLong("epic_id"));
        }
        if (json.has("person_id")) {
            comment.setPersonId(json.getLong("person_id"));
        }
        if (json.has("text")) {
            comment.setText(json.getString("text"));
        }
        if (json.has("file_attachments")) {
        	List<Attachment> attachments = new ArrayList<Attachment>();
        	JSONArray jAttachments = json.getJSONArray("file_attachments");
        	for (int i = 0; i < jAttachments.length(); i++) {
        		Attachment attachment = Attachment.fromJson(jAttachments.getJSONObject(i));
        		attachments.add(attachment);
        	}
        	comment.setAttachments(attachments);
        }
        if (json.has("created_at")) {
            comment.setCreateDate(Utils.parseISO8601(json.getString("created_at")));
        }
        return comment;
    }
}
