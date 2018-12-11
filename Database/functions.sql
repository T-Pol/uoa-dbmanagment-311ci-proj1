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
