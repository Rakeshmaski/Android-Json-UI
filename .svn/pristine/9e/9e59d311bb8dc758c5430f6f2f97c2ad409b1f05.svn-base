package com.qurater.pivotal.api;

import android.annotation.SuppressLint;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



@SuppressLint("UseSparseArrays") 
public class Project {
    private Long id;
    private Long accountId;
    private String name;
    private Integer currentVelocity;
    private Double currentVolatility;
    private String pointScale;
    private String profileContent;
    private Integer currentIterationNumber;
    private List<Long> scale;
    private Date createDate;
    private Date updateDate;
    private List<ProjectMembership> memberships;
	private Map<Long, Person> m_persons = new HashMap<Long, Person>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getAccountId() {
        return accountId;
    }
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPointScale() {
        return pointScale;
    }
    public void setPointScale(String pointScale) {
        this.pointScale = pointScale;
    }
    
    public String getProfileContent() {
        return profileContent;
    }
    public void setProfileContent(String profileContent) {
        this.profileContent = profileContent;
    }
    
    public Integer getCurrentVelocity() {
        return currentVelocity;
    }
    public void setCurrentVelocity(Integer currentVelocity) {
        this.currentVelocity = currentVelocity;
    }
    
    public Integer getCurrentIterationNumber() {
        return currentIterationNumber;
    }
    public void setCurrentIterationNumber(Integer currentIterationNumber) {
        this.currentIterationNumber = currentIterationNumber;
    }
    public Double getCurrentVolatility() {
        return currentVolatility;
    }
    public void setCurrentVolatility(Double currentVolatility) {
        this.currentVolatility = currentVolatility;
    }
    
    public List<Long> getScale() {
        return scale;
    }
    public void setScale(List<Long> scale) {
        this.scale = scale;
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
    
    public List<ProjectMembership> getMemberships() {
        return memberships;
    }
    
    public void setMemberships(List<ProjectMembership> memberships) {
        this.memberships = memberships;
        if (memberships != null) {
        	for (ProjectMembership member: memberships) {
        		Person person = member.getPerson();
        		if (person != null) {
        		    m_persons.put(person.getId(), person);
        		}
        	}
        }
    }
    
    public Person getMember(Long personId) {
        return m_persons.get(personId);
    }
    
    
    public boolean equals(Object o) {
        if (o != null && o instanceof Project) {
            return ((Project)o).getId().equals(this.getId());
        }
        return false;
    }

    public static Project fromJson(JSONObject json) throws JSONException {
    	Project p = new Project();
        p.setId(json.getLong("id"));
        p.setName(json.getString("name"));
        if (json.has("current_velocity")) {
        	p.setCurrentVelocity(json.getInt("current_velocity"));
        }
        if (json.has("current_iteration_number")) {
        	p.setCurrentIterationNumber(json.getInt("current_iteration_number"));
        }
        if (json.has("current_volatility")) {
        	p.setCurrentVolatility(json.getDouble("current_volatility"));
        }
        List<ProjectMembership> memberships = new ArrayList<ProjectMembership>();
        if (json.has("memberships")) {
        	JSONArray jMemberships = json.getJSONArray("memberships");
        	for (int i = 0; i < jMemberships.length(); i++) {
        		memberships.add(ProjectMembership.fromJson(jMemberships.getJSONObject(i)));
        	}
        	p.setMemberships(memberships);
        }
        if (json.has("point_scale")) {
        	String projectScale = json.getString("point_scale");
        	try {
        		List<Long> scale = new ArrayList<Long>();
        		String[] tmp = projectScale.split(",");
        		for (int i = 0; i < tmp.length; i++) {
        			scale.add(Long.valueOf(tmp[i]));
        		}
        		p.setScale(scale);
        		System.err.println(scale);
        	} catch(Exception e) {
        		
        	}
        }
        return p;
    }
}
