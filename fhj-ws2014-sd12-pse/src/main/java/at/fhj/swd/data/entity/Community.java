package at.fhj.swd.data.entity;

import java.io.Serializable;
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
 * @author Group4, Group3
 * */

@Entity
public class Community implements Serializable {

	/**
	 * Serial version UID for community entity.
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name")
	private String name;
	
	@Column(name= "visible")
	private boolean visible = false;

	@ManyToMany(mappedBy = "communities")
	private Collection<User> users;

	@OneToMany(mappedBy = "community")
	private transient List<Topic> topics;

	public Community() {
		users = new ArrayList<User>();
		topics = new ArrayList<Topic>();
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String newName) {
		this.name = newName;
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Community other = (Community) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getName()).append(
				String.format(": Id='%s', name='%s'!", id, name));
		return sb.toString();
	}
}
