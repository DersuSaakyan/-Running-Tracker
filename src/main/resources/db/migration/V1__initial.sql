CREATE DATABASE running_tracker;

CREATE SCHEMA IF NOT EXISTS running_tracker;

CREATE SEQUENCE IF NOT EXISTS user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS "user" (
    id BIGINT NOT NULL DEFAULT nextval('user_id_seq'::regclass),
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    birth_date TIMESTAMP NOT NULL,
    sex VARCHAR NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT EXISTS run_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE IF NOT EXISTS run (
    id BIGINT NOT NULL DEFAULT nextval('run_id_seq'::regclass),
    user_id BIGINT NOT NULL,
    start_latitude DOUBLE PRECISION NOT NULL,
    start_longitude DOUBLE PRECISION NOT NULL,
    start_datetime TIMESTAMP NOT NULL,
    finish_latitude DOUBLE PRECISION,
    finish_longitude DOUBLE PRECISION,
    finish_datetime TIMESTAMP,
    distance DOUBLE PRECISION,
    CONSTRAINT pk_run PRIMARY KEY (id),
    CONSTRAINT fk_run_user_id__user_id FOREIGN KEY(user_id) REFERENCES "user"(id)
);
