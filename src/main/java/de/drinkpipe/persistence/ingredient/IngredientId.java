package de.drinkpipe.persistence.ingredient;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "mixture_id", nullable = false)
	private String mixtureId;
	@Column(name = "unit_id", nullable = false)
	private String unitId;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mixtureId == null) ? 0 : mixtureId.hashCode());
		result = prime * result + ((unitId == null) ? 0 : unitId.hashCode());
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
		IngredientId other = (IngredientId) obj;
		if (mixtureId == null) {
			if (other.mixtureId != null) {
				return false;
			}
		} else if (!mixtureId.equals(other.mixtureId)) {
			return false;
		}
		if (unitId == null) {
			return other.unitId == null;
		}

		return unitId.equals(other.unitId);
	}
}
