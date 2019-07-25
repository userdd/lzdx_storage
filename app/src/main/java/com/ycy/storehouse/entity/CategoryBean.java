package com.ycy.storehouse.entity;

import java.util.List;

public class CategoryBean {


    /**
     * id : 1
     * name : 耗材
     * pid : 0
     * category : [{"id":2,"name":"食品耗材","pid":1,"category":[{"id":26,"name":"猪肉","pid":2,"category":null},{"id":27,"name":"牛肉","pid":2,"category":null},{"id":28,"name":"羊肉","pid":2,"category":null},{"id":29,"name":"鱼肉","pid":2,"category":null}]},{"id":4,"name":"颜料","pid":1,"category":[{"id":6,"name":"墨水","pid":4,"category":null}]},{"id":5,"name":"纸","pid":1,"category":[{"id":7,"name":"A4纸","pid":5,"category":null},{"id":9,"name":"A3纸","pid":5,"category":null}]},{"id":8,"name":"笔","pid":1,"category":[{"id":30,"name":"铅笔","pid":8,"category":null},{"id":31,"name":"钢笔","pid":8,"category":null}]}]
     */

    private int id;
    private String name;
    private int pid;
    private List<CategoryBean> category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public List<CategoryBean> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }

}
