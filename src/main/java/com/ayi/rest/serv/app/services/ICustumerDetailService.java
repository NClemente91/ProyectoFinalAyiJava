package com.ayi.rest.serv.app.services;

import com.ayi.rest.serv.app.dtos.request.CustomerDetailDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerDetailResponseDTO;

import java.util.List;

public interface ICustumerDetailService {
    List<CustomerDetailResponseDTO> findAllCustomersDetails();

    CustomerDetailResponseDTO findCustomerDetailById(Long id);

    CustomerDetailResponseDTO createCustomerDetail(CustomerDetailDTO customerDetailDTO);

    CustomerDetailResponseDTO updateCustomerDetail(CustomerDetailDTO customerDetailDTO);

    void deleteCustomerDetailById(Long id);
}
