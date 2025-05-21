package com.uguimar.mediams.youtube.dto;

public class VideoMetadataDto {
    private String title;
    private String description;
    private String url;
    private String[] tags;


    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public String getUrl() {return url;}
    public void setUrl(String url) {this.url = url;}

    public String[] getTags() {return tags;}
    public void setTags(String[] tags) {this.tags = tags;}
}
