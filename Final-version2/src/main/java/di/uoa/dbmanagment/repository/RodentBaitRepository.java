package di.uoa.dbmanagment.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import di.uoa.dbmanagment.model.RodentBait;


@Repository
public interface RodentBaitRepository extends PagingAndSortingRepository <RodentBait, UUID>{

}
