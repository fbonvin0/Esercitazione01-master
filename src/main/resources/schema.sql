CREATE TABLE books
(
    userId SERIAL PRIMARY KEY,
    titolo varchar(100) NOT NULL,
    autore varchar(100) NOT NULL,
    annoPB varchar(100) NOT NULL,
    link varchar(500) DEFAULT NULL

);
