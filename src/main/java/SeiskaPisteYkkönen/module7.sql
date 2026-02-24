DROP DATABASE IF EXISTS currency_db;
CREATE DATABASE currency_db;
USE currency_db;

CREATE TABLE currency (
    abbreviation VARCHAR(3) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    rate DECIMAL(15, 6) NOT NULL
);

INSERT INTO currency (abbreviation, name, rate) VALUES
                                                    ('AUD', 'Australian Dollar', 1.648000),
                                                    ('CHF', 'Swiss Franc', 0.951200),
                                                    ('EUR', 'Euro', 1.000000),
                                                    ('GBP', 'British Pound', 0.854300),
                                                    ('JPY', 'Japanese Yen', 162.750000),
                                                    ('NOK', 'Norwegian Krone', 11.412000),
                                                    ('SEK', 'Swedish Krona', 11.245000),
                                                    ('USD', 'US Dollar', 1.082500);


DROP USER IF EXISTS 'appuser'@'localhost';
CREATE USER 'appuser'@'localhost' IDENTIFIED BY 'salasana123';
GRANT ALL PRIVILEGES ON currency_db.* TO 'appuser'@'localhost';
