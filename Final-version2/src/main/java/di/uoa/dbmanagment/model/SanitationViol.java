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
@Table(name = "sanitation_code_complaints", schema = "public")
public class SanitationViol {
	
	@Id
	@Column(name="data_id")
	private UUID dataid;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Data data;
	
	@Column(name = "nature_of_code_violation")
	private String natureofcodeviolation;

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

	public String getNatureofcodeviolation() {
		return natureofcodeviolation;
	}

	public void setNatureofcodeviolation(String natureofcodeviolation) {
		this.natureofcodeviolation = natureofcodeviolation;
	}

	
}