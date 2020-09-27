package com.leewg.mvvm.app;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginBean {

    /**
     * loginTime : 2019-08-17 14:51:33
     * status : 2
     * token : eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJ7XCJpZFwiOjEyNyxcImluc3RpdHV0aW9uYWxMZXZlbERlc2NcIjpcIuS4reW_g1wiLFwiaW5zdGl0dXRpb25hbExldmVsSWRcIjozLFwibmFtZVwiOlwi5YiY5b-X6bmPXCIsXCJuZXR3b3JrQ29kZVwiOlwiU0gwMDFcIixcIm5ldHdvcmtJZFwiOjE5LFwibmV0d29ya05hbWVcIjpcIuS4iua1t-WFrOWPuFwiLFwic3RhZmZOb1wiOlwibGl1emhpcGVuZ1wiLFwidXNlclR5cGVcIjoyLFwidXVpZFwiOlwiZDRlNzllNzhlNmIyNGE3Nzk0NjA4NTYxZTJkOWZjMzlcIn0iLCJpYXQiOjE1NjYwMjQ2OTN9.rPKKTPdHg5CmN8gV1VGPMUeG25F7SvnNFlm6SgMp-pA
     * id : 127
     * name : 刘志鹏
     * sysResourceTree : [{"id":567,"cnName":"取件任务","enName":"","name":"取件任务","routeName":"","path":"","component":"","menuUrl":""},{"id":568,"cnName":"派件任务","enName":"","name":"派件任务","routeName":"","path":"","component":"","menuUrl":""},{"id":569,"cnName":"新建运单","enName":"","name":"新建运单","routeName":"","path":"","component":"","menuUrl":""},{"id":570,"cnName":"签收扫描","enName":"","name":"签收扫描","routeName":"","path":"","component":"","menuUrl":""},{"id":571,"cnName":"退件签收","enName":"","name":"退件签收","routeName":"","path":"","component":"","menuUrl":""},{"id":572,"cnName":"收件扫描","enName":"","name":"收件扫描","routeName":"","path":"","component":"","menuUrl":""},{"id":573,"cnName":"入库扫描","enName":"","name":"入库扫描","routeName":"","path":"","component":"","menuUrl":""},{"id":574,"cnName":"到件扫描","enName":"","name":"到件扫描","routeName":"","path":"","component":"","menuUrl":""},{"id":575,"cnName":"装包扫描","enName":"","name":"装包扫描","routeName":"","path":"","component":"","menuUrl":""},{"id":576,"cnName":"装车扫描","enName":"","name":"装车扫描","routeName":"","path":"","component":"","menuUrl":""},{"id":577,"cnName":"发件扫描","enName":"","name":"发件扫描","routeName":"","path":"","component":"","menuUrl":""},{"id":578,"cnName":"卸车扫描","enName":"","name":"卸车扫描","routeName":"","path":"","component":"","menuUrl":""},{"id":579,"cnName":"拆包扫描","enName":"","name":"拆包扫描","routeName":"","path":"","component":"","menuUrl":""}]
     * staffNo : liuzhipeng
     * networkId : 19
     * userType : 2
     * networkName : 上海公司
     * institutionalLevelId : 3
     * institutionalLevelDesc : 中心
     * uuid : d4e79e78e6b24a7794608561e2d9fc39
     * networkCode : SH001
     * financialCenterId : 12
     * financialCenterDesc : 总部
     * financialCenterCode : ZB001
     * isFinancialCenter : 1
     * enName : liuzhipeng
     * cnName : 刘志鹏
     * sex : 1
     * deptId : 1
     * deptName : 测试部门
     * postId : 1
     * postName : 分拣
     * mobile : null
     * email :
     * phone : null
     */

    private String loginTime;
    private int status;
    private String token;
    private int id;
    private String name;
    private String staffNo;
    private int networkId;
    private int userType;
    private String networkName;
    private int institutionalLevelId;
    private String institutionalLevelDesc;
    private String uuid;
    private String networkCode;
    private String financialCenterId;
    private String financialCenterDesc;
    private String financialCenterCode;
    private int isFinancialCenter;
    private String enName;
    private String cnName;
    private int sex;
    private int deptId;
    private String deptName;
    private int postId;
    private String postName;
    private String mobile;
    private String email;
    private String phone;
    private List<SysResourceTreeBean> sysResourceTree;
    private List<RolesBean> roles;
    private String loginNetworkMobile;
    private String loginNetworkTypeDesc;
    private String loginNetworkTypeId;
    private String cityDesc;
    private String providerDesc;
    public int isEnable;
    public int exceedWarnAmount;
    public int exceedClosedAmount;
    private AlertAmount bcIbkStaffVO;//警戒金额实体

    public AlertAmount getBcIbkStaffVO() {
        return bcIbkStaffVO;
    }

    public void setBcIbkStaffVO(AlertAmount bcIbkStaffVO) {
        this.bcIbkStaffVO = bcIbkStaffVO;
    }

    /**
     * 是否有扫码付权限
     */
    private boolean isAllowPay;

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public int getNetworkId() {
        return networkId;
    }

    public void setNetworkId(int networkId) {
        this.networkId = networkId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public int getInstitutionalLevelId() {
        return institutionalLevelId;
    }

    public void setInstitutionalLevelId(int institutionalLevelId) {
        this.institutionalLevelId = institutionalLevelId;
    }

    public String getInstitutionalLevelDesc() {
        return institutionalLevelDesc;
    }

    public void setInstitutionalLevelDesc(String institutionalLevelDesc) {
        this.institutionalLevelDesc = institutionalLevelDesc;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNetworkCode() {
        return networkCode;
    }

    public void setNetworkCode(String networkCode) {
        this.networkCode = networkCode;
    }

    public String getFinancialCenterId() {
        return financialCenterId;
    }

    public void setFinancialCenterId(String financialCenterId) {
        this.financialCenterId = financialCenterId;
    }

    public String getFinancialCenterDesc() {
        return financialCenterDesc;
    }

    public void setFinancialCenterDesc(String financialCenterDesc) {
        this.financialCenterDesc = financialCenterDesc;
    }

    public String getFinancialCenterCode() {
        return financialCenterCode;
    }

    public void setFinancialCenterCode(String financialCenterCode) {
        this.financialCenterCode = financialCenterCode;
    }

    public int getIsFinancialCenter() {
        return isFinancialCenter;
    }

    public void setIsFinancialCenter(int isFinancialCenter) {
        this.isFinancialCenter = isFinancialCenter;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<SysResourceTreeBean> getSysResourceTree() {
        return sysResourceTree;
    }

    public List<RolesBean> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesBean> roles) {
        this.roles = roles;
    }

    public String getNetworkContact() {
        return loginNetworkMobile;
    }

    public void setNetworkContact(String networkContact) {
        this.loginNetworkMobile = networkContact;
    }

    public String getNetworkTypeName() {
        return loginNetworkTypeDesc;
    }

    public void setNetworkTypeName(String networkTypeName) {
        this.loginNetworkTypeDesc = networkTypeName;
    }

    public String getNetworkTypeId() {
        return loginNetworkTypeId;
    }

    public void setNetworkTypeId(String networkTypeId) {
        this.loginNetworkTypeId = networkTypeId;
    }

    public String getNetworkCity() {
        return cityDesc;
    }

    public void setNetworkCity(String networkCity) {
        this.cityDesc = networkCity;
    }

    public String getNetworkProvince() {
        return providerDesc;
    }

    public void setNetworkProvince(String networkProvince) {
        this.providerDesc = networkProvince;
    }

    public void setAllowPay(boolean allowPay) {
        isAllowPay = allowPay;
    }

    public boolean isAllowPay() {
        return isAllowPay;
    }

    public static class SysResourceTreeBean {
        /**
         * id : 567
         * cnName : 取件任务
         * enName :
         * name : 取件任务
         * routeName :
         * path :
         * component :
         * menuUrl :
         */

        private int id;
        private String cnName;
        private String enName;
        private String name;
        private String routeName;
        private String path;
        private String component;
        private String menuUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCnName() {
            return cnName;
        }

        public void setCnName(String cnName) {
            this.cnName = cnName;
        }

        public String getEnName() {
            return enName;
        }

        public void setEnName(String enName) {
            this.enName = enName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRouteName() {
            return routeName;
        }

        public void setRouteName(String routeName) {
            this.routeName = routeName;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getComponent() {
            return component;
        }

        public void setComponent(String component) {
            this.component = component;
        }

        public String getMenuUrl() {
            return menuUrl;
        }

        public void setMenuUrl(String menuUrl) {
            this.menuUrl = menuUrl;
        }
    }

    public static class RolesBean {
        static final String ROLE_CODE_DISPATCH = "app0002";
        static final String ROLE_CODE_NetworkOperator = "app0001";
        static final String ROLE_CODE_PROXY = "app0004";
        /**
         * id : 360
         * roleCode : app0004
         * name : 数据分析员
         * createByName : 测试001/yangzhilong001
         * createTime : 2019-12-19 15:23:56
         */

        @SerializedName("id")
        private int idX;
        private String roleCode;
        @SerializedName("name")
        private String nameX;
        private String createByName;
        private String createTime;

        public int getIdX() {
            return idX;
        }

        public void setIdX(int idX) {
            this.idX = idX;
        }

        public String getRoleCode() {
            return roleCode;
        }

        public void setRoleCode(String roleCode) {
            this.roleCode = roleCode;
        }

        public String getNameX() {
            return nameX;
        }

        public void setNameX(String nameX) {
            this.nameX = nameX;
        }

        public String getCreateByName() {
            return createByName;
        }

        public void setCreateByName(String createByName) {
            this.createByName = createByName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public boolean isDispatch() {
            return ROLE_CODE_DISPATCH.equals(roleCode);
        }

        public boolean isProxy() {
            return ROLE_CODE_PROXY.equals(roleCode);
        }

        public boolean isNetworkOperator() {
            return ROLE_CODE_NetworkOperator.equals(roleCode);
        }

    }

    /**
     * 警戒金额
     */
    public static class AlertAmount {
        public String isEnable;     //1开启 0非开启
        public String amount;       //金额
        public String closeAmount;  //关闭金额
        public String warnAmount;   //警戒金额

        public String getIsEnable() {
            return isEnable;
        }

        public void setIsEnable(String isEnable) {
            this.isEnable = isEnable;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getCloseAmount() {
            return closeAmount;
        }

        public void setCloseAmount(String closeAmount) {
            this.closeAmount = closeAmount;
        }

        public String getWarnAmount() {
            return warnAmount;
        }

        public void setWarnAmount(String warnAmount) {
            this.warnAmount = warnAmount;
        }
    }

}
