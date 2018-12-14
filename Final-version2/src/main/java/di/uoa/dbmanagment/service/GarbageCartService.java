package di.uoa.dbmanagment.service;

import di.uoa.dbmanagment.model.GarbageCart;

public interface GarbageCartService {
	
	public GarbageCart save(GarbageCart gc);

	GarbageCart findbyid(String id);

	GarbageCart update(GarbageCart gc);

}
