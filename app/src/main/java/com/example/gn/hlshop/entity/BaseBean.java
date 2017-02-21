package com.example.gn.hlshop.entity;

import java.io.Serializable;

/**
 * Created by GN on 2017/2/15.
 */
public class BaseBean implements Serializable{
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
