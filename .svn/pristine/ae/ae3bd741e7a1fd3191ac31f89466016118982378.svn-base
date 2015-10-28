package com.qurater.pivotal.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class PivotalActivity {
    private String kind;
    private String guid;
    private Integer projectVersion;
    private String message;
    private String highlight;
    private List<Object> changes;
    private List<Object> primaryResources;
    private Integer projectId;
    private Integer performedById;
    private Date occurredAt;


    public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Integer getProjectVersion() {
		return projectVersion;
	}

	public void setProjectVersion(Integer projectVersion) {
		this.projectVersion = projectVersion;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	public List<Object> getChanges() {
		return changes;
	}

	public void setChanges(List<Object> changes) {
		this.changes = changes;
	}

	public List<Object> getPrimaryResources() {
		return primaryResources;
	}

	public void setPrimaryResources(List<Object> primaryResources) {
		this.primaryResources = primaryResources;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getPerformedById() {
		return performedById;
	}

	public void setPerformedById(Integer performedById) {
		this.performedById = performedById;
	}

	public Date getOccurredAt() {
		return occurredAt;
	}

	public void setOccurredAt(Date occurredAt) {
		this.occurredAt = occurredAt;
	}

	public boolean equals(Object o) {
        if (o != null && o instanceof PivotalActivity) {
            return ((PivotalActivity)o).getGuid().equals(this.getGuid());
        }
        return false;
    }

    public static PivotalActivity fromJson(JSONObject json) throws JSONException {
    	PivotalActivity a = new PivotalActivity();
    	a.setKind(json.getString("kind"));
        a.setGuid(json.getString("guid"));
        try {
            a.setMessage(json.getString("message"));
        } catch(Exception e) {
        }
        try {
            a.setHighlight(json.getString("highlight"));
        } catch(Exception e) {
        }
        try {
            a.setProjectVersion(json.getInt("project_version"));
        } catch(Exception e) {
        }
        try {
            a.setProjectId(json.getInt("project_id"));
        } catch(Exception e) {
        }
        try {
            a.setPerformedById(json.getInt("performed_by_id"));
        } catch(Exception e) {
        }
        try {
            a.setOccurredAt(Utils.parseISO8601(json.getString("occurred_at")));
        } catch(Exception e) {
        }
        List<Object> changes = new ArrayList<Object>();
        if (json.has("changes")) {
        	JSONArray jsonChanges = json.getJSONArray("changes");
        	for (int i = 0; i < jsonChanges.length(); i++) {
        		changes.add(jsonChanges.getJSONObject(i));
        	}
        	a.setChanges(changes);
        }
        List<Object> primaryResources = new ArrayList<Object>();
        if (json.has("primary_resources")) {
        	JSONArray jsonPrimaryResources = json.getJSONArray("primary_resources");
        	for (int i = 0; i < jsonPrimaryResources.length(); i++) {
        		JSONObject jsonResource = jsonPrimaryResources.getJSONObject(i);
        		String kind = jsonResource.getString(PivotalConstants.KIND);
        		if (PivotalConstants.STORY.equals(kind)) {
        			Story story = Story.fromJson(jsonResource);
        			primaryResources.add(story);
        		} else {
        		    primaryResources.add(jsonResource);
        		}
        	}
        	a.setPrimaryResources(primaryResources);
        }
        return a;
    }
}
