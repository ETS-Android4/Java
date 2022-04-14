--
-- Database: `Stardew Valley`
--

drop database cis2232_stardew_valley;

create database cis2232_stardew_valley;

use cis2232_stardew_valley;

CREATE TABLE IF NOT EXISTS PlantTracker( 

id int(5) NOT NULL PRIMARY KEY AUTO_INCREMENT,

plantSeason varchar(20) NOT NULL, 

plantName varchar(20) NOT NULL, 

plantDate varchar(2) NOT NULL,

numberPlanted int(5) NOT NULL )