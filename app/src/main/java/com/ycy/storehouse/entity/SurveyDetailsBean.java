package com.ycy.storehouse.entity;

public class SurveyDetailsBean {

    /**
     * id : 18
     * name : 还好
     * createTime : 2019-07-10 09:05:29
     * address : 你将计就计
     * remarks : 返回好好干
     * price : 700
     * state : null
     * goodsName : 哈哈还把
     * unitId : 3
     * categoryId : 49
     * sUrl : ["http:\/\/qiniu-store-landa.lz-cc.com\/1934903031423.JPEG","http:\/\/qiniu-store-landa.lz-cc.com\/5795871613539.JPEG"]
     */

    private int id;
    private String name;
    private String createTime;
    private String address;
    private String remarks;
    private int price;
    private Object state;
    private String goodsName;
    private int unitId;
    private int categoryId;
    private String sUrl;
    private String shopName;
    private String unitName;
    private String categoryName;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getsUrl() {
        return sUrl;
    }

    public void setsUrl(String sUrl) {
        this.sUrl = sUrl;
    }

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getSUrl() {
        return sUrl;
    }

    public void setSUrl(String sUrl) {
        this.sUrl = sUrl;
    }
}
