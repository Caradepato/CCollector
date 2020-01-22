--
-- Criar Base de dados
--
CREATE Ccollector;
USE Ccollector;

--
-- Estrutura da tabela Catalogue
--
CREATE TABLE `Catalogue` (
  `idCatalogue` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateAdded` datetime DEFAULT NULL,
  `lastModified` datetime DEFAULT NULL,
  PRIMARY KEY (`idCatalogue`)
);

--
-- Estrutura da tabela Category
--
CREATE TABLE `Category` (
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`name`)
);

--
-- Estrutura da tabela User
--
CREATE TABLE `User` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userEmail` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `reputation` int(11) NOT NULL DEFAULT '0',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`userId`)
);

--
-- Estrutura da tabela Collection
--
CREATE TABLE `Collection` (
  `idCollection` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) DEFAULT NULL,
  `catalogue` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCollection`),
  CONSTRAINT `catalogueFk` FOREIGN KEY (`catalogue`) REFERENCES `Catalogue` (`idcatalogue`),
  CONSTRAINT `userFK` FOREIGN KEY (`user`) REFERENCES `User` (`userid`)
);

--
-- Estrutura da tabela Item
--
CREATE TABLE `Item` (
  `idItem` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `catalogueId` int(11) DEFAULT NULL,
  `description` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `category` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idItem`),
  CONSTRAINT `catalogueIdFk` FOREIGN KEY (`catalogueId`) REFERENCES `Catalogue` (`idcatalogue`)
);

--
-- Estrutura da tabela Edition
--
CREATE TABLE `Edition` (
  `idEdition` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `publisher` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `langCode` varchar(3) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `idItem` int(11) DEFAULT NULL,
  `publicationDate` int(4) DEFAULT NULL,
  `image` mediumblob,
  PRIMARY KEY (`idEdition`),
  CONSTRAINT `idItemFk` FOREIGN KEY (`idItem`) REFERENCES `Item` (`iditem`)
);

--
-- Estrutura da tabela itemCategory
--
CREATE TABLE `itemCategory` (
  `categoryName` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `itemId` int(11) DEFAULT NULL,
  CONSTRAINT `categoryFK` FOREIGN KEY (`categoryName`) REFERENCES `Category` (`name`),
  CONSTRAINT `itemFk` FOREIGN KEY (`itemId`) REFERENCES `Item` (`iditem`)
);

--
-- Estrutura da tabela Message
--
CREATE TABLE `Message` (
  `idMessage` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(140) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `sender` int(11) DEFAULT NULL,
  `receiver` int(11) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `isRead` tinyint(1) NOT NULL,
  PRIMARY KEY (`idMessage`),
  CONSTRAINT `receiverFk` FOREIGN KEY (`receiver`) REFERENCES `User` (`userid`),
  CONSTRAINT `senderFk` FOREIGN KEY (`sender`) REFERENCES `User` (`userid`)
);

--
-- Estrutura da tabela userBook
--
CREATE TABLE `userBook` (
  `bookId` int(11) NOT NULL AUTO_INCREMENT,
  `idItem` int(11) DEFAULT NULL,
  `idCollection` int(11) DEFAULT NULL,
  `idEdition` int(11) DEFAULT NULL,
  `archived` tinyint(1) NOT NULL DEFAULT '0',
  `description` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
   PRIMARY KEY (`bookId`),
   CONSTRAINT `collectionFk` FOREIGN KEY (`idCollection`) REFERENCES `Collection` (`idcollection`),
   CONSTRAINT `editionFk` FOREIGN KEY (`idEdition`) REFERENCES `Edition` (`idedition`),
   CONSTRAINT `item_Fk` FOREIGN KEY (`idItem`) REFERENCES `Item` (`iditem`)
);

COMMIT;

