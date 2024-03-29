package at.fhj.swd.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * DB-Entity for Posts.
 * 
 * @author Group4
 * */

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private long id;

	@Column
	private String title;

	@Column
	private String text;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TOPIC_ID")
	private Topic topic;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String newTitle) {
		this.title = newTitle;
	}

	public String getText() {
		return text;
	}

	public void setText(String newText) {
		this.text = newText;
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
		
		Long boxedId = Long.valueOf(id);
		return boxedId.equals(((Post) obj).id);
	}
	
	@Override
	public int hashCode() {
		Long boxedId = Long.valueOf(id);
		return boxedId.hashCode();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getName()).append(
				String.format(": Id='%s', title='%s', text='%s'!", id, title,
						text));
		return sb.toString();
	}
}
