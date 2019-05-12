package com.tianwen.springcloud.microservice.bussinessassist.service;

import com.github.pagehelper.Page;
import com.tianwen.springcloud.commonapi.base.response.Response;
import com.tianwen.springcloud.commonapi.query.OrderMethod;
import com.tianwen.springcloud.commonapi.query.Pagination;
import com.tianwen.springcloud.commonapi.query.QueryTree;
import com.tianwen.springcloud.datasource.util.QueryUtils;
import com.tianwen.springcloud.microservice.bussinessassist.dao.GoodMapper;
import com.tianwen.springcloud.microservice.bussinessassist.entity.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GoodService extends MyBaseService<Good> {
    @Autowired
    private GoodMapper goodMapper;
    void fixGoodsParameter(Map<String, Object> map){
        fixTimestampParameter(map);
        // multiple catalog ids
        {
            Object obj = map.get("catalogids");
            List<String> catData = new ArrayList<String>();
            String catids = null;

            if (obj != null)
                catids = obj.toString();

            if (catids == null || catids.isEmpty() || catids.equals("[]"))
                map.remove("catalogids");
            else {
                String ids = catids;
                if (catids.indexOf(',') != -1)
                    ids = catids.substring(1, catids.length() - 1);
                String[] catid = ids.split(", ");

                for (String oneid : catid) {
                    if (oneid.isEmpty()) continue;
                    catData.add(oneid);
                }

                map.remove("catalogids");
                if (catData.size() > 0) {
                    if (catData.size() < 2)
                        catData.add("");
                    map.put("catalogids", catData);
                }
            }
        }

        {
            Object contypes = map.get("contenttype");
            if (contypes != null)
            {
                List<String> typeList = null;
                try{
                    typeList = (List<String>)contypes;
                }
                catch (Exception e) {
                    String contenttype = map.get("contenttype").toString();
                    if (!StringUtils.isEmpty(contenttype)) {
                        typeList = new ArrayList<>();
                        typeList.add(contenttype);
                    }
                }

                map.remove("contenttype");
                if (!CollectionUtils.isEmpty(typeList)) {
                    if (typeList.size() < 2)
                        typeList.add("");
                    map.put("contenttypes", typeList);
                }
            }
        }

        if (map.get("schoolsectionid") != null)
            map.put("schoolsectionid", map.get("schoolsectionid").toString());
        if (map.get("subjectid") != null)
            map.put("subjectid", map.get("subjectid").toString());
        if (map.get("gradeid") != null)
            map.put("gradeid", map.get("gradeid").toString());
        if (map.get("editiontypeid") != null)
            map.put("editiontypeid", map.get("editiontypeid").toString());
        if (map.get("bookmodelid") != null)
            map.put("bookmodelid", map.get("bookmodelid").toString());
    }

    public List<String> getProductIds(QueryTree queryTree) {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        fixTimestampParameter(map);
        List<String> idList = goodMapper.queryProductIdForList(map);
        return idList;
    }

    public Response<Good> search(QueryTree queryTree) {
        Pagination pagination = queryTree.getPagination();
        List<OrderMethod> ordermethods = queryTree.getOrderMethods();
        String orderBy;

        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        fixGoodsParameter(map);
        if (ordermethods != null) {
            orderBy = ordermethods.get(0).getField();
            if (orderBy.equals("推荐排序"))
                map.put("orderByVotes", "");
            else if (orderBy.equals("最新上传"))
                map.put("orderByLastUpdate", "");
            else if (orderBy.equals("最受欢迎"))
                map.put("orderByRating", "");
            else if (orderBy.equals("名校精品"))
                map.put("orderBySchool", "");
            else if (orderBy.equals("最热资源"))
                map.put("orderByHot", "");
            else if (orderBy.equals("orderBycreatetime"))
                map.put("orderBycreatetime", "");
        }

        Long count = goodMapper.countGoods(map);

        map.put("start", pagination.getStart());
        map.put("numPerPage", pagination.getNumPerPage());

        List<Good> queryList = goodMapper.queryGoodForList(map);

        Page<Good> page = new Page<>(pagination.getPageNo(), pagination.getNumPerPage());
        page.addAll(queryList);
        page.setTotal(count);

        return new Response<>(page);
    }

    public Good getById(String goodid) {
        return goodMapper.selectByPrimaryKey(goodid);
    }

    public Good getByContentid(String contentid) {
        return goodMapper.getByContentid(contentid);
    }

    public Response<Integer> getCountByCreator(String creator) {
        Good good = new Good();
        good.setUserid(creator);
        int count = goodMapper.selectCount(good);
        return new Response<>(count);
    }

    public Long getCount(QueryTree queryTree) {
        Map<String, Object> map = QueryUtils.queryTree2Map(queryTree);
        return goodMapper.countGoods(map);
    }
}
