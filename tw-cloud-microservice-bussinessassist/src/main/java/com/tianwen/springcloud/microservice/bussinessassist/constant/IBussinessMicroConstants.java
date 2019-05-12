package com.tianwen.springcloud.microservice.bussinessassist.constant;

/**
 * 
 * 系统常量类
 * 
 * @author jwp
 * @version [版本号, 2011-10-10]
 */
public interface IBussinessMicroConstants
{
    /*************** 对应t_e_user_logininfo.status begin *************************/
    /**
     * 用户帐号状态-1:正常
     */
    final String USER_ACCOUNT_STATUS_NORMAL = "1";
    
    /**
     * 用户帐号状态-2:待审核
     */
    final String USER_ACCOUNT_STATUS_TO_AUDIT = "2";
    
    /**
     * 用户帐号状态-4:审核不通过
     */
    final String USER_ACCOUNT_STATUS_NO_PASS = "4";
    
    /**
     * 用户帐号状态-9:删除
     */
    final String USER_ACCOUNT_STATUS_DELETE = "9";
    
    /*************** 对应t_e_user_logininfo.status end *************************/

    /*************************t_e_good.good_type start ***********************/

    final String GOOD_TYPE_RESOURCE = "2";

    final String GOOD_TYPE_QUESTION = "6";

    final String GOOD_TYPE_PAPER = "7";

    /*************************t_e_good.good_type end  ***********************/

    /************************ t_e_advert.status begin *************************/

    /**
     *
     */
    final String ADVERT_STATUS_VALID = "1";

    /**
     *
     */

    final String ADVERT_STATUS_INVALID = "0";

    /************************ t_e_advert.status end **************************/

    /**
     * 服务名称
     */
    final String SERVICE_NAME = "bussinessassist-service";

    /************************ t_e_resource_option.optiontype begin *************************/

    final String OPTION_OPTIONTYPE_DOWNLOAD = "1";

    final String OPTION_OPTIONTYPE_PURCHASE = "2";

    final String OPTION_OPTIONTYPE_MODIFY = "3";

    final String OPTION_OPTIONTYPE_ADD = "4";

    final String OPTION_OPTIONTYPE_DELETE = "5";

    final String OPTION_OPTIONTYPE_AUDIT_ALLOW = "6";

    final String OPTION_OPTIONTYPE_FEEDBACKERROR = "7";

    final String OPTION_OPTIONTYPE_FEEDBACK = "8";

    final String OPTION_OPTIONTYPE_COLLECTION_NEW = "9";

    final String OPTION_OPTIONTYPE_COLLECTION_IN = "10";

    final String OPTION_OPTIONTYPE_REWARD_NEW = "11";

    final String OPTION_OPTIONTYPE_REWARD_IN = "12";

    final String OPTION_OPTIONTYPE_UPLOAD = "13";

    final String OPTION_OPTIONTYPE_FAVORITE_ADD = "14";

    final String OPTION_OPTIONTYPE_FAVORITE_DELETE = "15";

    final String OPTION_OPTIONTYPE_CHARGE = "16";

    final String OPTION_OPTIONTYPE_BATCHIMPORT = "17";

    final String OPTION_OPTIONTYPE_PACKAGE_ADD = "18";

    final String OPTION_OPTIONTYPE_PRICESET = "19";

    final String OPTION_OPTIONTYPE_ALLOWSELL = "20";

    final String OPTION_OPTIONTYPE_DENYSELL = "21";

    final String OPTION_OPTIONTYPE_PAY = "22";

    final String OPTION_OPTIONTYPE_PRICEMODIFY = "23";

    final String OPTION_OPTIONTYPE_CATALOG_ADD = "24";

    final String OPTION_OPTIONTYPE_CATALOG_DELETE = "25";

    final String OPTION_OPTIONTYPE_CATALOG_MODIFY = "26";

    final String OPTION_OPTIONTYPE_CATALOG_MOVE = "27";

    final String OPTION_OPTIONTYPE_SUBNAVI_ADD = "28";

    final String OPTION_OPTIONTYPE_SUBNAVI_DELETE = "29";

    final String OPTION_OPTIONTYPE_SUBNAVI_MODIFY = "30";

    final String OPTION_OPTIONTYPE_SCHOOLNAMED = "31";

    final String OPTION_OPTIONTYPE_SCHOOLNORMAL = "32";

    final String OPTION_OPTIONTYPE_PACKAGE_MODIFY = "33";

    final String OPTION_OPTIONTYPE_PACKAGE_DELETE = "34";

    final String OPTION_OPTIONTYPE_AUDIT_DENY = "35";

    final String OPTION_OPTIONTYPE_COLLECTION_MODIFY = "36";

    final String OPTION_OPTIONTYPE_COLLECTION_DELETE = "37";

    final String OPTION_OPTIONTYPE_REWARD_MODIFY = "38";

    final String OPTION_OPTIONTYPE_REWARD_DELETE = "39";

    /************************ t_e_resource_option.optiontype end *************************/

