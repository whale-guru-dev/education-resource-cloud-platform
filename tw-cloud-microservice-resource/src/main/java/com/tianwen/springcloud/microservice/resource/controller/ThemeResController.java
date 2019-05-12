package com.tianwen.springcloud.microservice.resource.controller;

import com.alibaba.fastjson.JSONObject;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.constant.IStateCode;
import com.tianwen.springcloud.commonapi.log.SystemControllerLog;
import com.tianwen.springcloud.commonapi.query.QueryCondition;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.base.AbstractCRUDController;
import com.tianwen.springcloud.microservice.resource.api.ThemeResMicroApi;
import com.tianwen.springcloud.microservice.resource.constant.IResourceMicroConstants;
import com.tianwen.springcloud.microservice.resource.entity.ResConCatalogInfo;
import com.tianwen.springcloud.microservice.resource.entity.ThemeRes;
import com.tianwen.springcloud.microservice.resource.service.ESearchService;
import com.tianwen.springcloud.microservice.resource.service.ThemeResService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gems on 2018.12.21.
 */
@RestController
@RequestMapping(value = "/themeres")
public class ThemeResController extends AbstractCRUDController<ThemeRes> implements ThemeResMicroApi{
    @Autowired
    private ThemeResService themeResService;

    @Autowired
    private ESearchService eSearchService;

    @Value("${ESEARCH_SERVER}")
    protected String esearchServer;

    @Override
    public void validate(MethodType methodType, Object p) {

    }

    @Override
    public Response<ThemeRes> getList(@RequestBody QueryTree queryTree) {
        return themeResService.getList(queryTree);
    }

    @Override
    public Response<Integer> insert(@RequestBody List<ThemeRes> list) {

        for (int i=0; i<list.size()-1; i++) {
            ThemeRes item1 = list.get(i);
            for(int j=i+1; j<list.size(); j++) {
                ThemeRes item2 = list.get(j);
                if (item1.getThemeid().equals(item2.getThemeid()) && item1.getResourceno().equals(item2.getResourceno()))
                    return new Response<>(IResourceMicroConstants.THEME_RES_REPEAT);
            }
        }

        if (list != null) {
            for (ThemeRes themeRes : list) {
                List<ThemeRes> items = themeResService.select(themeRes, 0, 0);
                if (items != null && items.size() != 0)
                    return new Response<>(IResourceMicroConstants.THEME_RES_REPEAT);
            }
        }

        if (list != null) {
            for (ThemeRes item : list) {
                saveConToES(item);
            }
        }

        Integer res = themeResService.batchInsert(list);
        return new Response<>(res);
    }

    @Override
    public Response<Integer> remove(@RequestBody List<ThemeRes> list) {
        if (list != null) {
            for (ThemeRes item : list) {
                removeConFromES(item);
            }
        }
        Integer res = 0;
        for (ThemeRes themeRes : list) {
            if (themeRes != null) {
                res += themeResService.delete(themeRes);
            }
        }
        return new Response<>(res);
    }

    @Override
    public Response<ThemeRes> makeConESDb() {
        String indexUrl = "http://" + esearchServer + "/" + IResourceMicroConstants.TYPE_CON_RES_THEME;
        HttpDelete httpDelete = new HttpDelete(indexUrl);

        try{
            eSearchService.doRequest(httpDelete);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        String[] keys = {"themeid", "resourceno", "resourcetype"};
        String[] types = {"text", "text", "text"};

        JSONObject mapping = eSearchService.makeMapping(IResourceMicroConstants.TYPE_CON_RES_THEME, keys, types);

        HttpPut httpPut = new HttpPut(indexUrl);
        try {
            eSearchService.doRequestOperation(httpPut, mapping);
        }
        catch (Exception e) {
            return eSearchService.notFoundServerError();
        }

        return new Response<>();
    }

    @Override
    public Response<ThemeRes> batchSaveConToES(@RequestBody QueryTree queryTree) {
        int start;
        try{
            start = (int)queryTree.getQueryCondition("start").getFieldValues()[0];
        }
        catch (Exception e) { start = 1; }

        int pageSize = 50;
        int pageNo = start / pageSize;

        if (pageNo == 0)
            pageNo = 1;

        QueryTree resQuery = new QueryTree();
        resQuery.getPagination().setNumPerPage(pageSize);
        resQuery.getPagination().setPageNo(pageNo);

        List<ThemeRes> dataList = themeResService.getList(resQuery).getPageInfo().getList();

        if (!CollectionUtils.isEmpty(dataList)) {
            for (ThemeRes item : dataList)
                saveConToES(item);
        }
        else
            return new Response<>(IStateCode.PARAMETER_IS_INVALID, "");

        return new Response<>();
    }

    @Override
    public Response<ThemeRes> saveConToES(@RequestBody ThemeRes conInfo) {
        JSONObject json = new JSONObject();

        json.put("resourceno", conInfo.getResourceno());
        json.put("resourcetype", conInfo.getResourcetype());
        json.put("themeid", conInfo.getThemeid());

        String chapterid = conInfo.getThemeid();
        if (chapterid != null && !chapterid.isEmpty()) {

            HttpPut httpPut = new HttpPut("http://" + esearchServer + "/" + IResourceMicroConstants.INDEX_CON_RES_THEME + "/" + IResourceMicroConstants.TYPE_CON_RES_THEME + "/" + conInfo.getResourceno() + "_" + conInfo.getThemeid());

            try {
                eSearchService.doRequestOperation(httpPut, json);
            } catch (Exception e) {
                return eSearchService.notFoundServerError();
            }
        }

        return new Response<>();
    }

    @Override
    public Response<ThemeRes> removeConFromES(@RequestBody ThemeRes conInfo) {
        HttpDelete httpDelete = new HttpDelete("http://" + esearchServer + "/" + IResourceMicroConstants.INDEX_CON_RES_THEME + "/" + IResourceMicroConstants.TYPE_CON_RES_THEME + "/" + conInfo.getResourceno() + "_" + conInfo.getThemeid());
        HttpClient httpClient = new DefaultHttpClient();

        try {
            httpClient.execute(httpDelete);
        } catch (Exception e) {
            return eSearchService.notFoundServerError();
        }

        return new Response<>();
    }

    @Override
    public List<ThemeRes> getThemeResListById(@RequestBody QueryTree queryTree) {
        return themeResService.getListById(queryTree);
    }
}
