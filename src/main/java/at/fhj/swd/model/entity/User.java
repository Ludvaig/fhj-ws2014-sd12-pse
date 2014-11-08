package at.fhj.swd.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.TableGenerator;

import at.fhj.swd.model.entity.helper.TableGeneratorHelper;

/**
 * <p>
 * user class from an central storage (for example active directory) user with
 * the same id are always equal
 * </p>
 * 
 * @author Jörg Huber, Group4, Lukas Kranabetter, Sandra Marcher
 */

@Entity
public class User {

	public static final String TABLE_GEN_NAME = "userTableGen";
	public static final String PK_COL_VALUE = "userPk";
	
	public static final String JOIN_TABLE_User_UserGroup = "User_UserGroup";
  public static final String JOIN_TABLE_User_UserGroup_JoinColumn = "user_id";
  public static final String JOIN_TABLE_User_UserGroup_InverseJoinColumn = "usergroup_id";
	
	@Id
	@TableGenerator(name = TABLE_GEN_NAME,
   								table = TableGeneratorHelper.TABLE_NAME,
   								pkColumnName = TableGeneratorHelper.PK_COL_NAME,
   								pkColumnValue = PK_COL_VALUE,
 									valueColumnName = TableGeneratorHelper.VALUE_COL_NAME,
									allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_GEN_NAME)
	//@Column(name = "id", updatable = false, nullable = false)
	private Long id = null;

	@Column(unique = true)
	private String username; 

	@Column
	private String password;
	
	@Column(nullable = false, length = 256)
	private String hashedPassword;

	@Column
	private String email;
	
	@Column
	private String telephone;

	@Column(unique = true)
	private String token; // Don't remove the unique-property. All user stories regarding communities relay on the username being unique to work correctly!

	@ManyToMany
	@JoinTable(name = "USER_HAS_COMMUNITY", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "COMMUNITY_ID"))
	private List<Community> communities;
	
	@ManyToMany(targetEntity = UserGroup.class, fetch = FetchType.LAZY)
  @JoinTable(name = JOIN_TABLE_User_UserGroup,
             joinColumns = @JoinColumn(name=JOIN_TABLE_User_UserGroup_JoinColumn),
             inverseJoinColumns = @JoinColumn(name=JOIN_TABLE_User_UserGroup_InverseJoinColumn))
	private Set<UserGroup> _userGroups;

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
	
	public String getHashedPassword() {
    return hashedPassword;
	}
	
	public void setHashedPassword(String hashedPassword) {
    this.hashedPassword = hashedPassword;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}
	
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(final String telephone) {
		if (telephone.matches("(\\+?)(\\d{3,20})")){
			this.telephone = telephone;
		}
		else {
			throw new IllegalArgumentException("Invalid telephone number");
		}
		  
	}

	public List<Community> getSubscribedCommunities() {
		return communities;
	}
	
	public void setSubscribedCommunities(List<Community> communities){
		this.communities = communities;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public Set<UserGroup> getUserGroups() 
	{
    return _userGroups;
	}

	public void setUserGroups(Set<UserGroup> userGroups) 
	{
    _userGroups = userGroups;
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
