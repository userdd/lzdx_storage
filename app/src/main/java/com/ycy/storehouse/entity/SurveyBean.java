package com.ycy.storehouse.entity;

import java.util.List;

public class SurveyBean {


    /**
     * total : 18
     * list : [{"id":2,"name":"吴用","createTime":"2019-05-29 14:21:21","address":"忠义堂","remarks":"好汉饶命","price":123,"state":null,"goodsName":null,"unitId":null,"categoryId":null,"sUrl":null},{"id":3,"name":"卢本为","createTime":"2019-05-29 14:27:06","address":"梁山忠义堂","remarks":"好汉饶命","price":123,"state":null,"goodsName":null,"unitId":null,"categoryId":null,"sUrl":null},{"id":4,"name":"白猿","createTime":"2019-06-20 11:22:06","address":"梁山忠义堂","remarks":"好汉饶命","price":321,"state":null,"goodsName":"牛肉","unitId":2,"categoryId":3,"sUrl":null},{"id":5,"name":"杏子","createTime":"2019-07-09 10:08:36","address":"点点滴滴地方","remarks":"点点滴滴到底","price":500,"state":null,"goodsName":"杏子王","unitId":1,"categoryId":49,"sUrl":null},{"id":6,"name":"桃子","createTime":"2019-07-09 18:38:34","address":"发发发","remarks":"ddd","price":500,"state":null,"goodsName":"点点滴滴地方","unitId":2,"categoryId":49,"sUrl":null},{"id":7,"name":"桃子","createTime":"2019-07-09 18:38:58","address":"发发发","remarks":"ddd","price":500,"state":null,"goodsName":"点点滴滴地方","unitId":2,"categoryId":49,"sUrl":null},{"id":8,"name":"桃子","createTime":"2019-07-09 18:39:18","address":"发发发","remarks":"ddd","price":500,"state":null,"goodsName":"点点滴滴地方","unitId":2,"categoryId":49,"sUrl":null},{"id":9,"name":"桃子","createTime":"2019-07-09 18:42:43","address":"干活哈哈","remarks":"查查","price":6000,"state":null,"goodsName":"哥古古怪怪哈哈哈哈","unitId":3,"categoryId":49,"sUrl":null},{"id":10,"name":"打印机","createTime":"2019-07-09 18:44:55","address":"撤回刚刚","remarks":"将计就计","price":900,"state":null,"goodsName":"刚刚好","unitId":3,"categoryId":49,"sUrl":null},{"id":11,"name":"手机","createTime":"2019-07-09 18:52:50","address":"将计就计","remarks":"巴巴爸爸","price":900,"state":null,"goodsName":"火炬计划","unitId":3,"categoryId":49,"sUrl":null}]
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
         * id : 2
         * name : 吴用
         * createTime : 2019-05-29 14:21:21
         * address : 忠义堂
         * remarks : 好汉饶命
         * price : 123
         * state : null
         * goodsName : null
         * unitId : null
         * categoryId : null
         * sUrl : null
         */

        private int id;
        private String name;
        private String createTime;
        private String address;
        private String remarks;
        private int price;
        private int state;
        private String goodsName;
        private int unitId;
        private int categoryId;
        private String sUrl;
        private String shopName;

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

        public int getState() {
            return state;
        }

        public void setState(int state) {
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

        public String getsUrl() {
            return sUrl;
        }

        public void setsUrl(String sUrl) {
            this.sUrl = sUrl;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }
    }
}
