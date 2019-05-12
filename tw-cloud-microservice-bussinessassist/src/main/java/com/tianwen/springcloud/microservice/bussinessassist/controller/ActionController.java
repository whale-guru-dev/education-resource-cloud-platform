package com.tianwen.springcloud.microservice.bussinessassist.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.bussinessassist.api.ActionMicroApi;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Action;
import com.tianwen.springcloud.microservice.bussinessassist.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/action")
public class ActionController extends AbstractCRUDController<Action> implements ActionMicroApi {

    @Autowired
    ActionService actionService;

    @Override
    public Response<Action> getList(@RequestBody QueryTree queryTree) {
        Response<Action> response = actionService.getList(queryTree);
        List<Action> actionList = response.getPageInfo().getList();
        if (!actionList.isEmpty()){
            for (Action action : actionList){
                action.setActiontime(compareTwoTimeStamps(action.getEndtime(), action.getStarttime()));
            }
        }
        return response;
    }

    @Override
    public Response<Action> getActionListByUser(@PathVariable(value = "userid")String userid) {
        return actionService.getActionListByUser(userid);
    }

    public static long compareTwoTimeStamps(Timestamp currentTime, Timestamp oldTime) {
        long milliseconds1 = oldTime.getTime();
        long milliseconds2 = currentTime.getTime();

        long diff = milliseconds2 - milliseconds1;
        long diffSeconds = diff / 1000;

        return diffSeconds;
    }

    @Override
    public void validate(MethodType methodType, Object p) {

    }
}
