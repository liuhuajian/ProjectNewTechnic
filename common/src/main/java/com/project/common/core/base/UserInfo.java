package com.project.common.core.base;


import com.project.common.core.utils.MyLogger;

import java.io.Serializable;

/**
 * 项目：健康商城
 *
 * @Creator:曾招林julyzeng
 * @创建日期： 2018/6/21 13:59
 * @版本1.0
 * @类说明：
 */

public class UserInfo implements Serializable {


    /**
     * 账号唯一编号
     */
    private String accountNo;

    /**
     * token
     */
    private String token;
    /**
     * 姓名
     */
    private String name;
    /**
     * 系统昵称
     */
    private String nickName;
    /**
     * 系统头像
     */
    private String headImg;
    /**
     * 身高
     */
    private String height;
    /**
     * 性别,1男；2女;3未知
     */
    private String sex;
    /**
     * 手机号
     */
    private String phoneNo;
    /**
     * 手机号是否认证0未认证；1认证
     */
    private String phoneIsAuth;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 电子邮箱是否认证0未认证；1认证
     */
    private String emailIsAuth;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 登录密码是否设置(0未设置1已设置)
     */
    private String pwIsSet;
    /**
     * 支付密码
     */
    private String payPassword;
    /**
     * 证件号
     */
    private String idNo;
    /**
     * 证件类型0-身份证;1-护照;2-军人证;3-户口本;4-出生证;5-其它
     */
    private String idType;
    /**
     * 生日1999-01-01
     */
    private String birthday;
    /**
     * 年龄
     */
    private int age;

    private String weight;
    /**
     * 会员类型  分销员级别（0-VIP会员，1-店主，2-销售经理，3-服务经理）
     */
    private String vipType;
    /**
     * 帐号状态0初始状态；1待审核；2会员；3未通过；4黑名单；5锁定
     */
    private String accountState;
    /**
     * 注册第三方编码（注册来源01微信，02QQ，03支付宝，04APP，05电商PC...）
     */
    private String thirdCode;
    /**
     * 注册方式（0自己注册，1商户添加）
     */
    private String registerType;
    /**
     * 实名认证级别0-未认证；1-普通认证；2-高级认证
     */
    private String authLevel;
    /**
     * 职业类别
     */
    private String occupationType;

    public String getOccupationId() {
        return occupationId;
    }

    public void setOccupationId(String occupationId) {
        this.occupationId = occupationId;
    }

    /**
     * 职业代码
     */
    private String occupationId;
    /**
     * 职业名称
     */
    private String occupationName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 公司名
     */
    private String firmName;
    /**
     * 公司详细地址
     */
    private String firmAddress;
    /**
     * 公司所在省编码
     */
    private String firmProvinceCode;
    /**
     * 公司所在市编码
     */
    private String firmCityCode;
    /**
     * 公司所在区编码
     */
    private String firmAreaCode;
    /**
     * 住址详细住址
     */
    private String nativeAddress;
    /**
     * 住址省份编码
     */
    private String provinceCode;
    /**
     * 住址市区编码
     */
    private String cityCode;
    /**
     * 住址区域编码
     */
    private String areaCode;
    /**
     * 住址省份中文名
     */
    private String provinceMsg;
    /**
     * 住址市区中文
     */
    private String cityMsg;
    /**
     * 住址区域中文
     */
    private String areaMsg;
    /**
     * 是否绑定健康大使 0无，1绑定
     */
    private String isBindHealthMember;
    /**
     * 是否注册在1周内 0无，1在1周内
     */
    private String isInWeek;

    private int updateTimes;

    private String wechatIsBind;

    private String qqIsBind;

    private String alipayIsBind;

    private int payPwdIsSet;// 是否设置支付密码，0未设置，1设置

    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        MyLogger.i("setDeviceId--"+deviceId);
        this.deviceId = deviceId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUpdateTimes() {
        return updateTimes;
    }

    public void setUpdateTimes(int updateTimes) {
        this.updateTimes = updateTimes;
    }

    public String getIsInWeek() {
        return isInWeek;
    }

    public void setIsInWeek(String isInWeek) {
        this.isInWeek = isInWeek;
    }

    public String getIsBindHealthMember() {
        return isBindHealthMember;
    }

    public void setIsBindHealthMember(String isBindHealthMember) {
        this.isBindHealthMember = isBindHealthMember;
    }

    public int getPayPwdIsSet() {
        return payPwdIsSet;
    }

    public void setPayPwdIsSet(int payPwdIsSet) {
        this.payPwdIsSet = payPwdIsSet;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPhoneIsAuth() {
        return phoneIsAuth;
    }

    public void setPhoneIsAuth(String phoneIsAuth) {
        this.phoneIsAuth = phoneIsAuth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailIsAuth() {
        return emailIsAuth;
    }

    public void setEmailIsAuth(String emailIsAuth) {
        this.emailIsAuth = emailIsAuth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPwIsSet() {
        return pwIsSet;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getWechatIsBind() {
        return wechatIsBind;
    }

    public void setWechatIsBind(String wechatIsBind) {
        this.wechatIsBind = wechatIsBind;
    }

    public String getQqIsBind() {
        return qqIsBind;
    }

    public void setQqIsBind(String qqIsBind) {
        this.qqIsBind = qqIsBind;
    }

    public String getAlipayIsBind() {
        return alipayIsBind;
    }

    public void setAlipayIsBind(String alipayIsBind) {
        this.alipayIsBind = alipayIsBind;
    }

    public void setPwIsSet(String pwIsSet) {
        this.pwIsSet = pwIsSet;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getVipType() {
        return vipType;
    }

    public void setVipType(String vipType) {
        this.vipType = vipType;
    }

    public String getAccountState() {
        return accountState;
    }

    public void setAccountState(String accountState) {
        this.accountState = accountState;
    }

    public String getThirdCode() {
        return thirdCode;
    }

    public void setThirdCode(String thirdCode) {
        this.thirdCode = thirdCode;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getAuthLevel() {
        return authLevel;
    }

    public void setAuthLevel(String authLevel) {
        this.authLevel = authLevel;
    }

    public String getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(String occupationType) {
        this.occupationType = occupationType;
    }


    public String getOccupationName() {
        return occupationName;
    }

    public void setOccupationName(String occupationName) {
        this.occupationName = occupationName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getFirmAddress() {
        return firmAddress;
    }

    public void setFirmAddress(String firmAddress) {
        this.firmAddress = firmAddress;
    }

    public String getFirmProvinceCode() {
        return firmProvinceCode;
    }

    public void setFirmProvinceCode(String firmProvinceCode) {
        this.firmProvinceCode = firmProvinceCode;
    }

    public String getFirmCityCode() {
        return firmCityCode;
    }

    public void setFirmCityCode(String firmCityCode) {
        this.firmCityCode = firmCityCode;
    }

    public String getFirmAreaCode() {
        return firmAreaCode;
    }

    public void setFirmAreaCode(String firmAreaCode) {
        this.firmAreaCode = firmAreaCode;
    }

    public String getNativeAddress() {
        return nativeAddress;
    }

    public void setNativeAddress(String nativeAddress) {
        this.nativeAddress = nativeAddress;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getProvinceMsg() {
        return provinceMsg;
    }

    public void setProvinceMsg(String provinceMsg) {
        this.provinceMsg = provinceMsg;
    }

    public String getCityMsg() {
        return cityMsg;
    }

    public void setCityMsg(String cityMsg) {
        this.cityMsg = cityMsg;
    }

    public String getAreaMsg() {
        return areaMsg;
    }

    public void setAreaMsg(String areaMsg) {
        this.areaMsg = areaMsg;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
