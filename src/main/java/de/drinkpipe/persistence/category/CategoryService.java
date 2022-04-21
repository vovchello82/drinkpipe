package de.drinkpipe.persistence.category;

import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.MappingException;
import de.drinkpipe.bussineslogic.exceptions.ServiceException;
import de.drinkpipe.controllers.category.CategoryDTO;
import de.drinkpipe.persistence.RepoService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategoryService extends RepoService<CategoryRepository, CategoryEntity> {


  public CategoryService(Mapper mapper, CategoryRepository repository) {
    super(mapper, repository);
  }

  public Optional<CategoryDTO> findByIdAndMap(String id) throws ServiceException {
    try {
      return findById(id).map(category -> mapper.map(category, CategoryDTO.class));
    } catch (MappingException e) {
      log.error("Mapping error", e);
      throw new ServiceException("Error on converting entity to dto", e);
    }

  }

  public Optional<CategoryEntity> findByName(String name) {
    return repository.findByName(name);
  }

  public List<CategoryDTO> findAllAndMap() throws ServiceException {
    try {
      return findAll().stream().map(category -> mapper.map(category, CategoryDTO.class))
          .collect(Collectors.toList());
    } catch (MappingException e) {
      log.error("Mapping error", e);
      throw new ServiceException("Error on converting entity to dto", e);
    }

  }

  public CategoryEntity save(CategoryDTO categoryDTO) throws ServiceException {
    try {
      CategoryEntity categoryEntity = mapper.map(categoryDTO, CategoryEntity.class);
      return save(categoryEntity);
    } catch (MappingException e) {
      log.error("Mapping error", e);
      throw new ServiceException("Error on converting dto to entity", e);
    }

  }
}
