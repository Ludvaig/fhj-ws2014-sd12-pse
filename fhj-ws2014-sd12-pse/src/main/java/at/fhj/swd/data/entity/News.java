package at.fhj.swd.data.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * 
 * 
 * @author Julia Viehberger, Gruppe 3
 *
 */
@Entity
public class News {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name= "title", unique= true)
	private String title;
	
	@Column(name = "content", length= 1000)
	private String content;
	
	
	// @Colum(name="bilder")
	// private String bild; ????
	
	
	@Column(name ="startdate")
	private Date startdate;

	@Column(name= "visible")
	private boolean visible;
	
    public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@ManyToMany(mappedBy="news")
    private List<User> users = new ArrayList<User>();

	
	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	
	public String getTitle(){
		
		return this.title;
	}
	
	public void setTitle (final String title) {
		
		this.title = title;
	}
	
	
	public String getContent(){
		
		return this.content;
	}
	
	public void setContent (final String content) {
		
		this.content = content;
	}
	
	public Date getStartdate(){
		
		return this.startdate;
	}
	
	public void setStartdate (final Date startdate) {
		
		this.startdate = startdate;
	}

	
	public List<User> getUser() {
		return this.users;
	}
	
	public void setUser(List<User> users){
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		News other = (News) obj;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", content=" + content
				+ ", startdate=" + startdate + "]";
	}
	
	

}
