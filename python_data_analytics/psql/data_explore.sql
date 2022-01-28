-- Show table schema
\d+ retail;

-- Show first 10 rows
SELECT * FROM retail limit 10;

-- Check # of records
SELECT COUNT(*) FROM retail;

-- Number of clients (unique client ID)
SELECT COUNT(DISTINCT customer_id) FROM retail;

-- Invoice date range (max/min dates)
SELECT MAX(invoice_date) AS max, MIN(invoice_date) AS min FROM retail;

-- Number of SKU/merchants (unique stock code)
SELECT COUNT(DISTINCT stock_code) FROM retail;

-- Calculate average invoice amount excluding invoices with a negative amount (cancelled orders have a negative amount)
-- Attempt 1
SELECT AVG(avg) FROM (SELECT SUM(quantity * unit_price) AS avg FROM retail GROUP BY invoice_no HAVING SUM(quantity) > 0) AS invoices;

-- Attempt 2
CREATE VIEW invoices AS
    SELECT invoice_no, SUM(quantity * unit_price) AS amount
    FROM retail
    GROUP BY invoice_no
    HAVING SUM(quantity) > 0;
SELECT AVG(amount) FROM invoices;

-- Attempt 3
CREATE VIEW complete_orders AS
    SELECT invoice_no, quantity, unit_price
    FROM retail
    WHERE quantity > 0;
SELECT AVG(avg) FROM (SELECT SUM(quantity * unit_price) AS avg FROM complete_orders GROUP BY invoice_no) AS invoices;
SELECT COUNT(quantity) FROM complete_orders WHERE quantity < 0;

-- Calculate total revenue (sum of unit_price * quantity)
SELECT SUM(unit_price * quantity) AS sum FROM retail;

-- Calculate total revenue by YYYYMM
ALTER TABLE retail ADD yyyymm integer;
UPDATE retail SET yyyymm=((CAST(EXTRACT(YEAR FROM invoice_date) AS INTEGER) * 100) + CAST(EXTRACT(MONTH FROM invoice_date) AS INTEGER));
SELECT yyyymm, SUM(quantity * unit_price) AS sum FROM retail GROUP BY yyyymm ORDER BY yyyymm ASC LIMIT 25;