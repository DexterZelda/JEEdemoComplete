CREATE TABLE Notes (
	id INT IDENTITY(1,1) CONSTRAINT pk_notes PRIMARY KEY,
	contenu VARCHAR(500) NOT NULL,
	date DATETIME NOT NULL
);

INSERT INTO Notes(contenu, date) VALUES ('Acheter croquettes', GETDATE());
INSERT INTO Notes(contenu, date) VALUES ('Soirée Champagne (obsiously) le 16/02 à 20h', GETDATE());