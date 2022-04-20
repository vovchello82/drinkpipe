package de.drinkpipe.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="db_units")
public class UnitEntity extends PieceEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ean")
	private String ean;

	@Enumerated
	@Column(name = "flavour")
	private Flavour flavour;

	@Column(name = "flavour_raw_data")
	private String flavourRawData;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "category_id", nullable = false)
	private CategoryEntity category;

	@Transient
	private String rawData;

	public UnitEntity() {
		super();
	}

	public UnitEntity(String name, String ean, String title) {
		super(name);
		this.ean = ean;

		this.category = new CategoryEntity();
	}

	public UnitEntity(String name, String ean, CategoryEntity cat) {
		super(name);
		this.ean = ean;

		this.category = cat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((ean == null) ? 0 : ean.hashCode());
		result = prime * result + ((flavour == null) ? 0 : flavour.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		UnitEntity other = (UnitEntity) obj;

		if (category == null) {
			if (other.category != null) {
				return false;
			}
		} else if (!category.equals(other.category)) {
			return false;
		}
		if (ean == null) {
			if (other.ean != null) {
				return false;
			}
		} else if (!ean.equals(other.ean)) {
			return false;
		}
		return flavour == other.flavour;
	}

	@Override
	public String toString() {
		return "Product [ean=" + ean + ", name= ," + getName() + ", flavour=" + flavour + ", category="
				+ category + ", rawData=" + rawData + "]";
	}

}