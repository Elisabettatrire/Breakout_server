create table "APP"."PIANO"
(
    ID_piano int primary key not null generated always as identity (start with 1, increment by 1),
    quota varchar(20) not null
);

create table "APP"."MAPPA"
(
    ID_mappa int primary key not null generated always as identity (start with 1, increment by 1),
    IMG varchar(50),
    nome varchar(20) not null,
    ID_piano int references "APP"."PIANO"(ID_piano) on delete cascade
);

create table "APP"."NODO"
(
    ID_nodo int primary key not null generated always as identity (start with 1, increment by 1),
    codice varchar(10) not null unique,
    is_pdi boolean default false not null,
    tipo varchar(50),
    coordinata_x float not null,
    coordinata_y float not null,
    larghezza float not null,
    lunghezza float,
    ID_mappa int references "APP"."MAPPA"(ID_mappa) on delete cascade,
    ID_piano int references "APP"."PIANO"(ID_piano) on delete cascade
);

create table "APP"."BEACON"
(
    ID_beacon int primary key not null generated always as identity (start with 1, increment by 1),
    address varchar(30) not null unique,
    coordinata_x float not null,
    coordinata_y float not null,
    fuoco float not null,
    fumi float not null,
    ncd float not null,
    rischio float not null,
    ID_pdi int references "APP"."NODO"(ID_nodo) on delete cascade,
    ID_mappa int references "APP"."MAPPA"(ID_mappa) on delete cascade,
    ID_piano int references "APP"."PIANO"(ID_piano) on delete cascade
);

create table "APP"."TRONCO"
(
    ID_tronco int primary key not null generated always as identity (start with 1, increment by 1),
    lunghezza float not null,
    ID_nodo1 int references "APP"."NODO"(ID_nodo) on delete cascade,
    ID_nodo2 int references "APP"."NODO"(ID_nodo) on delete cascade,
    ID_beacon int references "APP"."BEACON"(ID_beacon) on delete cascade,
    ID_mappa int references "APP"."MAPPA"(ID_mappa) on delete cascade,
    ID_piano int references "APP"."PIANO"(ID_piano) on delete cascade
);

create table "APP"."UTENTE" 
(
    ID_utente int primary key not null generated always as identity (start with 1, increment by 1),
    username varchar(20) unique not null,
    password varchar(20) not null,
    email varchar(50) not null,
    nome varchar(35) not null,
    cognome varchar(35) not null,
    is_online boolean not null default false,
    ID_beacon int references "APP"."BEACON"(ID_beacon) on delete set null
);

create table "APP"."TOKEN"
(
    ID_token int primary key not null generated always as identity (start with 1, increment by 1),
    token varchar(256) unique not null,
    ID_utente int references "APP"."UTENTE"(ID_utente) on delete cascade
);

create table "APP"."MODIFICHE"
(
    ID_modifica int primary key not null generated always as identity (start with 1, increment by 1),
    data_modifica timestamp not null default current_timestamp,
    tipo_modifica varchar(30) not null,
    tabella varchar(20) not null,
    ID_oggetto_mod int 
);
create table "APP"."NOTIFICHE"
(
    ID_notifica int primary key not null generated always as identity (start with 1, increment by 1),
    is_emergenza boolean not null,
    data_emergenza timestamp not null default current_timestamp,
    address varchar(30) references "APP"."BEACON"(address)
);
