
-- Retorna o nome, descrição e ID das coleções de um determinado user

SELECT Catalogue.name, Catalogue.description, Collection.idCollection FROM User JOIN Collection 
ON User.userId = Collection.user JOIN Catalogue 
ON Collection.catalogue = Catalogue.idCatalogue WHERE User.userEmail LIKE 'email';


-- Retorna o nome, descrição, categoria e Id de todos os items de uma determinada coleção

SELECT Item.name, Item.description, Item.category, Item.idItem FROM Collection JOIN Item 
ON Collection.catalogue = Item.catalogueId WHERE Collection.idCollection = 0;


-- Retorna a descrição, editora, lingua e data de publicação de um livro de um determinado utilizador

SELECT userBook.description, Edition.publisher, Edition.langCode, Edition.publicationDate FROM userBook JOIN Edition 
ON userBook.idEdition = Edition.idEdition WHERE userBook.idCollection LIKE 0 AND userBook.idItem LIKE 2 ;


-- Retorna a password de um determinado user, para efetuar o login

SELECT User.password FROM User WHERE User.userEmail LIKE 'email';

-- Retorna o email dos users com um determinado livro, retorna mais do que uma vez um user se ele tiver o livro repetido 

select User.userEmail from userBook Join Item on userBook.idItem = Item.idItem Join Collection on userBook.idCollection = Collection.idCollection 
Join User on Collection.user = User.userId where Item.idItem = 2


-- Retorna o userEmail e o numero de livros igauis que um utilizador têm se este utilizador tiver mais do que 1 livro igual

SELECT User.userEmail, COUNT(*) AS number FROM userBook JOIN Item ON userBook.idItem = Item.idItem 
JOIN Collection on userBook.idCollection = Collection.idCollection JOIN User on Collection.user = User.userId WHERE Item.idItem = 2 
GROUP BY userEmail HAVING COUNT(*) > 1



-- Insere na tabela User um novo utilizador quando este se regista na aplicação

Insert into User (userEmail, password) Values ('email','password');



-- Retorna o nome e a descrição de todos os Catalogos de banda desenhada.

Select Catalogue.name, Catalogue.description, Catalogue.idCatalogue from Catalogue;


-- Retorna o userId de um determinado utilizador 

select User.userId FROM User WHERE User.userEmail LIKE 'email';


-- Insere num determinado utilizador a sua nova coleção

insert into Collection (user, catalogue) value (userId,catalogueId);


-- Retorna o userEmail onde o userEmail é igual ao inserido, serve para impedir um registo de um novo email que ja esteja 
-- na base de dados.

SELECT User.userEmail FROM User WHERE User.userEmail LIKE 'email';
