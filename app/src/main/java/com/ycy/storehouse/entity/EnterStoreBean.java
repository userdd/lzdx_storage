package com.ycy.storehouse.entity;

import java.util.List;

public class EnterStoreBean {


    /**
     * total : 16
     * list : [{"id":1,"number":50,"enterTime":"2019-04-25 17:36:17","enterPrice":1,"startTime":null,"endTime":null,"providerId":1,"goodsId":1,"longitude":35,"latitude":344,"address":"非官方的","providerName":"陈小小"},{"id":2,"number":50,"enterTime":"2019-04-18 17:36:22","enterPrice":2,"startTime":null,"endTime":null,"providerId":18,"goodsId":1,"longitude":null,"latitude":null,"address":null,"providerName":null},{"id":3,"number":50,"enterTime":"2019-04-18 17:36:24","enterPrice":3,"startTime":null,"endTime":null,"providerId":18,"goodsId":1,"longitude":null,"latitude":null,"address":null,"providerName":null},{"id":4,"number":50,"enterTime":"2019-04-25 16:35:01","enterPrice":2,"startTime":null,"endTime":null,"providerId":2,"goodsId":1,"longitude":null,"latitude":null,"address":null,"providerName":null},{"id":7,"number":5000,"enterTime":"2019-04-27 16:33:41","enterPrice":1,"startTime":null,"endTime":null,"providerId":1,"goodsId":1,"longitude":null,"latitude":null,"address":null,"providerName":"陈小小"},{"id":29,"number":500,"enterTime":"2019-05-08 10:12:34","enterPrice":277,"startTime":null,"endTime":null,"providerId":5,"goodsId":1,"longitude":null,"latitude":null,"address":null,"providerName":null},{"id":30,"number":500,"enterTime":"2019-05-08 10:14:45","enterPrice":277,"startTime":null,"endTime":null,"providerId":5,"goodsId":1,"longitude":null,"latitude":null,"address":null,"providerName":null},{"id":31,"number":200,"enterTime":"2019-05-08 14:26:11","enterPrice":null,"startTime":null,"endTime":null,"providerId":2,"goodsId":1,"longitude":null,"latitude":null,"address":null,"providerName":null},{"id":32,"number":1000,"enterTime":"2019-05-08 14:56:07","enterPrice":null,"startTime":null,"endTime":null,"providerId":3,"goodsId":1,"longitude":null,"latitude":null,"address":null,"providerName":null},{"id":33,"number":1000,"enterTime":"2019-05-08 14:57:22","enterPrice":null,"startTime":null,"endTime":null,"providerId":3,"goodsId":1,"longitude":null,"latitude":null,"address":null,"providerName":null}]
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
         * number : 50
         * enterTime : 2019-04-25 17:36:17
         * enterPrice : 1
         * startTime : null
         * endTime : null
         * providerId : 1
         * goodsId : 1
         * longitude : 35
         * latitude : 344
         * address : 非官方的
         * providerName : 陈小小
         */

        private int id;
        private int number;
        private String enterTime;
        private int enterPrice;
        private Object startTime;
        private Object endTime;
        private int providerId;
        private int goodsId;
        private int longitude;
        private int latitude;
        private String address;
        private String providerName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getEnterTime() {
            return enterTime;
        }

        public void setEnterTime(String enterTime) {
            this.enterTime = enterTime;
        }

        public int getEnterPrice() {
            return enterPrice;
        }

        public void setEnterPrice(int enterPrice) {
            this.enterPrice = enterPrice;
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

        public int getProviderId() {
            return providerId;
        }

        public void setProviderId(int providerId) {
            this.providerId = providerId;
        }

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getLongitude() {
            return longitude;
        }

        public void setLongitude(int longitude) {
            this.longitude = longitude;
        }

        public int getLatitude() {
            return latitude;
        }

        public void setLatitude(int latitude) {
            this.latitude = latitude;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getProviderName() {
            return providerName;
        }

        public void setProviderName(String providerName) {
            this.providerName = providerName;
        }
    }
}
