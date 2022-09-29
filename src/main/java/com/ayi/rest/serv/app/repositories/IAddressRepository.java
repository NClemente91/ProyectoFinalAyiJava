package com.ayi.rest.serv.app.repositories;

import com.ayi.rest.serv.app.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, Long> {
}
