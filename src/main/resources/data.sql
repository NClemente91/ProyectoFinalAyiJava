--CUSTOMERS DETAILS
INSERT INTO `ayi_project_final_db`.`customers_details` (`customer_detail_id`, `created_at`, `deleted`, `is_prime`, `score`, `updated_at`) SELECT 1, now(), 0, 1 , 12000, null
WHERE NOT EXISTS (SELECT * FROM `customers_details` WHERE customer_detail_id = 1);
INSERT INTO `ayi_project_final_db`.`customers_details` (`customer_detail_id`, `created_at`, `deleted`, `is_prime`, `score`, `updated_at`) SELECT 2, now(), 0, 0 , 3000, null
WHERE NOT EXISTS (SELECT * FROM `customers_details` WHERE customer_detail_id = 2);
INSERT INTO `ayi_project_final_db`.`customers_details` (`customer_detail_id`, `created_at`, `deleted`, `is_prime`, `score`, `updated_at`) SELECT 3, now(), 0, 1 , 10000, null
WHERE NOT EXISTS (SELECT * FROM `customers_details` WHERE customer_detail_id = 3);
INSERT INTO `ayi_project_final_db`.`customers_details` (`customer_detail_id`, `created_at`, `deleted`, `is_prime`, `score`, `updated_at`) SELECT 4, now(), 0, 1 , 9000, null
WHERE NOT EXISTS (SELECT * FROM `customers_details` WHERE customer_detail_id = 4);
INSERT INTO `ayi_project_final_db`.`customers_details` (`customer_detail_id`, `created_at`, `deleted`, `is_prime`, `score`, `updated_at`) SELECT 5, now(), 0, 0 , 4000, null
WHERE NOT EXISTS (SELECT * FROM `customers_details` WHERE customer_detail_id = 5);

--CUSTOMERS
INSERT INTO `ayi_project_final_db`.`customers` (`customer_id`, `created_at`, `date_of_birth`, `deleted`, `dni`, `last_name`, `name`, `updated_at`, `customer_detail_id`) SELECT 1, now(), '1990-01-12', 0, '34123456', 'Ramirez', 'Carlos', null, 4
WHERE NOT EXISTS (SELECT * FROM `customers` WHERE customer_id = 1);
INSERT INTO `ayi_project_final_db`.`customers` (`customer_id`, `created_at`, `date_of_birth`, `deleted`, `dni`, `last_name`, `name`, `updated_at`, `customer_detail_id`) SELECT 2, now(), '1992-05-01', 0, '36123456', 'Fernandez', 'Manuel', null, 2
WHERE NOT EXISTS (SELECT * FROM `customers` WHERE customer_id = 2);
INSERT INTO `ayi_project_final_db`.`customers` (`customer_id`, `created_at`, `date_of_birth`, `deleted`, `dni`, `last_name`, `name`, `updated_at`, `customer_detail_id`) SELECT 3, now(), '1989-08-19', 0, '33123456', 'Garay', 'Romina', null, 3
WHERE NOT EXISTS (SELECT * FROM `customers` WHERE customer_id = 3);
INSERT INTO `ayi_project_final_db`.`customers` (`customer_id`, `created_at`, `date_of_birth`, `deleted`, `dni`, `last_name`, `name`, `updated_at`, `customer_detail_id`) SELECT 4, now(), '1991-03-13', 0, '35123456', 'Peralta', 'Fernando', null, 5
WHERE NOT EXISTS (SELECT * FROM `customers` WHERE customer_id = 4);
INSERT INTO `ayi_project_final_db`.`customers` (`customer_id`, `created_at`, `date_of_birth`, `deleted`, `dni`, `last_name`, `name`, `updated_at`, `customer_detail_id`) SELECT 5, now(), '1993-11-26', 0, '36123456', 'Gutierrez', 'Sofia', null, 1
WHERE NOT EXISTS (SELECT * FROM `customers` WHERE customer_id = 5);

