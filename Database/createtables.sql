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
