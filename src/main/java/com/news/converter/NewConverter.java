package com.news.converter;

import com.news.dto.NewDTO;
import com.news.entity.NewEntity;
import com.news.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewConverter {


    @Autowired
    private CategoryRepository categoryRepository;

    public NewDTO toDTO(NewEntity entity){
        NewDTO result = new NewDTO();
        result.setId(entity.getId());
        result.setTitle(entity.getTitle());
        result.setShortDescription(entity.getShortDescription());
        result.setContent(entity.getContent());
        result.setThumbnail(entity.getThumbnail());
        result.setCategoryCode(entity.getCategory().getCode());
        return result;
    }

    public NewEntity toEntity(NewDTO dto) {
        NewEntity result = new NewEntity();
        result.setTitle(dto.getTitle());
        result.setShortDescription(dto.getShortDescription());
        result.setContent(dto.getContent());
        result.setThumbnail(dto.getThumbnail());
        result.setCategory(categoryRepository.findOneByCode(dto.getCategoryCode()));
        return result;
    }

    public NewEntity toEntity(NewEntity result, NewDTO dto) {
        result.setTitle(dto.getTitle());
        result.setShortDescription(dto.getShortDescription());
        result.setContent(dto.getContent());
        result.setThumbnail(dto.getThumbnail());
        return result;
    }

    public List<NewEntity> toListEntity(List<NewDTO> newDTOList) {
        List<NewEntity> entityList = new ArrayList<>();
        for (NewDTO newDTO:
             newDTOList) {
            entityList.add(toEntity(newDTO));
        }
        return entityList;
    }

    public List<NewDTO> toListDto(List<NewEntity> newEntityList) {
        List<NewDTO> newDTOList = new ArrayList<>();
        for (NewEntity newEntity: newEntityList) {
            newDTOList.add(toDTO(newEntity));
        }
        return newDTOList;
    }
}
