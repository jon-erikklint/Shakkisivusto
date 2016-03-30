CREATE TABLE Pelaaja(
id serial primary key,
kayttajanimi varchar[50] unique not null,
pelaajanimi varchar[50] not null,
salasana varchar[30] not null,
admin boolean not null
);

CREATE TABLE Peli(
id serial primary key,
nimi varchar[50] not null,
status varchar[25] not null
);

CREATE TABLE Pelinpelaaja(
pelaajaid int not null,
peliid int not null,
FOREIGN KEY pelaajaid REFERENCES Pelaaja(id),
FOREIGN KEY peliid REFERENCES Peli(id),
CONSTRAINT pk PRIMARY KEY (pelaajaid, peliid)
);

CREATE TABLE Vuoro(
vuoro int not null,
peliid int not null,
pelaaja int,
lauta varchat[50] not null,
erikoistilanteet varchar[20] not null,
tekoaika timestamp not null
FOREIGN KEY peliid REFERENCES Peli(id),
FOREIGN KEY pelaaja REFERENCES Pelaaja(id),
CONSTRAINT pk PRIMARY KEY (vuoro, peliid)
);
