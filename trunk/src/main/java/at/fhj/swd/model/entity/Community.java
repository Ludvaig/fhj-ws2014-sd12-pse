package at.fhj.swd.model.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * DB-Entity for Communities.
 * 
 * @author Group4
 * */

@Entity
public class Community {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id = null;

	private String name;
	
	
	// Gruppe 3: brauchen das für US25
	@Column(name= "visible")
	private boolean visible = false;

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@ManyToMany(mappedBy = "communities")
	private Collection<User> users;

	@OneToMany(mappedBy = "community")
	private List<Topic> topics;

	public Community() {
		users = new ArrayList<User>();
		topics = new ArrayList<Topic>();
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		this.name = newName;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		if (id != null) {
			return id.equals(((Community) obj).id);
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		if (id != null) {
			return id.hashCode();
		}
		return super.hashCode();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getName()).append(
				String.format(": Id='%s', name='%s'!", id, name));
		return sb.toString();
	}
}