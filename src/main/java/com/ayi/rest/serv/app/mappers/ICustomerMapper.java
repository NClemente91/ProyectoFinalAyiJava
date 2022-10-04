package com.ayi.rest.serv.app.mappers;

import com.ayi.rest.serv.app.dtos.request.CustomerDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerResponseDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerWithDetailResponseDTO;
import com.ayi.rest.serv.app.entities.Customer;
import com.ayi.rest.serv.app.entities.CustomerDetail;

public interface ICustomerMapper {
    CustomerResponseDTO entityToResponseDto(Customer entity);
    Customer requestDtoToEntity(CustomerDTO requestDto);

    CustomerWithDetailResponseDTO entitiesToCustomerWithResponseDto(Customer customer, CustomerDetail customerDetail);
}
