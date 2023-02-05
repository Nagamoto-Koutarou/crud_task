DROP TABLE IF EXISTS coffeeDiary;

CREATE TABLE coffeeDiary (
   id int unsigned AUTO_INCREMENT,
   dataDay timestamp NOT NULL,
   productName VARCHAR(50) NOT NULL,
   countryOfOrigin VARCHAR(20) NOT NULL,
   degreeOfRoasting VARCHAR(20) NOT NULL,
   thoughts VARCHAR(200) NOT NULL,
   PRIMARY KEY (id)
);
