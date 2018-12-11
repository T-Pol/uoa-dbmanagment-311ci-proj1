--1--
--Find the total requests per type that were created within a specified time range and sort them in a descending order.

SELECT type_of_service_request.idtype_of_service_request,count(id) as cnt
FROM data, type_of_service_request
WHERE data.typeofservicerequest_idtype_of_service_request = type_of_service_request.idtype_of_service_request
and data.creation_date>='2018-11-05 00:00:00+02' AND data.creation_date<='2018-11-07 00:00:00+02'
group by type_of_service_request.idtype_of_service_request
ORDER BY cnt desc;

--2--
--Find the total requests per day for a specific request type and time range.

SELECT data.creation_date,count(id) as cnt
FROM data, type_of_service_request
WHERE data.typeofservicerequest_idtype_of_service_request = type_of_service_request.idtype_of_service_request
and type_of_service_request.idtype_of_service_request='Graffiti Removal'
and data.creation_date>='2018-11-04 00:00:00+02' AND data.creation_date<='2018-11-07 00:00:00+02'
group by data.creation_date;

--3--
--Find the most common service request per zipcode for a specific day.

select g1.zip, g1.typeofreq, g2.mx_cnt
from(
SELECT data.zip_code as zip,type_of_service_request.idtype_of_service_request as typeofreq,count(id) as cnt
FROM data, type_of_service_request
WHERE data.typeofservicerequest_idtype_of_service_request = type_of_service_request.idtype_of_service_request
and data.creation_date='2018-11-04 00:00:00+02'
group by data.zip_code,type_of_service_request.idtype_of_service_request
) as g1 
inner join (
select dt.zip as zip, MAX(cnt) as mx_cnt
from(
SELECT data.zip_code as zip,type_of_service_request.idtype_of_service_request as typeofreq,count(id) as cnt
FROM data, type_of_service_request
WHERE data.typeofservicerequest_idtype_of_service_request = type_of_service_request.idtype_of_service_request
and data.creation_date='2018-11-04 00:00:00+02'
group by data.zip_code,type_of_service_request.idtype_of_service_request) as dt
group by dt.zip) as g2
on g1.zip=g2.zip and g1.cnt=g2.mx_cnt

--4--
--Find the average completion time per service request for a specific date range.

select typeofservicerequest_idtype_of_service_request, avg(extract(epoch from(completition_date - creation_date))) as sr_avg
from data
where completition_date is not null
and creation_date between '2018-11-04 00:00:00+02' and '2018-11-07 00:00:00+02'
group by typeofservicerequest_idtype_of_service_request;

--5--
--Find the most common service request in a specified bounding box (as designated by GPS-coordinates) for a specific day.

select typeofservicerequest_idtype_of_service_request as req_type, count(*) as cnt 
from data
where creation_date='2010-09-25'
and lat between 39.826815 and 42.208679
and lon between  -92.168946 and  -85.828476
group by req_type
order by cnt desc 
limit 1;

--6--
--Find the top-5 Special Service Areas (SSA) with regards to total number of requests per day for a specific date range (for service requests types that SSA is available: abandoned vehicles, garbage carts, graffiti removal, pot holes reported)

select  ssa, count(distinct service_request_number) as cnt
from data
where data.ssa is not null
and data.creation_date>='2018-11-01 00:00:00+02' AND data.creation_date<='2018-11-06 00:00:00+02'
group by ssa
order by cnt desc
limit 5;

--7--
--Find the license plates (if any) that have been involved in abandoned vehicle complaints more than once.

select abv.license_plate, count(distinct data.service_request_number) as cnt
from abandoned_vehicles as abv ,data
where data.id=abv.data_id
and abv.license_plate is not null
group by abv.license_plate
having count(data.service_request_number)>1
order by cnt desc;

--8--
--Find the second most common color of vehicles involved in abandoned vehicle complaints.

select abv.vehicle_color, count(distinct data.service_request_number) as cnt
from abandoned_vehicles as abv ,data
where data.id=abv.data_id
group by abv.vehicle_color
order by cnt desc
OFFSET  1 ROWS
FETCH NEXT 1 ROWS ONLY;

--9--
--Find the rodent baiting requests where the number of premises baited is less than a specified number.
select distinct data.service_request_number, rb.number_of_premises_baited
from data, rodent_baiting as rb
where data.id=rb.data_id
and rb.number_of_premises_baited<2
order by rb.number_of_premises_baited desc;

--10--
--Same as the above (i.e., 9) for premises with garbage.

select distinct data.service_request_number, rb.number_of_permises_garbage
from data, rodent_baiting as rb
where data.id=rb.data_id
and rb.number_of_permises_garbage<2
order by rb.number_of_permises_garbage desc
;

--11--
--Same as the above (i.e., 10) for premises with rats.

select distinct data.service_request_number, rb.number_of_permises_rats
from data, rodent_baiting as rb
where data.id=rb.data_id
and rb.number_of_permises_rats<2
order by rb.number_of_permises_rats desc
;

--12--
--Find the police districts that have handled “pot holes” requests with more than one number of potholes on the same day that they also handled “rodent baiting” requests with more than one number of premises baited, for a specific day.

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
		where completition_date ='2018-11-01 00:00:00+02'
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
where completition_date ='2018-11-01 00:00:00+02'
and typeofservicerequest_idtype_of_service_request =  'Pot Hole in Street'
)
)

