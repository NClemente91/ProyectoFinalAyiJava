package com.ayi.rest.serv.app.services;

import com.ayi.rest.serv.app.dtos.request.CustomerDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerResponseDTO;
import com.ayi.rest.serv.app.dtos.response.PagesResponseDTO;

import java.util.List;

public interface ICustomerService {

    PagesResponseDTO<CustomerResponseDTO> findAllCustomers(Integer page, Integer size);

    CustomerResponseDTO findCustomerById(Long id);

    CustomerResponseDTO createCustomer(CustomerDTO customerDTO);

    CustomerResponseDTO updateCustomer(CustomerDTO customerDTO, Long id);

    void deleteCustomerById(Long id);
}
