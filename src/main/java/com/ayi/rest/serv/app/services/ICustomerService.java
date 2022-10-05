package com.ayi.rest.serv.app.services;

import com.ayi.rest.serv.app.dtos.request.CustomerDTO;
import com.ayi.rest.serv.app.dtos.request.FullCustomerDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerResponseDTO;
import com.ayi.rest.serv.app.dtos.response.FullCustomerResponseDTO;
import com.ayi.rest.serv.app.dtos.response.PagesResponseDTO;

public interface ICustomerService {

    PagesResponseDTO<CustomerResponseDTO> findAllCustomers(Integer page, Integer size);

    FullCustomerResponseDTO findCustomerById(Long id);

    FullCustomerResponseDTO createCustomer(FullCustomerDTO fullCustomerDTO);

    CustomerResponseDTO updateCustomer(CustomerDTO customerDTO, Long id);

    void deleteCustomerById(Long id);
}
