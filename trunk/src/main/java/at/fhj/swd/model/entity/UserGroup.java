package at.fhj.swd.model.entity;

import java.io.Serializable;
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
import javax.persistence.Transient;

import at.fhj.swd.model.entity.helper.TableGeneratorHelper;

/**
 * Entity for user group.<br>
 * <p>
 * Users can be collected in groups to easy apply roles to users.
 * </p> 
 * 
 * @author Lukas Kranabetter
 */
@Entity
public class UserGroup implements Serializable
{
	public static final String TABLE_GEN_NAME = "userGroupTableGen";
	public static final String PK_COL_VAL = "userGroupPk";
	public static final String JOIN_TABLE_UserGroup_JaasRole = "UserGroup_JaasRole";
	public static final String JOIN_TABLE_UserGroup_JaasRole_JoinColumn = "usergroup_id";
	public static final String JOIN_TABLE_UserGroup_JaasRole_InverseJoinColumn = "jaasrole_id";
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator(name = TABLE_GEN_NAME, 
									table = TableGeneratorHelper.TABLE_NAME, 
									pkColumnName = TableGeneratorHelper.PK_COL_NAME,
									pkColumnValue = PK_COL_VAL,
									valueColumnName = TableGeneratorHelper.VALUE_COL_NAME, 
									allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_GEN_NAME)
	private Integer _id;
	
	@Column(unique = true)
	private String _name;
	
	@ManyToMany(targetEntity = UserRole.class, fetch = FetchType.LAZY)
	@JoinTable(name = JOIN_TABLE_UserGroup_JaasRole, 
							joinColumns = @JoinColumn(name = JOIN_TABLE_UserGroup_JaasRole_JoinColumn), 
							inverseJoinColumns = @JoinColumn(name = JOIN_TABLE_UserGroup_JaasRole_InverseJoinColumn))
	private Set<UserRole> _userRoles;
	
	public int getId()
	{
		return _id;
	}
	
	public void setId(int id)
	{
		_id = id;
	}
		
	public String getName()
	{
		return _name;
	}
	
	public void setName(String name)
	{
		_name = name;
	}
	
	public Set<UserRole> getUserRoles()
	{
		return _userRoles;
	}
		
	public void setUserRoles(Set<UserRole> userRoles)
	{
		_userRoles = userRoles;
	}
	
	@Override
	@Transient
	public String toString()
	{
		return "Name: " + _name;
	}
}