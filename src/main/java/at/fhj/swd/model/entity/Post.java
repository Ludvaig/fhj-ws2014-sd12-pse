package at.fhj.swd.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id = null;
	
	@Column
	private String title;
	
	@Column
	private String text;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TOPIC_ID")
	private Topic topic;
	
	public Post() {
		// empty on purpose!
	}
	
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
		if (id != null) {
			return id.equals(((Post) obj).id);
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
				String.format(": Id='%s', title='%s', text='%s'!", id, title, text));
		return sb.toString();
	}
}
