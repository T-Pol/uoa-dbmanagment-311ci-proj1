package di.uoa.dbmanagment.service;

import di.uoa.dbmanagment.model.AbandonedVehicles;

public interface AbandonedVehiclesService {
	
	public AbandonedVehicles save(AbandonedVehicles ab);
	
	public AbandonedVehicles findbyid(String id);
	
	AbandonedVehicles update (AbandonedVehicles ab);

}
