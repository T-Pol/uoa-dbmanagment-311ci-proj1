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


-- -----------------------------------------------------
-- Table abandoned_vehicles
-- -----------------------------------------------------

CREATE TABLE abandoned_vehicles
(
  data_id uuid NOT NULL,
  license_plate text,
  vehicle_make text,
  vehicle_color character varying(30),
  current_activity text,
  most_recent_action text,
  days_reported numeric,
  PRIMARY KEY (data_id),
  FOREIGN KEY (data_id) REFERENCES data (id) 
)


-- -----------------------------------------------------
-- Table garbage_carts
-- -----------------------------------------------------
CREATE TABLE garbage_carts
(
  data_id uuid NOT NULL,
  number_of_carts numeric,
  current_activity text,
  most_recent_action text,
  PRIMARY KEY (data_id),
  FOREIGN KEY (data_id) REFERENCES data (id)
)


-- -----------------------------------------------------
-- Table graffiti_removal
-- -----------------------------------------------------
CREATE TABLE graffiti_removal
(
  data_id uuid NOT NULL,
  type_of_surface character varying(45),
  graffiti_located character varying(45),
  PRIMARY KEY (data_id),
  FOREIGN KEY (data_id) REFERENCES data (id)
)

-- -----------------------------------------------------
-- Table pot_holes_reported
-- -----------------------------------------------------

CREATE TABLE pot_holes_reported
(
  data_id uuid NOT NULL,
  number_pots_filled_block integer,
  current_activity text,
  most_recent_action text,
  PRIMARY KEY (data_id),
  FOREIGN KEY (data_id) REFERENCES data (id)
)

-- -----------------------------------------------------
-- Table rodent_baiting
-- -----------------------------------------------------

CREATE TABLE rodent_baiting
(
  data_id uuid NOT NULL,
  number_of_premises_baited integer,
  number_of_permises_garbage integer,
  number_of_permises_rats integer,
  current_activity text,
  most_recent_action text,
  PRIMARY KEY (data_id),
  FOREIGN KEY (data_id) REFERENCES data (id)
)

-- -----------------------------------------------------
-- Table sanitation_code_complaints
-- -----------------------------------------------------

CREATE TABLE sanitation_code_complaints
(
  data_id uuid NOT NULL,
  nature_of_code_violation text,
  PRIMARY KEY (data_id),
  FOREIGN KEY (data_id) REFERENCES data (id) 
)

-- -----------------------------------------------------
-- Table tree_derbis
-- -----------------------------------------------------

CREATE TABLE tree_derbis
(
  data_id uuid NOT NULL,
  derbis_located text,
  current_activity text,
  most_recent_action text,
  PRIMARY KEY (data_id),
  FOREIGN KEY (data_id) REFERENCES data (id)
)

-- -----------------------------------------------------
-- Table tree_trims
-- -----------------------------------------------------

CREATE TABLE tree_trims
(
  data_id uuid NOT NULL,
  location_of_trees text,
  PRIMARY KEY (data_id),
  FOREIGN KEY (data_id) REFERENCES data (id) 
)


--data for users

CREATE TABLE users
(
  id serial NOT NULL,
  name character varying(255) NOT NULL,
  username character varying(255) NOT NULL,
  email character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  address character varying(255) NOT NULL,
  role integer,
  PRIMARY KEY (id)
)

CREATE TABLE useractions
(
  action_id serial NOT NULL,
  user_id integer NOT NULL,
  action character varying(255),
  action_details character varying(255),
  CONSTRAINT useractions_pkey PRIMARY KEY (action_id),
  FOREIGN KEY (user_id) REFERENCES user (id) 
)
