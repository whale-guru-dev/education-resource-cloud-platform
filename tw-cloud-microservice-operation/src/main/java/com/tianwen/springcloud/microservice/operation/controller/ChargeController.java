package com.tianwen.springcloud.microservice.operation.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryCondition;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.operation.entity.AreaInfo;
import com.tianwen.springcloud.microservice.operation.api.ChargeMicroApi;
import com.tianwen.springcloud.microservice.operation.constant.IOperationConstants;
import com.tianwen.springcloud.microservice.operation.entity.*;
import com.tianwen.springcloud.microservice.operation.service.ChargeService;
import com.tianwen.springcloud.microservice.operation.service.IntegralService;
import com.tianwen.springcloud.microservice.operation.service.MemberService;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping(value = "/charge")
public class ChargeController extends AbstractCRUDController<Charge> implements ChargeMicroApi {
    @Autowired
    private ChargeService chargeService;

    @Autowired
    private IntegralService integralService;

    @Autowired
    private MemberService memberService;

    @Override
    public void validate(MethodType methodType, Object p)
    {
    }

    @Override
    public Response<Charge> getStatistics(@RequestBody QueryTree queryTree) {
        return chargeService.getStatistics(queryTree);
    }

    @Override
    public Response<Charge> chargeToAccount(@RequestBody Map<String, Object> param) {
        Charge charge = new Charge();

        /*pay transaction must be implemented here*/
        String result = "1";        /*result of transaction*/

        double exchange = Double.parseDouble(param.get("exchange").toString());
        double cost = Double.parseDouble(param.get("cost").toString());
        String userid = param.get("userid").toString();

        charge.setExchange(exchange);
        charge.setCost(cost);
        charge.setUserid(userid);

        charge.setStatus(result);
        charge.setPayinfo(null);
        charge.setCreatetime(new Timestamp(System.currentTimeMillis()));

        chargeService.save(charge);

        Member member = memberService.getByUserId(param.get("userid").toString());
        if (member != null)
        {
            Member memData = new Member();
            memData.setMemberid(member.getMemberid());
            Integer curInt = member.getUseintegral();
            curInt += (int)(exchange * cost);
            memData.setUseintegral(curInt);
            memberService.updateNotNull(memData);

            Integral integral = new Integral();
            integral.setUserid(userid);
            integral.setOperationtype("1");
            integral.setIncometype("0");
            integral.setIntegralvalue((int)(exchange * cost));
            integral.setUserintegralvalue(curInt);
            integral.setObjectid("");
            integral.setScoretype(IOperationConstants.SCORE_TYPE_CHARGE);
            integral.setCreatetime(new Timestamp(System.currentTimeMillis()));
            integral.setLastmodifytime(new Timestamp(System.currentTimeMillis()));
            integralService.save(integral);
        }

        return new Response<>(charge);
    }

    @Override
    public Response<Charge> placeCharge(Map<String, Object> param) {
        Charge charge = new Charge();

        String result = "0";

        double exchange = Double.parseDouble(param.get("exchange").toString());
        double cost = Double.parseDouble(param.get("cost").toString());
        String userid = param.get("userid").toString();

        charge.setExchange(exchange);
        charge.setCost(cost);
        charge.setUserid(userid);

        charge.setStatus(result);
        charge.setPayinfo(null);
        charge.setCreatetime(new Timestamp(System.currentTimeMillis()));

        chargeService.save(charge);

        return new Response<>(charge);
    }

    @Override
    public Response<Charge> getAllData(@RequestBody QueryTree queryTree) {
        List<Charge> chargeData = chargeService.getAllData(queryTree);
        return new Response<>(chargeData);
    }

    @Override
    public Response<Charge> getChargeItemList(@RequestBody QueryTree queryTree) {
        return chargeService.search(queryTree);
    }

    @Override
    public Response<Double> getTotalChargeScore(@RequestBody QueryTree queryTree) {
        double sum = chargeService.getChargePoint(queryTree);
        return new Response<>(sum);
    }

