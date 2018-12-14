package di.uoa.dbmanagment.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import di.uoa.dbmanagment.model.Data;


public interface DataService {
	
	public Data save(Data data);
	
	Data update (Data data);
	
	Page<Data> findAllPageable(Pageable pageable);
	
	public Data findbyid(String id);
	
	public List<Data> findbyzip(int zip);

	public Page<Data> findallbyzip(int zip, Pageable pageable);

	public String findlastservicereqid();

	List<Tuple> stored_function_1(LocalDateTime start, LocalDateTime end);

	List<Tuple> stored_function_2(String tr, LocalDateTime start, LocalDateTime end);

	List<Tuple> stored_function_3(LocalDateTime dt);

	Page<Data> findallbystreet(String street, Pageable pageable);

	List<Tuple> stored_function_4(LocalDateTime start, LocalDateTime end);

	List<Tuple> stored_function_6(LocalDateTime start, LocalDateTime end);

	List<String> stored_function_12(LocalDateTime dt);
	
	
}