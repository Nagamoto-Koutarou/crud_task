 DROP TABLE IF EXISTS coffees;

 CREATE TABLE coffees (
   id int unsigned AUTO_INCREMENT,
   createdDate DATE NOT NULL,
   countryOfOrigin VARCHAR(20) NOT NULL,
   productName VARCHAR(50) NOT NULL,
   degreeOfRoasting VARCHAR(20) NOT NULL,
   thoughts TEXT,
   PRIMARY KEY (id)
 );

 INSERT INTO coffees (createdDate, countryOfOrigin, productName, degreeOfRoasting, thoughts) VALUES
 (now(), "ブルーマウンテン", "ジャマイカ", "浅煎り", "バランスがよく美味しかった");
