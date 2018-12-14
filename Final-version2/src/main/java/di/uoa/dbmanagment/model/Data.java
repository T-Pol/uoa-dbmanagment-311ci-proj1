package di.uoa.dbmanagment.model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity()
@Table(name = "data", schema = "public")
public class Data {

	@Id
    @Column(name = "id", unique = true, nullable = false, columnDefinition= "uuid")
    private UUID id;
	
	@Column(name = "creation_date", columnDefinition= "TIMESTAMP WITH TIME ZONE")
	@NotNull
    private LocalDateTime creationDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stat_status_id", nullable = false)
	@NotNull
	private Status status;
	
	@Column(name = "completition_date", columnDefinition= "TIMESTAMP WITH TIME ZONE")
	
    private LocalDateTime completitionDate ;
	
	@Column(name = "service_request_number",length=15)
	@NotNull
	private String serviceRequestNumber;	
	
	
	
	//@Column(name = "typeofservicerequest",length=15)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "typeofservicerequest_idtype_of_service_request", nullable = false)
	@NotNull
	private Typeofrequest typeofservicerequest;	
	
	@Column(name = "street_address")
	
	private String streetAddress;
	
	@Column(name = "zip_code")
	
	private Integer zipCode;
	
	@Column(name = "x_coor",columnDefinition="Numeric")
	
	private Double xCoor;
	
	@Column(name = "y_coor",columnDefinition="Numeric")

	private Double yCoor;
	
	@Column(name = "ward",columnDefinition="smallint")
	
	private Short ward;
	
	@Column(name = "police_district",columnDefinition="smallint")
	
	private Short policeDistrict;
	
	@Column(name = "community_area",columnDefinition="smallint")
	
	private Short communityArea;

	@Column(name = "ssa",columnDefinition="smallint")
	
	private Short ssa;
	
	@Column(name = "lat",columnDefinition="Numeric")
	
	private Double lat;
	
	@Column(name = "lon",columnDefinition="Numeric")
	
	private Double lon;
	
	@Column(name = "location")
	
	private String location;
	
	
	//@OneToOne(mappedBy="data")
	//private AbandonedVehicles av;
	
	
	public Data() {}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getCompletitionDate() {
		return completitionDate;
	}

	public void setCompletitionDate(LocalDateTime completitionDate) {
		this.completitionDate = completitionDate;
	}

	public String getServiceRequestNumber() {
		return serviceRequestNumber;
	}

	public void setServiceRequestNumber(String serviceRequestNumber) {
		this.serviceRequestNumber = serviceRequestNumber;
	}

	public Typeofrequest getTypeofservicerequest() {
		return typeofservicerequest;
	}

	public void setTypeofservicerequest(Typeofrequest typeofservicerequest) {
		this.typeofservicerequest = typeofservicerequest;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public Double getxCoor() {
		return xCoor;
	}

	public void setxCoor(Double xCoor) {
		
		this.xCoor = xCoor;
	}

	public Double getyCoor() {
		return yCoor;
	}

	public void setyCoor(Double yCoor) {
		this.yCoor = yCoor;
	}

	public Short getWard() {
		return ward;
	}

	public void setWard(Short ward) {
		this.ward = ward;
	}

	public Short getPoliceDistrict() {
		return policeDistrict;
	}

	public void setPoliceDistrict(Short policeDistrict) {
		this.policeDistrict = policeDistrict;
	}

	public Short getCommunityArea() {
		return communityArea;
	}

	public void setCommunityArea(Short communityArea) {
		this.communityArea = communityArea;
	}

	public Short getSsa() {
		return ssa;
	}

	public void setSsa(Short ssa) {
		this.ssa = ssa;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
	


}
