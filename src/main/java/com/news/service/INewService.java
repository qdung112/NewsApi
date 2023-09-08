package com.news.service;

import com.news.dto.NewDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface INewService {
	//List<NewModel> findByCategoryId(Long categoryId);
	NewDTO save(NewDTO newDTO);
	NewDTO update(NewDTO newDTO);
	void delete(long[] ids);
	List<NewDTO> findAll(Pageable pageable);
	int getTotalItem();
	NewDTO findOne(long id);
	List<NewDTO> saveAll(List<NewDTO> newDTOList);
	List<NewDTO> search(Pageable pageable, String searchTxt);
	List<NewDTO> saveFromExcel(MultipartFile file);
}
