-- 1. Pudotetaan vanha kanta ja luodaan uusi
DROP DATABASE IF EXISTS currency_db;
CREATE DATABASE currency_db;
USE currency_db;

-- 2. Luodaan taulu
CREATE TABLE currency (
                          abbreviation VARCHAR(10) PRIMARY KEY,
                          name VARCHAR(50) NOT NULL,
                          rate DOUBLE NOT NULL
);

-- 3. Lisätään vähintään 8 valuuttaa
INSERT INTO currency (abbreviation, name, rate) VALUES
                                                    ('EUR', 'Euro', 1.0),
                                                    ('USD', 'US Dollar', 1.08),
                                                    ('GBP', 'British Pound', 0.86),
                                                    ('SEK', 'Swedish Krona', 11.20),
                                                    ('JPY', 'Japanese Yen', 160.50),
                                                    ('CHF', 'Swiss Franc', 0.95),
                                                    ('CAD', 'Canadian Dollar', 1.47),
                                                    ('AUD', 'Australian Dollar', 1.65);

-- 4. Käyttäjän hallinta: pudotetaan ja luodaan uudestaan
DROP USER IF EXISTS 'appuser'@'localhost';
CREATE USER 'appuser'@'localhost' IDENTIFIED BY 'salasana123';

-- 5. Annetaan vain tarvittavat oikeudet
GRANT SELECT, INSERT, UPDATE, DELETE ON currency_db.* TO 'appuser'@'localhost';