package com.news.service;

import com.news.dto.NewDTO;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.List;

public interface IExcelUploadService {
    List<NewDTO> uploadNewsData(InputStream inputStream);
    boolean isValidExcelFile(MultipartFile file);
}
