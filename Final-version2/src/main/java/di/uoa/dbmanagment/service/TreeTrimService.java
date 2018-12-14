package di.uoa.dbmanagment.service;

import di.uoa.dbmanagment.model.TreeTrim;

public interface TreeTrimService {
	
	public TreeTrim save(TreeTrim tr);

	TreeTrim findbyid(String id);

	TreeTrim update(TreeTrim tr);

}