package di.uoa.dbmanagment.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import di.uoa.dbmanagment.model.GarbageCart;


@Repository
public interface GarbageCartRepository extends PagingAndSortingRepository <GarbageCart, UUID>{

}
