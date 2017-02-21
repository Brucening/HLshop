package com.example.gn.hlshop.entity;

/**
 * Created by GN on 2017/2/15.
 */
public class Category extends BaseBean{
    private String name;

    public Category() {

    }
    public Category(String name) {
        this.name = name;
    }
    public Category(long id,String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
