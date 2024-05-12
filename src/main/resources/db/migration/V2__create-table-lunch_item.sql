CREATE TABLE lunch_item (
     id BIGINT NOT NULL AUTO_INCREMENT,
     name VARCHAR(100) NOT NULL UNIQUE,
     type VARCHAR(100) NOT NULL,
     price DECIMAL(3,2) NOT NULL,
     PRIMARY KEY(id)
);