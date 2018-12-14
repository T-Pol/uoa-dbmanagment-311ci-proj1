package di.uoa.dbmanagment.service.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import di.uoa.dbmanagment.model.TreeTrim;
import di.uoa.dbmanagment.repository.TreeTrimRepository;
import di.uoa.dbmanagment.service.TreeTrimService;


@Service
@Transactional
public class TreeTrimImpl implements TreeTrimService{

	@Autowired
	private TreeTrimRepository repo;
	@Override
	public TreeTrim save(TreeTrim tr) {
		// TODO Auto-generated method stub
		return repo.save(tr);
	}
	
	@Override
    public TreeTrim findbyid(String id) {
        return repo.findById(UUID.fromString(id)).get();
    }
	
	@Override
	public TreeTrim update(TreeTrim tr)
	{
		return repo.save(tr);
	}

	
	
}