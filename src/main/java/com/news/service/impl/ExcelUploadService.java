package com.news.service.impl;

import com.news.dto.NewDTO;
import com.news.repository.CategoryRepository;
import com.news.service.IExcelUploadService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class ExcelUploadService implements IExcelUploadService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<NewDTO> uploadNewsData(InputStream inputStream) {
        List<NewDTO> newDTOList = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Trang tính1");
            int rowIndex =0;
            for (Row row : sheet){
                if (rowIndex ==0){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                NewDTO newDTO = new NewDTO();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 0 : newDTO.setTitle(cell.getStringCellValue());
                        case 1 : newDTO.setCategoryCode(cell.getStringCellValue());
                        case 2 : newDTO.setShortDescription(cell.getStringCellValue());
                        case 3 : newDTO.setContent(cell.getStringCellValue());
                        default:
                    }
                    cellIndex++;
                }
                newDTOList.add(newDTO);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return newDTOList;
    }

    @Override
    public boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" );
    }
}
