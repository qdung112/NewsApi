package com.news.api;

import com.news.dto.NewDTO;
import com.news.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
public class NewAPI {

    @Autowired
    private INewService newService;

    @PostMapping("/api/new")
    public NewDTO createNew(@RequestBody NewDTO model){
        return newService.save(model);
    }

    @PostMapping("/api/newList")
    public List<NewDTO> createListNew(@RequestBody List<NewDTO> newDTOList){
        return newService.saveAll(newDTOList);
    }

    @PostMapping("/api/uploadNew")
    public List<NewDTO> excelUpload(@RequestBody MultipartFile file){
        return newService.saveFromExcel(file);
    }

    @PutMapping("/api/new")
    public NewDTO updateNew(@RequestBody NewDTO model){
        return newService.save(model);
    }

    @DeleteMapping("/api/new")
    public void deleteNew(@RequestBody long [] ids ){
        newService.delete(ids);
    }
}
