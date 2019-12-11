/* Tabela Category*/
INSERT INTO Category (name)
VALUES('Adventure');

/* Tabela Catalogue */
INSERT INTO Catalogue  (idCatalogue, name, description, dateAdded, lastModified)
VALUES(0, 'The Adventures of Tintin', 'Classic Belgian comic written by Herge about the reporter Tintin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

/* Tabela Item */
INSERT INTO Item (idItem, name, catalogueId, description, category)
VALUES(0, 'Tintin in the Land of the Soviets', 0, 'Tintin is sent to the Soviet Union', 'Adventure');

INSERT INTO Item (idItem, name, catalogueId, description, category)
VALUES(1, 'Tintin the Congo', 0, 'Tintin is sent to the Congo','Adventure');

INSERT INTO Item (idItem, name, catalogueId, description, category)
VALUES(2, 'Tintin in America', 0, 'Tintin has an adventure in America','Adventure');

/* Tabela Edition */
INSERT INTO Edition (idEdition, name, publisher, langCode, idItem, publicationDate)
VALUES(0,  'Tintin in the Land of the Soviets', 'Sundancer', 'EN', 0, 1989);

INSERT INTO Edition (idEdition, name, publisher, langCode, idItem, publicationDate)
VALUES(1, 'Tintin au Congo', 'Le Petit Vingtième', 'FR', 1, 1931);

INSERT INTO Edition (idEdition, name, publisher, langCode, idItem, publicationDate)
VALUES(2, 'Tintin in America', 'Le Petit Vingtième', 'FR', 2, 1932);

/* Tabela ItemCategory */
INSERT INTO itemCategory (categoryName, itemId)
Values ('Adventure', 0);


/* Tabela User */
INSERT INTO User (userId, userEmail, reputation, password)
Value (0, 'carlwork@example.com', 0, 1234);

INSERT INTO User (userId, userEmail, reputation, password)
Value (1, 'tomasand@example.com', 0, 4321);

INSERT INTO User (userId, userEmail, reputation, password)
Value (2, 'luis82@example.com', 0, 7532);

/* Tabela Collection */

Insert INTO Collection (idCollection, user, catalogue)
Value (0, 0, 0);

Insert INTO Collection (idCollection, user, catalogue)
Value (1, 1, 0);

Insert INTO Collection (idCollection, user, catalogue)
Value (2, 2, 0);


/* Tabela userBook */

/*Carlos*/
INSERT INTO userBook (bookId, idItem, idCollection, idEdition, description)
Value (0, 0, 0, 0, 'The book is in a perfect shape');

INSERT INTO userBook (bookId, idItem, idCollection, idEdition, description)
Value (1, 1, 0, 1, 'There is a page with a stain');

/*Tomas*/
INSERT INTO userBook (bookId, idItem, idCollection, idEdition, description)
Value (2, 0, 1, 0, 'Book with some stains');

INSERT INTO userBook (bookId, idItem, idCollection, idEdition, description)
Value (3, 1, 1, 1, 'Book in a perfect shape');

INSERT INTO userBook (bookId, idItem, idCollection, idEdition, description)
Value (4, 2, 1, 2, 'Page 12 is missing');

/*Luis*/

INSERT INTO userBook (bookId, idItem, idCollection, idEdition, description)
Value (5, 0, 2, 0, 'Book is in a perfect shape');

INSERT INTO userBook (bookId, idItem, idCollection, idEdition, description)
Value (6, 1, 2, 1, 'Book is in a perfect shape');

INSERT INTO userBook (bookId, idItem, idCollection, idEdition, description)
Value (7, 2, 2, 2, 'Book is in a perfect shape');

INSERT INTO userBook (bookId, idItem, idCollection, idEdition, description)
Value (8, 2, 2, 2, 'Book is in a perfect shape');

