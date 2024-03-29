-- This script was generated by a beta version of the ERD tool in pgAdmin 4.
-- Please log an issue at https://redmine.postgresql.org/projects/pgadmin4/issues/new if you find any bugs, including reproduction steps.
BEGIN;


CREATE TABLE IF NOT EXISTS public.survivor
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name text COLLATE pg_catalog."default" NOT NULL,
    age integer,
    gender text COLLATE pg_catalog."default",
    location text COLLATE pg_catalog."default",
    inventory text COLLATE pg_catalog."default",
    status text COLLATE pg_catalog."default",
    CONSTRAINT survivor_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
);
END;