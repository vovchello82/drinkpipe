package de.drinkpipe.persistence.mixture;

import de.drinkpipe.persistence.entity.PieceEntity;
import de.drinkpipe.persistence.ingredient.IngredientEntity;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "dp_mixtures")
public class MixtureEntity extends PieceEntity {

	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "mixture", orphanRemoval = true)
	private Set<IngredientEntity> ingredients;

	public MixtureEntity() {
		super();
	}

	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
