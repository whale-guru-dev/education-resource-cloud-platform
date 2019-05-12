package com.tianwen.springcloud.microservice.operation.constant;

/**
 * 
 * 系统常量类
 * 
 * @author jwp
 * @version [版本号, 2011-10-10]
 */
public interface IOperationConstants
{
    /*************** 对应t_e_good.status begin *************************/

    /**
     * 商品状态-0:无效
     */
    final String GOOD_STATUS_NOT_ALLOW = "0";

    /**
     * 商品状态-1:有效
     */
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


    /*************** 对应t_e_user_integraldetail.incometype start *************************/

    /**
     * income type bonus : 0
     */
    final String INCOME_TYPE_ADD = "0";

    /**
     * income type use : 1
     */
    final String INCOME_TYPE_USE = "1";

    /*************** 对应t_e_user_integraldetail.incometype end *************************/


    /*********************** 对应t_e_integral_detail.operationtype start *************************/

    /*
    operation type:1 用户充值
     */
    public final String OPERATION_CHARGE = "1";

    /*
    operation type:2 购买校园号
     */
    public final String OPERATION_SCHOOL_ACCOUNT_SELL = "2";

    /*
    operation type:3 用户登录
    */
    public final String OPERATION_USER_LOGIN = "3";

    /*
    operation type:4 完善信息
    */
    public final String OPERATION_USER_INFO_COMPLETE = "4";

    /*
    operation type:5 完成任务
    */
    public final String OPERATION_USER_TASK_COMPLETE = "5";

    /*
    operation type:6 传资源
    */
    public final String OPERATION_UPLOAD = "6";

    /*
    operation type:7 参与征集资源
    */
    public final String OPERATION_UPLOAD_COLLECTION = "7";

    /*
    operation type:8 参与悬赏资源
    */
    public final String OPERATION_UPLOAD_REWARD = "8";

    /*
    operation type:9 评星资源
    */
    public final String OPERATION_RATING = "9";

    /*
    operation type:10 点赞资源
    */
    public final String OPERATION_VOTE = "10";

    /*
    operation type:11 收藏资源
    */
    public final String OPERATION_FAVOURITE = "11";

    /*
    operation type:12 纠错资源
    */
    public final String OPERATION_ERROR_REPORT = "12";

    /*
    operation type:13 举报资源属实
    */
    public final String OPERATION_REPORT = "13";

    /*
    operation type:14 发起悬赏资源
    */
    public final String OPERATION_CREATE_REWARD = "14";

    /*
    operation type:15
    */
    public final String OPERATION_BUY_GOOD = "15";

    /*
    operation type:16
    */
    public final String OPERATION_DOWNLOAD = "16";

    /*
    operation type:17
    */
    public final String OPERATION_RETURN_FROZEN_INTEGRAL = "17";

    /*
    operation type:18
    */
    public final String OPERATION_UPLOAD_ESTIMATE = "18";

    /*********************** 对应t_e_integral_detail.operationtype end *************************/


    /*********************** 对应t_e_integral_detail.scoretype end *************************/

    /**
     * 1日常积分
     */
    public final String SCORE_TYPE_NORMAL = "1";

    /**
     * 2任务积分
     */
    public final String SCORE_TYPE_TASK = "2";

    /**
     * 3活动积分
     */
    public final String SCORE_TYPE_ACTIVITY = "3";

    /**
     * 4资源积分
     */
    public final String SCORE_TYPE_RESOURCE = "4";

    /**
     * 5题库积分
     */
    public final String SCORE_TYPE_REFERENCE = "5";

    /**
     * 6充值积分
     */
    public final String SCORE_TYPE_CHARGE = "6";

    /*********************** 对应t_e_integral_detail.scoretype end *************************/

    /**
     * 服务名称
     */
    final String SERVICE_NAME = "operation-service";
}