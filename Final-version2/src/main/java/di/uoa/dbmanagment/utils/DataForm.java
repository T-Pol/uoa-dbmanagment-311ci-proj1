package di.uoa.dbmanagment.utils;

public class DataForm {
	
	private int userId;
	
	private String dataid;
	
	private String servreqnum;
	
	private String typeofrequest;
	
	private String state;
	
	private String creationdate;
	
	private String streetaddress;

	private Integer zipcode;
	
	private Double xcoor;
	
	private Double ycoor;
	
	private Short ward;

	private Short policedistrict;
	
	private Short communityarea;
	
	private Short ssa;
	
	private Double lat;
	
	private Double lon;
	
	private String locationdetails;
	
	private String licenseplate;
	
	private String vehiclemake;
	
	private String vehiclecolor;
	
	private String currentactivityab;
	
	private String mostrecentactivityab;
	
	private Integer daysreportedab;
	
	private Integer numberofcarts;
	
	private String currentactivitygb;
	
	private String mostrecentactiongb;
	
	private String typeofsurface;
	
	private String graffitilocation;
	
	private Integer numberofpotholes;
	
	private String currentactivitypots;
	
	private String mostrecentactionpots;

	private Integer numberofpermisesbaited;
	private Integer numberofpermisesgarbage;
	private Integer numberofpermisesrats;
	private String currentactivitybait;
	private String mostrecentactionbait;
	
	private String natureofviolation;
	
	private String locationofdebis;
	private String currentactivitydebris;
	private String mostrecentactiondebris;
	
	private String locationoftrims;
	
	public DataForm() {
	}

	public String getTypeofrequest() {
		return typeofrequest;
	}

