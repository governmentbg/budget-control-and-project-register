package bg.infosys.daeu.db.entity.pub;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import bg.infosys.daeu.db.entity.security.User;

@Audited
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public static final String _id = "id";

    @Column(name = _content)
    private String content;
    public static final String _content = "content";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    public static final String _user = "user";

    @Column(name = _seen)
    private Boolean seen; 
    public static final String _seen = "seen";

    @Column(name = "received_timestamp")
    private Date receivedTimestamp; 
    public static final String _receivedTimestamp = "receivedTimestamp";
    
    @Column(name = "is_visible")
    private String isVisible;
    public static final String _isVisible = "isVisible";
    
    public Notification() {}
    
    public Notification(String content, User user) {
    	this.content = content;
    	this.user = user;
    	this.seen = false;
    	this.isVisible = "Y";
    	this.receivedTimestamp = new Date();
    }
    
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Boolean getSeen() {
		return seen;
	}
	
	public void setSeen(Boolean seen) {
		this.seen = seen;
	}
	
	public Date getReceivedTimestamp() {
		return receivedTimestamp;
	}
	
	public void setReceivedTimestamp(Date receivedTimestamp) {
		this.receivedTimestamp = receivedTimestamp;
	}

	public String getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(String isVisible) {
		this.isVisible = isVisible;
	}
}