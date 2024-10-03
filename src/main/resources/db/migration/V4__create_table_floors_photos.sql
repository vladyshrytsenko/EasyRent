
CREATE TABLE floors_photos (
    floor_id BIGINT NOT NULL,
    photo TEXT,
    PRIMARY KEY (floor_id, photo),
    CONSTRAINT fk_floor FOREIGN KEY (floor_id) REFERENCES floors(id)
);
