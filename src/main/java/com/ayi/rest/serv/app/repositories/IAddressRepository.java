package com.ayi.rest.serv.app.repositories;

import com.ayi.rest.serv.app.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAddressRepository extends JpaRepository<Address, Long> {

    @Query("Select count(A) from Address A " +
            "where (A.street = :street) AND " +
            "(A.streetNumber = :streetNumber) AND " +
            "(A.apartment = :apartment) AND " +
            "(A.city = :city)")
    long repeatedAddressValidation(
            @Param("street") String street,
            @Param("streetNumber") Integer streetNumber,
            @Param("apartment") String apartment,
            @Param("city") String city);

}
