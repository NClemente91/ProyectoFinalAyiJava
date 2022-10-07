package com.ayi.rest.serv.app.services;

import com.ayi.rest.serv.app.dtos.request.CustomerDetailDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerDetailResponseDTO;
import com.ayi.rest.serv.app.dtos.response.PagesResponseDTO;

public interface ICustomerDetailService {

    PagesResponseDTO<CustomerDetailResponseDTO> findAllCustomersDetails(Integer page, Integer size);

    CustomerDetailResponseDTO findCustomerDetailById(Long id);

    CustomerDetailResponseDTO updateCustomerDetail(CustomerDetailDTO customerDetailDTO, Long id);
}
