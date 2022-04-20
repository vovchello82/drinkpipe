package de.drinkpipe.persistence.entity;

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

	@OneToMany(mappedBy = "mixture")
	private Set<IngredientEntity> ingredients;

	public MixtureEntity() {
		super();
	}

}
