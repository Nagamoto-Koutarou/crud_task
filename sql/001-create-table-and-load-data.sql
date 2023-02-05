 DROP TABLE IF EXISTS coffees;

 CREATE TABLE coffees (
   id int unsigned AUTO_INCREMENT,
   dataDay timestamp NOT NULL,
   countryOfOrigin VARCHAR(20) NOT NULL,
   productName VARCHAR(50) NOT NULL,
   degreeOfRoasting VARCHAR(20) NOT NULL,
   thoughts text default 'No Data',
   PRIMARY KEY (id)
 );

 INSERT INTO coffees (dataDay, countryOfOrigin, productName, degreeOfRoasting, thoughts) VALUES
 (now(), "ブルーマウンテン", "ジャマイカ", "浅煎り");
