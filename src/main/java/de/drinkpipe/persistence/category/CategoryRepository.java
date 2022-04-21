package de.drinkpipe.persistence.category;

import org.springframework.data.jpa.repository.JpaRepository;

interface CategoryRepository extends JpaRepository<CategoryEntity, String> {

}
