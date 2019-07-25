package com.ycy.storehouse.entity;

import java.util.List;

public class StoreGoodsBean {

    /**
     * total : 14
     * list : [{"id":1,"name":"牛肉","pinYin":"niurou","cateId":2,"unitId":3,"storeId":5,"minWarn":200,"state":0,"stock":378,"createTime":"2019-04-23 10:43:03","updateTime":"2019-06-21 11:37:23","stateGoods":0,"providerId":1,"startTime":null,"endTime":null,"price":571,"position":"B区","gSpecs":"小包","gRemarks":"小包","barCode":null,"storeName":"建材库1","cateName":"食品耗材","unitName":"千克","providerName":"陈小小","enterStoreDomains":null,"outStoreDomains":null},{"id":65,"name":"鱼翅","pinYin":null,"cateId":2,"unitId":3,"storeId":2,"minWarn":15,"state":0,"stock":100,"createTime":"2019-06-14 15:47:05","updateTime":"2019-06-14 15:47:07","stateGoods":0,"providerId":1,"startTime":null,"endTime":null,"price":123213,"position":"兰州大学X区","gSpecs":null,"gRemarks":null,"barCode":null,"storeName":"肉类库1","cateName":"食品耗材","unitName":"千克","providerName":"陈小小","enterStoreDomains":null,"outStoreDomains":null},{"id":42,"name":"金锣火腿肠","pinYin":"jlhtc","cateId":2,"unitId":1,"storeId":2,"minWarn":10,"state":0,"stock":5000,"createTime":"2019-05-07 14:35:29","updateTime":"2019-06-06 16:15:49","stateGoods":0,"providerId":2,"startTime":null,"endTime":null,"price":25,"position":"A区","gSpecs":null,"gRemarks":null,"barCode":null,"storeName":"肉类库1","cateName":"食品耗材","unitName":"箱","providerName":null,"enterStoreDomains":null,"outStoreDomains":null},{"id":53,"name":"建材","pinYin":"jc","cateId":15,"unitId":1,"storeId":1,"minWarn":1989,"state":0,"stock":2000,"createTime":"2019-05-21 16:12:27","updateTime":"2019-05-29 16:18:28","stateGoods":0,"providerId":1,"startTime":null,"endTime":null,"price":5656,"position":"雁滩建材市场","gSpecs":null,"gRemarks":null,"barCode":null,"storeName":"大药材库","cateName":"纯水泥","unitName":"箱","providerName":"陈小小","enterStoreDomains":null,"outStoreDomains":null},{"id":48,"name":"大象肉","pinYin":"daxiangrou","cateId":1,"unitId":1,"storeId":1,"minWarn":500,"state":0,"stock":500,"createTime":"2019-05-08 14:57:22","updateTime":"2019-05-24 15:01:45","stateGoods":0,"providerId":2,"startTime":null,"endTime":null,"price":570,"position":"a区","gSpecs":null,"gRemarks":null,"barCode":null,"storeName":"大药材库","cateName":"耗材","unitName":"箱","providerName":null,"enterStoreDomains":null,"outStoreDomains":null},{"id":43,"name":"犀牛肉","pinYin":"xnr","cateId":2,"unitId":3,"storeId":2,"minWarn":10,"state":0,"stock":5,"createTime":"2019-05-07 17:12:35","updateTime":"2019-05-24 15:00:35","stateGoods":0,"providerId":1,"startTime":null,"endTime":null,"price":200,"position":"A区","gSpecs":null,"gRemarks":null,"barCode":null,"storeName":"肉类库1","cateName":"食品耗材","unitName":"千克","providerName":"陈小小","enterStoreDomains":null,"outStoreDomains":null},{"id":56,"name":"熊猫肉","pinYin":"xmr","cateId":27,"unitId":null,"storeId":2,"minWarn":80,"state":0,"stock":40,"createTime":"2019-05-21 18:21:49","updateTime":"2019-05-24 14:56:28","stateGoods":0,"providerId":5,"startTime":null,"endTime":null,"price":1000,"position":"兰州","gSpecs":null,"gRemarks":null,"barCode":null,"storeName":"肉类库1","cateName":"牛肉","unitName":null,"providerName":null,"enterStoreDomains":null,"outStoreDomains":null},{"id":47,"name":"西红柿","pinYin":"xhs","cateId":2,"unitId":3,"storeId":2,"minWarn":10,"state":0,"stock":10000,"createTime":"2019-05-08 14:26:11","updateTime":"2019-05-24 14:33:00","stateGoods":0,"providerId":2,"startTime":null,"endTime":null,"price":10,"position":"A区","gSpecs":null,"gRemarks":null,"barCode":null,"storeName":"肉类库1","cateName":"食品耗材","unitName":"千克","providerName":null,"enterStoreDomains":null,"outStoreDomains":null},{"id":45,"name":"老虎肉","pinYin":"lhr","cateId":2,"unitId":3,"storeId":2,"minWarn":10,"state":0,"stock":50000,"createTime":"2019-05-08 09:54:31","updateTime":"2019-05-24 14:32:49","stateGoods":0,"providerId":2,"startTime":null,"endTime":null,"price":1000,"position":"B区","gSpecs":null,"gRemarks":null,"barCode":null,"storeName":"肉类库1","cateName":"食品耗材","unitName":"千克","providerName":null,"enterStoreDomains":null,"outStoreDomains":null},{"id":49,"name":"蚂蚁肉","pinYin":"mayirou","cateId":1,"unitId":1,"storeId":1,"minWarn":500,"state":0,"stock":5000,"createTime":"2019-05-08 14:58:03","updateTime":"2019-05-24 14:32:24","stateGoods":0,"providerId":1,"startTime":null,"endTime":null,"price":570,"position":"a区","gSpecs":null,"gRemarks":null,"barCode":null,"storeName":"大药材库","cateName":"耗材","unitName":"箱","providerName":"陈小小","enterStoreDomains":null,"outStoreDomains":null}]
     * pageNum : 1
     * pageSize : 10
     * size : 10
     * startRow : 1
     * endRow : 10
     * pages : 2
     * prePage : 0
     * nextPage : 2
     * isFirstPage : true
     * isLastPage : false
     * hasPreviousPage : false
     * hasNextPage : true
     * navigatePages : 8
     * navigatepageNums : [1,2]
     * navigateFirstPage : 1
     * navigateLastPage : 2
     * firstPage : 1
     * lastPage : 2
     */

