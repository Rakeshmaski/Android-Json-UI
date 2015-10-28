package com.qurater.pivotal.api;

import java.util.HashMap;
import java.util.Map;


public class URLMap {
	public static String PIVOTAL_BASE_URL = "https://www.pivotaltracker.com/services/v5";
	public static Map<String, String> m_urls = new HashMap<String, String>();
	static {
		m_urls.put("login_url", "/me");
		m_urls.put("projects_url", "/projects");
		m_urls.put("project_stories_url", "/projects/{project_id}/stories");
		m_urls.put("project_iterations_url", "/projects/{project_id}/iterations");
		m_urls.put("story_url", "/projects/{project_id}/stories/{story_id}");
		m_urls.put("search_url", "/projects/{project_id}/search");
		m_urls.put("labels_url", "/projects/{project_id}/labels");
		m_urls.put("project_activities_url", "/projects/{project_id}/activity");
	}
	
	public static String getUrl(String key) {
		return PIVOTAL_BASE_URL + m_urls.get(key);
	}
}