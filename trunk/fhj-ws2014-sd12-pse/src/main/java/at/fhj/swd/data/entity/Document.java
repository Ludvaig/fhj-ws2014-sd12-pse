package at.fhj.swd.data.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity for a Document.
 * 
 * @author Group1
 * */
@Entity
public class Document {
	@Id
	private String name;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
