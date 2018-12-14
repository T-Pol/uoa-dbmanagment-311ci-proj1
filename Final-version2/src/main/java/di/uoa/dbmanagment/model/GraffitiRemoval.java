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
@Table(name = "graffiti_removal", schema = "public")
public class GraffitiRemoval {
	
	@Id
	@Column(name="data_id")
	private UUID dataid;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Data data;
	
	@Column(name = "type_of_surface")
	private String typeofsurface;
	
	@Column(name = "graffiti_located")
	private String graffitilocated;

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

	public String getTypeofsurface() {
		return typeofsurface;
	}

	public void setTypeofsurface(String typeofsurface) {
		this.typeofsurface = typeofsurface;
	}

	public String getGraffitilocated() {
		return graffitilocated;
	}

	public void setGraffitilocated(String graffitilocated) {
		this.graffitilocated = graffitilocated;
	}
	
	

}
