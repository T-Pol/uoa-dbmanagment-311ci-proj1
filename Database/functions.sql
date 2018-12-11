--stored function 1--
--Find the total requests per type that were created within a specified time range and sort them in a descending order.

CREATE OR REPLACE FUNCTION stored_function_1 
(
in in_start_date timestamp without time zone, 
in in_end_date   timestamp without time zone
)
  returns table(out_requesttype varchar, out_count int8)
AS $$
declare
  request record;
begin
  for request in 
					(
					SELECT type_of_service_request.idtype_of_service_request as reqtp,count(id) as cnt
					FROM data, type_of_service_request
					WHERE data.typeofservicerequest_idtype_of_service_request = type_of_service_request.idtype_of_service_request
					and data.creation_date>= in_start_date AND data.creation_date<= in_end_date
					group by type_of_service_request.idtype_of_service_request
					ORDER BY cnt desc
					)
				
  loop
    out_requesttype := request.reqtp;
    out_count := request.cnt;
    return next;
  end loop;
END; $$
LANGUAGE plpgsql;

--stored function 2--
--Find the total requests per day for a specific request type and time range.

CREATE OR REPLACE FUNCTION stored_function_2
  (in in_req_type    varchar,
   in in_start_date timestamp without time zone,
   in in_end_date   timestamp without time zone)
  returns table(out_req_day timestamp with time zone, out_cnt int8)
AS $$
declare
  request record;
begin
  for request in (					
					SELECT data.creation_date as crdate,count(id) as cnt
					FROM data, type_of_service_request
					WHERE data.typeofservicerequest_idtype_of_service_request = type_of_service_request.idtype_of_service_request
					and type_of_service_request.idtype_of_service_request=in_req_type
					and data.creation_date>= in_start_date AND data.creation_date<=in_end_date
					group by data.creation_date
				)
  loop
    out_req_day := request.crdate;
    out_cnt := request.cnt;
    return next;
  end loop;
END; $$
LANGUAGE plpgsql;

--stored function 3--
--Find the most common service request per zipcode for a specific day.

CREATE OR REPLACE FUNCTION stored_function_3
  (in in_date timestamp without time zone)
  returns table(out_zip_code int8, out_req_type varchar, out_cnt int8)
AS $$
declare
  request record;
begin
  for request in 					
					(
							select g1.zip as zip, g1.typeofreq as typeofreq, g2.mx_cnt as cnt
							from(
							SELECT data.zip_code as zip,type_of_service_request.idtype_of_service_request as typeofreq,count(id) as cnt
							FROM data, type_of_service_request
							WHERE data.typeofservicerequest_idtype_of_service_request = type_of_service_request.idtype_of_service_request
							and data.creation_date= in_date
							group by data.zip_code,type_of_service_request.idtype_of_service_request
							) as g1 
							inner join (
							select dt.zip as zip, MAX(cnt) as mx_cnt
							from(
							SELECT data.zip_code as zip,type_of_service_request.idtype_of_service_request as typeofreq,count(id) as cnt
							FROM data, type_of_service_request
							WHERE data.typeofservicerequest_idtype_of_service_request = type_of_service_request.idtype_of_service_request
							and data.creation_date= in_date
							group by data.zip_code,type_of_service_request.idtype_of_service_request) as dt
							group by dt.zip) as g2
							on g1.zip=g2.zip and g1.cnt=g2.mx_cnt
					)
  loop
    out_zip_code := request.zip;
    out_req_type := request.typeofreq;
    out_cnt := request.cnt;
    return next;
  end loop;
END; $$
LANGUAGE plpgsql;


--stored function 4--
--Find the average completion time per service request for a specific date range.

CREATE OR REPLACE FUNCTION stored_function_4
(
in in_start_date timestamp without time zone, 
in in_end_date timestamp without time zone
)
returns table(out_requesttype varchar, out_avg float8)
AS $$
declare
  request record;
begin
  for request in (
					select typeofservicerequest_idtype_of_service_request as reqtp, avg(extract(epoch from(completition_date - creation_date))) as avgd
					from data
					where completition_date is not null
					and creation_date between in_start_date and in_end_date
					group by typeofservicerequest_idtype_of_service_request
				)
  loop
    out_requesttype := request.reqtp;
    out_avg := request.avgd;
    return next;
  end loop;
END; $$
LANGUAGE plpgsql;


--stored function 5--
--Find the most common service request in a specified bounding box (as designated by GPS-coordinates) for a specific day.

CREATE OR REPLACE FUNCTION stored_function_5
(
in in_min_x float8,
in in_max_x float8,
in in_min_y float8,
in in_max_y float8,
in in_date  timestamp without time zone
)
returns table(out_requesttype varchar, out_cnt int8)
AS $$
declare
  request record;
begin
  for request in (
					
					select typeofservicerequest_idtype_of_service_request as req_type, count(*) as cnt 
					from data
					where creation_date= in_date
					and lat between in_min_y and in_max_y
					and lon between  in_min_x and  in_max_x
					group by req_type
					order by cnt desc 
					limit 1
				)	
  loop
    out_requesttype := request.req_type;
    out_cnt := request.cnt;
    return next;
  end loop;
END; $$
LANGUAGE plpgsql;


--stored function 6--
--Find the top-5 Special Service Areas (SSA) with regards to total number of requests per day for a specific date range (for service requests types that SSA is available: abandoned vehicles, garbage carts, graffiti removal, pot holes reported)

