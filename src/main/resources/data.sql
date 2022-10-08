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
INSERT INTO `ayi_project_final_db`.`customers` (`customer_id`, `created_at`, `date_of_birth`, `deleted`, `dni`, `last_name`, `name`, `updated_at`, `customer_detail_id`) SELECT 1, now(), '1990-01-12', 0, '34123456', 'Carlos', 'Ramirez', null, 4
WHERE NOT EXISTS (SELECT * FROM `customers` WHERE customer_id = 1);
INSERT INTO `ayi_project_final_db`.`customers` (`customer_id`, `created_at`, `date_of_birth`, `deleted`, `dni`, `last_name`, `name`, `updated_at`, `customer_detail_id`) SELECT 2, now(), '1992-05-01', 0, '36123456', 'Manuel', 'Fernandez', null, 2
WHERE NOT EXISTS (SELECT * FROM `customers` WHERE customer_id = 2);
INSERT INTO `ayi_project_final_db`.`customers` (`customer_id`, `created_at`, `date_of_birth`, `deleted`, `dni`, `last_name`, `name`, `updated_at`, `customer_detail_id`) SELECT 3, now(), '1989-08-19', 0, '33123456', 'Romina', 'Garay', null, 3
WHERE NOT EXISTS (SELECT * FROM `customers` WHERE customer_id = 3);
INSERT INTO `ayi_project_final_db`.`customers` (`customer_id`, `created_at`, `date_of_birth`, `deleted`, `dni`, `last_name`, `name`, `updated_at`, `customer_detail_id`) SELECT 4, now(), '1991-03-13', 0, '35123456', 'Fernando', 'Peralta', null, 5
WHERE NOT EXISTS (SELECT * FROM `customers` WHERE customer_id = 4);
INSERT INTO `ayi_project_final_db`.`customers` (`customer_id`, `created_at`, `date_of_birth`, `deleted`, `dni`, `last_name`, `name`, `updated_at`, `customer_detail_id`) SELECT 5, now(), '1993-11-26', 0, '36123456', 'Sofia', 'Gutierrez', null, 1
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


