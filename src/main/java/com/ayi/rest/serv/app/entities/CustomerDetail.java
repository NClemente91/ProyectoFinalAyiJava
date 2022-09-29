package com.ayi.rest.serv.app.entities;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers_details")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@ApiModel(
        value = "Customer Detail",
        description = "Represents the data associated with customers details"
)
public class CustomerDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_detail_id")
    private Long customerDetailId;

    @Column(name = "is_prime", nullable = false)
    private Boolean isPrime;

    @Column(name = "score", nullable = false)
    private Integer score;

    @OneToOne(mappedBy = "customerDetail")
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
