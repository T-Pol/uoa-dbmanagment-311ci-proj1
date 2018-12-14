package di.uoa.dbmanagment.service;

import di.uoa.dbmanagment.model.RodentBait;

public interface RodentBaitService {

	public RodentBait save(RodentBait rb);

	RodentBait findbyid(String id);

	RodentBait update(RodentBait rb);
}