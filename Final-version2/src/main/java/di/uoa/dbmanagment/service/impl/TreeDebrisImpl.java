package di.uoa.dbmanagment.service.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import di.uoa.dbmanagment.model.TreeDebris;
import di.uoa.dbmanagment.repository.TreeDebrisRepository;
import di.uoa.dbmanagment.service.TreeDebrisService;


@Service
@Transactional
public class TreeDebrisImpl implements TreeDebrisService{

	@Autowired
	private TreeDebrisRepository repo;
	@Override
	public TreeDebris save(TreeDebris td) {
		// TODO Auto-generated method stub
		return repo.save(td);
	}
	
	@Override
    public TreeDebris findbyid(String id) {
        return repo.findById(UUID.fromString(id)).get();
    }

	@Override
	public TreeDebris update(TreeDebris td)
	{
		return repo.save(td);
	}
}
