package di.uoa.dbmanagment.service.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import di.uoa.dbmanagment.model.SanitationViol;
import di.uoa.dbmanagment.repository.SanitationViolRepository;
import di.uoa.dbmanagment.service.SanitationViolService;


@Service
@Transactional
public class SanitationImpl implements SanitationViolService{

	@Autowired
	private SanitationViolRepository repo;
	@Override
	public SanitationViol save(SanitationViol sv) {
		// TODO Auto-generated method stub
		return repo.save(sv);
	}
	
	@Override
    public SanitationViol findbyid(String id) {
        return repo.findById(UUID.fromString(id)).get();
    }
	
	@Override
	public SanitationViol update(SanitationViol st)
	{
		return repo.save(st);
	}

}
