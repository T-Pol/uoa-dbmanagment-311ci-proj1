package di.uoa.dbmanagment.service.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import di.uoa.dbmanagment.model.PotHole;
import di.uoa.dbmanagment.repository.PotHoleRepository;
import di.uoa.dbmanagment.service.PotHoleService;


@Service
@Transactional
public class PotHoleImpl implements PotHoleService{

	@Autowired
	private PotHoleRepository repo;
	@Override
	public PotHole save(PotHole ph) {
		// TODO Auto-generated method stub
		return repo.save(ph);	}

	@Override
    public PotHole findbyid(String id) {
        return repo.findById(UUID.fromString(id)).get();
    }
	
	public PotHole update(PotHole ph)
	{
		return repo.save(ph);
	}
}