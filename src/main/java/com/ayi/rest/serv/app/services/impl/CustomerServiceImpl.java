package com.ayi.rest.serv.app.services.impl;

import com.ayi.rest.serv.app.dtos.request.CustomerDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerResponseDTO;
import com.ayi.rest.serv.app.mappers.ICustomerMapper;
import com.ayi.rest.serv.app.repositories.ICustomerRepository;
import com.ayi.rest.serv.app.services.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private ICustomerMapper customerMapper;

    @Override
    public List<CustomerResponseDTO> findAllCustomers(){
        return null;
    }

    @Override
    public CustomerResponseDTO findCustomerById(Long id){
        return null;
    }

    @Override
    public CustomerResponseDTO createCustomer(CustomerDTO customerDTO){
        return null;
    }

    @Override
    public CustomerResponseDTO updateCustomer(CustomerDTO customerDTO){
        return null;
    }

    @Override
    public void deleteCustomerById(Long id){

    }

}
