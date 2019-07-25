package com.ycy.storehouse.entity;

import java.util.List;

public class LoginBean {


    /**
     * id : 1
     * accountName : admin
     * accountPass : d89f84f15ac3ede2737b95be7949e279
     * salt : abcd
     * createTime : 2019-01-10 00:00:00
     * token : 75d1d745bcabd52ba80c0e3e7af2b67e
     * type : 0
     * roleId : 1
     * personId : 4228
     * roleDomain : {"id":1,"roleTitle":"超级管理员角色","remark":"超级管理员角色","createBy":1,"createTime":"2019-01-09 10:35:22","state":1,"menuDomain":[{"id":288,"roleId":null,"menu":"[{\"value\":\"人事档案管理\",\"title\":\"人事档案管理\",\"expand\":true,\"id\":\"rsgl\",\"children\":[{\"value\":\"人员信息\",\"title\":\"人员信息\",\"id\":\"rsgl\",\"children\":[{\"value\":\"人员信息管理\",\"title\":\"人员信息管理\",\"id\":\"rsgl\",\"nodeKey\":2,\"checked\":true,\"indeterminate\":false},{\"value\":\"人员操作权限\",\"title\":\"人员操作权限\",\"id\":\"rsgl\",\"children\":[{\"value\":\"人员添加\",\"title\":\"人员添加\",\"id\":\"rsgl\",\"nodeKey\":4,\"checked\":true,\"indeterminate\":false},{\"value\":\"人员编辑\",\"title\":\"人员编辑\",\"id\":\"rsgl\",\"nodeKey\":5,\"checked\":true,\"indeterminate\":false},{\"value\":\"人员调动\",\"title\":\"人员调动\",\"id\":\"rsgl\",\"nodeKey\":6,\"checked\":true,\"indeterminate\":false},{\"value\":\"修改调动\",\"title\":\"修改调动\",\"id\":\"rsgl\",\"nodeKey\":7,\"checked\":true,\"indeterminate\":false},{\"value\":\"人员导入\",\"title\":\"人员导入\",\"id\":\"rsgl\",\"nodeKey\":8,\"checked\":true,\"indeterminate\":false},{\"value\":\"添加五险\",\"title\":\"添加五险\",\"id\":\"rsgl\",\"nodeKey\":9,\"checked\":true,\"indeterminate\":false},{\"value\":\"修改五险\",\"title\":\"修改五险\",\"id\":\"rsgl\",\"nodeKey\":10,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":3,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":1,\"checked\":true,\"indeterminate\":false},{\"value\":\"合同信息管理\",\"title\":\"合同信息管理\",\"id\":\"rsgl\",\"children\":[{\"value\":\"合同管理\",\"title\":\"合同管理\",\"id\":\"rsgl\",\"nodeKey\":12,\"checked\":true,\"indeterminate\":false},{\"value\":\"合同操作权限\",\"title\":\"合同操作权限\",\"id\":\"rsgl\",\"children\":[{\"value\":\"添加合同\",\"title\":\"添加合同\",\"id\":\"rsgl\",\"nodeKey\":14,\"checked\":true,\"indeterminate\":false},{\"value\":\"修改合同\",\"title\":\"修改合同\",\"id\":\"rsgl\",\"nodeKey\":15,\"checked\":true,\"indeterminate\":false},{\"value\":\"删除合同\",\"title\":\"删除合同\",\"id\":\"rsgl\",\"nodeKey\":16,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":13,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":11,\"checked\":true,\"indeterminate\":false},{\"value\":\"退休人员管理\",\"title\":\"退休人员管理\",\"id\":\"rsgl\",\"children\":[{\"value\":\"退休管理\",\"title\":\"退休管理\",\"id\":\"rsgl\",\"nodeKey\":18,\"checked\":true,\"indeterminate\":false},{\"value\":\"退休操作权限\",\"title\":\"退休操作权限\",\"id\":\"rsgl\",\"children\":[{\"value\":\"退休办理\",\"title\":\"退休办理\",\"id\":\"rsgl\",\"nodeKey\":20,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":19,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":17,\"checked\":true,\"indeterminate\":false},{\"value\":\"离职信息管理\",\"title\":\"离职信息管理\",\"id\":\"rsgl\",\"children\":[{\"value\":\"离职管理\",\"title\":\"离职管理\",\"id\":\"rsgl\",\"nodeKey\":22,\"checked\":true,\"indeterminate\":false},{\"value\":\"离职操作权限\",\"title\":\"离职操作权限\",\"id\":\"rsgl\",\"children\":[{\"value\":\"离职办理\",\"title\":\"离职办理\",\"id\":\"rsgl\",\"nodeKey\":24,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":23,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":21,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":0,\"checked\":true,\"indeterminate\":false},{\"value\":\"培训管理\",\"title\":\"培训管理\",\"expand\":true,\"id\":\"pxgl\",\"children\":[{\"value\":\"培训档案管理\",\"title\":\"培训档案管理\",\"id\":\"pxgl\",\"children\":[{\"value\":\"培训档案\",\"title\":\"培训档案\",\"id\":\"pxgl\",\"nodeKey\":27,\"checked\":true,\"indeterminate\":false},{\"value\":\"培训操作权限\",\"title\":\"培训操作权限\",\"id\":\"pxgl\",\"children\":[{\"value\":\"创建培训档案\",\"title\":\"创建培训档案\",\"id\":\"pxgl\",\"nodeKey\":29,\"checked\":true,\"indeterminate\":false},{\"value\":\"添加评估\",\"title\":\"添加评估\",\"id\":\"pxgl\",\"nodeKey\":30,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":28,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":26,\"checked\":true,\"indeterminate\":false},{\"value\":\"培训课程管理\",\"title\":\"培训课程管理\",\"id\":\"pxgl\",\"children\":[{\"value\":\"课程管理\",\"title\":\"课程管理\",\"id\":\"pxgl\",\"nodeKey\":32,\"checked\":true,\"indeterminate\":false},{\"value\":\"课程操作权限\",\"title\":\"课程操作权限\",\"id\":\"pxgl\",\"children\":[{\"value\":\"添加课程\",\"title\":\"添加课程\",\"id\":\"pxgl\",\"nodeKey\":34,\"checked\":true,\"indeterminate\":false},{\"value\":\"修改课程\",\"title\":\"修改课程\",\"id\":\"pxgl\",\"nodeKey\":35,\"checked\":true,\"indeterminate\":false},{\"value\":\"删除课程\",\"title\":\"删除课程\",\"id\":\"pxgl\",\"nodeKey\":36,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":33,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":31,\"checked\":true,\"indeterminate\":false},{\"value\":\"培训计划设计\",\"title\":\"培训计划设计\",\"id\":\"pxgl\",\"children\":[{\"value\":\"培训计划\",\"title\":\"培训计划\",\"id\":\"pxgl\",\"nodeKey\":38,\"checked\":true,\"indeterminate\":false},{\"value\":\"计划操作权限\",\"title\":\"计划操作权限\",\"id\":\"pxgl\",\"children\":[{\"value\":\"添加计划\",\"title\":\"添加计划\",\"id\":\"pxgl\",\"nodeKey\":40,\"checked\":true,\"indeterminate\":false},{\"value\":\"添加人员\",\"title\":\"添加人员\",\"id\":\"pxgl\",\"nodeKey\":41,\"checked\":true,\"indeterminate\":false},{\"value\":\"删除人员\",\"title\":\"删除人员\",\"id\":\"pxgl\",\"nodeKey\":42,\"checked\":true,\"indeterminate\":false},{\"value\":\"修改计划\",\"title\":\"修改计划\",\"id\":\"pxgl\",\"nodeKey\":43,\"checked\":true,\"indeterminate\":false},{\"value\":\"删除计划\",\"title\":\"删除计划\",\"id\":\"pxgl\",\"nodeKey\":44,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":39,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":37,\"checked\":true,\"indeterminate\":false},{\"value\":\"培训资源\",\"title\":\"培训资源\",\"id\":\"pxgl\",\"nodeKey\":45,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":25,\"checked\":true,\"indeterminate\":false},{\"value\":\"住宿管理\",\"title\":\"住宿管理\",\"expand\":true,\"id\":\"zsgl\",\"children\":[{\"value\":\"宿舍信息管理\",\"title\":\"宿舍信息管理\",\"id\":\"zsgl\",\"children\":[{\"value\":\"宿舍管理\",\"title\":\"宿舍管理\",\"id\":\"zsgl\",\"nodeKey\":48,\"checked\":true,\"indeterminate\":false},{\"value\":\"宿舍操作权限\",\"title\":\"宿舍操作权限\",\"id\":\"zsgl\",\"children\":[{\"value\":\"添加宿舍\",\"title\":\"添加宿舍\",\"id\":\"zsgl\",\"nodeKey\":50,\"checked\":true,\"indeterminate\":false},{\"value\":\"添加床位\",\"title\":\"添加床位\",\"id\":\"zsgl\",\"nodeKey\":51,\"checked\":true,\"indeterminate\":false},{\"value\":\"修改宿舍\",\"title\":\"修改宿舍\",\"id\":\"zsgl\",\"nodeKey\":52,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":49,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":47,\"checked\":true,\"indeterminate\":false},{\"value\":\"住宿信息列表\",\"title\":\"住宿信息列表\",\"id\":\"zsgl\",\"children\":[{\"value\":\"住宿列表\",\"title\":\"住宿列表\",\"id\":\"zsgl\",\"nodeKey\":54,\"checked\":true,\"indeterminate\":false},{\"value\":\"住宿操作权限\",\"title\":\"住宿操作权限\",\"id\":\"zsgl\",\"children\":[{\"value\":\"床位分配\",\"title\":\"床位分配\",\"id\":\"zsgl\",\"nodeKey\":56,\"checked\":true,\"indeterminate\":false},{\"value\":\"编辑住宿\",\"title\":\"编辑住宿\",\"id\":\"zsgl\",\"nodeKey\":57,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":55,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":53,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":46,\"checked\":true,\"indeterminate\":false},{\"value\":\"薪资管理\",\"expand\":true,\"title\":\"薪资管理\",\"id\":\"薪资管理\",\"children\":[{\"value\":\"薪资表\",\"title\":\"薪资表\",\"id\":\"薪资管理\",\"nodeKey\":59,\"checked\":true,\"indeterminate\":false},{\"value\":\"生成薪资表\",\"title\":\"生成薪资表\",\"id\":\"薪资管理\",\"nodeKey\":60,\"checked\":true,\"indeterminate\":false},{\"value\":\"薪资审核\",\"title\":\"薪资审核\",\"id\":\"薪资管理\",\"nodeKey\":61,\"checked\":true,\"indeterminate\":false},{\"value\":\"福利\",\"title\":\"福利\",\"id\":\"薪资管理\",\"nodeKey\":62,\"checked\":true,\"indeterminate\":false},{\"value\":\"账套管理\",\"title\":\"账套管理\",\"id\":\"薪资管理\",\"nodeKey\":63,\"checked\":true,\"indeterminate\":false},{\"value\":\"工资项目\",\"title\":\"工资项目\",\"id\":\"薪资管理\",\"nodeKey\":64,\"checked\":true,\"indeterminate\":false},{\"value\":\"税金设置\",\"title\":\"税金设置\",\"id\":\"薪资管理\",\"nodeKey\":65,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":58,\"checked\":true,\"indeterminate\":false},{\"value\":\"报表统计\",\"expand\":true,\"title\":\"报表统计\",\"id\":\"报表统计\",\"children\":[{\"value\":\"人员清单\",\"title\":\"人员清单\",\"id\":\"报表统计\",\"nodeKey\":67,\"checked\":true,\"indeterminate\":false},{\"value\":\"薪资明细\",\"title\":\"薪资明细\",\"id\":\"报表统计\",\"nodeKey\":68,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":66,\"checked\":true,\"indeterminate\":false},{\"value\":\"系统设置\",\"expand\":true,\"title\":\"系统设置\",\"id\":\"系统设置\",\"children\":[{\"value\":\"角色权限设置\",\"title\":\"角色权限设置\",\"id\":\"系统设置\",\"nodeKey\":70,\"checked\":true,\"indeterminate\":false},{\"value\":\"管理员管理\",\"title\":\"管理员管理\",\"id\":\"系统设置\",\"nodeKey\":71,\"checked\":true,\"indeterminate\":false},{\"value\":\"机构管理\",\"title\":\"机构管理\",\"id\":\"系统设置\",\"nodeKey\":72,\"checked\":true,\"indeterminate\":false},{\"value\":\"基本数据管理\",\"title\":\"基本数据管理\",\"id\":\"系统设置\",\"nodeKey\":73,\"checked\":true,\"indeterminate\":false},{\"value\":\"系统日志\",\"title\":\"系统日志\",\"id\":\"系统设置\",\"nodeKey\":74,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":69,\"checked\":true,\"indeterminate\":false},[\"人事档案管理\",\"人员信息\",\"人员信息管理\",\"人员操作权限\",\"人员添加\",\"人员编辑\",\"人员调动\",\"修改调动\",\"人员导入\",\"添加五险\",\"修改五险\",\"合同信息管理\",\"合同管理\",\"合同操作权限\",\"添加合同\",\"修改合同\",\"删除合同\",\"退休人员管理\",\"退休管理\",\"退休操作权限\",\"退休办理\",\"离职信息管理\",\"离职管理\",\"离职操作权限\",\"离职办理\",\"培训管理\",\"培训档案管理\",\"培训档案\",\"培训操作权限\",\"创建培训档案\",\"添加评估\",\"培训课程管理\",\"课程管理\",\"课程操作权限\",\"添加课程\",\"修改课程\",\"删除课程\",\"培训计划设计\",\"培训计划\",\"计划操作权限\",\"添加计划\",\"添加人员\",\"删除人员\",\"修改计划\",\"删除计划\",\"培训资源\",\"住宿管理\",\"宿舍信息管理\",\"宿舍管理\",\"宿舍操作权限\",\"添加宿舍\",\"添加床位\",\"修改宿舍\",\"住宿信息列表\",\"住宿列表\",\"住宿操作权限\",\"床位分配\",\"编辑住宿\",\"薪资管理\",\"薪资表\",\"生成薪资表\",\"薪资审核\",\"福利\",\"账套管理\",\"工资项目\",\"税金设置\",\"报表统计\",\"人员清单\",\"薪资明细\",\"系统设置\",\"角色权限设置\",\"管理员管理\",\"机构管理\",\"基本数据管理\",\"系统日志\"]]","value":"undefined"}]}
     * roles : null
     * personnelInfoDomain : {"id":4228,"name":"毛捷","mobile":"13399460047","num":null,"pinYin":"MJ","age":"43","nation":"1","image":"http://qiniu-hr-landa.lz-cc.com/1560306400085.jpg","postTitle":0,"institutionId":263,"state":0,"createTime":"2019-05-21 12:27:25","idCard":"620102197603305328","institutionName":"材料采供科"}
     */