--ADDRESSES
INSERT INTO `ayi_project_final_db`.`addresses` (`address_id`, `apartment`, `city`, `country`, `created_at`, `deleted`, `postcode`, `province`, `street`, `street_number`, `updated_at`, `customer_id`)
SELECT 1, '2A', 'Rosario', 'Argentina', now(), 0, '2000', 'Santa Fe', 'Urquiza', '2546', null, 1
WHERE NOT EXISTS (SELECT * FROM `addresses` WHERE address_id = 1);
INSERT INTO `ayi_project_final_db`.`addresses` (`address_id`, `apartment`, `city`, `country`, `created_at`, `deleted`, `postcode`, `province`, `street`, `street_number`, `updated_at`, `customer_id`)
SELECT 2, '5B', 'Paraná', 'Argentina', now(), 0, '3100', 'Entre Rios', 'Sarmiento', '420', null, 1
WHERE NOT EXISTS (SELECT * FROM `addresses` WHERE address_id = 2);
INSERT INTO `ayi_project_final_db`.`addresses` (`address_id`, `apartment`, `city`, `country`, `created_at`, `deleted`, `postcode`, `province`, `street`, `street_number`, `updated_at`, `customer_id`)
SELECT 3, 'Casa', 'Córdoba', 'Argentina', now(), 0, '5000', 'Córdoba', 'San Martin', '100', null, 2
WHERE NOT EXISTS (SELECT * FROM `addresses` WHERE address_id = 3);
INSERT INTO `ayi_project_final_db`.`addresses` (`address_id`, `apartment`, `city`, `country`, `created_at`, `deleted`, `postcode`, `province`, `street`, `street_number`, `updated_at`, `customer_id`)
SELECT 4, '1D', 'Santa Fe', 'Argentina', now(), 0, '3000', 'Santa Fe', 'Belgrano', '1234', null, 3
WHERE NOT EXISTS (SELECT * FROM `addresses` WHERE address_id = 4);
INSERT INTO `ayi_project_final_db`.`addresses` (`address_id`, `apartment`, `city`, `country`, `created_at`, `deleted`, `postcode`, `province`, `street`, `street_number`, `updated_at`, `customer_id`)
SELECT 5, 'Casa', 'Santa Fe', 'Argentina', now(), 0, '3000', 'Santa Fe', 'Corrientes', '320', null, 3
WHERE NOT EXISTS (SELECT * FROM `addresses` WHERE address_id = 5);
INSERT INTO `ayi_project_final_db`.`addresses` (`address_id`, `apartment`, `city`, `country`, `created_at`, `deleted`, `postcode`, `province`, `street`, `street_number`, `updated_at`, `customer_id`)
SELECT 6, 'Casa', 'Paraná', 'Argentina', now(), 0, '3100', 'Entre Rios', 'Colon', '123', null, 4
WHERE NOT EXISTS (SELECT * FROM `addresses` WHERE address_id = 6);
INSERT INTO `ayi_project_final_db`.`addresses` (`address_id`, `apartment`, `city`, `country`, `created_at`, `deleted`, `postcode`, `province`, `street`, `street_number`, `updated_at`, `customer_id`)
SELECT 7, '7C', 'Córdoba', 'Argentina', now(), 0, '5000', 'Córdoba', 'Velez', '900', null, 5
WHERE NOT EXISTS (SELECT * FROM `addresses` WHERE address_id = 7);
INSERT INTO `ayi_project_final_db`.`addresses` (`address_id`, `apartment`, `city`, `country`, `created_at`, `deleted`, `postcode`, `province`, `street`, `street_number`, `updated_at`, `customer_id`)
SELECT 8, 'Casa', 'Córdoba', 'Argentina', now(), 0, '5000', 'Córdoba', 'Alameda', '456', null, 5
WHERE NOT EXISTS (SELECT * FROM `addresses` WHERE address_id = 8);

--INVOICES
INSERT INTO `ayi_project_final_db`.`invoices` (`invoice_id`, `created_at`, `deleted`, `description`, `invoice_type`, `total`, `updated_at`, `customer_id`) SELECT 1, now(), 0, 'TV Smart', 'A', '35000', null, 1
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 1);
INSERT INTO `ayi_project_final_db`.`invoices` (`invoice_id`, `created_at`, `deleted`, `description`, `invoice_type`, `total`, `updated_at`, `customer_id`) SELECT 2, now(), 0, 'Notebook', 'B', '45000', null, 2
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 2);
INSERT INTO `ayi_project_final_db`.`invoices` (`invoice_id`, `created_at`, `deleted`, `description`, `invoice_type`, `total`, `updated_at`, `customer_id`) SELECT 3, now(), 0, 'Cafetera', 'C', '35000', null, 2
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 3);
INSERT INTO `ayi_project_final_db`.`invoices` (`invoice_id`, `created_at`, `deleted`, `description`, `invoice_type`, `total`, `updated_at`, `customer_id`) SELECT 4, now(), 0, 'Aspiradora', 'C', '35000', null, 2
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 4);
INSERT INTO `ayi_project_final_db`.`invoices` (`invoice_id`, `created_at`, `deleted`, `description`, `invoice_type`, `total`, `updated_at`, `customer_id`) SELECT 5, now(), 0, 'Monitor', 'B', '35000', null, 2
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 5);
INSERT INTO `ayi_project_final_db`.`invoices` (`invoice_id`, `created_at`, `deleted`, `description`, `invoice_type`, `total`, `updated_at`, `customer_id`) SELECT 6, now(), 0, 'Licuadora', 'A', '35000', null, 3
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 6);
INSERT INTO `ayi_project_final_db`.`invoices` (`invoice_id`, `created_at`, `deleted`, `description`, `invoice_type`, `total`, `updated_at`, `customer_id`) SELECT 7, now(), 0, 'Horno', 'A', '35000', null, 3
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 7);
INSERT INTO `ayi_project_final_db`.`invoices` (`invoice_id`, `created_at`, `deleted`, `description`, `invoice_type`, `total`, `updated_at`, `customer_id`) SELECT 8, now(), 0, 'Estufa', 'B', '35000', null, 3
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 8);
INSERT INTO `ayi_project_final_db`.`invoices` (`invoice_id`, `created_at`, `deleted`, `description`, `invoice_type`, `total`, `updated_at`, `customer_id`) SELECT 9, now(), 0, 'Anafe', 'C', '35000', null, 4
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 9);
INSERT INTO `ayi_project_final_db`.`invoices` (`invoice_id`, `created_at`, `deleted`, `description`, `invoice_type`, `total`, `updated_at`, `customer_id`) SELECT 10, now(), 0, 'Celular', 'C', '35000', null, 5
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 10);
INSERT INTO `ayi_project_final_db`.`invoices` (`invoice_id`, `created_at`, `deleted`, `description`, `invoice_type`, `total`, `updated_at`, `customer_id`) SELECT 11, now(), 0, 'Lámpara', 'B', '35000', null, 5
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 11);
INSERT INTO `ayi_project_final_db`.`invoices` (`invoice_id`, `created_at`, `deleted`, `description`, `invoice_type`, `total`, `updated_at`, `customer_id`) SELECT 12, now(), 0, 'Escritorio', 'A', '35000', null, 5
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 12);


