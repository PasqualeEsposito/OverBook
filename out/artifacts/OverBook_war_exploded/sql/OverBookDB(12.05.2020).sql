CREATE DATABASE if not exists OverBookDB;

USE OverBookDB;

CREATE TABLE Cliente (
	email varchar(100) primary key,
	password varchar(500) NOT NULL,
    nome varchar(100) NOT NULL,
    cognome varchar(100) NOT NULL,
    data_nascita date NOT NULL,
    regione varchar(70) NOT NULL ,
    provincia varchar(70) NOT NULL,
    comune varchar(70) NOT NULL,
    admin boolean not null
);

CREATE TABLE Carrello(
	ID_carrello int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    totale decimal(10,2),
    data_ordine date,
    data_arrivo date,
    cliente varchar(100) NOT NULL,
    ordine boolean,
    FOREIGN KEY (cliente) REFERENCES Cliente(email)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE Categoria(
	ID_categoria int AUTO_INCREMENT PRIMARY KEY,
    nome varchar(100) NOT NULL,
    descrizione varchar(1000) NOT NULL
);

CREATE TABLE Prodotto(
	ID_prodotto int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titolo varchar(100) NOT NULL,
    disponibilita int NOT NULL,
    prezzo double NOT NULL,
    descrizione varchar(500) NOT NULL,
    piattaforma varchar(100),
    data_pubblicazione date NOT NULL,
    immagine varchar(100) NOT NULL,
    autore varchar(50) NOT NULL,
    casa_editrice varchar(50),
    categoria int not null,
    foreign key(categoria) references Categoria(ID_categoria)
);

CREATE TABLE Inserimento(
	carrello int NOT NULL,
    prodotto int NOT NULL,
    quantita int not null,
    prezzo decimal(10,2) not null,
    PRIMARY KEY(carrello, prodotto),
    FOREIGN KEY (carrello) REFERENCES Carrello(ID_carrello)
	ON UPDATE CASCADE
    ON DELETE CASCADE,
    FOREIGN KEY (prodotto) REFERENCES Prodotto(ID_prodotto)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

INSERT INTO Cliente VALUES
('p.esposito47@studenti.unisa.it', SHA1('Pasquale1!'), 'Pasquale', 'Esposito','1999-09-25', 'Campania', 'Napoli', 'Somma Vesuviana', 1),
('n.serra2@studenti.unisa.it',SHA1('NicolaSerra1!'), 'Nicola', 'Serra', '1991-07-18', 'Campania', 'Caserta', 'San Nicola la Strada', 1),
('a.russo324@studenti.unisa.it',SHA1('AntonioRusso1!'), 'Antonio', 'Russo','1999-07-28', 'Campania', 'Caserta', 'San Nicola la Strada', 1);

INSERT INTO Categoria VALUES
(1, 'VideoGames', 'Raccolta di tutti i videogames'),
(2, 'Libri', 'Raccolta di tutti i libri');
    
INSERT INTO Prodotto VALUES
(1, 'FIFA20', 10, 49.99, 'Gioco per XBOX/PS4', 'PS4', '2019-09-10', './img/fifa20.jpg', 'EASports',NULL,1),
(2, 'Il Piccolo Principe', 5, 8.90, 'Libro fantasy', NULL, '1943-05-15', './img/piccolo.jpg','Antoine de Saint-Exupéry', 'Gallimard',2),
(3, 'Call of duty 4', 10, 49.99, 'Gioco per XBOX/PS4', 'PS4', '2018-09-10', './img/cod4.jpg', 'Activision',NULL,1),
(4, 'Harry Potter e la pietra filosofale', 3, 8.90, 'Libro fantasy', NULL, '1999-05-15', './img/HP1.jpg','J.K.Rowlings', 'Salani Editore',2),
(5, 'Destiny 2', 12, 34.99, 'Gioco per XBOX/PS4', 'PS4', '2017-09-10', './img/dest2.jpg', 'Activision',NULL,1),
(6, 'Il Signore degli anelli e la compagnia compagnia dell\'anello', 1, 11.90, 'Libro fantasy', NULL, '1954-05-15', './img/LOTR1.jpg','J.R.R. Tolkien', 'Astrolabio',2),
(7, 'The last of US', 23, 79.99, 'Gioco per XBOX/PS4', 'PS4', '2019-09-10', './img/TLOU.jpg', 'Naughty Dog',NULL,1),
(8, 'Un capitano', 3, 11.90, 'Libro autobiografico', NULL, '2018-05-15', './img/UnCapitano.jpg','Francesco Totti', 'Rizzoli',2),
(9, 'Fortnite', 30, 29.99, 'Gioco per XBOX/PS4', 'PS4', '2017-09-10', './img/fortnite.jpg', 'Epic games',NULL,1),
(10, 'Seppelliteci qui', 5, 13.90, 'Libro sportivo', NULL, '2010-05-15', './img/sepqui.jpg','Raffaele Auriemma', 'Graf',2),
(11,'Control', 15,39.50,'Gioco per XBOX/PS4','PS4','2019-08-27','./img/control.jpg','505 Games',NULL,1),
(12, 'Come passare gli esami',15,8.90,'Libro narrativa',NULL,'2019-12-28','./img/passareEsami.jpg','Marco Fogliarino','Independently published',2),
(13,'Prey', 15,39.50,'Gioco per PS4','PS4','2017-05-05','./img/prey.jpg','Bethesda Softworks',NULL,1),
(14, 'Rothschild e gli altri',15,8.90,'Libro narrativa',NULL,'2015-07-03','./img/rothschild.jpg','Pietro Ratto','Arianna Editrice',2),
(15,'Star Wars Jedi', 15,69.50,'Gioco per XBOX/PS4','XBOX','2019-11-15','./img/jed.jpg','Eletronics Arts',NULL,1),
(16, 'Hunger Games',17,8.90,'Libro fantasy',NULL,'2015-12-28','./img/hungerGames.jpg','Suzanne Collins','Mondadori',2),
(17,'The Witcher 3', 15,39.50,'Gioco per PS4','PS4','2015-05-19','./img/theWitcher.jpg','CD Projekt RED',NULL,1),
(18, '1001 Film - I grandi capolavori del cinema',15,8.90,'Libro narrativa',NULL,'2016-10-13','./img/film.jpg','S. J. Schneider','Atlante',2),
(19,'Residente evil 2', 15,69.50,'Gioco per XBOX/PS4','PS4','2019-01-25','./img/resident.jpg','CAPCOM',NULL,1),
(20, 'Il trono di spade',17,8.90,'Libro fantasy',NULL,'2015-12-28','./img/trono.jpg','George Martin','Mondadori',2),
(21,'Minecraft', 15,39.50,'Gioco per XBOX','XBOX','2015-05-19','./img/mine.jpg','Mojang',NULL,1),
(22, 'Inseguendo un \' ombra',15,8.90,'Libro narrativa',NULL,'2015-10-13','./img/Montalbano.jpg','Andrea Camilleri','Sellerio Editore',2),
(23,'Uncharted 4', 15,69.50,'Gioco per PS4','PS4','2016-05-10','./img/uncharted.jpg','Sony computer entertainment',NULL,1),
(24, 'Resta con me',17,8.90,'Libro narrativa',NULL,'2015-12-28','./img/resta.jpg','Susea McGearhart','HarperCollins',2),
(25,'Watch Dogs', 15,79.50,'Gioco per XBOX','XBOX','2014-05-27','./img/watch.jpg','Ubisoft',NULL,1),
(26, 'IT',15,8.90,'Libro Horror',NULL,'1986-10-13','./img/it.jpg','Stephen king','Perling & Kupfer ',2),
(27,'Assassins creed 3', 15,69.50,'Gioco per PS4','PS4','2016-05-10','./img/assassins.jpg','Ubisoft',NULL,1),
(28, 'Grande libro del corpo umano',17,8.90,'Libro narrativa',NULL,'2016-12-28','./img/corpo.jpg','A.A.V.V.','Idea Libri',2),
(29,'Crash CTR', 15,79.50,'Gioco per PS4','PS4','2015-05-27','./img/crash.jpg','Sony computer entertainment',NULL,1),
(30, 'La ragazza del treno',15,8.90,'Libro narrativa',NULL,'2017-10-13','./img/ragazza.jpg','Paula Hawkins','Piemme',2),
(31,'Hitman', 15,69.50,'Gioco per PS4','PS4','2015-05-10','./img/Hitman.jpg','Square Enix',NULL,1),
(32, 'Javascript',17,8.90,'Libro narrativa',NULL,'2018-12-28','./img/javascript.jpg','Marjin Haverbeke','Hoepli informatica',2),
(33,'F1 2018', 15,79.50,'Gioco per PS4','PS4','2018-05-27','./img/f12018.jpg','Code Masters',NULL,1),
(34, 'Rivoluzione',15,8.90,'Libro narrativa',NULL,'2016-10-13','./img/rivoluzione.jpg','Bruno Vespa','Mondadori',2),
(35,'Fallout 76', 15,69.50,'Gioco per PS4','PS4','2017-05-10','./img/fallout.jpg','Bethesda',NULL,1),
(36, 'La verità sul caso Harry Quebert',17,8.90,'Libro narrativa',NULL,'2018-12-28','./img/quebert.jpg','Joël Dicker','Piemme',2),
(37,'Far Cry Primat', 15,79.50,'Gioco per PS4','PS4','2018-05-27','./img/farCry.jpg','Ubisoft',NULL,1),
(38,'Shadow of Tomb Rider', 15,79.50,'Gioco per XBOX','XBOX','2018-05-27','./img/tomb.jpg','Square Enix',NULL,1),
(39,'God of war', 15,79.50,'Gioco per PS4','PS4','2017-05-27','./img/god.jpg','Sony computer entertainment',NULL,1),
(40,'Jumanji', 15,79.50,'Gioco per XBOX','XBOX','2017-05-27','./img/jumanji.jpg','Bandai',NULL,1);



INSERT INTO Carrello VALUES
(1, 58.89, '2020-05-08', '2020-05-10', 'p.esposito47@studenti.unisa.it', 0),
(2, 46.89, '2020-05-08', '2020-05-10', 'a.russo324@studenti.unisa.it', 1),
(3, 93.89, '2020-05-08', '2020-05-10', 'n.serra2@studenti.unisa.it', 0),
(4, 46.89, '2020-05-08', null, 'a.russo324@studenti.unisa.it', 0);


INSERT INTO Inserimento VALUES
(1, 1, 1, 49.99),
(1, 2, 1, 8.90),
(2, 5, 1, 34.99),
(2, 8, 1, 11.90),
(3, 10, 1, 13.90),
(3, 7, 1, 79.99),
(4, 4, 1, 8.90),
(4, 6, 1, 11.90);