    private int id;
    private String accountName;
    private String accountPass;
    private String salt;
    private String createTime;
    private String token;
    private int type;
    private int roleId;
    private int personId;
    private int storeId;
    private String storeName;
    private RoleDomainBean roleDomain;
    private Object roles;
    private PersonnelInfoDomainBean personnelInfoDomain;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPass() {
        return accountPass;
    }

    public void setAccountPass(String accountPass) {
        this.accountPass = accountPass;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public RoleDomainBean getRoleDomain() {
        return roleDomain;
    }

    public void setRoleDomain(RoleDomainBean roleDomain) {
        this.roleDomain = roleDomain;
    }

    public Object getRoles() {
        return roles;
    }

    public void setRoles(Object roles) {
        this.roles = roles;
    }

    public PersonnelInfoDomainBean getPersonnelInfoDomain() {
        return personnelInfoDomain;
    }

    public void setPersonnelInfoDomain(PersonnelInfoDomainBean personnelInfoDomain) {
        this.personnelInfoDomain = personnelInfoDomain;
    }

    public static class RoleDomainBean {
        /**
         * id : 1
         * roleTitle : 超级管理员角色
         * remark : 超级管理员角色
         * createBy : 1
         * createTime : 2019-01-09 10:35:22
         * state : 1
         * menuDomain : [{"id":288,"roleId":null,"menu":"[{\"value\":\"人事档案管理\",\"title\":\"人事档案管理\",\"expand\":true,\"id\":\"rsgl\",\"children\":[{\"value\":\"人员信息\",\"title\":\"人员信息\",\"id\":\"rsgl\",\"children\":[{\"value\":\"人员信息管理\",\"title\":\"人员信息管理\",\"id\":\"rsgl\",\"nodeKey\":2,\"checked\":true,\"indeterminate\":false},{\"value\":\"人员操作权限\",\"title\":\"人员操作权限\",\"id\":\"rsgl\",\"children\":[{\"value\":\"人员添加\",\"title\":\"人员添加\",\"id\":\"rsgl\",\"nodeKey\":4,\"checked\":true,\"indeterminate\":false},{\"value\":\"人员编辑\",\"title\":\"人员编辑\",\"id\":\"rsgl\",\"nodeKey\":5,\"checked\":true,\"indeterminate\":false},{\"value\":\"人员调动\",\"title\":\"人员调动\",\"id\":\"rsgl\",\"nodeKey\":6,\"checked\":true,\"indeterminate\":false},{\"value\":\"修改调动\",\"title\":\"修改调动\",\"id\":\"rsgl\",\"nodeKey\":7,\"checked\":true,\"indeterminate\":false},{\"value\":\"人员导入\",\"title\":\"人员导入\",\"id\":\"rsgl\",\"nodeKey\":8,\"checked\":true,\"indeterminate\":false},{\"value\":\"添加五险\",\"title\":\"添加五险\",\"id\":\"rsgl\",\"nodeKey\":9,\"checked\":true,\"indeterminate\":false},{\"value\":\"修改五险\",\"title\":\"修改五险\",\"id\":\"rsgl\",\"nodeKey\":10,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":3,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":1,\"checked\":true,\"indeterminate\":false},{\"value\":\"合同信息管理\",\"title\":\"合同信息管理\",\"id\":\"rsgl\",\"children\":[{\"value\":\"合同管理\",\"title\":\"合同管理\",\"id\":\"rsgl\",\"nodeKey\":12,\"checked\":true,\"indeterminate\":false},{\"value\":\"合同操作权限\",\"title\":\"合同操作权限\",\"id\":\"rsgl\",\"children\":[{\"value\":\"添加合同\",\"title\":\"添加合同\",\"id\":\"rsgl\",\"nodeKey\":14,\"checked\":true,\"indeterminate\":false},{\"value\":\"修改合同\",\"title\":\"修改合同\",\"id\":\"rsgl\",\"nodeKey\":15,\"checked\":true,\"indeterminate\":false},{\"value\":\"删除合同\",\"title\":\"删除合同\",\"id\":\"rsgl\",\"nodeKey\":16,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":13,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":11,\"checked\":true,\"indeterminate\":false},{\"value\":\"退休人员管理\",\"title\":\"退休人员管理\",\"id\":\"rsgl\",\"children\":[{\"value\":\"退休管理\",\"title\":\"退休管理\",\"id\":\"rsgl\",\"nodeKey\":18,\"checked\":true,\"indeterminate\":false},{\"value\":\"退休操作权限\",\"title\":\"退休操作权限\",\"id\":\"rsgl\",\"children\":[{\"value\":\"退休办理\",\"title\":\"退休办理\",\"id\":\"rsgl\",\"nodeKey\":20,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":19,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":17,\"checked\":true,\"indeterminate\":false},{\"value\":\"离职信息管理\",\"title\":\"离职信息管理\",\"id\":\"rsgl\",\"children\":[{\"value\":\"离职管理\",\"title\":\"离职管理\",\"id\":\"rsgl\",\"nodeKey\":22,\"checked\":true,\"indeterminate\":false},{\"value\":\"离职操作权限\",\"title\":\"离职操作权限\",\"id\":\"rsgl\",\"children\":[{\"value\":\"离职办理\",\"title\":\"离职办理\",\"id\":\"rsgl\",\"nodeKey\":24,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":23,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":21,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":0,\"checked\":true,\"indeterminate\":false},{\"value\":\"培训管理\",\"title\":\"培训管理\",\"expand\":true,\"id\":\"pxgl\",\"children\":[{\"value\":\"培训档案管理\",\"title\":\"培训档案管理\",\"id\":\"pxgl\",\"children\":[{\"value\":\"培训档案\",\"title\":\"培训档案\",\"id\":\"pxgl\",\"nodeKey\":27,\"checked\":true,\"indeterminate\":false},{\"value\":\"培训操作权限\",\"title\":\"培训操作权限\",\"id\":\"pxgl\",\"children\":[{\"value\":\"创建培训档案\",\"title\":\"创建培训档案\",\"id\":\"pxgl\",\"nodeKey\":29,\"checked\":true,\"indeterminate\":false},{\"value\":\"添加评估\",\"title\":\"添加评估\",\"id\":\"pxgl\",\"nodeKey\":30,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":28,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":26,\"checked\":true,\"indeterminate\":false},{\"value\":\"培训课程管理\",\"title\":\"培训课程管理\",\"id\":\"pxgl\",\"children\":[{\"value\":\"课程管理\",\"title\":\"课程管理\",\"id\":\"pxgl\",\"nodeKey\":32,\"checked\":true,\"indeterminate\":false},{\"value\":\"课程操作权限\",\"title\":\"课程操作权限\",\"id\":\"pxgl\",\"children\":[{\"value\":\"添加课程\",\"title\":\"添加课程\",\"id\":\"pxgl\",\"nodeKey\":34,\"checked\":true,\"indeterminate\":false},{\"value\":\"修改课程\",\"title\":\"修改课程\",\"id\":\"pxgl\",\"nodeKey\":35,\"checked\":true,\"indeterminate\":false},{\"value\":\"删除课程\",\"title\":\"删除课程\",\"id\":\"pxgl\",\"nodeKey\":36,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":33,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":31,\"checked\":true,\"indeterminate\":false},{\"value\":\"培训计划设计\",\"title\":\"培训计划设计\",\"id\":\"pxgl\",\"children\":[{\"value\":\"培训计划\",\"title\":\"培训计划\",\"id\":\"pxgl\",\"nodeKey\":38,\"checked\":true,\"indeterminate\":false},{\"value\":\"计划操作权限\",\"title\":\"计划操作权限\",\"id\":\"pxgl\",\"children\":[{\"value\":\"添加计划\",\"title\":\"添加计划\",\"id\":\"pxgl\",\"nodeKey\":40,\"checked\":true,\"indeterminate\":false},{\"value\":\"添加人员\",\"title\":\"添加人员\",\"id\":\"pxgl\",\"nodeKey\":41,\"checked\":true,\"indeterminate\":false},{\"value\":\"删除人员\",\"title\":\"删除人员\",\"id\":\"pxgl\",\"nodeKey\":42,\"checked\":true,\"indeterminate\":false},{\"value\":\"修改计划\",\"title\":\"修改计划\",\"id\":\"pxgl\",\"nodeKey\":43,\"checked\":true,\"indeterminate\":false},{\"value\":\"删除计划\",\"title\":\"删除计划\",\"id\":\"pxgl\",\"nodeKey\":44,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":39,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":37,\"checked\":true,\"indeterminate\":false},{\"value\":\"培训资源\",\"title\":\"培训资源\",\"id\":\"pxgl\",\"nodeKey\":45,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":25,\"checked\":true,\"indeterminate\":false},{\"value\":\"住宿管理\",\"title\":\"住宿管理\",\"expand\":true,\"id\":\"zsgl\",\"children\":[{\"value\":\"宿舍信息管理\",\"title\":\"宿舍信息管理\",\"id\":\"zsgl\",\"children\":[{\"value\":\"宿舍管理\",\"title\":\"宿舍管理\",\"id\":\"zsgl\",\"nodeKey\":48,\"checked\":true,\"indeterminate\":false},{\"value\":\"宿舍操作权限\",\"title\":\"宿舍操作权限\",\"id\":\"zsgl\",\"children\":[{\"value\":\"添加宿舍\",\"title\":\"添加宿舍\",\"id\":\"zsgl\",\"nodeKey\":50,\"checked\":true,\"indeterminate\":false},{\"value\":\"添加床位\",\"title\":\"添加床位\",\"id\":\"zsgl\",\"nodeKey\":51,\"checked\":true,\"indeterminate\":false},{\"value\":\"修改宿舍\",\"title\":\"修改宿舍\",\"id\":\"zsgl\",\"nodeKey\":52,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":49,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":47,\"checked\":true,\"indeterminate\":false},{\"value\":\"住宿信息列表\",\"title\":\"住宿信息列表\",\"id\":\"zsgl\",\"children\":[{\"value\":\"住宿列表\",\"title\":\"住宿列表\",\"id\":\"zsgl\",\"nodeKey\":54,\"checked\":true,\"indeterminate\":false},{\"value\":\"住宿操作权限\",\"title\":\"住宿操作权限\",\"id\":\"zsgl\",\"children\":[{\"value\":\"床位分配\",\"title\":\"床位分配\",\"id\":\"zsgl\",\"nodeKey\":56,\"checked\":true,\"indeterminate\":false},{\"value\":\"编辑住宿\",\"title\":\"编辑住宿\",\"id\":\"zsgl\",\"nodeKey\":57,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":55,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":53,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":46,\"checked\":true,\"indeterminate\":false},{\"value\":\"薪资管理\",\"expand\":true,\"title\":\"薪资管理\",\"id\":\"薪资管理\",\"children\":[{\"value\":\"薪资表\",\"title\":\"薪资表\",\"id\":\"薪资管理\",\"nodeKey\":59,\"checked\":true,\"indeterminate\":false},{\"value\":\"生成薪资表\",\"title\":\"生成薪资表\",\"id\":\"薪资管理\",\"nodeKey\":60,\"checked\":true,\"indeterminate\":false},{\"value\":\"薪资审核\",\"title\":\"薪资审核\",\"id\":\"薪资管理\",\"nodeKey\":61,\"checked\":true,\"indeterminate\":false},{\"value\":\"福利\",\"title\":\"福利\",\"id\":\"薪资管理\",\"nodeKey\":62,\"checked\":true,\"indeterminate\":false},{\"value\":\"账套管理\",\"title\":\"账套管理\",\"id\":\"薪资管理\",\"nodeKey\":63,\"checked\":true,\"indeterminate\":false},{\"value\":\"工资项目\",\"title\":\"工资项目\",\"id\":\"薪资管理\",\"nodeKey\":64,\"checked\":true,\"indeterminate\":false},{\"value\":\"税金设置\",\"title\":\"税金设置\",\"id\":\"薪资管理\",\"nodeKey\":65,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":58,\"checked\":true,\"indeterminate\":false},{\"value\":\"报表统计\",\"expand\":true,\"title\":\"报表统计\",\"id\":\"报表统计\",\"children\":[{\"value\":\"人员清单\",\"title\":\"人员清单\",\"id\":\"报表统计\",\"nodeKey\":67,\"checked\":true,\"indeterminate\":false},{\"value\":\"薪资明细\",\"title\":\"薪资明细\",\"id\":\"报表统计\",\"nodeKey\":68,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":66,\"checked\":true,\"indeterminate\":false},{\"value\":\"系统设置\",\"expand\":true,\"title\":\"系统设置\",\"id\":\"系统设置\",\"children\":[{\"value\":\"角色权限设置\",\"title\":\"角色权限设置\",\"id\":\"系统设置\",\"nodeKey\":70,\"checked\":true,\"indeterminate\":false},{\"value\":\"管理员管理\",\"title\":\"管理员管理\",\"id\":\"系统设置\",\"nodeKey\":71,\"checked\":true,\"indeterminate\":false},{\"value\":\"机构管理\",\"title\":\"机构管理\",\"id\":\"系统设置\",\"nodeKey\":72,\"checked\":true,\"indeterminate\":false},{\"value\":\"基本数据管理\",\"title\":\"基本数据管理\",\"id\":\"系统设置\",\"nodeKey\":73,\"checked\":true,\"indeterminate\":false},{\"value\":\"系统日志\",\"title\":\"系统日志\",\"id\":\"系统设置\",\"nodeKey\":74,\"checked\":true,\"indeterminate\":false}],\"nodeKey\":69,\"checked\":true,\"indeterminate\":false},[\"人事档案管理\",\"人员信息\",\"人员信息管理\",\"人员操作权限\",\"人员添加\",\"人员编辑\",\"人员调动\",\"修改调动\",\"人员导入\",\"添加五险\",\"修改五险\",\"合同信息管理\",\"合同管理\",\"合同操作权限\",\"添加合同\",\"修改合同\",\"删除合同\",\"退休人员管理\",\"退休管理\",\"退休操作权限\",\"退休办理\",\"离职信息管理\",\"离职管理\",\"离职操作权限\",\"离职办理\",\"培训管理\",\"培训档案管理\",\"培训档案\",\"培训操作权限\",\"创建培训档案\",\"添加评估\",\"培训课程管理\",\"课程管理\",\"课程操作权限\",\"添加课程\",\"修改课程\",\"删除课程\",\"培训计划设计\",\"培训计划\",\"计划操作权限\",\"添加计划\",\"添加人员\",\"删除人员\",\"修改计划\",\"删除计划\",\"培训资源\",\"住宿管理\",\"宿舍信息管理\",\"宿舍管理\",\"宿舍操作权限\",\"添加宿舍\",\"添加床位\",\"修改宿舍\",\"住宿信息列表\",\"住宿列表\",\"住宿操作权限\",\"床位分配\",\"编辑住宿\",\"薪资管理\",\"薪资表\",\"生成薪资表\",\"薪资审核\",\"福利\",\"账套管理\",\"工资项目\",\"税金设置\",\"报表统计\",\"人员清单\",\"薪资明细\",\"系统设置\",\"角色权限设置\",\"管理员管理\",\"机构管理\",\"基本数据管理\",\"系统日志\"]]","value":"undefined"}]
         */

        private int id;
        private String roleTitle;
        private String remark;
        private int createBy;
        private String createTime;
        private int state;
        private List<MenuDomainBean> menuDomain;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRoleTitle() {
            return roleTitle;
        }

        public void setRoleTitle(String roleTitle) {
            this.roleTitle = roleTitle;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getCreateBy() {
            return createBy;
        }

        public void setCreateBy(int createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public List<MenuDomainBean> getMenuDomain() {
            return menuDomain;
        }

        public void setMenuDomain(List<MenuDomainBean> menuDomain) {
            this.menuDomain = menuDomain;
        }

        public static class MenuDomainBean {
            /**
             * id : 288
             * roleId : null
             * menu : [{"value":"人事档案管理","title":"人事档案管理","expand":true,"id":"rsgl","children":[{"value":"人员信息","title":"人员信息","id":"rsgl","children":[{"value":"人员信息管理","title":"人员信息管理","id":"rsgl","nodeKey":2,"checked":true,"indeterminate":false},{"value":"人员操作权限","title":"人员操作权限","id":"rsgl","children":[{"value":"人员添加","title":"人员添加","id":"rsgl","nodeKey":4,"checked":true,"indeterminate":false},{"value":"人员编辑","title":"人员编辑","id":"rsgl","nodeKey":5,"checked":true,"indeterminate":false},{"value":"人员调动","title":"人员调动","id":"rsgl","nodeKey":6,"checked":true,"indeterminate":false},{"value":"修改调动","title":"修改调动","id":"rsgl","nodeKey":7,"checked":true,"indeterminate":false},{"value":"人员导入","title":"人员导入","id":"rsgl","nodeKey":8,"checked":true,"indeterminate":false},{"value":"添加五险","title":"添加五险","id":"rsgl","nodeKey":9,"checked":true,"indeterminate":false},{"value":"修改五险","title":"修改五险","id":"rsgl","nodeKey":10,"checked":true,"indeterminate":false}],"nodeKey":3,"checked":true,"indeterminate":false}],"nodeKey":1,"checked":true,"indeterminate":false},{"value":"合同信息管理","title":"合同信息管理","id":"rsgl","children":[{"value":"合同管理","title":"合同管理","id":"rsgl","nodeKey":12,"checked":true,"indeterminate":false},{"value":"合同操作权限","title":"合同操作权限","id":"rsgl","children":[{"value":"添加合同","title":"添加合同","id":"rsgl","nodeKey":14,"checked":true,"indeterminate":false},{"value":"修改合同","title":"修改合同","id":"rsgl","nodeKey":15,"checked":true,"indeterminate":false},{"value":"删除合同","title":"删除合同","id":"rsgl","nodeKey":16,"checked":true,"indeterminate":false}],"nodeKey":13,"checked":true,"indeterminate":false}],"nodeKey":11,"checked":true,"indeterminate":false},{"value":"退休人员管理","title":"退休人员管理","id":"rsgl","children":[{"value":"退休管理","title":"退休管理","id":"rsgl","nodeKey":18,"checked":true,"indeterminate":false},{"value":"退休操作权限","title":"退休操作权限","id":"rsgl","children":[{"value":"退休办理","title":"退休办理","id":"rsgl","nodeKey":20,"checked":true,"indeterminate":false}],"nodeKey":19,"checked":true,"indeterminate":false}],"nodeKey":17,"checked":true,"indeterminate":false},{"value":"离职信息管理","title":"离职信息管理","id":"rsgl","children":[{"value":"离职管理","title":"离职管理","id":"rsgl","nodeKey":22,"checked":true,"indeterminate":false},{"value":"离职操作权限","title":"离职操作权限","id":"rsgl","children":[{"value":"离职办理","title":"离职办理","id":"rsgl","nodeKey":24,"checked":true,"indeterminate":false}],"nodeKey":23,"checked":true,"indeterminate":false}],"nodeKey":21,"checked":true,"indeterminate":false}],"nodeKey":0,"checked":true,"indeterminate":false},{"value":"培训管理","title":"培训管理","expand":true,"id":"pxgl","children":[{"value":"培训档案管理","title":"培训档案管理","id":"pxgl","children":[{"value":"培训档案","title":"培训档案","id":"pxgl","nodeKey":27,"checked":true,"indeterminate":false},{"value":"培训操作权限","title":"培训操作权限","id":"pxgl","children":[{"value":"创建培训档案","title":"创建培训档案","id":"pxgl","nodeKey":29,"checked":true,"indeterminate":false},{"value":"添加评估","title":"添加评估","id":"pxgl","nodeKey":30,"checked":true,"indeterminate":false}],"nodeKey":28,"checked":true,"indeterminate":false}],"nodeKey":26,"checked":true,"indeterminate":false},{"value":"培训课程管理","title":"培训课程管理","id":"pxgl","children":[{"value":"课程管理","title":"课程管理","id":"pxgl","nodeKey":32,"checked":true,"indeterminate":false},{"value":"课程操作权限","title":"课程操作权限","id":"pxgl","children":[{"value":"添加课程","title":"添加课程","id":"pxgl","nodeKey":34,"checked":true,"indeterminate":false},{"value":"修改课程","title":"修改课程","id":"pxgl","nodeKey":35,"checked":true,"indeterminate":false},{"value":"删除课程","title":"删除课程","id":"pxgl","nodeKey":36,"checked":true,"indeterminate":false}],"nodeKey":33,"checked":true,"indeterminate":false}],"nodeKey":31,"checked":true,"indeterminate":false},{"value":"培训计划设计","title":"培训计划设计","id":"pxgl","children":[{"value":"培训计划","title":"培训计划","id":"pxgl","nodeKey":38,"checked":true,"indeterminate":false},{"value":"计划操作权限","title":"计划操作权限","id":"pxgl","children":[{"value":"添加计划","title":"添加计划","id":"pxgl","nodeKey":40,"checked":true,"indeterminate":false},{"value":"添加人员","title":"添加人员","id":"pxgl","nodeKey":41,"checked":true,"indeterminate":false},{"value":"删除人员","title":"删除人员","id":"pxgl","nodeKey":42,"checked":true,"indeterminate":false},{"value":"修改计划","title":"修改计划","id":"pxgl","nodeKey":43,"checked":true,"indeterminate":false},{"value":"删除计划","title":"删除计划","id":"pxgl","nodeKey":44,"checked":true,"indeterminate":false}],"nodeKey":39,"checked":true,"indeterminate":false}],"nodeKey":37,"checked":true,"indeterminate":false},{"value":"培训资源","title":"培训资源","id":"pxgl","nodeKey":45,"checked":true,"indeterminate":false}],"nodeKey":25,"checked":true,"indeterminate":false},{"value":"住宿管理","title":"住宿管理","expand":true,"id":"zsgl","children":[{"value":"宿舍信息管理","title":"宿舍信息管理","id":"zsgl","children":[{"value":"宿舍管理","title":"宿舍管理","id":"zsgl","nodeKey":48,"checked":true,"indeterminate":false},{"value":"宿舍操作权限","title":"宿舍操作权限","id":"zsgl","children":[{"value":"添加宿舍","title":"添加宿舍","id":"zsgl","nodeKey":50,"checked":true,"indeterminate":false},{"value":"添加床位","title":"添加床位","id":"zsgl","nodeKey":51,"checked":true,"indeterminate":false},{"value":"修改宿舍","title":"修改宿舍","id":"zsgl","nodeKey":52,"checked":true,"indeterminate":false}],"nodeKey":49,"checked":true,"indeterminate":false}],"nodeKey":47,"checked":true,"indeterminate":false},{"value":"住宿信息列表","title":"住宿信息列表","id":"zsgl","children":[{"value":"住宿列表","title":"住宿列表","id":"zsgl","nodeKey":54,"checked":true,"indeterminate":false},{"value":"住宿操作权限","title":"住宿操作权限","id":"zsgl","children":[{"value":"床位分配","title":"床位分配","id":"zsgl","nodeKey":56,"checked":true,"indeterminate":false},{"value":"编辑住宿","title":"编辑住宿","id":"zsgl","nodeKey":57,"checked":true,"indeterminate":false}],"nodeKey":55,"checked":true,"indeterminate":false}],"nodeKey":53,"checked":true,"indeterminate":false}],"nodeKey":46,"checked":true,"indeterminate":false},{"value":"薪资管理","expand":true,"title":"薪资管理","id":"薪资管理","children":[{"value":"薪资表","title":"薪资表","id":"薪资管理","nodeKey":59,"checked":true,"indeterminate":false},{"value":"生成薪资表","title":"生成薪资表","id":"薪资管理","nodeKey":60,"checked":true,"indeterminate":false},{"value":"薪资审核","title":"薪资审核","id":"薪资管理","nodeKey":61,"checked":true,"indeterminate":false},{"value":"福利","title":"福利","id":"薪资管理","nodeKey":62,"checked":true,"indeterminate":false},{"value":"账套管理","title":"账套管理","id":"薪资管理","nodeKey":63,"checked":true,"indeterminate":false},{"value":"工资项目","title":"工资项目","id":"薪资管理","nodeKey":64,"checked":true,"indeterminate":false},{"value":"税金设置","title":"税金设置","id":"薪资管理","nodeKey":65,"checked":true,"indeterminate":false}],"nodeKey":58,"checked":true,"indeterminate":false},{"value":"报表统计","expand":true,"title":"报表统计","id":"报表统计","children":[{"value":"人员清单","title":"人员清单","id":"报表统计","nodeKey":67,"checked":true,"indeterminate":false},{"value":"薪资明细","title":"薪资明细","id":"报表统计","nodeKey":68,"checked":true,"indeterminate":false}],"nodeKey":66,"checked":true,"indeterminate":false},{"value":"系统设置","expand":true,"title":"系统设置","id":"系统设置","children":[{"value":"角色权限设置","title":"角色权限设置","id":"系统设置","nodeKey":70,"checked":true,"indeterminate":false},{"value":"管理员管理","title":"管理员管理","id":"系统设置","nodeKey":71,"checked":true,"indeterminate":false},{"value":"机构管理","title":"机构管理","id":"系统设置","nodeKey":72,"checked":true,"indeterminate":false},{"value":"基本数据管理","title":"基本数据管理","id":"系统设置","nodeKey":73,"checked":true,"indeterminate":false},{"value":"系统日志","title":"系统日志","id":"系统设置","nodeKey":74,"checked":true,"indeterminate":false}],"nodeKey":69,"checked":true,"indeterminate":false},["人事档案管理","人员信息","人员信息管理","人员操作权限","人员添加","人员编辑","人员调动","修改调动","人员导入","添加五险","修改五险","合同信息管理","合同管理","合同操作权限","添加合同","修改合同","删除合同","退休人员管理","退休管理","退休操作权限","退休办理","离职信息管理","离职管理","离职操作权限","离职办理","培训管理","培训档案管理","培训档案","培训操作权限","创建培训档案","添加评估","培训课程管理","课程管理","课程操作权限","添加课程","修改课程","删除课程","培训计划设计","培训计划","计划操作权限","添加计划","添加人员","删除人员","修改计划","删除计划","培训资源","住宿管理","宿舍信息管理","宿舍管理","宿舍操作权限","添加宿舍","添加床位","修改宿舍","住宿信息列表","住宿列表","住宿操作权限","床位分配","编辑住宿","薪资管理","薪资表","生成薪资表","薪资审核","福利","账套管理","工资项目","税金设置","报表统计","人员清单","薪资明细","系统设置","角色权限设置","管理员管理","机构管理","基本数据管理","系统日志"]]
             * value : undefined
             */

            private int id;
            private Object roleId;
            private String menu;
            private String value;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getRoleId() {
                return roleId;
            }

            public void setRoleId(Object roleId) {
                this.roleId = roleId;
            }

            public String getMenu() {
                return menu;
            }

            public void setMenu(String menu) {
                this.menu = menu;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }

    public static class PersonnelInfoDomainBean {
        /**
         * id : 4228
         * name : 毛捷
         * mobile : 13399460047
         * num : null
         * pinYin : MJ
         * age : 43
         * nation : 1
         * image : http://qiniu-hr-landa.lz-cc.com/1560306400085.jpg
         * postTitle : 0
         * institutionId : 263
         * state : 0
         * createTime : 2019-05-21 12:27:25
         * idCard : 620102197603305328
         * institutionName : 材料采供科
         */

        private int id;
        private String name;
        private String mobile;
        private Object num;
        private String pinYin;
        private String age;
        private String nation;
        private String image;
        private int postTitle;
        private int institutionId;
        private int state;
        private String createTime;
        private String idCard;
        private String institutionName;

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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getNum() {
            return num;
        }

        public void setNum(Object num) {
            this.num = num;
        }

        public String getPinYin() {
            return pinYin;
        }

        public void setPinYin(String pinYin) {
            this.pinYin = pinYin;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getPostTitle() {
            return postTitle;
        }

        public void setPostTitle(int postTitle) {
            this.postTitle = postTitle;
        }

        public int getInstitutionId() {
            return institutionId;
        }

        public void setInstitutionId(int institutionId) {
            this.institutionId = institutionId;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getInstitutionName() {
            return institutionName;
        }

        public void setInstitutionName(String institutionName) {
            this.institutionName = institutionName;
        }
    }
}
