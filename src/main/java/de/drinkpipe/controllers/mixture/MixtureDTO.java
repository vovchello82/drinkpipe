package de.drinkpipe.controllers.mixture;

import de.drinkpipe.persistence.ingredient.IngredientEntity;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MixtureDTO {

  private String id;
  @NotEmpty
  private String name;
  @NotEmpty
  private String description;
  private Set<IngredientEntity> ingredients;
}
