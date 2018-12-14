package di.uoa.dbmanagment.service.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import di.uoa.dbmanagment.model.GarbageCart;
import di.uoa.dbmanagment.repository.GarbageCartRepository;
import di.uoa.dbmanagment.service.GarbageCartService;


@Service
@Transactional
public class GarbageCartImpl implements GarbageCartService{

	@Autowired
	private GarbageCartRepository repo;
	
	@Override
	public GarbageCart save(GarbageCart gc) {
		// TODO Auto-generated method stub
		return repo.save(gc);
	}
	
	@Override
    public GarbageCart findbyid(String id) {
        return repo.findById(UUID.fromString(id)).get();
    }
	
	@Override
	public GarbageCart update(GarbageCart gc) {
		return repo.save(gc);
	}

}
