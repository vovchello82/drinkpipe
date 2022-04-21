package de.drinkpipe.persistence.unit;

import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.MappingException;
import de.drinkpipe.bussineslogic.exceptions.ServiceException;
import de.drinkpipe.controllers.unit.UnitDTO;
import de.drinkpipe.persistence.RepoService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnitService extends RepoService<UnitRepository, UnitEntity> {

  public UnitService(Mapper mapper, UnitRepository repository) {
    super(mapper, repository);
  }

  public Optional<UnitDTO> findByIdAndMap(String id) throws ServiceException {
    try {
      return findById(id).map(u -> mapper.map(u, UnitDTO.class));
    } catch (MappingException e) {
      log.error("Mapping error", e);
      throw new ServiceException("Error on converting entity to dto", e);
    }

  }

  public List<UnitDTO> findAllAndMap() throws ServiceException {
    try {
      return repository.findAll().stream().map(u -> mapper.map(u, UnitDTO.class))
          .collect(Collectors.toList());
    } catch (MappingException e) {
      log.error("Mapping error", e);
      throw new ServiceException("Error on converting entity to dto", e);
    }

  }

  public UnitEntity save(UnitDTO unitDTO) throws ServiceException {
    try {
      UnitEntity unitEntity = mapper.map(unitDTO, UnitEntity.class);
      return save(unitEntity);
    } catch (MappingException e) {
      log.error("Mapping error", e);
      throw new ServiceException("Error on converting dto to entity", e);
    }
  }
}
