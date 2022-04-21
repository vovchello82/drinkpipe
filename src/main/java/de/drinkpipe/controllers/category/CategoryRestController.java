package de.drinkpipe.controllers.category;

import de.drinkpipe.bussineslogic.exceptions.ServiceException;
import de.drinkpipe.persistence.category.CategoryService;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/category")
@AllArgsConstructor
public class CategoryRestController {

  private final CategoryService categoryService;

  @PostMapping
  ResponseEntity<Void> createCategory(@RequestBody @Valid CategoryDTO cat) {
    try {
      categoryService.save(cat);

      return ResponseEntity.ok().build();
    } catch (ServiceException e) {
      e.printStackTrace();
    }
    return ResponseEntity.badRequest().build();
  }

  @GetMapping()
  ResponseEntity<List<CategoryDTO>> getAllCategories() {
    try {
      return ResponseEntity.ok(categoryService.findAllAndMap());
    } catch (ServiceException e) {
      e.printStackTrace();
    }

    return ResponseEntity.internalServerError().build();
  }

  @GetMapping("/{id}")
  ResponseEntity<CategoryDTO> getCategoryById(@PathVariable String id) {
    try {
      return categoryService.findByIdAndMap(id).map(ResponseEntity::ok)
          .orElseGet(() -> ResponseEntity.notFound().build());
    } catch (ServiceException e) {
      e.printStackTrace();
    }

    return ResponseEntity.internalServerError().build();
  }
}
