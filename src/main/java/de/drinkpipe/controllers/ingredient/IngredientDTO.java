package de.drinkpipe.controllers.ingredient;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO {

  private String id;
  @NotEmpty
  private String portion;
  @NotEmpty
  private String mixtureId;
  @NotEmpty
  private String unitId;
}