	public void setTypeofrequest(String typeofrequest) {
		this.typeofrequest = typeofrequest;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getStreetaddress() {
		return streetaddress;
	}

	public void setStreetaddress(String streetaddress) {
		this.streetaddress = streetaddress;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public Double getXcoor() {
		return xcoor;
	}

	public void setXcoor(Double xcoor) {
		this.xcoor = xcoor;
	}

	public Double getYcoor() {
		return ycoor;
	}

	public void setYcoor(Double ycoor) {
		this.ycoor = ycoor;
	}

	public Short getWard() {
		return ward;
	}

	public void setWard(Short ward) {
		this.ward = ward;
	}

	public Short getPolicedistrict() {
		return policedistrict;
	}

	public void setPolicedistrict(Short policedistrict) {
		this.policedistrict = policedistrict;
	}

	public Short getCommunityarea() {
		return communityarea;
	}

	public void setCommunityarea(Short communityarea) {
		this.communityarea = communityarea;
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

	public String getLocationdetails() {
		return locationdetails;
	}

	public void setLocationdetails(String locationdetails) {
		this.locationdetails = locationdetails;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLicenseplate() {
		return licenseplate;
	}

	public void setLicenseplate(String licenseplate) {
		this.licenseplate = licenseplate;
	}

	public String getVehiclemake() {
		return vehiclemake;
	}

	public void setVehiclemake(String vehiclemake) {
		this.vehiclemake = vehiclemake;
	}

	public String getVehiclecolor() {
		return vehiclecolor;
	}

	public void setVehiclecolor(String vehiclecolor) {
		this.vehiclecolor = vehiclecolor;
	}

	public String getCurrentactivity() {
		return currentactivityab;
	}

	public void setCurrentactivity(String currentactivity) {
		this.currentactivityab = currentactivity;
	}

	public String getMostrecentactivity() {
		return mostrecentactivityab;
	}

	public void setMostrecentactivity(String mostrecentactivity) {
		this.mostrecentactivityab = mostrecentactivity;
	}


	public String getCurrentactivityab() {
		return currentactivityab;
	}

	public void setCurrentactivityab(String currentactivityab) {
		this.currentactivityab = currentactivityab;
	}

	public String getMostrecentactivityab() {
		return mostrecentactivityab;
	}

	public void setMostrecentactivityab(String mostrecentactivityab) {
		this.mostrecentactivityab = mostrecentactivityab;
	}

	public Integer getDaysreportedab() {
		return daysreportedab;
	}

	public void setDaysreportedab(Integer daysreportedab) {
		this.daysreportedab = daysreportedab;
	}

	public Integer getNumberofcarts() {
		return numberofcarts;
	}

	public void setNumberofcarts(Integer numberofcarts) {
		this.numberofcarts = numberofcarts;
	}

	public String getCurrentactivitygb() {
		return currentactivitygb;
	}

	public void setCurrentactivitygb(String currentactivitygb) {
		this.currentactivitygb = currentactivitygb;
	}

	public String getMostrecentactiongb() {
		return mostrecentactiongb;
	}

	public void setMostrecentactiongb(String mostrecentactiongb) {
		this.mostrecentactiongb = mostrecentactiongb;
	}

	public String getTypeofsurface() {
		return typeofsurface;
	}

	public void setTypeofsurface(String typeofsurface) {
		this.typeofsurface = typeofsurface;
	}

	public String getGraffitilocation() {
		return graffitilocation;
	}

	public void setGraffitilocation(String graffitilocation) {
		this.graffitilocation = graffitilocation;
	}

	public Integer getNumberofpotholes() {
		return numberofpotholes;
	}

	public void setNumberofpotholes(Integer numberofpotholes) {
		this.numberofpotholes = numberofpotholes;
	}

	public String getCurrentactivitypots() {
		return currentactivitypots;
	}

	public void setCurrentactivitypots(String currentactivitypots) {
		this.currentactivitypots = currentactivitypots;
	}

	public String getMostrecentactionpots() {
		return mostrecentactionpots;
	}

	public void setMostrecentactionpots(String mostrecentactionpots) {
		this.mostrecentactionpots = mostrecentactionpots;
	}

	public Integer getNumberofpermisesbaited() {
		return numberofpermisesbaited;
	}

	public void setNumberofpermisesbaited(Integer numberofpermisesbaited) {
		this.numberofpermisesbaited = numberofpermisesbaited;
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

	public String getCurrentactivitybait() {
		return currentactivitybait;
	}

	public void setCurrentactivitybait(String currentactivitybait) {
		this.currentactivitybait = currentactivitybait;
	}

	public String getMostrecentactionbait() {
		return mostrecentactionbait;
	}

	public void setMostrecentactionbait(String mostrecentactionbait) {
		this.mostrecentactionbait = mostrecentactionbait;
	}

	public String getNatureofviolation() {
		return natureofviolation;
	}

	public void setNatureofviolation(String natureofviolation) {
		this.natureofviolation = natureofviolation;
	}

	public String getLocationofdebis() {
		return locationofdebis;
	}

	public void setLocationofdebis(String locationofdebis) {
		this.locationofdebis = locationofdebis;
	}

	public String getCurrentactivitydebris() {
		return currentactivitydebris;
	}

	public void setCurrentactivitydebris(String currentactivitydebris) {
		this.currentactivitydebris = currentactivitydebris;
	}

	public String getMostrecentactiondebris() {
		return mostrecentactiondebris;
	}

	public void setMostrecentactiondebris(String mostrecentactiondebris) {
		this.mostrecentactiondebris = mostrecentactiondebris;
	}

	public String getLocationoftrims() {
		return locationoftrims;
	}

	public void setLocationoftrims(String locationoftrims) {
		this.locationoftrims = locationoftrims;
	}

	public String getDataid() {
		return dataid;
	}

	public void setDataid(String dataid) {
		this.dataid = dataid;
	}

	public String getServreqnum() {
		return servreqnum;
	}

	public void setServreqnum(String servreqnum) {
		this.servreqnum = servreqnum;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	

}
