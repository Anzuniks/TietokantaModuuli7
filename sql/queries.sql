-- 1. Kaikki valuutat
SELECT * FROM currency;

-- 2. Valuutta EUR
SELECT * FROM currency WHERE abbreviation = 'EUR';

-- 3. Valuuttojen lukumäärä
SELECT COUNT(*) FROM currency;

-- 4. Valuutta, jolla on korkein kurssi
SELECT * FROM currency ORDER BY rate DESC LIMIT 1;