package com.ayi.rest.serv.app.repositories;

import com.ayi.rest.serv.app.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByDni(String dni);

}