--
--UPDATE `ayi_final_project`.`clients` SET `client_detail_id` = '1' WHERE (`client_id` = '1');
--UPDATE `ayi_final_project`.`clients` SET `client_detail_id` = '2' WHERE (`client_id` = '2');
--UPDATE `ayi_final_project`.`clients` SET `client_detail_id` = '3' WHERE (`client_id` = '3');
--UPDATE `ayi_final_project`.`clients` SET `client_detail_id` = '4' WHERE (`client_id` = '4');
--UPDATE `ayi_final_project`.`clients` SET `client_detail_id` = '5' WHERE (`client_id` = '5');
--UPDATE `ayi_final_project`.`clients` SET `client_detail_id` = '6' WHERE (`client_id` = '6');
--
--INSERT INTO `ayi_final_project`.`invoices` (`invoice_id`, `description`,  `total_amount`, `client_id`) SELECT '1', 'TV Smart Samsung','750', '1'
--WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 1);
--INSERT INTO `ayi_final_project`.`invoices` (`invoice_id`, `description`,  `total_amount`, `client_id`) SELECT '2', 'Heladera Samsung','690', '2'
--WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 2);
--INSERT INTO `ayi_final_project`.`invoices` (`invoice_id`, `description`,  `total_amount`, `client_id`) SELECT '3', 'Aspiradora Samsung', '120', '3'
--WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 3);
--INSERT INTO `ayi_final_project`.`invoices` (`invoice_id`, `description`,  `total_amount`, `client_id`) SELECT '4', 'Escritorio','230', '1'
--WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 4);
--INSERT INTO `ayi_final_project`.`invoices` (`invoice_id`, `description`,  `total_amount`, `client_id`) SELECT '5', 'Lavarropas Samsung','480', '4'
--WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 5);
--INSERT INTO `ayi_final_project`.`invoices` (`invoice_id`, `description`,  `total_amount`, `client_id`) SELECT '6', 'Licuadora','140', '5'
--WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 6);
--INSERT INTO `ayi_final_project`.`invoices` (`invoice_id`, `description`,  `total_amount`, `client_id`) SELECT '7', 'Perchero', '90', '6'
--WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE invoice_id = 7);
--
--
--INSERT INTO `ayi_final_project`.`address` (`address_id`, `apartment_unit`, `city`, `country`, `floor`,  `number`, `postal_code`, `province`, `street`, `client_id`)
--SELECT '1', 'B', 'Rosario', 'Argentina', '3', '5561', '2000', 'Santa Fe', 'Uquiza', '1'
--WHERE NOT EXISTS (SELECT * FROM `address` WHERE address_id = 1);
--INSERT INTO `ayi_final_project`.`address` (`address_id`, `apartment_unit`, `city`, `country`, `floor`,  `number`, `postal_code`, `province`, `street`, `client_id`)
--SELECT '2', 'A', 'Posadas', 'Argentina', '10', '3421', '3360', 'Misiones', 'Dorrego', '2'
--WHERE NOT EXISTS (SELECT * FROM `address` WHERE address_id = 2);
--INSERT INTO `ayi_final_project`.`address` (`address_id`, `apartment_unit`, `city`, `country`, `floor`,  `number`, `postal_code`, `province`, `street`, `client_id`)
--SELECT '3', 'D', 'Córdoba', 'Argentina', '6', '341', '5000', 'Córdoba', 'Moreno', '3'
--WHERE NOT EXISTS (SELECT * FROM `address` WHERE address_id = 3);
--INSERT INTO `ayi_final_project`.`address` (`address_id`, `apartment_unit`, `city`, `country`, `floor`,  `number`, `postal_code`, `province`, `street`, `client_id`)
--SELECT '4', 'C', 'Rosario', 'Argentina', '1', '256', '2000', 'Santa Fe', 'Belgrano', '1'
--WHERE NOT EXISTS (SELECT * FROM `address` WHERE address_id = 4);
--INSERT INTO `ayi_final_project`.`address` (`address_id`, `apartment_unit`, `city`, `country`, `floor`,  `number`, `postal_code`, `province`, `street`, `client_id`)
--SELECT '5', 'E', 'Corrientes', 'Argentina', '7', '694', '3478', 'Corrientes', 'Güemes', '4'
--WHERE NOT EXISTS (SELECT * FROM `address` WHERE address_id = 5);
--INSERT INTO `ayi_final_project`.`address` (`address_id`, `apartment_unit`, `city`, `country`, `floor`,  `number`, `postal_code`, `province`, `street`, `client_id`)
--SELECT '6', 'R', 'Resistencia', 'Argentina', '9', '8451', '3501', 'Chaco', 'Saavedra', '5'
--WHERE NOT EXISTS (SELECT * FROM `address` WHERE address_id = 6);
--INSERT INTO `ayi_final_project`.`address` (`address_id`, `apartment_unit`, `city`, `country`, `floor`,  `number`, `postal_code`, `province`, `street`, `client_id`)
--SELECT '7', 'F', 'Mendoza', 'Argentina', '2', '1067', '5500', 'Mendoza', 'Cabral', '6'
--WHERE NOT EXISTS (SELECT * FROM `address` WHERE address_id = 7);
--
--INSERT INTO `ayi_final_project`.`clients_address_list` (`client_client_id`, `address_list_address_id`) SELECT '1', '1'
--WHERE NOT EXISTS (SELECT * FROM `clients_address_list` WHERE address_list_address_id = 1);
--INSERT INTO `ayi_final_project`.`clients_address_list` (`client_client_id`, `address_list_address_id`) SELECT '2', '2'
--WHERE NOT EXISTS (SELECT * FROM `clients_address_list` WHERE address_list_address_id = 2);
--INSERT INTO `ayi_final_project`.`clients_address_list` (`client_client_id`, `address_list_address_id`) SELECT '3', '3'
--WHERE NOT EXISTS (SELECT * FROM `clients_address_list` WHERE address_list_address_id = 3);
--INSERT INTO `ayi_final_project`.`clients_address_list` (`client_client_id`, `address_list_address_id`) SELECT '1', '4'
--WHERE NOT EXISTS (SELECT * FROM `clients_address_list` WHERE address_list_address_id = 4);
--INSERT INTO `ayi_final_project`.`clients_address_list` (`client_client_id`, `address_list_address_id`) SELECT '4', '5'
--WHERE NOT EXISTS (SELECT * FROM `clients_address_list` WHERE address_list_address_id = 5);
--INSERT INTO `ayi_final_project`.`clients_address_list` (`client_client_id`, `address_list_address_id`) SELECT '5', '6'
--WHERE NOT EXISTS (SELECT * FROM `clients_address_list` WHERE address_list_address_id = 6);
--INSERT INTO `ayi_final_project`.`clients_address_list` (`client_client_id`, `address_list_address_id`) SELECT '6', '7'
--WHERE NOT EXISTS (SELECT * FROM `clients_address_list` WHERE address_list_address_id = 7);
--
--INSERT INTO `ayi_final_project`.`clients_invoice_list` (`client_client_id`, `invoice_list_invoice_id`) SELECT '1', '1'
--WHERE NOT EXISTS (SELECT * FROM `clients_invoice_list` WHERE invoice_list_invoice_id = 1);
--INSERT INTO `ayi_final_project`.`clients_invoice_list` (`client_client_id`, `invoice_list_invoice_id`) SELECT '2', '2'
--WHERE NOT EXISTS (SELECT * FROM `clients_invoice_list` WHERE invoice_list_invoice_id = 2);
--INSERT INTO `ayi_final_project`.`clients_invoice_list` (`client_client_id`, `invoice_list_invoice_id`) SELECT '3', '3'
--WHERE NOT EXISTS (SELECT * FROM `clients_invoice_list` WHERE invoice_list_invoice_id = 3);
--INSERT INTO `ayi_final_project`.`clients_invoice_list` (`client_client_id`, `invoice_list_invoice_id`) SELECT '1', '4'
--WHERE NOT EXISTS (SELECT * FROM `clients_invoice_list` WHERE invoice_list_invoice_id = 4);
--INSERT INTO `ayi_final_project`.`clients_invoice_list` (`client_client_id`, `invoice_list_invoice_id`) SELECT '4', '5'
--WHERE NOT EXISTS (SELECT * FROM `clients_invoice_list` WHERE invoice_list_invoice_id = 5);
--INSERT INTO `ayi_final_project`.`clients_invoice_list` (`client_client_id`, `invoice_list_invoice_id`) SELECT '5', '6'
--WHERE NOT EXISTS (SELECT * FROM `clients_invoice_list` WHERE invoice_list_invoice_id = 6);
--INSERT INTO `ayi_final_project`.`clients_invoice_list` (`client_client_id`, `invoice_list_invoice_id`) SELECT '6', '7'
--WHERE NOT EXISTS (SELECT * FROM `clients_invoice_list` WHERE invoice_list_invoice_id = 7);