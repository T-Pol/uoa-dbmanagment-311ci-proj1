package di.uoa.dbmanagment.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity()
@Table(name = "tree_derbis", schema = "public")
public class TreeDebris {

	@Id
	@Column(name="data_id")
	private UUID dataid;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Data data;
	
	@Column(name = "derbis_located")
	private String debrislocated;
	
	@Column(name = "current_activity")
	private String currentactivity;
	
	@Column(name="most_recent_action")
	private String mostrecentaction;

	public UUID getDataid() {
		return dataid;
	}

	public void setDataid(UUID dataid) {
		this.dataid = dataid;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public String getDebrislocated() {
		return debrislocated;
	}

	public void setDebrislocated(String debrislocated) {
		this.debrislocated = debrislocated;
	}

	public String getCurrentactivity() {
		return currentactivity;
	}

	public void setCurrentactivity(String currentactivity) {
		this.currentactivity = currentactivity;
	}

	public String getMostrecentaction() {
		return mostrecentaction;
	}

	public void setMostrecentaction(String mostrecentaction) {
		this.mostrecentaction = mostrecentaction;
	}
	
}