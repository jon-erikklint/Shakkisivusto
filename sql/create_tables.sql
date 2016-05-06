CREATE TABLE Pelaaja(
idpelaaja serial primary key,
kayttajanimi varchar(30) unique not null,
pelaajanimi varchar(30) not null,
salasana varchar(30) not null,
admin boolean not null
);

CREATE TABLE Peli(
idpeli serial primary key,
nimi varchar(50) not null,
status varchar(25) not null
);

CREATE TABLE Pelinpelaaja(
pelaajaid int not null,
peliid int not null,
valkoinen boolean not null,
voittaja boolean not null,
FOREIGN KEY (pelaajaid) REFERENCES Pelaaja(id),
FOREIGN KEY (peliid) REFERENCES Peli(id),
CONSTRAINT pk_pelinpelaaja PRIMARY KEY (pelaajaid, peliid)
);

CREATE TABLE Vuoro(
vuoro int not null,
peli int not null,
pelaaja int,
lauta varchar(200) not null,
erikoistilanteet varchar(20) not null,
tekoaika timestamp not null,
FOREIGN KEY (peli) REFERENCES Peli(id),
FOREIGN KEY (pelaaja) REFERENCES Pelaaja(id),
CONSTRAINT pk_vuoro PRIMARY KEY (vuoro, peli)
);
