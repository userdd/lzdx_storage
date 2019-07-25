package com.ycy.storehouse.entity;

import java.util.List;

public class OutStoreBean {

    /**
     * total : 63
     * list : [{"id":1,"number":50,"createTime":"2019-04-25 19:19:20","person":"希希希","startTime":null,"endTime":null,"cateId":2,"unitId":3,"providerId":1,"longitude":57,"latitude":46,"address":"gdfsgs","goodsId":1,"providerName":"陈小小","goodsName":null},{"id":2,"number":50,"createTime":"2019-04-26 19:19:25","person":"希希希","startTime":null,"endTime":null,"cateId":1,"unitId":null,"providerId":2,"longitude":null,"latitude":null,"address":null,"goodsId":null,"providerName":null,"goodsName":null},{"id":3,"number":500,"createTime":"2019-04-26 19:04:22","person":"希希希","startTime":null,"endTime":null,"cateId":0,"unitId":null,"providerId":0,"longitude":null,"latitude":null,"address":null,"goodsId":null,"providerName":null,"goodsName":null},{"id":4,"number":30,"createTime":"2019-04-26 19:11:03","person":"希希希","startTime":null,"endTime":null,"cateId":0,"unitId":null,"providerId":0,"longitude":null,"latitude":null,"address":null,"goodsId":null,"providerName":null,"goodsName":null},{"id":5,"number":40,"createTime":"2019-04-26 19:12:30","person":"希希希","startTime":null,"endTime":null,"cateId":0,"unitId":null,"providerId":0,"longitude":null,"latitude":null,"address":null,"goodsId":null,"providerName":null,"goodsName":null},{"id":6,"number":40,"createTime":"2019-04-26 19:17:18","person":"希希希","startTime":null,"endTime":null,"cateId":0,"unitId":null,"providerId":0,"longitude":null,"latitude":null,"address":null,"goodsId":null,"providerName":null,"goodsName":null},{"id":7,"number":40,"createTime":"2019-04-26 19:18:36","person":"希希希","startTime":null,"endTime":null,"cateId":0,"unitId":null,"providerId":0,"longitude":null,"latitude":null,"address":null,"goodsId":null,"providerName":null,"goodsName":null},{"id":8,"number":40,"createTime":"2019-04-27 09:15:05","person":"希希希","startTime":null,"endTime":null,"cateId":0,"unitId":null,"providerId":0,"longitude":null,"latitude":null,"address":null,"goodsId":null,"providerName":null,"goodsName":null},{"id":9,"number":50,"createTime":"2019-04-27 09:15:40","person":"希希希","startTime":null,"endTime":null,"cateId":0,"unitId":null,"providerId":0,"longitude":null,"latitude":null,"address":null,"goodsId":null,"providerName":null,"goodsName":null},{"id":10,"number":50,"createTime":"2019-04-27 09:21:11","person":"希希希","startTime":null,"endTime":null,"cateId":0,"unitId":null,"providerId":0,"longitude":null,"latitude":null,"address":null,"goodsId":null,"providerName":null,"goodsName":null}]
     * pageNum : 1
     * pageSize : 10
     * size : 10
     * startRow : 1
     * endRow : 10
     * pages : 7
     * prePage : 0
     * nextPage : 2
     * isFirstPage : true
     * isLastPage : false
     * hasPreviousPage : false
     * hasNextPage : true
     * navigatePages : 8
     * navigatepageNums : [1,2,3,4,5,6,7]
     * navigateFirstPage : 1
     * navigateLastPage : 7
     * firstPage : 1
     * lastPage : 7
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
         * createTime : 2019-04-25 19:19:20
         * person : 希希希
         * startTime : null
         * endTime : null
         * cateId : 2
         * unitId : 3
         * providerId : 1
         * longitude : 57
         * latitude : 46
         * address : gdfsgs
         * goodsId : 1
         * providerName : 陈小小
         * goodsName : null
         */

        private int id;
        private int number;
        private String createTime;
        private String person;
        private Object startTime;
        private Object endTime;
        private int cateId;
        private int unitId;
        private int providerId;
        private int longitude;
        private int latitude;
        private String address;
        private int goodsId;
        private String providerName;
        private Object goodsName;

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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getPerson() {
            return person;
        }

        public void setPerson(String person) {
            this.person = person;
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

        public int getProviderId() {
            return providerId;
        }

        public void setProviderId(int providerId) {
            this.providerId = providerId;
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

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public String getProviderName() {
            return providerName;
        }

        public void setProviderName(String providerName) {
            this.providerName = providerName;
        }

        public Object getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(Object goodsName) {
            this.goodsName = goodsName;
        }
    }
}
