package de.drinkpipe.persistence.mixture;

import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.MappingException;
import de.drinkpipe.bussineslogic.exceptions.ServiceException;
import de.drinkpipe.controllers.mixture.MixtureDTO;
import de.drinkpipe.persistence.RepoService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MixtureService extends RepoService<MixtureRepository, MixtureEntity> {

  public MixtureService(Mapper mapper, MixtureRepository repository) {
    super(mapper, repository);
  }

  public Optional<MixtureDTO> findByIdAndMap(String id) throws ServiceException {
    try {
      return findById(id).map(mix -> mapper.map(mix, MixtureDTO.class));
    } catch (MappingException e) {
      log.error("Mapping error", e);
      throw new ServiceException("Error on converting entity to dto", e);
    }

  }

  public List<MixtureDTO> findAllAndMap() throws ServiceException {
    try {
      return findAll().stream().map(mix -> mapper.map(mix, MixtureDTO.class))
          .collect(Collectors.toList());
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
