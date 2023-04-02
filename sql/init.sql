CREATE TABLE account
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL primary key,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    email      VARCHAR(255) unique,
    password   VARCHAR(255)
);

CREATE TABLE location_point
(
    id        BIGINT GENERATED BY DEFAULT AS IDENTITY primary key,
    latitude  DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    unique (latitude, longitude)
);

CREATE TABLE animal_type
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL primary key,
    type VARCHAR(255) unique
);

CREATE TABLE animal
(
    id                   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL primary key,
    weight               FLOAT                                   NOT NULL,
    length               FLOAT                                   NOT NULL,
    height               FLOAT                                   NOT NULL,
    gender               VARCHAR(10)                             NOT NULL,
    life_status          VARCHAR(10)                             NOT NULL,
    chipping_date_time   TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    chipper_id           BIGINT                                  NOT NULL,
    chipping_location_id BIGINT                                  NOT NULL,
    death_date_time      TIMESTAMP WITHOUT TIME ZONE DEFAULT NULL,
    foreign key (chipper_id) references account (id),
    foreign key (chipping_location_id) references location_point (id)
);

CREATE TABLE animal_visited_locations
(
    id                                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL primary key,
    animal_id                         BIGINT                                  NOT NULL,
    visited_locations_id              BIGINT                                  NOT NULL,
    date_time_of_visit_location_point TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    foreign key (animal_id) references animal (id),
    foreign key (visited_locations_id) references location_point (id)
);

CREATE TABLE animal_animal_type
(
    animal_id      BIGINT NOT NULL,
    animal_type_id BIGINT NOT NULL,
    primary key (animal_id, animal_type_id),
    foreign key (animal_id) references animal (id),
    foreign key (animal_type_id) references animal_type (id)
);