package di.uoa.dbmanagment.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity()
@Table(name = "status", schema = "public")
public class Status {
	
	@Id
    @Column(name = "status_id", unique = true, nullable = false, columnDefinition= "varchar(15)")
	private String statusid;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "status")
	private Set<Data> data;
	
	public String getStatusid() {
		return statusid;
	}

	public void setStatusid(String statusid) {
		this.statusid = statusid;
	}
	

}