package di.uoa.dbmanagment.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import di.uoa.dbmanagment.model.UserActions;



@Repository
public interface UserActionsRepository extends PagingAndSortingRepository <UserActions, Integer>{

}
