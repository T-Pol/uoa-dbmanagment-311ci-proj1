package di.uoa.dbmanagment.service;

import di.uoa.dbmanagment.model.PotHole;

public interface PotHoleService {
	
	public PotHole save(PotHole ph);

	PotHole findbyid(String id);
	
	PotHole update(PotHole ph);

}
