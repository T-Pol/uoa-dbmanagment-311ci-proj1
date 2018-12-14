package di.uoa.dbmanagment.service.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import di.uoa.dbmanagment.model.RodentBait;
import di.uoa.dbmanagment.repository.RodentBaitRepository;
import di.uoa.dbmanagment.service.RodentBaitService;


@Service
@Transactional
public class RodentBaitImpl implements RodentBaitService{

	@Autowired
	private RodentBaitRepository repo;
	@Override
	public RodentBait save(RodentBait rb) {
		// TODO Auto-generated method stub
		return repo.save(rb);	}
	
	@Override
    public RodentBait findbyid(String id) {
        return repo.findById(UUID.fromString(id)).get();
    }
	
	@Override
	public RodentBait update(RodentBait rb)
	{
		return repo.save(rb);
	}

}
