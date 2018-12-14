package di.uoa.dbmanagment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity()
@Table(name = "useractions", schema = "public")
public class UserActions {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "action_id", unique = true, nullable = false)
    private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@NotNull
	private User user;
	
	@Column(name = "action")
	private String action;

	@Column(name = "action_details")
	private String actiondetails;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActiondetails() {
		return actiondetails;
	}

	public void setActiondetails(String actiondetails) {
		this.actiondetails = actiondetails;
	}
	
	
	

}
