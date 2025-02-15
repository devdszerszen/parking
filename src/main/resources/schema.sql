CREATE TABLE spots
(
    id INTEGER NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE reservations
(
    id VARCHAR(100) NOT NULL,
    reservation_date DATE NOT NULL,
    registration_number VARCHAR(20) NOT NULL,
    spot INTEGER NOT NULL,
    PRIMARY KEY (id)
)