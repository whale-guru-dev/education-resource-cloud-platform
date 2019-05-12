package com.tianwen.springcloud.microservice.bussinessassist.controller;

import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.log.SystemControllerLog;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.bussinessassist.api.MessageMicroApi;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Message;
import com.tianwen.springcloud.microservice.bussinessassist.service.MessageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping(value = "/message")
public class MessageController extends AbstractCRUDController<Message> implements MessageMicroApi {

    @Override
    public void validate(MethodType methodType, Object p) {

    }
}
