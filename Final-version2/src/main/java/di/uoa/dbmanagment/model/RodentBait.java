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
@Table(name = "rodent_baiting", schema = "public")
public class RodentBait {
	
	@Id
	@Column(name="data_id")
	private UUID dataid;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Data data;
	
	@Column(name = "number_of_premises_baited")
	private Integer numberofpremisesbaited;
	
	@Column(name = "number_of_permises_garbage")
	private Integer numberofpermisesgarbage;
	
	@Column(name = "number_of_permises_rats")
	private Integer numberofpermisesrats;
	
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

	public Integer getNumberofpremisesbaited() {
		return numberofpremisesbaited;
	}

	public void setNumberofpremisesbaited(Integer numberofpremisesbaited) {
		this.numberofpremisesbaited = numberofpremisesbaited;
	}

	public Integer getNumberofpermisesgarbage() {
		return numberofpermisesgarbage;
	}

	public void setNumberofpermisesgarbage(Integer numberofpermisesgarbage) {
		this.numberofpermisesgarbage = numberofpermisesgarbage;
	}

	public Integer getNumberofpermisesrats() {
		return numberofpermisesrats;
	}

	public void setNumberofpermisesrats(Integer numberofpermisesrats) {
		this.numberofpermisesrats = numberofpermisesrats;
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