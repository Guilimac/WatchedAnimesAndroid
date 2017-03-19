CREATE TABLE user (
   id integer NOT NULL PRIMARY KEY,
   username varchar(255) NOT NULL,
   islogged integer NOT NULL  DEFAULT 0
);

CREATE TABLE anime (
   id integer NOT NULL PRIMARY KEY AUTOINCREMENT,
   user_id integer NOT NULL,
   name varchar(255) NOT NULL,
   description varchar(255) NULL,
   rating real NOT NULL,
   FOREIGN KEY (user_id) REFERENCES user (id)
);