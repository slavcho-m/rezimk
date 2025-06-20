CREATE TABLE town
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(255) NOT NULL,
    CONSTRAINT pk_town PRIMARY KEY (id),
    CONSTRAINT uc_town_name UNIQUE (name)
);

CREATE TABLE apartment
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name        VARCHAR(255),
    address     VARCHAR(255),
    description VARCHAR(255),
    town_id     BIGINT,
    CONSTRAINT pk_apartment PRIMARY KEY (id),
    CONSTRAINT FK_APARTMENT_ON_TOWN FOREIGN KEY (town_id) REFERENCES town (id)
);

CREATE TABLE room
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    room_type       VARCHAR(255),
    capacity        INTEGER NOT NULL,
    price_per_night DECIMAL,
    apartment_id    BIGINT,
    CONSTRAINT pk_room PRIMARY KEY (id),
    CONSTRAINT FK_ROOM_ON_APARTMENT FOREIGN KEY (apartment_id) REFERENCES apartment (id)
);

CREATE TABLE image
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name         VARCHAR(255),
    image_type   SMALLINT,
    data         BYTEA,
    apartment_id BIGINT,
    room_id      BIGINT,
    CONSTRAINT pk_image PRIMARY KEY (id),
    CONSTRAINT FK_IMAGE_ON_APARTMENT FOREIGN KEY (apartment_id) REFERENCES apartment (id),
    CONSTRAINT FK_IMAGE_ON_ROOM FOREIGN KEY (room_id) REFERENCES room (id)
);

CREATE TABLE review
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    rating       VARCHAR(255),
    comment      VARCHAR(255),
    apartment_id BIGINT,
    CONSTRAINT pk_review PRIMARY KEY (id),
    CONSTRAINT FK_REVIEW_ON_APARTMENT FOREIGN KEY (apartment_id) REFERENCES apartment (id)
);