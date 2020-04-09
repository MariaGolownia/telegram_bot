-- Table: public.city_info

-- DROP TABLE public.city_info;

CREATE TABLE public.city_info
(
    id integer NOT NULL DEFAULT nextval('city_info_id_seq'::regclass),
    city character varying COLLATE pg_catalog."default" NOT NULL,
    info character varying(255) COLLATE pg_catalog."default"
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.city_info
    OWNER to postgres;