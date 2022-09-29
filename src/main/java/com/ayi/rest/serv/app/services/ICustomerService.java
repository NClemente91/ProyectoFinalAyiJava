package com.ayi.rest.serv.app.services;

import com.ayi.rest.serv.app.dtos.request.CustomerDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerResponseDTO;

import java.util.List;

public interface ICustomerService {

    List<CustomerResponseDTO> findAllCustomers();

    CustomerResponseDTO findCustomerById(Long id);

    CustomerResponseDTO createCustomer(CustomerDTO customerDTO);

    CustomerResponseDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomerById(Long id);
}
