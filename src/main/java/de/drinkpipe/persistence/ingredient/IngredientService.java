package de.drinkpipe.persistence.ingredient;

import de.drinkpipe.controllers.ingredient.IngredientDTO;
import de.drinkpipe.persistence.mixture.MixtureEntity;
import de.drinkpipe.persistence.mixture.MixtureService;
import de.drinkpipe.persistence.unit.UnitEntity;
import de.drinkpipe.persistence.unit.UnitService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientService {

  private final UnitService unitService;
  private final MixtureService mixtureService;
  private final IngredientRepository ingredientRepository;

  public IngredientEntity save(IngredientEntity ingredient) {
    return ingredientRepository.save(ingredient);
  }

  public Optional<IngredientEntity> findById(IngredientId id) {
    return ingredientRepository.findById(id);
  }

  public Optional<IngredientEntity> applyIngredient(IngredientDTO ingredientDTO) {
    Optional<UnitEntity> unit = unitService.findById(ingredientDTO.getUnitId());
    if (unit.isEmpty()) {
      log.warn("unit not found: " + ingredientDTO.getUnitId());
      return Optional.empty();
    }
    Optional<MixtureEntity> mixture = mixtureService.findById(ingredientDTO.getMixtureId());
    if (mixture.isEmpty()) {
      log.warn("mix not found: " + ingredientDTO.getMixtureId());
      return Optional.empty();
    }

    MixtureEntity mixtureEntity = mixture.get();
    UnitEntity unitEntity = unit.get();

    if (mixtureEntity.getIngredients().stream().map(IngredientEntity::getUnit)
        .anyMatch(u -> u.equals(unitEntity))) {
      return Optional.empty();
    }

    IngredientEntity ingredientEntity = new IngredientEntity();
    ingredientEntity.setMixture(mixtureEntity);
    ingredientEntity.setUnit(unitEntity);
    ingredientEntity.setPortion(ingredientDTO.getPortion());

    ingredientEntity = ingredientRepository.save(ingredientEntity);
    mixtureEntity.getIngredients().add(ingredientEntity);
    return Optional.of(ingredientEntity);
  }

}
