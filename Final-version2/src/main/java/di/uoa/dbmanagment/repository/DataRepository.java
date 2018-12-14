package di.uoa.dbmanagment.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Tuple;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import di.uoa.dbmanagment.model.Data;



@Repository
public interface DataRepository extends PagingAndSortingRepository <Data, UUID>{
	
	
	 @Query(value="SELECT * FROM data WHERE zip_code = :zip ",nativeQuery = true)
	    Page<Data> findBySomethingElseId(@Param("zip") int zip, Pageable pageable);
	 
	 @Query(value="SELECT * FROM data WHERE street_address like :street ",nativeQuery = true)
	    Page<Data> findBySomethingElseIdstreet(@Param("street") String street, Pageable pageable);
	
	 @Query(value="SELECT service_request_number FROM data order by creation_date desc limit 1; ",nativeQuery = true)
	 	String findlastservicereqid ();
	 
	 @Query(value="select * from stored_function_1(cast(:start as timestamp without time zone),cast(:end as timestamp without time zone))",nativeQuery=true)
	 	List<Tuple> stored_function_1(@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);

	 @Query(value="select * from stored_function_2(:typeofrequest, cast(:start as timestamp without time zone),cast(:end as timestamp without time zone))", nativeQuery=true)
	 	List<Tuple> stored_function_2(@Param("typeofrequest") String tr, @Param("start") LocalDateTime start,@Param("end") LocalDateTime end);

	 @Query(value="select * from stored_function_3(cast(:in_date as timestamp without time zone))", nativeQuery=true)
	 	List<Tuple> stored_function_3(@Param("in_date") LocalDateTime dt);
	 
	 @Query(value="select * from stored_function_4(cast(:start as timestamp without time zone),cast(:end as timestamp without time zone))",nativeQuery=true)
	 	List<Tuple> stored_function_4(@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);
	 
	 @Query(value="select * from stored_function_6(cast(:start as timestamp without time zone),cast(:end as timestamp without time zone))",nativeQuery=true)
	 	List<Tuple> stored_function_6(@Param("start") LocalDateTime start,@Param("end") LocalDateTime end);
	 
	 @Query(value="select * from stored_function_12(cast(:in_date as timestamp without time zone))", nativeQuery=true)
	 	List<String> stored_function_12(@Param("in_date") LocalDateTime dt);
}
