create table "APP"."PIANO"
(
	ID_piano int primary key not null generated always as identity (start with 1, increment by 1),
	NOME varchar(20) not null
);

create table "APP"."BEACON"
(
	ID_beacon int primary key not null generated always as identity (start with 1, increment by 1),
	coordinata_x float not null,
	coordinata_y float not null,
        fuoco float not null,
        fumi float not null,
        LOS float not null,
        rischio float not null
);

create table "APP"."MAPPA"
(
	ID_mappa int primary key not null generated always as identity (start with 1, increment by 1),
	IMG varchar(50) not null,
        ID_piano int references "APP"."PIANO"(ID_piano) on delete cascade
);

create table "APP"."NODI"
(
	ID_nodo int primary key not null generated always as identity (start with 1, increment by 1),
	coordinata_x float not null,
	coordinata_y float not null,
	ID_mappa int references "APP"."MAPPA"(ID_mappa) on delete cascade
);

create table "APP"."PDR"
(
	ID_nodo int references "APP"."NODI"(ID_nodo) on delete cascade,
	ID_punto_di_raccolta int primary key not null generated always as identity (start with 1, increment by 1)
);

create table "APP"."TRONCO"
(
	ID_tronco int primary key not null generated always as identity (start with 1, increment by 1),
	larghezza float not null,
        lunghezza float not null,
	ID_mappa int references "APP"."MAPPA"(ID_mappa) on delete cascade,
        ID_nodo1 int references "APP"."NODI"(ID_nodo) on delete cascade,
        ID_nodo2 int references "APP"."NODI"(ID_nodo) on delete cascade,
        ID_beacon int references "APP"."BEACON"(ID_beacon) on delete cascade
);

create table "APP"."UTENTE" 
(
	ID_utente int primary key not null generated always as identity (start with 1, increment by 1),
	username varchar(20) unique not null,
	password varchar(20) not null,
	email varchar(50) not null,
	nome varchar(25) not null,
	cognome varchar(35) not null,
	is_autenticato boolean not null,
	ID_beacon int references "APP"."BEACON"(ID_beacon) on delete set null,
        tipologia boolean not null default false
);

create table "APP"."TOKEN"
(
	ID_token int primary key not null generated always as identity (start with 1, increment by 1),
	token varchar(256) unique not null,
	ID_utente int references "APP"."UTENTE"(ID_utente) on delete cascade
);

create table "APP"."AULE"
(
	ID_nodo int references "APP"."NODI"(ID_nodo) on delete cascade,
	ID_aula int primary key not null generated always as identity (start with 1, increment by 1),
        nome varchar(20) not null,
        ID_piano int references "APP"."PIANO"(ID_piano) on delete cascade
);