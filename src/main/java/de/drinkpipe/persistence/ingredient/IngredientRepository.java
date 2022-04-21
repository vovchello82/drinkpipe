package de.drinkpipe.persistence.ingredient;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface IngredientRepository extends JpaRepository<IngredientEntity, IngredientId> {

  List<IngredientEntity> findByIdUnitId(String unitId);

  List<IngredientEntity> findByIdMixtureId(String mixtureId);
}
