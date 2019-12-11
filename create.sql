-- Create Database: `Ccollector`
--
  Create Database Ccollector;
  Use Ccollector;
-- --------------------------------------------------------

--
-- Table structure for table `Catalogue`
--

CREATE TABLE `Catalogue` (
  `idCatalogue` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateAdded` datetime DEFAULT NULL,
  `lastModified` datetime DEFAULT NULL,
  PRIMARY KEY (`idCatalogue`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Category`
--

CREATE TABLE `Category` (
  `name` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`name`)
); 

-- --------------------------------------------------------

--
-- Table structure for table `Collection`
--

CREATE TABLE `Collection` (
  `idCollection` int(11) NOT NULL AUTO_INCREMENT,
  `user` int(11) DEFAULT NULL,
  `catalogue` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCollection`)
);

-- --------------------------------------------------------

--
-- Table structure for table `Edition`
--

CREATE TABLE `Edition` (
  `idEdition` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `publisher` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `langCode` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idItem` int(11) DEFAULT NULL,
  `publicationDate` int(4) DEFAULT NULL,
  `image` mediumblob,
  PRIMARY KEY (`idEdition`)
);

-- --------------------------------------------------------

--
-- Table structure for table `Item`
--

CREATE TABLE `Item` (
  `idItem` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `catalogueId` int(11) DEFAULT NULL,
  `description` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idItem`)
);

-- --------------------------------------------------------

--
-- Table structure for table `itemCategory`
--

CREATE TABLE `itemCategory` (
  `categoryName` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `itemId` int(11) DEFAULT NULL
);

-- --------------------------------------------------------

--
-- Table structure for table `Message`
--

CREATE TABLE `Message` (
  `idMessage` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(140) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sender` int(11) DEFAULT NULL,
  `receiver` int(11) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `isRead` boolean NOT NULL,
  PRIMARY KEY (`idMessage`)
);

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userEmail` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `reputation` int(11) NOT NULL DEFAULT '0',
  `password` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`userId`)
);

-- --------------------------------------------------------

--
-- Table structure for table `userBook`
--

CREATE TABLE `userBook` (
  `bookId` int(11) NOT NULL AUTO_INCREMENT,
  `idItem` int(11) DEFAULT NULL,
  `idCollection` int(11) DEFAULT NULL,
  `idEdition` int(11) DEFAULT NULL,
  `description` varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`bookId`)
);


--
-- Indexes for table `Collection`
--
ALTER TABLE `Collection`
  ADD KEY `userFK` (`user`),
  ADD KEY `catalogueFk` (`catalogue`);

--
-- Indexes for table `Edition`
--
ALTER TABLE `Edition`
  ADD KEY `idItemFk` (`idItem`);

--
-- Indexes for table `Item`
--
ALTER TABLE `Item`
  ADD KEY `catalogueIdFk` (`catalogueId`);

--
-- Indexes for table `itemCategory`
--
ALTER TABLE `itemCategory`
  ADD KEY `categoryFK` (`categoryName`),
  ADD KEY `itemFk` (`itemId`);

--
-- Indexes for table `Message`
--
ALTER TABLE `Message`
  ADD KEY `senderFk` (`sender`),
  ADD KEY `receiverFk` (`receiver`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD UNIQUE KEY `userEmail` (`userEmail`);

--
-- Indexes for table `userBook`
--
ALTER TABLE `userBook`
  ADD KEY `item_Fk` (`idItem`),
  ADD KEY `collectionFk` (`idCollection`),
  ADD KEY `editionFk` (`idEdition`);


--
-- Constraints for table `Collection`
--
ALTER TABLE `Collection`
  ADD CONSTRAINT `catalogueFk` FOREIGN KEY (`catalogue`) REFERENCES `Catalogue` (`idcatalogue`),
  ADD CONSTRAINT `userFK` FOREIGN KEY (`user`) REFERENCES `User` (`userid`);

--
-- Constraints for table `Edition`
--
ALTER TABLE `Edition`
  ADD CONSTRAINT `idItemFk` FOREIGN KEY (`idItem`) REFERENCES `Item` (`iditem`);

--
-- Constraints for table `Item`
--
ALTER TABLE `Item`
  ADD CONSTRAINT `catalogueIdFk` FOREIGN KEY (`catalogueId`) REFERENCES `Catalogue` (`idcatalogue`);

--
-- Constraints for table `itemCategory`
--
ALTER TABLE `itemCategory`
  ADD CONSTRAINT `categoryFK` FOREIGN KEY (`categoryName`) REFERENCES `Category` (`name`),
  ADD CONSTRAINT `itemFk` FOREIGN KEY (`itemId`) REFERENCES `Item` (`iditem`);

--
-- Constraints for table `Message`
--
ALTER TABLE `Message`
  ADD CONSTRAINT `receiverFk` FOREIGN KEY (`receiver`) REFERENCES `User` (`userid`),
  ADD CONSTRAINT `senderFk` FOREIGN KEY (`sender`) REFERENCES `User` (`userid`);

--
-- Constraints for table `userBook`
--
ALTER TABLE `userBook`
  ADD CONSTRAINT `collectionFk` FOREIGN KEY (`idCollection`) REFERENCES `Collection` (`idcollection`),
  ADD CONSTRAINT `editionFk` FOREIGN KEY (`idEdition`) REFERENCES `Edition` (`idedition`),
  ADD CONSTRAINT `item_Fk` FOREIGN KEY (`idItem`) REFERENCES `Item` (`iditem`);

-- 
-- INDEXES
--

CREATE INDEX Email 
	on User (userEmail);


  
COMMIT;


