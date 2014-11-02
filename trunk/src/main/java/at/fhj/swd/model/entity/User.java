package at.fhj.swd.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * user class from an central storage (for example active directory) user with
 * the same id are always equal
 * 
 * @author Jörg Huber, Group4
 */

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id = null;

	@Column(unique = true)
	private String username; // Don't remove the unique-property. All user stories regarding communities relay on the username being unique to work correctly!

	@Column
	private String password;

	@Column
	private String email;
	
	@Column(unique = true)
	private String token;

	@ManyToMany
	@JoinTable(name = "USER_HAS_COMMUNITY", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "COMMUNITY_ID"))
	private List<Community> communities;

	public User() {
		communities = new ArrayList<Community>();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public List<Community> getSubscribedCommunities() {
		return communities;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		if (id != null) {
			return id.equals(((User) that).id);
		}
		return super.equals(that);
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
				String.format(
						": Id='%s', username='%s', password='%s', email='%s'!",
						id, username, password, email));
		return sb.toString();
	}
}
