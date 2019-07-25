package com.ycy.storehouse.entity;

import java.util.List;

public class BarCodeBean {


    /**
     * id : 1
     * name : 牛肉
     * pinYin : niurou
     * cateId : 2
     * unitId : 3
     * storeId : 5
     * minWarn : 200
     * state : 0
     * stock : 378
     * createTime : 2019-04-23 10:43:03
     * updateTime : 2019-06-21 11:37:23
     * stateGoods : 0
     * providerId : 1
     * startTime : 2019-04-23 10:43:03
     * endTime : 2019-04-23 10:43:03
     * price : 571
     * position : B区
     * gSpecs : 小包
     * gRemarks : 小包
     * barCode : null
     * storeName : 建材库1
     * cateName : 食品耗材
     * unitName : 千克
     * providerName : 陈小小
     * enterStoreDomains : [{"id":1,"number":50,"enterTime":"2019-04-25 17:36:17","enterPrice":1,"startTime":"2019-04-23 10:43:03","endTime":"2019-04-23 10:43:03","providerId":1,"goodsId":1,"providerDomain":{},"goods":{},"longitude":35,"latitude":344,"address":"非官方的"}]
     * outStoreDomains : [{"id":1,"number":50,"createTime":"2019-04-25 19:19:20","person":"希希希","startTime":"2019-04-23 10:43:03","endTime":"2019-04-23 10:43:03","cateId":1,"unitId":1,"providerId":1,"longitude":56.78,"latitude":45.67,"address":"null","goodsId":1,"providerDomain":{},"goodsName":"希希希"}]
     */

    private int id;
    private String name;
    private String pinYin;
    private int cateId;
    private int unitId;
    private int storeId;
    private int minWarn;
    private int state;
    private int stock;
    private String createTime;
    private String updateTime;
    private int stateGoods;
    private int providerId;
    private String startTime;
    private String endTime;
    private int price;
    private String position;
    private String gSpecs;
    private String gRemarks;
    private String barCode;
    private String storeName;
    private String cateName;
    private String unitName;
    private String providerName;


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

    public String getPinYin() {
        return pinYin;
    }

    public void setPinYin(String pinYin) {
        this.pinYin = pinYin;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getMinWarn() {
        return minWarn;
    }

    public void setMinWarn(int minWarn) {
        this.minWarn = minWarn;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public int getStateGoods() {
        return stateGoods;
    }

    public void setStateGoods(int stateGoods) {
        this.stateGoods = stateGoods;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGSpecs() {
        return gSpecs;
    }

    public void setGSpecs(String gSpecs) {
        this.gSpecs = gSpecs;
    }

    public String getGRemarks() {
        return gRemarks;
    }

    public void setGRemarks(String gRemarks) {
        this.gRemarks = gRemarks;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }



    public static class EnterStoreDomainsBean {

    }

    public static class OutStoreDomainsBean {


        public static class ProviderDomainBeanX {
        }
    }
}
