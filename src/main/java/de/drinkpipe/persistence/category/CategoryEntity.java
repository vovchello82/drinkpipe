package de.drinkpipe.persistence.category;

import de.drinkpipe.persistence.entity.CategoryType;
import de.drinkpipe.persistence.entity.Flavour;
import de.drinkpipe.persistence.entity.PieceEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dp_categories")
@Getter
@Setter
public class CategoryEntity extends PieceEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "type", nullable = false)
	private CategoryType type = CategoryType.NONE;

	@Column(name = "raw_data")
	private String rawData;
/*
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "parent_id", nullable = true)
	private Set<Category> subcategories = new HashSet<Category>();
*/
	public CategoryEntity() {
		super("");
		this.rawData = "";
	}

	public CategoryEntity(String name) {
		super(name);
	}

	public CategoryEntity(CategoryType type, String name, Flavour flavour) {
		super(name);
		this.type = type;
	}


	@Override
	public String toString() {
		return "Category [id=" + getId() + ", name = "+ name + "type=" + type + ", rawData=" + rawData + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		//result = prime * result + ((subcategories == null) ? 0 : subcategories.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		CategoryEntity other = (CategoryEntity) obj;

		return type == other.type;
	}
}
