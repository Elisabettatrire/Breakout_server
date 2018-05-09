create table "APP"."PIANO"
(
	ID_piano int primary key not null generated always as identity (start with 1, increment by 1),
	quota varchar(20) not null
);

create table "APP"."PDI"
(
        tipo varchar(50) not null,
        descrizione varchar(20) not null,
        larghezza float,
        lunghezza float,
        coordinata_x float not null,
	coordinata_y float not null,
	ID_pdi int primary key not null generated always as identity (start with 1, increment by 1)
);

create table "APP"."BEACON"
(
	ID_beacon int primary key not null generated always as identity (start with 1, increment by 1),
	coordinata_x float not null,
	coordinata_y float not null,
        fuoco float not null,
        fumi float not null,
        LOS float not null,
        rischio float not null,
        ID_pdi int references "APP"."PDI"(ID_pdi) on delete set null
);

create table "APP"."MAPPA"
(
	ID_mappa int primary key not null generated always as identity (start with 1, increment by 1),
	IMG varchar(50) not null,
        nome varchar(20) not null,
        ID_piano int references "APP"."PIANO"(ID_piano) on delete cascade
);

create table "APP"."NODI"
(
	ID_nodo int primary key not null generated always as identity (start with 1, increment by 1),
        codice varchar(10) not null unique,
	coordinata_x float not null,
	coordinata_y float not null,
        larghezza float not null,
	ID_mappa int references "APP"."MAPPA"(ID_mappa) on delete cascade
);

create table "APP"."TRONCO"
(
	ID_tronco int primary key not null generated always as identity (start with 1, increment by 1),
        lunghezza float not null,
	ID_mappa int references "APP"."MAPPA"(ID_mappa) on delete cascade,
        ID_nodo1 int references "APP"."NODI"(ID_nodo) on delete cascade,
        ID_nodo2 int references "APP"."NODI"(ID_nodo) on delete cascade,
        ID_beacon int references "APP"."BEACON"(ID_beacon) on delete cascade
);

create table "APP"."SCALA"
(
	ID_scala int primary key not null generated always as identity (start with 1, increment by 1),
        lunghezza float not null,
        ID_nodo1 int references "APP"."NODI"(ID_nodo) on delete cascade,
        ID_nodo2 int references "APP"."NODI"(ID_nodo) on delete cascade,
        ID_beacon int references "APP"."BEACON"(ID_beacon) on delete set null
);

create table "APP"."UTENTE" 
(
	ID_utente int primary key not null generated always as identity (start with 1, increment by 1),
	username varchar(20) unique not null,
	password varchar(20) not null,
	email varchar(50) not null,
	nome varchar(25) not null,
	cognome varchar(35) not null,
	is_online boolean not null,
	ID_beacon int references "APP"."BEACON"(ID_beacon) on delete set null,
        privilegi boolean not null default false
);

create table "APP"."TOKEN"
(
	ID_token int primary key not null generated always as identity (start with 1, increment by 1),
	token varchar(256) unique not null,
	ID_utente int references "APP"."UTENTE"(ID_utente) on delete cascade
);