package de.drinkpipe.controllers.mixture;

import de.drinkpipe.controllers.ingredient.IngredientDTO;
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
  private Set<IngredientDTO> ingredients;
}
