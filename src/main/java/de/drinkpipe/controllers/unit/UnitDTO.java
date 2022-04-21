package de.drinkpipe.controllers.unit;

import de.drinkpipe.persistence.entity.Flavour;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnitDTO {

  private String id;
  @NotEmpty
  private String name;
  private String ean;
  private Flavour flavour;
  private String flavourRawData;
  @NotEmpty
  private String categoryName;
}
