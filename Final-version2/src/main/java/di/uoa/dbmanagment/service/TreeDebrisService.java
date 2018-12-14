package di.uoa.dbmanagment.service;

import di.uoa.dbmanagment.model.TreeDebris;

public interface TreeDebrisService {

	public TreeDebris save(TreeDebris td);

	TreeDebris findbyid(String id);

	TreeDebris update(TreeDebris td);
}