package com.ayi.rest.serv.app.services.impl;

import com.ayi.rest.serv.app.dtos.request.AddressDTO;
import com.ayi.rest.serv.app.dtos.response.AddressResponseDTO;
import com.ayi.rest.serv.app.mappers.IAddressMapper;
import com.ayi.rest.serv.app.repositories.IAddressRepository;
import com.ayi.rest.serv.app.services.IAddressService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class AddressServiceImpl extends Exception implements IAddressService {

    @Autowired
    private IAddressRepository addressRepository;

    @Autowired
    private IAddressMapper addressMapper;

    @Override
    public List<AddressResponseDTO> findAllAddresses(){
        return null;
    }

    @Override
    public AddressResponseDTO findAddressById(Long id){
        return null;
    }

    @Override
    public AddressResponseDTO createAddress(AddressDTO addressDTO){
        return null;
    }

    @Override
    public AddressResponseDTO updateAddress(AddressDTO addressDTO){
        return null;
    }

    @Override
    public void deleteAddressById(Long id){

    }

}
