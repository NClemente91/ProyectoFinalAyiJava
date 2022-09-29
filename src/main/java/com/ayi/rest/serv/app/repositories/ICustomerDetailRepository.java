package com.ayi.rest.serv.app.repositories;

import com.ayi.rest.serv.app.entities.CustomerDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerDetailRepository extends JpaRepository<CustomerDetail, Long> {
}
