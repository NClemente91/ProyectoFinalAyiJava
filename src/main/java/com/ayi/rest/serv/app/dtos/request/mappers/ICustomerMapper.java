package com.ayi.rest.serv.app.dtos.request.mappers;

import com.ayi.rest.serv.app.dtos.request.CustomerDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerResponseDTO;
import com.ayi.rest.serv.app.dtos.response.FullCustomerResponseDTO;
import com.ayi.rest.serv.app.entities.Address;
import com.ayi.rest.serv.app.entities.Customer;
import com.ayi.rest.serv.app.entities.CustomerDetail;

public interface ICustomerMapper {
    CustomerResponseDTO entityToResponseDto(Customer entity);
    Customer requestDtoToEntity(CustomerDTO requestDto);
    FullCustomerResponseDTO entitiesToFullCustomerResponseDto(Customer customer, CustomerDetail customerDetail, Address address);
}