    /************************ t_e_resource_option.optionname begin *************************/

    final String OPTION_OPTIONNAME_DOWNLOAD = "下载";

    final String OPTION_OPTIONNAME_PURCHASE = "购买";

    final String OPTION_OPTIONNAME_MODIFY = "修改";

    final String OPTION_OPTIONNAME_ADD = "添加";

    final String OPTION_OPTIONNAME_DELETE = "删除";

    final String OPTION_OPTIONNAME_AUDIT_ALLOW = "审核通过";

    final String OPTION_OPTIONNAME_FEEDBACKERROR = "纠错";

    final String OPTION_OPTIONNAME_FEEDBACK = "举报";

    final String OPTION_OPTIONNAME_COLLECTION_NEW = "征集发起";

    final String OPTION_OPTIONNAME_COLLECTION_IN = "征集参与";

    final String OPTION_OPTIONNAME_REWARD_NEW = "悬赏发起";

    final String OPTION_OPTIONNAME_REWARD_IN = "悬赏参与";

    final String OPTION_OPTIONNAME_UPLOAD = "上传";

    final String OPTION_OPTIONNAME_FAVORITE_ADD = "收藏夹添加";

    final String OPTION_OPTIONNAME_FAVORITE_DELETE = "收藏夹删除";

    final String OPTION_OPTIONNAME_CHARGE = "充值";

    final String OPTION_OPTIONNAME_BATCHIMPORT = "资源批量导入";

    final String OPTION_OPTIONNAME_PACKAGE_ADD = "商品打包添加";

    final String OPTION_OPTIONNAME_PRICESET = "定价";

    final String OPTION_OPTIONNAME_ALLOWSELL = "上架";

    final String OPTION_OPTIONNAME_DENYSELL = "下架";

    final String OPTION_OPTIONNAME_PAY = "支付";

    final String OPTION_OPTIONNAME_PRICEMODIFY = "定价修改";

    final String OPTION_OPTIONNAME_CATALOG_ADD = "目录添加";

    final String OPTION_OPTIONNAME_CATALOG_DELETE = "目录删除";

    final String OPTION_OPTIONNAME_CATALOG_MODIFY = "目录修改";

    final String OPTION_OPTIONNAME_CATALOG_MOVE = "目录";

    final String OPTION_OPTIONNAME_SUBNAVI_ADD = "学科添加";

    final String OPTION_OPTIONNAME_SUBNAVI_DELETE = "学科删除";

    final String OPTION_OPTIONNAME_SUBNAVI_MODIFY = "学科修改";

    final String OPTION_OPTIONNAME_SCHOOLNAMED = "名校";

    final String OPTION_OPTIONNAME_SCHOOLNORMAL = "普通校";

    final String OPTION_OPTIONNAME_PACKAGE_MODIFY = "商品打包修改";

    final String OPTION_OPTIONNAME_PACKAGE_DELETE = "商品打包删除";

    final String OPTION_OPTIONNAME_AUDIT_DENY ="审核不通过";

    final String OPTION_OPTIONNAME_COLLECTION_MODIFY = "征集修改";

    final String OPTION_OPTIONNAME_COLLECTION_DELETE = "征集删除";

    final String OPTION_OPTIONNAME_REWARD_MODIFY = "悬赏修改";

    final String OPTION_OPTIONNAME_REWARD_DELETE = "悬赏删除";

    /************************ t_e_resource_option.optionname end *************************/

    /************************ t_e_user_action actiontype start *************************/

    final String ACTION_ACTIONTYPE_NAMEDSCHOOL_CONTENT = "名校资源";

    final String ACTION_ACTIONTYPE_SYNCHRO = "同步资源";

    final String ACTION_ACTIONTYPE_PAPER = "试卷";

    final String ACTION_ACTIONTYPE_SPECIAL = "特供专题";

    final String ACTION_ACTIONTYPE_MICRO = "微视频课程";

    final String ACTION_ACTIONTYPE_ENGLISH = "培生英语";

    final String ACTION_ACTIONTYPE_STEM = "stem课程";

    final String ACTION_ACTIONTYPE_BEAUTY = "美育课程";

    final String ACTION_ACTIONTYPE_LOGO = "品牌专区";

    final String ACTION_ACTIONTYPE_SUBJECT = "课例资源包";

    final String ACTION_ACTIONTYPE_FINESCHOOL = "精品学堂";

    final String ACTION_ACTIONTYPE_COLLECTION = "参与征集";

    final String ACTION_ACTIONTYPE_REWARD = "参与悬赏";

    final String ACTION_ACTIONTYPE_ESTIMATE = "参与评比";

    final String ACTION_ACTIONTYPE_UPLAOD = "上传资源";

    /************************ t_e_user_action actiontype end *************************/

    /*
    * Elasticsearch - index / type
    * */

    final String INDEX_GOOD = "goods";
    final String TYPE_GOOD = "goods";

    final String ES_SERVER_ERROR = "不能与ES服务器进行通信";
}