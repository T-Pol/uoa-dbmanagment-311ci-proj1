package di.uoa.dbmanagment.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Tuple;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import di.uoa.dbmanagment.model.Data;
import di.uoa.dbmanagment.repository.DataRepository;
import di.uoa.dbmanagment.service.DataService;



@Service
@Transactional
public class DataServiceImpl implements DataService {
	
	@Autowired
    protected JdbcTemplate jtm;
	
	@Autowired
	private DataSource datasource;
	
	@Autowired
	private DataRepository datarepository;
	
	@Override
	public Data save(Data data) {
		return datarepository.save(data);
	}
	
	
	
	@Override
	public Page<Data> findAllPageable(Pageable pageable) {
			return datarepository.findAll(pageable);
	}
	
	@Override
	public Page<Data> findallbyzip(int zip, Pageable pageable)
	{
		return datarepository.findBySomethingElseId(zip, pageable);
	}
	
	@Override
	public Page<Data> findallbystreet(String street, Pageable pageable)
	{
		return datarepository.findBySomethingElseIdstreet(street, pageable);
	}
	
	@Override
	public String findlastservicereqid()
	{
		return datarepository.findlastservicereqid();
	}

	
    public Data findbyid(String id) {

        //String sql = "SELECT * FROM data WHERE id = cast(? as uuid)";
       // Object[] param = new Object[] {id};

       // Data dt = (Data) jtm.queryForObject(sql, param, new BeanPropertyRowMapper(Data.class));
    	
    	
        return datarepository.findById(UUID.fromString(id)).get();
    }
    
    
	/*
	@Override
    public Task findById(int id) {
        return taskRepository.findById(id).get();
    }
	*/
    
	public List<Data> findbyzip(int zip)
	{
		String sql = "SELECT * FROM data WHERE zip_code = cast(? as Int)";
		List<Data> data = jtm.query(sql, new BeanPropertyRowMapper(Data.class));
		
		return data;
		
	}
	
	@Override
	public List<Tuple> stored_function_1(LocalDateTime start, LocalDateTime end)
	{
		return datarepository.stored_function_1(start, end);
	}
	
	@Override
	public List<Tuple> stored_function_4(LocalDateTime start, LocalDateTime end)
	{
		return datarepository.stored_function_4(start, end);
	}
	
	@Override
	public List<Tuple> stored_function_2(String tr, LocalDateTime start, LocalDateTime end)
	{
		return datarepository.stored_function_2(tr, start, end);
	}
	
	@Override
	public List<Tuple> stored_function_3(LocalDateTime dt)
	{
		return datarepository.stored_function_3(dt);
	}
	
	
	 public DataServiceImpl(DataRepository datarepository) {
	        this.datarepository = datarepository;
	    }



	@Override
	public Data update(Data data) {
		return datarepository.save(data);
	}



	@Override
	public List<Tuple> stored_function_6(LocalDateTime start, LocalDateTime end) {
		return datarepository.stored_function_6(start, end);
	}

	@Override
	public List<String> stored_function_12(LocalDateTime dt)
	{
		return datarepository.stored_function_12(dt);
	}
	
	
}
