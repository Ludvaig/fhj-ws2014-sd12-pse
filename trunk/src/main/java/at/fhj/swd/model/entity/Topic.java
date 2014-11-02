package at.fhj.swd.model.entity;

import java.util.ArrayList;
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

/**
 * DB-Entity for Topics.
 * 
 * @author Group4
 * */

@Entity
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id = null;

	@Column
	private String name;

	@Column
	private String text;

	@Column
	private Long communityId;

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

	public Long getCommunityId() {
		return communityId;
	}

	public void setCommunityId(Long newCommunityId) {
		this.communityId = newCommunityId;
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
						": Id='%s', name='%s', text='%s', communityId='%s'!",
						id, name, text, communityId));
		return sb.toString();
	}
}
