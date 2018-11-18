package com.project.common.core.http.constant;

/**
 * create by yexm
 * date 2018/7/6
 * time  15:07
 * describe 请求链接
 **/

public class NetConstantURL {
    //请求最基础Url
//    public final static String BASE_URL = "http://192.168.1.75:10002/wjj-web-manager/";//李磊本地
//    public final static String BASE_URL = "http://api.dev.nhf.cn/wjj-web-manager/";//dev开发环境
    public final static String BASE_URL = "http://api.test.nhf.cn/wjj-web-manager/";//test环境
//    public final static String BASE_URL = "http://api.uat.nhf.cn/wjj-web-manager/";//正式环境
//<<<<<<< HEAD
//    public final static String BASE_URL_H5 = "http://h5.dev.nhf.cn/";
        public final static String BASE_URL_H5 = "http://h5.test.nhf.cn/";
//=======
//    public final static String BASE_URL_H5 = "http://h5.dev.nhf.cn/";
////    public final static String BASE_URL = "http://119.3.56.222:10007/";//dev开发环境
////        public final static String BASE_URL_H5 = "http://h5.test.nhf.cn/";
//>>>>>>> ad0e234b9c846d69f9ee79292cc012eb101e5227
    //    public final static String BASE_URL_H5 = "http://h5.uat.nhf.cn/";//正式环境 v1.0

    /**
     * 部分H5新的链接
     */
    public final static String BASE_PUBLIC_URL_H5 = "http://public.dev.nhf.cn/";
//    public final static String BASE_PUBLIC_URL_H5 = "http://public.test.nhf.cn/";
    //优惠券分享链接
    public final static String URL_H5_SHARE_COUPON = BASE_URL_H5 + "couponBind?" + "&sonCon=";
    //优惠券说明
    public final static String URL_H5_SHARE_COUPON_DES = BASE_URL_H5 + "couponNotes";
    //分销中心
    public final static String URL_H5_SALE_CENTER = BASE_URL_H5 + "distributionHub";
    //购买大礼包
    public final static String URL_H5_BUY_BIG_GIFT = BASE_URL_H5 + "distributionPromotion";
    //H5app分享下载页
    public final static String URL_H5_APP_DOWNLOAD = BASE_PUBLIC_URL_H5 + "appDownload";
    //H5app文章详情页
    public final static String URL_H5_APP_ARTICE = BASE_PUBLIC_URL_H5 + "articalDetail.html?id=";

    /**
     * 商城模块url
     */
    /*
     *新增地址
     */
    public final static String ADD_ADDRESS = "manager/account/accountAddress/insertAddress";
    /**
     * 收货地址列表
     */
    public final static String ADDRESS_LIST = "manager/account/accountAddress/selectAddressList";
    /**
     * 删除地址
     */
    public final static String ADDRESS_DELETE = "manager/account/accountAddress/deleteAddress";
    /**
     * 更新地址
     */
    public final static String ADDRESS_UPDATE = "manager/account/accountAddress/updateAddress";
    /**
     * 设置默认地址
     */
    public final static String ADDRESS_SET_DEAFAUT = "manager/account/accountAddress/setDefaultAddress";
    /**
     * 健康银行健康树
     */
    public final static String SELECT_RELATION_SHIP_TREE = "dweller/physique/selectRelationshipTree";
    /**
     * 添加家庭成员
     */
    public final static String ADD_FAMILY_MEMBER = "dweller/physique/addFamilyMember";
    /**
     * 查询系统标签
     */
    public final static String SELECT_TAG_FORAPP = "sysTag/tagInfo/selectTagForApp";

    /**
     * 查询家庭成员健康档案
     */
    public final static String SELECT_FAMILY_MEMBER_ARCHIVES = "dweller/physique/selectFamilyMemberArchives";
    /**
     * 更新家庭成员健康档案
     */
    public final static String UPDATE_FAMILY_MEMBER_ARCHIVES = "dweller/physique/updateFamilyMemberArchives";


    /**
     * 查看家庭成员健康档案服务机构
     */
    public final static String SELECT_BUSINESS = "dweller/physique/selectBusiness";
    /**
     * 查看家庭成员健康档案权限
     */
    public final static String SELECT_RECORDS_AUTHORITY = "dweller/physique/selectRecordsAuthority";
    /**
     * 更新家庭成员健康档案权限
     */
    public final static String UPDATE_RECORDS_AUTHORITY = "dweller/physique/updateRecordsAuthority";
    /**
     * 删除家庭成员
     */
    public final static String DELETE_FAMILY_MEMBER = "dweller/physique/deleteFamilyMember";
    /**
     * 健康档案授权商户
     */
    public final static String AUTHORIZE_BUSINESS = "dweller/physique/authorizeBusiness";
    //
    public final static String UPDATE_FAMILY_MEMBERINFO = "dweller/physique/updateFamilyMemberInfo";
    /**
     * 健康服务
     */
    public final static String SELECT_HEALTH_RECORD = "dweller/physique/selectHealthRecord";

