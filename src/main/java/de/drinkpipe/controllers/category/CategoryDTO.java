package de.drinkpipe.controllers.category;

import de.drinkpipe.persistence.entity.CategoryType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

  private String id;
  @NotEmpty
  private String name;
  @NotNull
  private CategoryType type;
  private String rawData;
}
