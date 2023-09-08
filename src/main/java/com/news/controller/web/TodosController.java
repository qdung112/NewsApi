package com.news.controller.web;

import com.news.dto.NewDTO;
import com.news.service.INewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TodosController {

    @Autowired
    private INewService newService;

    @GetMapping("/name")
    public String findAllName(){
        return "Hello";
    }

    @GetMapping("/new")
    public NewDTO showList(@RequestParam(name = "page",required = false) Integer page,
                                 @RequestParam(name = "limit",required = false) Integer limit,
                                 @RequestParam(name = "searchTxt",required = false) String searchTxt, HttpServletRequest request ) {
        NewDTO model = new NewDTO();
        if(page == null){
            page = 1;
        }
        if(limit == null){
            limit = 10;
        }
        model.setPage(page);
        model.setLimit(limit);
        if(searchTxt == null){
            model.setListResult(newService.findAll(PageRequest.of(page,limit)));
            model.setTotalItem(newService.getTotalItem());
        } else {
            model.setListResult(newService.search(PageRequest.of(page,limit), searchTxt));
            model.setTotalItem(model.getListResult().size());
        }
        model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
        return model;
    }
}