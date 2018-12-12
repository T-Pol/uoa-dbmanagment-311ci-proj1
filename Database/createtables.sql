-- -----------------------------------------------------
-- Table status
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS status 
(
  status_id VARCHAR(15) NOT NULL,
  PRIMARY KEY (status_id)
)


-- -----------------------------------------------------
-- Table type_of_service_Request
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS type_of_Service_Request 
(
  idtype_of_service_request VARCHAR(45) NOT NULL,
  PRIMARY KEY (idtype_of_Service_Request)
)



-- -----------------------------------------------------
-- Table data
-- -----------------------------------------------------

CREATE TABLE public.data
(
  id uuid NOT NULL,
  creation_date timestamp without time zone NOT NULL,
  stat_status_id character varying(15) NOT NULL,
  completition_date timestamp without time zone,
  service_request_number character varying(15) NOT NULL,
  typeofservicerequest_idtype_of_service_request character varying(45) NOT NULL,
  street_address text,
  zip_code integer,
  x_coor numeric,
  y_coor numeric,
  ward smallint,
  police_district smallint,
  community_area smallint,
  ssa smallint,
  lat numeric,
  lon numeric,
  location text,
  PRIMARY KEY (id),
  FOREIGN KEY (stat_status_id) REFERENCES status (status_id),
  FOREIGN KEY (typeofservicerequest_idtype_of_service_request)REFERENCES type_of_service_request (idtype_of_service_request)
)
