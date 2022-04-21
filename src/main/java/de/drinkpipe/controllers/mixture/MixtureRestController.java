package de.drinkpipe.controllers.mixture;

import de.drinkpipe.bussineslogic.exceptions.ServiceException;
import de.drinkpipe.persistence.mixture.MixtureService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/mixture")
@RequiredArgsConstructor
public class MixtureRestController {

  private final MixtureService mixtureService;

  @PostMapping
  ResponseEntity<Void> createMixture(@RequestBody @Valid MixtureDTO mixtureDTO) {
    try {
      mixtureService.save(mixtureDTO);

      return ResponseEntity.ok().build();
    } catch (ServiceException e) {
      e.printStackTrace();
    }
    return ResponseEntity.badRequest().build();
  }

  @GetMapping("/{id}")
  ResponseEntity<MixtureDTO> getMixture(@PathVariable String id) {
    try {
      return mixtureService.findByIdAndMap(id).map(ResponseEntity::ok)
          .orElseGet(() -> ResponseEntity.notFound().build());
    } catch (ServiceException e) {
      e.printStackTrace();
    }

    return ResponseEntity.internalServerError().build();
  }

  @GetMapping()
  ResponseEntity<List<MixtureDTO>> getAllMixtures() {
    try {
      return ResponseEntity.ok().body(mixtureService.findAllAndMap());
    } catch (ServiceException e) {
      e.printStackTrace();
    }

    return ResponseEntity.internalServerError().build();
  }

}
