package di.uoa.dbmanagment.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import di.uoa.dbmanagment.model.TreeDebris;


@Repository
public interface TreeDebrisRepository extends PagingAndSortingRepository <TreeDebris, UUID>{

}
