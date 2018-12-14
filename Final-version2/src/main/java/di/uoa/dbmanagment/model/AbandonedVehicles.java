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
@Table(name = "abandoned_vehicles", schema = "public")
public class AbandonedVehicles {
	
	@Id
	@Column(name="data_id")
	private UUID dataid;
	
	@OneToOne(fetch = FetchType.LAZY)
	@MapsId
	private Data data;
	
	
	@Column(name = "license_plate")
	private String licenseplate;
	
	@Column(name = "vehicle_make")
	private String vehicle_make;
	
	@Column(name = "vehicle_color",length=30)
	private String vehicle_color;
	
	@Column(name = "current_activity")
	private String currentactivity;
	
	@Column(name="most_recent_action")
	private String mostrecentaction;
	
	@Column(name = "days_reported")
	private Integer daysreported;

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

	public String getLicenseplate() {
		return licenseplate;
	}

	public void setLicenseplate(String licenseplate) {
		this.licenseplate = licenseplate;
	}

	public String getVehicle_make() {
		return vehicle_make;
	}

	public void setVehicle_make(String vehicle_make) {
		this.vehicle_make = vehicle_make;
	}

	public String getVehicle_color() {
		return vehicle_color;
	}

	public void setVehicle_color(String vehicle_color) {
		this.vehicle_color = vehicle_color;
	}

	public String getCurrentactivity() {
		return currentactivity;
	}

	public void setCurrentactivity(String currentactivity) {
		this.currentactivity = currentactivity;
	}

	public Integer getDaysreported() {
		return daysreported;
	}

	public void setDaysreported(Integer daysreported) {
		this.daysreported = daysreported;
	}

	public String getMostrecentaction() {
		return mostrecentaction;
	}

	public void setMostrecentaction(String mostrecentaction) {
		this.mostrecentaction = mostrecentaction;
	}
	
	


}