    @Override
    public Response<Map<String, Object>> getChargeStatus(@RequestBody  QueryTree queryTree) {
        Map<String, Object> result = new HashMap<>();
        Calendar calendar;
        List<QueryCondition> conditions = queryTree.getConditions();
        Date begin_time = new Date(System.currentTimeMillis()), end_time = new Date(System.currentTimeMillis());
        // total and average
        {
            QueryTree total = new QueryTree();
            total.setConditions(conditions);
            int userCnt = chargeService.getChargeUsers().size();
            total.addCondition(new QueryCondition("status", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, "1"));
            double totalCharge = chargeService.getChargePoint(total) ;
            result.put("average", userCnt == 0 ? 0 : totalCharge / userCnt);
            result.put("total", userCnt == 0 ? 0 : totalCharge);
        }

        // today
        {
            QueryTree today = new QueryTree();
            today.setConditions(conditions);
            today.addCondition(new QueryCondition("status", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, "1"));
            today.addCondition(new QueryCondition("begin_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(begin_time, "yyyy-MM-dd")));
            today.addCondition(new QueryCondition("end_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(end_time, "yyyy-MM-dd")));

            result.put("today", chargeService.getChargePoint(today));
        }

        // last week
        {
            QueryTree lastweek = new QueryTree();
            calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -7);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            begin_time = calendar.getTime();
            calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -7);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
            end_time = calendar.getTime();
            lastweek.setConditions(conditions);
            lastweek.addCondition(new QueryCondition("status", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, "1"));
            lastweek.addCondition(new QueryCondition("begin_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(begin_time, "yyyy-MM-dd")));
            lastweek.addCondition(new QueryCondition("end_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(end_time, "yyyy-MM-dd")));
            result.put("lastweek", chargeService.getChargePoint(lastweek));
        }

        // last month
        {
            QueryTree lastmonth = new QueryTree();
            calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE, 1);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            end_time = calendar.getTime();
            calendar.set(Calendar.DATE, 1);
            begin_time = calendar.getTime();
            lastmonth.setConditions(conditions);
            lastmonth.addCondition(new QueryCondition("status", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, "1"));
            lastmonth.addCondition(new QueryCondition("begin_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(begin_time, "yyyy-MM-dd")));
            lastmonth.addCondition(new QueryCondition("end_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(end_time, "yyyy-MM-dd")));
            result.put("lastmonth", chargeService.getChargePoint(lastmonth));
        }

        return new Response<>(result);
    }

    @Override
    public Response<Map<String, Object>> getChargeStatistics(@PathVariable(value = "mode") int mode) {
        Map<String, Object> result = new HashMap<>();
        if (mode == 1)  // last 10 days
        {
            List<Object> dataList = new ArrayList<>();
            List<String> titleList = new ArrayList<>();

            Calendar calendar;
            Date begin_time, end_time;

            QueryTree days = new QueryTree();

            calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -9);
            for (int i = 0; i < 10; i++) {
                begin_time = calendar.getTime();
                end_time = calendar.getTime();

                days.addCondition(new QueryCondition("status", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, "1"));
                days.addCondition(new QueryCondition("begin_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(begin_time, "yyyy-MM-dd")));
                days.addCondition(new QueryCondition("end_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(end_time, "yyyy-MM-dd")));

                dataList.add(chargeService.getChargePoint(days));
                titleList.add(DateUtils.formatDate(begin_time, "yyyy-MM-dd"));

                calendar.add(Calendar.DATE, 1);
            }

            result.put("title", titleList);
            result.put("data", dataList);
        }
        else if (mode == 2) // last 4 weeks
        {
            List<Object> dataList = new ArrayList<>();
            List<String> titleList = new ArrayList<>();

            for (int i = 0; i < 4; i++)
            {
                Calendar calendar;
                Date begin_time, end_time;

                QueryTree weeks = new QueryTree();

                calendar = Calendar.getInstance();
                calendar.add(calendar.DATE, (i - 3) * 7);
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                begin_time = calendar.getTime();
                calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, (i - 3) * 7);
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                end_time = calendar.getTime();

                weeks.addCondition(new QueryCondition("status", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, "1"));
                weeks.addCondition(new QueryCondition("begin_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(begin_time, "yyyy-MM-dd")));
                weeks.addCondition(new QueryCondition("end_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(end_time, "yyyy-MM-dd")));

                dataList.add(chargeService.getChargePoint(weeks));
                titleList.add(DateUtils.formatDate(begin_time, "yyyy-MM-dd") + " ~ " + DateUtils.formatDate(end_time, "yyyy-MM-dd"));

                calendar.add(Calendar.DATE, 1);
            }

            result.put("title", titleList);
            result.put("data", dataList);
        }
        else if (mode == 3)  // last 12 months
        {
            List<Object> dataList = new ArrayList<>();
            List<String> titleList = new ArrayList<>();

            for (int i = 0; i < 12; i++) {
                Calendar calendar;
                Date begin_time, end_time;

                QueryTree months = new QueryTree();

                calendar = Calendar.getInstance();
                calendar.set(Calendar.DATE, 1);
                calendar.add(Calendar.MONTH, i - 10);
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                end_time = calendar.getTime();
                calendar.set(calendar.DATE, 1);
                begin_time = calendar.getTime();

                months.addCondition(new QueryCondition("status", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, "1"));
                months.addCondition(new QueryCondition("begin_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(begin_time, "yyyy-MM-dd")));
                months.addCondition(new QueryCondition("end_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(end_time, "yyyy-MM-dd")));

                dataList.add(chargeService.getChargePoint(months));
                titleList.add(DateUtils.formatDate(begin_time, "yyyy-MM"));
            }

            result.put("title", titleList);
            result.put("data", dataList);
        }
        else if (mode == 4) // last 5 years
        {
            List<Object> dataList = new ArrayList<>();
            List<String> titleList = new ArrayList<>();

            for (int i = 4; i >= 0; i--)
            {
                Date begin_time = new Date(0), end_time = new Date(0);
                Date curDate = new Date(System.currentTimeMillis());
                QueryTree years = new QueryTree();

                begin_time.setYear(curDate.getYear() - i);
                begin_time.setMonth(0);
                begin_time.setDate(1);
                end_time.setYear(curDate.getYear() - i + 1);
                end_time.setMonth(0);
                end_time.setDate(31);

                years.addCondition(new QueryCondition("status", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, "1"));
                years.addCondition(new QueryCondition("begin_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(begin_time, "yyyy-MM-dd")));
                years.addCondition(new QueryCondition("end_time", QueryCondition.Prepender.AND, QueryCondition.Operator.EQUAL, DateUtils.formatDate(end_time, "yyyy-MM-dd")));

                dataList.add(chargeService.getChargePoint(years));
                titleList.add(DateUtils.formatDate(begin_time, "yyyy"));
            }

            result.put("title", titleList);
            result.put("data", dataList);
        }

        return new Response<>(result);
    }

    @Override
    public Response<ChargeStatInfo> getChargeStatByArea(@RequestBody List<AreaInfo> areaList) {
        return new Response<>(chargeService.getAreaStatistics(areaList));
    }
}
