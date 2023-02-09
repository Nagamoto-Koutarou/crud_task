 DROP TABLE IF EXISTS coffees;

 CREATE TABLE coffees (
   id int unsigned AUTO_INCREMENT,
   recordDay DATE NOt NULL,
   countryOfOrigin VARCHAR(20) NOT NULL,
   productName VARCHAR(50) NOT NULL,
   degreeOfRoasting VARCHAR(20) NOT NULL,
   thoughts TEXT,
   PRIMARY KEY (id)
 );

 INSERT INTO coffees (dataDay, countryOfOrigin, productName, degreeOfRoasting, thoughts) VALUES
 (now(), "ブルーマウンテン", "ジャマイカ", "浅煎り", "バランスがよく美味しかった");
