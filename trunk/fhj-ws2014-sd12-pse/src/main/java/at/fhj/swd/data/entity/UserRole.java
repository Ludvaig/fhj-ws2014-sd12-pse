package at.fhj.swd.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import at.fhj.swd.data.entity.helper.TableGeneratorHelper;

/**
 * Entity for user roles.<br>
 * <p>
 * A user role defines access rights inside the application and for the wildfly server realms.
 * </p> 
 * 
 * @author Lukas Kranabetter
 */
@Entity
public class UserRole implements Serializable
{
	public static final String TABLE_GEN_NAME = "userRoleTableGen";
	public static final String PK_COL_VALUE = "userRolePk";
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator(name = TABLE_GEN_NAME, 
									table = TableGeneratorHelper.TABLE_NAME, 
									pkColumnName = TableGeneratorHelper.PK_COL_NAME, 
									pkColumnValue = PK_COL_VALUE, 
									valueColumnName = TableGeneratorHelper.VALUE_COL_NAME, 
									allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = TABLE_GEN_NAME)
	private int _id;
	
	@Column(unique = true)
	private String _name;
	
	@Column(nullable = false)
	private String _displayName;
		
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
	
	public String getDisplayName()
	{
		return _displayName;
	}
	
	public void setDisplayName(String displayName)
	{
		_displayName = displayName;
	}
	
	@Override
	@Transient
	public String toString()
	{
		return "Name: " + _displayName + ", Role: " + _name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_displayName == null) ? 0 : _displayName.hashCode());
		result = prime * result + _id;
		result = prime * result + ((_name == null) ? 0 : _name.hashCode());
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
		UserRole other = (UserRole) obj;
		if (_displayName == null) {
			if (other._displayName != null)
				return false;
		} else if (!_displayName.equals(other._displayName))
			return false;
		if (_id != other._id)
			return false;
		if (_name == null) {
			if (other._name != null)
				return false;
		} else if (!_name.equals(other._name))
			return false;
		return true;
	}
	
	
}