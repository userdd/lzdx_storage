package com.ycy.storehouse.entity;

public class StoreBean {

    /**
     * id : 5
     * name : 建材库1
     * address : 兰州大学榆中校区
     * state : 0
     * uid : 0
     * createTime : 2019-05-05 15:04:57
     * updateTime : 2019-05-06 15:35:57
     */

    private int id;
    private String name;
    private String address;
    private int state;
    private int uid;
    private String createTime;
    private String updateTime;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
