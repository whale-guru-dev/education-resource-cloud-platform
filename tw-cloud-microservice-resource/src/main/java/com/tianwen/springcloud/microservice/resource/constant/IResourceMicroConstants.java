package com.tianwen.springcloud.microservice.resource.constant;

/**
 * 
 * 系统常量类
 * 
 * @author jwp
 * @version [版本号, 2011-10-10]
 */
public interface IResourceMicroConstants
{
    /*************** 对应t_e_good.status begin *************************/

    /**
     * 商品状态-0:无效
     */
    final String GOOD_STATUS_NOT_ALLOW = "0";

    /**
     * 商品状态-1:有效*/
    final String GOOD_STATUS_ALLOW = "1";

    /*************** 对应t_e_good.status end *************************/


    /*************** 对应t_e_good.goodtype begin *************************/

    /**
     * 商品类型-1:服务
     */
    final String GOOD_TYPE_SERVICE = "1";

    /**
     * 商品类型-2:资源／资源包
     */
    final String GOOD_TYPE_RESROUCE = "2";

    /**
     * 商品类型-3:APP
     */
    final String GOOD_TYPE_APP = "3";

    /**
     * 商品类型-4:会员
     */
    final String GOOD_TYPE_MEMBER = "4";

    /**
     * 商品类型-5:礼品
     */
    final String GOOD_TYPE_DRAFT = "5";
    /*************** 对应t_e_good.goodtype end *************************/

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



    /*************** 对应t_e_content.status begin *************************/
    /**
     * 资源状态-0:草稿
     */
    final String RES_STATUS_DRAFT = "0";

    /**
     * 资源状态-1:待审核
     */
    final String RES_STATUS_HOLD = "1";

    /**
     * 资源状态-2:待终审
     */
    final String RES_STATUS_REHOLD = "2";

    /**
     * 资源状态-2:审核通过
     */
    final String RES_STATUS_PASS = "3";

    /**
     * 资源状态-4:已驳回
     */
    final String RES_STATUS_NO_PASS = "4";

    /**
     * 资源状态-7:禁用
     */
    final String RES_STATUS_STOP = "7";

    /**
     * 资源状态-8:删除
     */
    final String RES_STATUS_DELETE = "8";

    /**
     * 资源状态-9:归档
     */
    final String RES_STATUS_SAVE = "9";

    /*************** 对应t_e_content.status end *************************/

    /*************** 对应t_e_content.sourceid begin *************************/

    /**
     * 资源来源-1:用户上传
     */
    final String RES_SOURCE_UPLOAD = "1";


    /**
     * 资源来源-2:征集
     */
    final String RES_SOURCE_COLLECTION = "2";


    /**
     * 资源来源-3:悬赏
     */
    final String RES_SOURCE_REWARD = "3";

    /**
     * 资源来源-4:评比
     */
    final String RES_SOURCE_ESTIMATE = "4";

    /**
     * 资源来源-5:天闻
     */
    final String RES_SOURCE_TIANWEN = "5";


    /**
     * 资源来源-6:第三方
     */
    final String RES_SOURCE_OTHER = "6";

    /*************** 对应t_e_content.sourceid end *************************/

    /*************** 对应t_e_content.isanonymity start *************************/

    /**
     * 资源是不匿名-1 ： 是匿名
     */
    final String RES_ANONYMITY = "1";

    /**
     * 资源是不匿名-0 ： 不匿名
     */
    final String RES_NOT_ANONYMITY = "0";

    /*************** 对应t_e_content.isanonymity end *************************/

    /*************** 对应t_e_content.isanswer start *************************/
    /**
     * 资源来源-6:第三方
     */
    final String RES_SOURCE_NOT_ANSWER = "0";

    /**
     * 资源-1:
     */
    final String RES_SOURCE_ANSWER = "1";


    /*************** 对应t_e_content.isanswer end *************************/

    /*************** 对应t_e_content.isgoods start *************************/
    /**
     * 资源-0:
     */
    final String RES_SOURCE_NOT_GOODS = "0";

    /**
     * 资源-1:
     */
    final String RES_SOURCE_GOODS = "1";


    /*************** 对应t_e_content.isgoods end *************************/

    /*************** 对应t_e_content.sharerange begin *************************/

    /**
     * 共享范围-1:全网
     */
    final String RES_SHARE_RANGE_ALL_NET = "1";


    /**
     * 共享范围-2:全省
     */
    final String RES_SHARE_RANGE_ALL_DEPARTMENT = "2";


    /**
     * 共享范围-3:全市
     */
    final String RES_SHARE_RANGE_ALL_CITY = "3";


    /**
     * 共享范围-4:全区
     */
    final String RES_SHARE_RANGE_ALL_AREA = "4";


    /**
     * 共享范围-5:全校
     */
    final String RES_SHARE_RANGE_ALL_SCHOOL = "5";

    /*************** 对应t_e_content.sharerange end *************************/

    /*************** 对应t_e_content.importstatus begin *************************/

    /**
     * 导入状态-0:失败
     */
    final  String RES_IMPORT_STATUS_FAIL = "0";

    /**
     * 导入状态-1:成功
     */
    final  String RES_IMPORT_STATUS_SUCCESS = "1";

    /*************** 对应t_e_content.importstatus end *************************/

    /*************** 对应t_e_content.importstatus begin *************************/

    final  Integer THEME_RES_REPEAT = -1;


    /*************** 对应t_e_content.importstatus end *************************/

    /**
     * 服务名称
     */
    final String SERVICE_NAME = "user-service";

    /*
    * Elasticsearch - index / type
    * */

    final String INDEX_GOOD = "goods";
    final String TYPE_GOOD = "goods";

    final String INDEX_CONTENT = "contents";
    final String TYPE_CONTENT = "contents";

    final String INDEX_CON_RES_CAT = "res_con_catalog";
    final String TYPE_CON_RES_CAT = "res_con_catalog";

    final String INDEX_CON_RES_THEME = "res_con_theme";
    final String TYPE_CON_RES_THEME = "res_con_theme";

    final String ES_SERVER_ERROR = "不能与ES服务器进行通信";
}