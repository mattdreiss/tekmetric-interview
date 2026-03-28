CREATE TABLE car (
    id          UUID PRIMARY KEY,
    model_year  VARCHAR(4),
    make        VARCHAR(512),
    model       VARCHAR(512),
    trim        VARCHAR(512),
    color       VARCHAR(512),
    vin         VARCHAR(512),
    transmission VARCHAR(255),
    drivetrain  VARCHAR(255),
    created_at  TIMESTAMP WITH TIME ZONE,
    modified_at TIMESTAMP WITH TIME ZONE
);
