CREATE TABLE car_entity
(
  id      SERIAL NOT NULL,
  seats   INTEGER,
  PRIMARY KEY (id)
);

CREATE TABLE group_entity
(
  id      SERIAL NOT NULL,
  people  INTEGER,
  PRIMARY KEY (id)
);

CREATE TABLE journey_entity
(
  id                  INTEGER NOT NULL,
  car_entity_id       INTEGER,
  group_entity_id     INTEGER,
  PRIMARY KEY (id),
  FOREIGN KEY (car_entity_id) REFERENCES car_entity (id),
  FOREIGN KEY (group_entity_id) REFERENCES group_entity (id)
);

CREATE SEQUENCE hibernate_sequence START 1;