package com.nic.memcache;

import java.io.Serializable;

public class TBean implements Serializable {

    private static final long serialVersionUID = 111771200193345828L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
