package de.drinkpipe.controllers.ingredient;

import de.drinkpipe.persistence.ingredient.IngredientService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientRestController {

  private final IngredientService ingredientService;

  @PostMapping
  ResponseEntity<Void> applyIngredient(@RequestBody @Valid IngredientDTO ingredientDTO) {
    if (ingredientService.applyIngredient(ingredientDTO).isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().build();
  }
}
