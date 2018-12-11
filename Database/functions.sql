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
