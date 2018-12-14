package di.uoa.dbmanagment.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import di.uoa.dbmanagment.model.AbandonedVehicles;


@Repository
public interface AbandonedVehiclesRepository extends PagingAndSortingRepository <AbandonedVehicles, UUID>{
	

}
