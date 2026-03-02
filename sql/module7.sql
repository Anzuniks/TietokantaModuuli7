-- Moduuli 7 - Tietokannan luonti
-- Luo tietokanta ja taulu valuutoille

CREATE DATABASE IF NOT EXISTS currency_db;
USE currency_db;

-- Luo käyttäjä jos ei ole
CREATE USER IF NOT EXISTS 'appuser'@'localhost' IDENTIFIED BY 'salasana123';
GRANT ALL PRIVILEGES ON currency_db.* TO 'appuser'@'localhost';
FLUSH PRIVILEGES;

-- Luo valuuttataulu
CREATE TABLE IF NOT EXISTS currency (
    abbreviation VARCHAR(10) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    rate DOUBLE NOT NULL
);

-- Lisää esimerkkidataa
INSERT INTO currency (abbreviation, name, rate) VALUES
('EUR', 'Euro', 1.0),
('USD', 'US Dollar', 1.08),
('GBP', 'British Pound', 0.86),
('SEK', 'Swedish Krona', 11.20),
('JPY', 'Japanese Yen', 160.50)
ON DUPLICATE KEY UPDATE name=VALUES(name), rate=VALUES(rate);

