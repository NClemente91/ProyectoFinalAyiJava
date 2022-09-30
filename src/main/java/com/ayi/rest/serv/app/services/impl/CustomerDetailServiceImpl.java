package com.ayi.rest.serv.app.services.impl;

import com.ayi.rest.serv.app.dtos.request.CustomerDetailDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerDetailResponseDTO;
import com.ayi.rest.serv.app.mappers.ICustomerDetailMapper;
import com.ayi.rest.serv.app.repositories.ICustomerDetailRepository;
import com.ayi.rest.serv.app.services.ICustumerDetailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class CustomerDetailServiceImpl implements ICustumerDetailService {

    @Autowired
    private ICustomerDetailRepository customerDetailRepository;

    @Autowired
    private ICustomerDetailMapper customerDetailMapper;

    @Override
    public List<CustomerDetailResponseDTO> findAllCustomersDetails(){
        return null;
    }

    @Override
    public CustomerDetailResponseDTO findCustomerDetailById(Long id){
        return null;
    }

    @Override
    public CustomerDetailResponseDTO createCustomerDetail(CustomerDetailDTO customerDetailDTO){
        return null;
    }

    @Override
    public CustomerDetailResponseDTO updateCustomerDetail(CustomerDetailDTO customerDetailDTO){
        return null;
    }

    @Override
    public void deleteCustomerDetailById(Long id){

    }

}
