package de.drinkpipe.persistence.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class PieceEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	protected String id;
	@Column(name = "name", nullable = false)
	protected String name;

	protected PieceEntity() {
	}

	protected PieceEntity(String name) {
		this.name = name;
	}

	@PrePersist
	protected void prePersist() {
		this.id = UUID.randomUUID().toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof PieceEntity)) {
			return false;
		}
		PieceEntity that = (PieceEntity) o;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
