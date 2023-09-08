package com.news.service.impl;

import com.news.converter.NewConverter;
import com.news.dto.NewDTO;
import com.news.entity.CategoryEntity;
import com.news.entity.NewEntity;
import com.news.repository.CategoryRepository;
import com.news.repository.NewRepository;
import com.news.service.IExcelUploadService;
import com.news.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewService implements INewService {

	@Autowired
	private NewRepository newRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private NewConverter newConverter;

	@Autowired
	private IExcelUploadService uploadService;

/*	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		return newDao.findByCategoryId(categoryId);
	}*/

	@Transactional
	@Override
	public NewDTO save(NewDTO newDTO) {
		CategoryEntity category = categoryRepository.findOneByCode(newDTO.getCategoryCode());
		NewEntity newEntity;
		if (newDTO.getId() != null) {
			NewEntity oldNew = newRepository.getReferenceById(newDTO.getId());
			oldNew.setCategory(category);
			newEntity = newConverter.toEntity(oldNew, newDTO);
		} else {
			newEntity = newConverter.toEntity(newDTO);
			newEntity.setCategory(category);
		}
		return newConverter.toDTO(newRepository.save(newEntity));
	}

	@Transactional
	@Override
	public NewDTO update(NewDTO dto) {
		return null;
	}

	@Transactional
	@Override
	public void delete(long[] ids) {
		for (long id : ids) {
			//1.delete comment (khoa ngoai new_id)
			//2.delete news
			newRepository.deleteById(id);
		}
	}

	@Override
	public List<NewDTO> findAll(Pageable pageable) {
		List<NewEntity> entities = newRepository.findAll(pageable).getContent();
		List<NewDTO> models = new ArrayList<>();
		for (NewEntity entity : entities) {
			NewDTO newDTO = newConverter.toDTO(entity);
			models.add(newDTO);
		}
		return models;
	}

	@Override
	public int getTotalItem() {
		return (int) newRepository.count();
	}

	@Override
	public NewDTO findOne(long id) {
		NewEntity newEntity = newRepository.getReferenceById(id);
		return newConverter.toDTO(newEntity);
	}

	@Override
	public List<NewDTO> saveAll(List<NewDTO> newDTOList) {
		List<NewEntity> newEntityList = newConverter.toListEntity(newDTOList);
		newRepository.saveAll(newEntityList);
		return newConverter.toListDto(newEntityList);
	}

	@Override
	public List<NewDTO> search(Pageable pageable, String searchTxt) {
		List<NewEntity> newEntityList = newRepository.findByTitleLikeOrShortDescriptionLike("%" + searchTxt + "%", "%" + searchTxt + "%", pageable);
		return newConverter.toListDto(newEntityList);
	}

	@Transactional
	@Override
	public List<NewDTO> saveFromExcel(MultipartFile file) {
		List<NewEntity> newEntityList = new ArrayList<>();
		if (uploadService.isValidExcelFile(file)) {
			try {
				List<NewDTO> newDTOList = uploadService.uploadNewsData(file.getInputStream());
				newEntityList = newRepository.saveAll(newConverter.toListEntity(newDTOList));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return newConverter.toListDto(newEntityList);
	}
}
