package com.ayi.rest.serv.app.entities;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@ApiModel(
        value = "Address",
        description = "Represents the data associated with addresses"
)
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "street", nullable = false, length = 50)
    private String street;

    @Column(name = "street_number", nullable = false)
    private Integer streetNumber;

    @Column(name = "apartment", length = 25)
    private String apartment;

    @Column(name = "postcode", nullable = false, length = 25)
    private String postcode;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "province", nullable = false, length = 50)
    private String province;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @ManyToMany(mappedBy = "addressList")
    private List<Customer> customerList = new ArrayList<>();

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
