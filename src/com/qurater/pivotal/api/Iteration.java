package com.qurater.pivotal.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Iteration {
    private Integer number;
    private Integer projectId;
    private Integer length;
    private Integer teamStrength;
    private List<Integer> storyIds;
    private Date startDate;
    private Date finishDate;
	
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getTeamStrength() {
		return teamStrength;
	}

	public void setTeamStrength(Integer teamStrength) {
		this.teamStrength = teamStrength;
	}

	public List<Integer> getStoryIds() {
		return storyIds;
	}

	public void setStoryIds(List<Integer> storyIds) {
		this.storyIds = storyIds;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

    public boolean equals(Object o) {
        if (o != null && o instanceof Iteration) {
            return ((Iteration)o).getNumber().equals(this.getNumber());
        }
        return false;
    }

    public static Iteration fromJson(JSONObject json) throws JSONException {
    	Iteration iter = new Iteration();
    	if (json.has("number")) {
    		iter.setNumber(json.getInt("number"));
    	}
    	if (json.has("project_id")) {
    		iter.setProjectId(json.getInt("project_id"));
    	}
    	if (json.has("length")) {
    		iter.setLength(json.getInt("length"));
    	}
    	if (json.has("team_strength")) {
    		iter.setTeamStrength(json.getInt("team_strength"));
    	}
    	List<Integer> storyIds = new ArrayList<Integer>();
    	if (json.has("story_ids")) {
    		JSONArray jsonStoryIds = json.getJSONArray("story_ids");
    		for (int i = 0; i < jsonStoryIds.length(); i++) {
    			storyIds.add(jsonStoryIds.getInt(i));
    		}
    	}
    	iter.setStoryIds(storyIds);
    	if (json.has("start")) {
    		iter.setStartDate(Utils.parseISO8601(json.getString("start")));
        }
    	if (json.has("finish")) {
    		iter.setFinishDate(Utils.parseISO8601(json.getString("finish")));
        }
        return iter;
    }

}
