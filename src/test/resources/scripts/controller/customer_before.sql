INSERT INTO products(id, buying_cost, day_of_week, frequency, name, selling_cost, type) VALUES
    (10000, 1.2, null, 'DAILY', 'Athlone Daily', 1.4, 'NEWSPAPER'),
    (10001, 1.0, 5, 'WEEKLY', 'Athlone Weekly', 1.4, 'MAGAZINE');
INSERT INTO route(id,name) VALUES (10000, 'Athlone');
INSERT INTO newspaper_delivery.customers (id, address, full_name, holiday, phone_no, subscription, route) VALUES
    (10001, 'ASDASD', 'Aditya Gupta', null, 1231231222, '[10000,10001]', 10000);
