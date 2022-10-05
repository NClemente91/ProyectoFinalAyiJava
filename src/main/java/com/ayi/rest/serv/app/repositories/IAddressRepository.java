package com.ayi.rest.serv.app.repositories;

import com.ayi.rest.serv.app.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IAddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "Select a from Address a " +
            "where (a.street = :street) AND " +
            "(a.streetNumber = :streetNumber) AND " +
            "(a.apartment = :apartment) AND " +
            "(a.postcode = :postcode) AND " +
            "(a.city = :city) AND " +
            "(a.province = :province) AND " +
            "(a.country = :country)")
    Optional<Address> isAddressExist(
            @Param("street") String street,
            @Param("streetNumber") String streetNumber,
            @Param("apartment") String apartment,
            @Param("postcode") String postcode,
            @Param("city") String city,
            @Param("province") String province,
            @Param("country") String country);

}
