package com.ycy.storehouse.entity;

public class BalanceBean {

    private String name;

    private String address;

    public BalanceBean(String name,String address){
        setName(name);
        setAddress(address);
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
}
