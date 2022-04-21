package de.drinkpipe.persistence.mixture;

import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.MappingException;
import de.drinkpipe.bussineslogic.exceptions.ObjectNotFoundException;
import de.drinkpipe.bussineslogic.exceptions.ServiceException;
import de.drinkpipe.controllers.ingredient.IngredientDTO;
import de.drinkpipe.controllers.mixture.MixtureDTO;
import de.drinkpipe.persistence.RepoService;
import de.drinkpipe.persistence.ingredient.IngredientEntity;
import de.drinkpipe.persistence.ingredient.IngredientId;
import de.drinkpipe.persistence.ingredient.IngredientService;
import de.drinkpipe.persistence.unit.UnitEntity;
import de.drinkpipe.persistence.unit.UnitService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MixtureService extends RepoService<MixtureRepository, MixtureEntity> {

  private final UnitService unitService;
  private final IngredientService ingredientService;

  public MixtureService(Mapper mapper, MixtureRepository repository, UnitService unitService,
      IngredientService ingredientService) {
    super(mapper, repository);
    this.unitService = unitService;
    this.ingredientService = ingredientService;
  }

  public void addUnitPortionToMixture(String mixtureId, String unitId, String portion)
      throws ObjectNotFoundException {
    if (ingredientService.findById(new IngredientId(mixtureId, unitId)).isEmpty()) {
      return;
    }

    MixtureEntity mixture = repository.findById(mixtureId)
        .orElseThrow(() -> new ObjectNotFoundException("mixture not exists"));

    UnitEntity unit = unitService.findById(unitId)
        .orElseThrow(() -> new ObjectNotFoundException("unit not exists"));

    IngredientEntity newIngredient = new IngredientEntity();
    newIngredient.setMixture(mixture);
    newIngredient.setUnit(unit);
    newIngredient.setPortion(portion);

    ingredientService.save(newIngredient);
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
