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
@Table(name = "garbage_carts", schema = "public")
public class GarbageCart {

	@Id
	@Column(name="data_id")
	private UUID dataid;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Data data;
	
	@Column(name = "number_of_carts")
	private Integer numberofcarts;
	
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

	public Integer getNumberofcarts() {
		return numberofcarts;
	}

	public void setNumberofcarts(Integer numberofcarts) {
		this.numberofcarts = numberofcarts;
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