    private int total;
    private int pageNum;
    private int pageSize;
    private int size;
    private int startRow;
    private int endRow;
    private int pages;
    private int prePage;
    private int nextPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private boolean hasPreviousPage;
    private boolean hasNextPage;
    private int navigatePages;
    private int navigateFirstPage;
    private int navigateLastPage;
    private int firstPage;
    private int lastPage;
    private List<ListBean> list;
    private List<Integer> navigatepageNums;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public int getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public static class ListBean {
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
         * startTime : null
         * endTime : null
         * price : 571
         * position : B区
         * gSpecs : 小包
         * gRemarks : 小包
         * barCode : null
         * storeName : 建材库1
         * cateName : 食品耗材
         * unitName : 千克
         * providerName : 陈小小
         * enterStoreDomains : null
         * outStoreDomains : null
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
        private Object startTime;
        private Object endTime;
        private int price;
        private String position;
        private String gSpecs;
        private String gRemarks;
        private Object barCode;
        private String storeName;
        private String cateName;
        private String unitName;
        private String providerName;
        private Object enterStoreDomains;
        private Object outStoreDomains;

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

        public Object getStartTime() {
            return startTime;
        }

        public void setStartTime(Object startTime) {
            this.startTime = startTime;
        }

        public Object getEndTime() {
            return endTime;
        }

        public void setEndTime(Object endTime) {
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

        public Object getBarCode() {
            return barCode;
        }

        public void setBarCode(Object barCode) {
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

        public Object getEnterStoreDomains() {
            return enterStoreDomains;
        }

        public void setEnterStoreDomains(Object enterStoreDomains) {
            this.enterStoreDomains = enterStoreDomains;
        }

        public Object getOutStoreDomains() {
            return outStoreDomains;
        }

        public void setOutStoreDomains(Object outStoreDomains) {
            this.outStoreDomains = outStoreDomains;
        }
    }
}
