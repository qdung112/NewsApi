package com.news.service.impl;

import com.news.converter.CategoryConverter;
import com.news.dto.CategoryDTO;
import com.news.entity.CategoryEntity;
import com.news.repository.CategoryRepository;
import com.news.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryConverter categoryConverter;

	/*@Override
	public Map<String, String> findAll() {
		Map<String, String> result = new HashMap<>();
		List<CategoryEntity> entities = categoryRepository.findAll();
		for (CategoryEntity item: entities) {
			result.put(item.getCode(), item.getName());
		}
		return result;
	}*/

	@Override
	public List<CategoryDTO> findAll() {
		List<CategoryEntity> entities = categoryRepository.findAll();
		List<CategoryDTO> models = new ArrayList<>();
		for(CategoryEntity entity : entities){
			CategoryDTO categoryDTO = categoryConverter.toDto(entity);
			models.add(categoryDTO);
		}
		return models;
	}
}