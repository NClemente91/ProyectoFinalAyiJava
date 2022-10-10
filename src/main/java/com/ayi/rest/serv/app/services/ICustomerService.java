package com.ayi.rest.serv.app.services;

import com.ayi.rest.serv.app.dtos.request.CustomerDTO;
import com.ayi.rest.serv.app.dtos.request.CustomerUpdateDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerResponseDTO;
import com.ayi.rest.serv.app.dtos.response.InvoiceWithoutCustomerResponseDTO;
import com.ayi.rest.serv.app.dtos.response.PagesResponseDTO;

import java.util.List;

public interface ICustomerService {

    PagesResponseDTO<CustomerResponseDTO> findAllCustomers(Integer page, Integer size);

    List<InvoiceWithoutCustomerResponseDTO> findAllInvoicesById(Long id);

    CustomerResponseDTO findCustomerById(Long id);

    CustomerResponseDTO findCustomerByDni(String dni);

    CustomerResponseDTO createCustomer(CustomerDTO customerDTO);

    CustomerResponseDTO updateCustomer(CustomerUpdateDTO customerDTO, Long id);

    void deleteCustomerById(Long id);
}
