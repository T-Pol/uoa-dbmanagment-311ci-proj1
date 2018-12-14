package di.uoa.dbmanagment.service.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import di.uoa.dbmanagment.model.AbandonedVehicles;
import di.uoa.dbmanagment.repository.AbandonedVehiclesRepository;
import di.uoa.dbmanagment.service.AbandonedVehiclesService;


@Service
@Transactional
public class AbandonedVehiclesImpl implements AbandonedVehiclesService{

	@Autowired
	private AbandonedVehiclesRepository abrepo;
	
	@Autowired
    protected JdbcTemplate jtm;
	
	@Override
	public AbandonedVehicles save(AbandonedVehicles ab) {
		return abrepo.save(ab);
	}
	
	@Override
    public AbandonedVehicles findbyid(String id) {
        return abrepo.findById(UUID.fromString(id)).get();
    }

	@Override
	public AbandonedVehicles update(AbandonedVehicles ab) {
		return abrepo.save(ab);
	}
	

}
