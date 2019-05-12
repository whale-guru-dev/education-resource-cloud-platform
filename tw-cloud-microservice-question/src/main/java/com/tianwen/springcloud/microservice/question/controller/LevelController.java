package com.tianwen.springcloud.microservice.question.controller;

        import com.tianwen.springcloud.commonapi.base.response.Response;
        import com.tianwen.springcloud.commonapi.query.QueryTree;
        import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
        import com.tianwen.springcloud.microservice.question.api.LevelMicroApi;
        import com.tianwen.springcloud.microservice.question.entity.Level;
        import com.tianwen.springcloud.microservice.question.service.LevelService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/level")
public class LevelController extends AbstractCRUDController<Level> implements LevelMicroApi {
    @Autowired
    private LevelService levelService;

    @Override
    public Response<Level> getList(@RequestBody QueryTree queryTree) {
        return levelService.getList(queryTree);
    }


    @Override
    public void validate(MethodType methodType, Object p) {    }
}
