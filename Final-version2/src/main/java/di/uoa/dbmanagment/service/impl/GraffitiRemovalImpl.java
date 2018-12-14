package di.uoa.dbmanagment.service.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import di.uoa.dbmanagment.model.GraffitiRemoval;
import di.uoa.dbmanagment.repository.GraffitiRemovalRepository;
import di.uoa.dbmanagment.service.GraffitiRemovalService;


@Service
@Transactional
public class GraffitiRemovalImpl implements GraffitiRemovalService {

	@Autowired
	private GraffitiRemovalRepository repo;
	
	@Override
	public GraffitiRemoval save(GraffitiRemoval gr) {
		// TODO Auto-generated method stub
		return repo.save(gr);
	}
	
	@Override
    public GraffitiRemoval findbyid(String id) {
        return repo.findById(UUID.fromString(id)).get();
    }
	
	@Override
	public GraffitiRemoval update(GraffitiRemoval g) {
		return repo.save(g);
	}

}
