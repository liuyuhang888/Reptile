package com.offcn.reptile.controller;

import com.offcn.reptile.domain.New;
import com.offcn.reptile.service.SearchService;
import com.offcn.reptile.vo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: Reptile
 * @description:
 * @author: 刘宇航
 * @create: 2020-06-03 21:05
 **/
@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/key")
    public String searchByKeywords( String keywords, @RequestParam(defaultValue = "0") int pageNumber,@RequestParam(defaultValue = "8") int pageSize, Model model){
        HighlightPage page = null;
        List<QueryVo> search = searchService.search(keywords, pageNumber, pageSize, page);
        model.addAttribute("searchList",search);
        model.addAttribute("keywords",keywords);
        model.addAttribute("pageInfo",page);
        return "info";
    }
    @GetMapping("id")
    public String searchById(String id,Model model){
        New aNew = searchService.searchById(id);
        model.addAttribute("searchNew",aNew);
        return "content";
    }
}
