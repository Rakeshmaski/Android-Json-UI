package com.qurater.pivotal.api;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;



public class Attachment {
    private Long id;
    private String fileName;
    private Long uploaderId;
    private boolean thumbnailable;
    private Integer height;
    private Integer width;
    private Integer size;
    private String downloadUrl;
    private String contentType;
    private boolean uploaded;
    private String bigUrl;
    private String thumbnailUrl;
    private Date createDate;
    
	

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getUploaderId() {
        return uploaderId;
    }
    public void setUploaderId(Long uploaderId) {
        this.uploaderId = uploaderId;
    }

    public boolean getThumbnailable() {
        return thumbnailable;
    }
    public void setThumbnailable(boolean thumbnailable) {
        this.thumbnailable = thumbnailable;
    }

    public boolean getUploaded() {
        return uploaded;
    }
    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }
    
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public Integer getHeight() {
        return height;
    }
    public void setHeight(Integer height) {
        this.height = height;
    }
    
    public Integer getWidth() {
        return width;
    }
    public void setWidth(Integer width) {
        this.width = width;
    }
    
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }
    
    public String getDownloadUrl() {
        return downloadUrl;
    }
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
    
    public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
    public String getBigUrl() {
        return bigUrl;
    }
    public void setBigUrl(String bigUrl) {
        this.bigUrl = bigUrl;
    }
    
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Attachment) {
            return ((Attachment)o).getId().equals(this.getId());
        }
        return false;
    }

    public static Attachment fromJson(JSONObject json) throws JSONException {
    	Attachment attachment = new Attachment();
    	attachment.setId(json.getLong("id"));
    	if (json.has("filename")) {
    		attachment.setFileName(json.getString("filename"));
        }
    	if (json.has("uploader_id")) {
    		attachment.setUploaderId(json.getLong("uploader_id"));
        }
    	if (json.has("thumbnailable")) {
    		attachment.setThumbnailable(json.getBoolean("thumbnailable"));
        }
    	if (json.has("height")) {
    		attachment.setHeight(json.getInt("height"));
        }
    	if (json.has("width")) {
    		attachment.setWidth(json.getInt("width"));
        }
    	if (json.has("size")) {
    		attachment.setSize(json.getInt("size"));
        }
    	if (json.has("download_url")) {
    		attachment.setDownloadUrl(json.getString("download_url"));
        }
    	if (json.has("content_type")) {
    		attachment.setContentType(json.getString("content_type"));
        }
    	if (json.has("big_url")) {
    		attachment.setBigUrl(json.getString("big_url"));
        }
    	if (json.has("thumbnail_url")) {
    		attachment.setThumbnailUrl(json.getString("thumbnail_url"));
        }
    	if (json.has("uploaded")) {
    		attachment.setUploaded(json.getBoolean("uploaded"));
        }
        return attachment;
    }
}
