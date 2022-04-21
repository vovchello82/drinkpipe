package de.drinkpipe.controllers.ingredient;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

  @NotEmpty
  private String portion;
  @NotEmpty
  @JsonIgnore
  private String mixtureId;
  @NotEmpty
  private String unitId;
}
