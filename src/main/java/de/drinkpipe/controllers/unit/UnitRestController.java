package de.drinkpipe.controllers.unit;

import de.drinkpipe.bussineslogic.exceptions.ServiceException;
import de.drinkpipe.persistence.unit.UnitService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/unit")
@RequiredArgsConstructor
public class UnitRestController {

  private final UnitService unitService;

  @GetMapping
  ResponseEntity<List<UnitDTO>> getAllUnits() {
    try {
      return ResponseEntity.ok(unitService.findAllAndMap());
    } catch (ServiceException e) {
      e.printStackTrace();
    }

    return ResponseEntity.internalServerError().build();
  }

  @GetMapping("/{id}")
  ResponseEntity<UnitDTO> getUnitById(@PathVariable String id) {
    try {
      return unitService.findByIdAndMap(id).map(ResponseEntity::ok)
          .orElseGet(() -> ResponseEntity.notFound().build());
    } catch (ServiceException e) {
      e.printStackTrace();
    }

    return ResponseEntity.internalServerError().build();
  }

  @PostMapping
  public ResponseEntity<Void> createUnit(@RequestBody @Valid UnitDTO unit) {
    try {
      unitService.save(unit);

      return ResponseEntity.ok().build();
    } catch (ServiceException e) {
      e.printStackTrace();
    }
    return ResponseEntity.badRequest().build();
  }
}
