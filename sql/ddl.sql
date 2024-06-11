-- CREATE, DROP, ALTER

DROP database "test_data_db";

CREATE database "test_data_db";

CREATE TABLE charge_test_data (
id INT PRIMARY KEY,
charge_input INT not null,
charge_expected INT not null
);