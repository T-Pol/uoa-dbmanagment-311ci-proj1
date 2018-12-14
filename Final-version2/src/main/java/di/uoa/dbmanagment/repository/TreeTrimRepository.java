package di.uoa.dbmanagment.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import di.uoa.dbmanagment.model.TreeTrim;


@Repository
public interface TreeTrimRepository extends PagingAndSortingRepository <TreeTrim, UUID>{

}