CREATE OR REPLACE FUNCTION stored_function_6
(
in in_start_date timestamp without time zone,
in in_end_date  timestamp without time zone
)
returns table(out_ssa int8, out_cnt int8)
AS $$
declare
  request record;
begin
  for request in (					
					select  ssa , count(distinct service_request_number) as cnt
					from data
					where data.ssa is not null
					and data.creation_date>=in_start_date AND data.creation_date<=in_end_date
					group by ssa
					order by cnt desc
					limit 5
				)
  loop
    out_ssa := request.ssa;
    out_cnt := request.cnt;
    return next;
  end loop;
END; $$
LANGUAGE plpgsql;


--stored function 7--
--Find the license plates (if any) that have been involved in abandoned vehicle complaints more than once.

CREATE OR REPLACE FUNCTION stored_function_7()
returns table(out_lic varchar, out_cnt int8)
AS $$
declare
  request record;
begin
  for request in (					
					select abv.license_plate as lic, count(distinct data.service_request_number) as cnt
					from abandoned_vehicles as abv ,data
					where data.id=abv.data_id
					and abv.license_plate is not null
					group by abv.license_plate
					having count(data.service_request_number)>1
					order by cnt desc
				)	
  loop
    out_lic := request.lic;
    out_cnt := request.cnt;
    return next;
  end loop;
END; $$
LANGUAGE plpgsql;


--stored function 8--
--Find the second most common color of vehicles involved in abandoned vehicle complaints.

CREATE OR REPLACE FUNCTION stored_function_8()
  returns table(out_color varchar, out_cnt int8)
AS $$
declare
  request record;
begin
  for request in (					
					select abv.vehicle_color as color, count(distinct data.service_request_number) as cnt
					from abandoned_vehicles as abv ,data
					where data.id=abv.data_id
					group by abv.vehicle_color
					order by cnt desc
					OFFSET  1 ROWS
					FETCH NEXT 1 ROWS ONLY
				)	
  loop
    out_color := request.color;
    out_cnt := request.cnt;
    return next;
  end loop;
END; $$
LANGUAGE plpgsql;


--stored function 9--
--Find the rodent baiting requests where the number of premises baited is less than a specified number.

CREATE OR REPLACE FUNCTION stored_function_9(in in_num int8)
returns table(out_reqnumber varchar, oun_numberbaited int8)
AS $$
declare
  request record;
begin
  for request in (					
					select distinct data.service_request_number as reqnumber, rb.number_of_premises_baited as prembait
					from data, rodent_baiting as rb
					where data.id=rb.data_id
					and rb.number_of_premises_baited< in_num
					order by rb.number_of_premises_baited desc
				)	
  loop
    out_reqnumber := request.reqnumber;
    oun_numberbaited := request.prembait;
    return next;
  end loop;
END; $$
LANGUAGE plpgsql;


--stored function 10--
--Same as the above (i.e., 9) for premises with garbage.

CREATE OR REPLACE FUNCTION stored_function_10(in in_num int8)
returns table(out_reqnumber varchar, oun_numbergarb int8)
AS $$
declare
  request record;
begin
  for request in (					
					select distinct data.service_request_number as reqnumber, rb.number_of_permises_garbage as permisegarb
					from data, rodent_baiting as rb
					where data.id=rb.data_id
					and rb.number_of_permises_garbage < in_num
					order by rb.number_of_permises_garbage desc
				)	
  loop
    out_reqnumber := request.reqnumber;
    oun_numbergarb := request.permisegarb;
    return next;
  end loop;
END; $$
LANGUAGE plpgsql;


--stored function 11--
--Same as the above (i.e., 10) for premises with rats.

CREATE OR REPLACE FUNCTION stored_function_11(in in_num int8)
returns table(out_reqnumber varchar, oun_numberrats int8)
AS $$
declare
  request record;
begin
  for request in (					
					select distinct data.service_request_number as reqnumber, rb.number_of_permises_rats as permiserats
					from data, rodent_baiting as rb
					where data.id=rb.data_id
					and rb.number_of_permises_rats < in_num
					order by rb.number_of_permises_rats desc
				)	
  loop
    out_reqnumber := request.reqnumber;
    oun_numberrats := request.permiserats;
    return next;
  end loop;
END; $$
LANGUAGE plpgsql;


--stored function 12--
--Find the police districts that have handled “pot holes” requests with more than one number of potholes on the same day that they also handled “rodent baiting” requests with more than one number of premises baited, for a specific day.

CREATE OR REPLACE FUNCTION stored_function_12
(in_timestamp timestamp without time zone)
returns table(out_police_district int8)
AS $$
declare
  request record;
begin
  for request in(
					select distinct police_district
					from data
					where data.id in 
					(
						select data_id
						from rodent_baiting
						where data_id in
						(
							select id
							from data 
							where completition_date =in_timestamp
							and typeofservicerequest_idtype_of_service_request = 'Rodent Baiting/Rat Complaint'
						)

					union all
					select pot.data_id
					from pot_holes_reported as pot
					where pot.number_pots_filled_block > 1
					and pot.data_id in
					(
					select id
					from data
					where completition_date =in_timestamp
					and typeofservicerequest_idtype_of_service_request =  'Pot Hole in Street'
					)
					)
					  				
				)
  loop
    out_police_district := request.police_district;
    return next;
  end loop;
END; $$
LANGUAGE plpgsql;
