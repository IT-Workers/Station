package com.unitmb.station.admin.model;

import java.io.Serializable;

public class HeadNavMenu implements Serializable{

    private static final long serialVersionUID = 2147877526758120702L;

    private long id;

    private String name;

    private String href;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
