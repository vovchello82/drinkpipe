package de.drinkpipe.persistence.category;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

  Optional<CategoryEntity> findByName(String name);
}
