create table if not exists Village(
    villageId int primary key,
    villageName varchar(255)
);

INSERT INTO VILLAGE (villageId, villageName) VALUES ('1', 'Mitte');
INSERT INTO VILLAGE (villageId, villageName) VALUES ('2', 'Berlin');
INSERT INTO VILLAGE (villageId, villageName) VALUES ('3', 'Kreuzberg');
INSERT INTO VILLAGE (villageId, villageName) VALUES ('4', 'Hamburg');
INSERT INTO VILLAGE (villageId, villageName) VALUES ('5', 'Hesse');
INSERT INTO VILLAGE (villageId, villageName) VALUES ('6', 'Aalen');
INSERT INTO VILLAGE (villageId, villageName) VALUES ('7', 'Offenburg');
INSERT INTO VILLAGE (villageId, villageName) VALUES ('8', 'Ludwigsburg');
INSERT INTO VILLAGE (villageId, villageName) VALUES ('9', 'Coburg');
INSERT INTO VILLAGE (villageId, villageName) VALUES ('10', 'Bremen');

create table if not exists Counter(
  cntId int AUTO_INCREMENT primary key,
  counterId int,
  amount int,
  consumptionDate varchar(255),
  villageId int,
  FOREIGN KEY (villageId) REFERENCES Village(villageId)
);




