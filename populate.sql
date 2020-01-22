
--
-- Tabela Catalogue
--
INSERT INTO `Catalogue` (`idCatalogue`, `name`, `description`, `dateAdded`, `lastModified`) VALUES
(0, 'The Adventures of Tintin', 'Classic Belgian comic written by Herge about the reporter Tintin', '2019-12-11 11:13:35', '2019-12-11 11:13:35'),
(1, 'Blacksad', 'Private detective solves crime in 1950 america', '2020-01-09 08:59:08', '2020-01-09 08:59:08');

--
-- Tabela Category
--
INSERT INTO `Category` (`name`) VALUES
('Adventure'),
('Criminal');

--
-- Tabela User
--
INSERT INTO `User` (`userId`, `userEmail`, `reputation`, `password`) VALUES
(0, 'carlwork@example.com', 0, '1234'),
(1, 'tomasand@example.com', 0, '4321'),
(2, 'luis82@example.com', 0, '7532');

--
-- Tabela Collection
--
INSERT INTO `Collection` (`idCollection`, `user`, `catalogue`) VALUES
(0, 0, 0),
(1, 1, 0),
(2, 2, 0);


--
-- Tabela Item
--
INSERT INTO `Item` (`idItem`, `name`, `catalogueId`, `description`, `category`) VALUES
(0, 'Tintin in the Land of the Soviets', 0, 'Tintin is sent to the Soviet Union', 'Adventure'),
(1, 'Tintin the Congo', 0, 'Tintin is sent to the Congo', 'Adventure'),
(2, 'Tintin in America', 0, 'Tintin has an adventure in America', 'Adventure'),
(4, 'Somewhere Within the Shadows', 1, 'Actress Natalia Willfred has been murdered', 'Criminal'),
(5, 'Artic Nation', 1, 'Lynching and racially motivated crime is on the rise', 'Criminal'),
(6, 'Red Soul', 1, 'Blacksad is employed has a bodyguard', 'Criminal');

--
-- Tabela Edition
--
INSERT INTO `Edition` (`idEdition`, `name`, `publisher`, `langCode`, `idItem`, `publicationDate`, `image`) VALUES
(0, 'Tintin in the Land of the Soviets', 'Sundancer', 'EN', 0, 1989, NULL),
(1, 'Tintin au Congo', 'Le Petit Vingtième', 'FR', 1, 1931, NULL),
(2, 'Tintin in America', 'Le Petit Vingtième', 'FR', 2, 1932, NULL),
(3, 'Somewhere Within the Shadows', 'Dark Horse', 'EN', 4, 2000, NULL),
(4, 'Artic Nation', 'Dark Horse', 'EN', 5, 2002, NULL),
(5, 'Red Soul', 'Dark Horse', 'EN', 6, 2003, NULL);

--
-- Tabela ItemCategory
--
INSERT INTO `itemCategory` (`categoryName`, `itemId`) VALUES
('Adventure', 0),
('Criminal', 1);

--
-- Tabela userBook
--
INSERT INTO `userBook` (`bookId`, `idItem`, `idCollection`, `idEdition`, `description`) VALUES
(0, 0, 0, 0, 'The book is in a perfect shape'),
(1, 1, 0, 1, 'There is a page with a stain'),
(2, 0, 1, 0, 'Book with some stains'),
(3, 1, 1, 1, 'Book in a perfect shape'),
(4, 2, 1, 2, 'Page 12 is missing'),
(5, 0, 2, 0, 'Book is in a perfect shape'),
(6, 1, 2, 1, 'Book is in a perfect shape'),
(7, 2, 2, 2, 'Book is in a perfect shape'),
(8, 2, 2, 2, 'Book is in a perfect shape');