DROP TABLE IF EXISTS pfc;

CREATE TABLE pfc (
   id int unsigned AUTO_INCREMENT,
   dataDay timestamp NOT NULL,
   protein int,
   fat int,
   carb int,
   PRIMARY KEY (id)
);
