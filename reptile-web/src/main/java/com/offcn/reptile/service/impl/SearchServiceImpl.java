package com.offcn.reptile.service.impl;

import com.offcn.reptile.service.SearchService;
import com.offcn.reptile.vo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-06-03 14:41
 **/
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public List<QueryVo> search(String keywords, int pageNumber, int pageSize) {
        //构造高亮查询器
        SimpleHighlightQuery query = new SimpleHighlightQuery();
        //高亮查询的使用标签和高亮的字段
        HighlightOptions options=new HighlightOptions();

        options.setSimplePrefix("<span style='color:red'>");

        options.addField("title_ik", "intro_ik");

        options.setSimplePostfix("</span>");
        //设置高亮查询项
        query.setHighlightOptions(options);

        //设置分页信息
        query.setPageRequest(new SolrPageRequest(pageNumber, pageSize));

        //设置查询条件
        query.addCriteria(new Criteria("keyWords").is(keywords));

        HighlightPage<QueryVo> highlightPage = solrTemplate.queryForHighlightPage("collection1", query, QueryVo.class);

        //合并的列表
        List<QueryVo> list = new ArrayList<>();
        //获取高亮和不高亮的数据
        List<HighlightEntry<QueryVo>> highlighted = highlightPage.getHighlighted();

        for (HighlightEntry<QueryVo> entry : highlighted){
            //非高亮数据
            QueryVo queryVo = entry.getEntity();
            //高亮数据
            List<HighlightEntry.Highlight> highlights = entry.getHighlights();
            for (HighlightEntry.Highlight highlight : highlights){
                String name = highlight.getField().getName();
                if ("title_ik".equals(name)) {
                    queryVo.setTitle_ik(highlight.getSnipplets().get(0));
                }

                if("intro_ik".equals(name)){
                    queryVo.setIntro_ik(highlight.getSnipplets().get(0));
                }

            }
            list.add(queryVo);
        }
        return list;
    }
}
