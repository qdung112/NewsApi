package com.news.controller.admin;

import com.news.dto.NewDTO;
import com.news.service.ICategoryService;
import com.news.service.INewService;
import com.news.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller(value = "newControllerOfAdmin")
public class NewController {

    @Autowired
    private INewService newService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private MessageUtil messageUtil;


    @RequestMapping(value = "/quan-tri/bai-viet/danh-sach", method = RequestMethod.GET)
    public ModelAndView showList(@RequestParam(name = "page",required = false) Integer page,
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
        ModelAndView mav = new ModelAndView("admin/new/list");
        if (request.getParameter("message") != null) {
            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        if(searchTxt == null){
            model.setListResult(newService.findAll(PageRequest.of(page,limit)));
            model.setTotalItem(newService.getTotalItem());
        } else {
            model.setListResult(newService.search(PageRequest.of(page,limit), searchTxt));
            model.setTotalItem(model.getListResult().size());
        }
        model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
        mav.addObject("model", model);
        return mav;
    }

    /*@RequestMapping(value = "/quan-tri/bai-viet/danh-sach", method = RequestMethod.GET)
    public ModelAndView showList(@ModelAttribute("model")NewModel model) {
        ModelAndView mav = new ModelAndView("admin/new/list");
        model.setListResult(newService.findAll());
        mav.addObject("model",model);
        return mav;
    }*/

    @RequestMapping(value = "/quan-tri/bai-viet/chinh-sua", method = RequestMethod.GET)
    public ModelAndView editNew(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/new/edit");
        NewDTO model = new NewDTO();
        if (id != null) {
            model = newService.findOne(id);
        }
        if (request.getParameter("message") != null) {
            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        mav.addObject("categories", categoryService.findAll());
        mav.addObject("model", model);
        return mav;
    }

    @RequestMapping(value = "/quan-tri/bai-viet/them-moi", method = RequestMethod.GET)
    public ModelAndView addNew(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/new/add");
        NewDTO model = new NewDTO();
        mav.addObject("categories", categoryService.findAll());
        mav.addObject("model", model);
        return mav;
    }

    @RequestMapping(value = "/quan-tri/bai-viet/them-moi-tu-excel", method = RequestMethod.POST)
    public String excelUploadNew(@RequestParam("fileExcel") MultipartFile file) {
        newService.saveFromExcel(file);
        return "redirect:/quan-tri/bai-viet/danh-sach";
    }
}
