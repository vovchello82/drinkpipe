package de.drinkpipe.persistence.ingredient;

import de.drinkpipe.persistence.mixture.MixtureEntity;
import de.drinkpipe.persistence.unit.UnitEntity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dp_ingredients")
public class IngredientEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private IngredientId id;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@MapsId("unitId")
	private UnitEntity unit;

	@Column(name = "portion", nullable = false)
	private String portion;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@MapsId("mixtureId")
	private MixtureEntity mixture;

	@PrePersist
	void generateId() {
		this.id = new IngredientId(mixture.getId(), unit.getId());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : id.hashCode());
		result = prime * result + ((mixture == null) ? 0 : mixture.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		result = prime * result + ((portion == null) ? 0 : portion.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		IngredientEntity other = (IngredientEntity) obj;
		if (unit == null) {
			return other.unit == null;
		}

		return unit.equals(other.unit);
	}

}