    /**
     * 新增就诊记录
     */
    public final static String MEDICAL_RECORD_INSERT = "healthRecords/medicalRecord/medicalRecordInsert";
    /**
     * 就诊记录
     */
    public final static String MEDICAL_RECORD_LIST = "healthRecords/medicalRecord/medicalRecordList";

    public final static String MEDICAL_RECORD_DELETE = "healthRecords/medicalRecord/medicalRecordDelete";
    /**
     * 更新就诊记录
     */
    public final static String MEDICAL_RECORD_UPDATE = "healthRecords/medicalRecord/medicalRecordUpdate";
    /**
     * 插入医嘱
     */
    public final static String ADVICE_INSERT = "healthRecords/medicalRecord/adviceInsert";

    /**
     * 更新医嘱
     */
    public final static String ADVICE_UPDATE = "healthRecords/medicalRecord/adviceUpdate";

    /**
     * 删除医嘱
     */
    public final static String DELETE_ADVICEBYID = "healthRecords/medicalRecord/deleteAdviceById";

    /**
     * 插入处方
     */
    public final static String PRESCRIPTION_INSERT = "healthRecords/medicalRecord/prescriptionInsert";
    /**
     * 更新处方
     */
    public final static String PRESCRIPTION_UPDATE = "healthRecords/medicalRecord/prescriptionUpdate";
    /**
     *
     */
    public final static String DELETE_PRESCRIPTION_BY_ID = "healthRecords/medicalRecord/deletePrescriptionById";

    /**
     *
     */
    public final static String DELETE_TEST_RECORD_BYID = "healthRecords/medicalRecord/deleteTestRecordById";
    /**
     *
     */
    public final static String TEST_RECORD_INSERT = "healthRecords/medicalRecord/testRecordInsert";
    /**
     *
     */
    public final static String TEST_RECORD_UPDATE = "healthRecords/medicalRecord/testRecordUpdate";

    public final static String EXAMINATION_RECORD_INSERT = "healthRecords/medicalRecord/examinationRecordInsert";
    public final static String EXAMINATION_RECORD_UPDATE = "healthRecords/medicalRecord/examinationRecordUpdate";
    public final static String DELETE_EXAMINATION_RECORD_BY_ID = "/healthRecords/medicalRecord/deleteExaminationRecordById";

    public final static String PHYSICAL_EXAMINATION_RECORD_LIST = "healthRecords/physicalExaminationRecord/physicalExaminationRecordList";
    public final static String PHYSICAL_EXAMINATION_RECORD_DELETE = "healthRecords/physicalExaminationRecord/physicalExaminationRecordDelete";
    public final static String PHYSICAL_EXAMINATION_RECORD_UPDATE = "healthRecords/physicalExaminationRecord/physicalExaminationRecordUpdate";
    public final static String PHYSICAL_EXAMINATION_RECORD_INSERT = "healthRecords/physicalExaminationRecord/physicalExaminationRecordInsert";


    public final static String WJJ_GM_OPEN_TOKEN = "homeMall/classfy/classfyIndex/wjjGmOpenToken";
    public final static String ABNORMAL_RECORD_INSERT = "healthRecords/physicalExaminationRecord/abnormalRecordInsert";
    public final static String INDEX_WJJ_GM_OPEN_TOKEN = "homeMall/adAndGoodsHomeMall/index/wjjGmOpenToken";
    public final static String SELECT_GOODS_CLASSIFY_TYPE = "goodsclassifytype/selectGoodsClassifyTypeBySecondLevelForApp/wjjGmOpenToken";
    public final static String SELECT_CLAAND_GOODS_BY_TWO = "index/selectClaAndGoodsBYTwo/wjjGmOpenToken";
    public final static String SELECT_CLAAND_GOODS_BY_Three = "index/selectClaAndGoodsBYThree/wjjGmOpenToken";
    public final static String VEDIO_GOODSBY_ACTIVEID = "homeMall/vedioGoodsByActiveId/wjjGmOpenToken";
    public final static String GET_ADDRESS = "manager/JDTMS/getAddress";
}
