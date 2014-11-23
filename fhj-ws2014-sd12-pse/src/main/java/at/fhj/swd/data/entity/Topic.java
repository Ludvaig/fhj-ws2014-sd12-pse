package at.fhj.swd.data.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.TableGenerator;

import at.fhj.swd.data.entity.helper.TableGeneratorHelper;

/**
 * DB-Entity for Topics.
 * 
 * @author Group4
 * */

@Entity
public class Topic {

//	@Id
//	@GeneratedValue(strategy = GenerationType.TABLE)
//	@Column(name = "id", updatable = false, nullable = false)
//	private Long id = null;

	public static final String TABLE_GEN_NAME = "topicTableGen";
	public static final String PK_COL_VALUE = "topicPk";
	
	
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
	
	@Column
	private Date date;
	
	@Column
	private String name;

	@Column
	private String text;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMMUNITY_ID")
	private Community community;

	@OneToMany(mappedBy = "topic")
	private List<Post> posts;

	public Topic() {
		this.posts = new ArrayList<Post>();
	}

	public String getName() {
		return name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public String getText() {
		return text;
	}

	public void setText(String newText) {
		this.text = newText;
	}
	
	public Community getCommunity() {
	return community;
	}
	public void setCommunity(Community community) {
	this.community = community;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@PrePersist
	public void onCreate(){
		this.date = new Date();
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
			return id.equals(((Topic) obj).id);
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
				String.format(
						": Id='%s', name='%s', text='%s' !",
						id, name, text));
		return sb.toString();
	}
}
