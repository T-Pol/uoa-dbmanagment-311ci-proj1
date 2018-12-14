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
@Table(name = "type_of_service_request", schema = "public")
public class Typeofrequest {
	

	@Id
    @Column(name = "idtype_of_service_request", unique = true, nullable = false, columnDefinition= "varchar(15)")
	private String typeofservicerequestid;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "typeofservicerequest")
	private Set<Data> data;
	
	
	public String getTypeofservicerequestid() {
		return typeofservicerequestid;
	}

	public void setTypeofservicerequestid(String typeofservicerequestid) {
		this.typeofservicerequestid = typeofservicerequestid;
	}

}
