package di.uoa.dbmanagment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import di.uoa.dbmanagment.model.User;



@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);
}