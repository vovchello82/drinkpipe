package de.drinkpipe.persistence.mixture;

import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.MappingException;
import de.drinkpipe.bussineslogic.exceptions.ServiceException;
import de.drinkpipe.controllers.ingredient.IngredientDTO;
import de.drinkpipe.controllers.mixture.MixtureDTO;
import de.drinkpipe.persistence.RepoService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MixtureService extends RepoService<MixtureRepository, MixtureEntity> {

  public MixtureService(Mapper mapper, MixtureRepository repository) {
    super(mapper, repository);
  }

  private MixtureDTO mapEntityToDTO(MixtureEntity mixtureEntity) {
    MixtureDTO dto = new MixtureDTO();
    dto.setDescription(mixtureEntity.getDescription());
    dto.setName(mixtureEntity.getName());
    dto.setId(mixtureEntity.getId());
    dto.setIngredients(mixtureEntity.getIngredients().stream().map(ing -> {
      IngredientDTO ingredientDTO = new IngredientDTO();
      ingredientDTO.setMixtureId(ing.getId().getMixtureId());
      ingredientDTO.setUnitId(ing.getId().getUnitId());
      ingredientDTO.setPortion(ing.getPortion());
      return ingredientDTO;
    }).collect(Collectors.toSet()));
    return dto;
  }

  public Optional<MixtureDTO> findByIdAndMap(String id) throws ServiceException {
    try {
      return findById(id).map(this::mapEntityToDTO);
    } catch (MappingException e) {
      log.error("Mapping error", e);
      throw new ServiceException("Error on converting entity to dto", e);
    }
  }

  public List<MixtureDTO> findAllAndMap() throws ServiceException {
    try {
      return findAll().stream().map(this::mapEntityToDTO).collect(Collectors.toList());
    } catch (MappingException e) {
      log.error("Mapping error", e);
      throw new ServiceException("Error on converting entity to dto", e);
    }

  }

  public MixtureEntity save(MixtureDTO mixtureDTO) throws ServiceException {
    try {
      MixtureEntity mixtureEntity = mapper.map(mixtureDTO, MixtureEntity.class);
      return save(mixtureEntity);
    } catch (MappingException e) {
      log.error("Mapping error", e);
      throw new ServiceException("Error on converting dto to entity", e);
    }
  }
}
