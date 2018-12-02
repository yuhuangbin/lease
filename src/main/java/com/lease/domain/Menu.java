package com.lease.domain;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable{

    public static final Integer ROOT = 0;
    /**
     *  
     */
    private Integer id;

    /**
     *  
     */
    private String text;

    /**
     *  
     */
    private String url;

    /**
     *  
     */
    private Integer upMenuId;

    private List<Menu> nodes;

    public List<Menu> getNodes() {
        return nodes;
    }

    public void setNodes(List<Menu> nodes) {
        this.nodes = nodes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getUpMenuId() {
        return upMenuId;
    }

    public void setUpMenuId(Integer upMenuId) {
        this.upMenuId = upMenuId;
    }
}