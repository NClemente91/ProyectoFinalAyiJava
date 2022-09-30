package com.ayi.rest.serv.app.services.impl;

import com.ayi.rest.serv.app.dtos.request.AddressDTO;
import com.ayi.rest.serv.app.dtos.response.AddressResponseDTO;
import com.ayi.rest.serv.app.entities.Address;
import com.ayi.rest.serv.app.mappers.IAddressMapper;
import com.ayi.rest.serv.app.repositories.IAddressRepository;
import com.ayi.rest.serv.app.services.IAddressService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private IAddressRepository addressRepository;

    @Autowired
    private IAddressMapper addressMapper;

    /**
     * Method that returns a list of addresses
     * @return List<AddressResponseDTO>
     */
    @Override
    public List<AddressResponseDTO> findAllAddresses(){

        List<AddressResponseDTO> addressResponseDTOList = new ArrayList<>();

        List<Address> addressList = addressRepository.findAll();

        for(Address a:addressList){
            addressResponseDTOList.add(addressMapper.entityToResponseDto(a));
        }

        return addressResponseDTOList;
    }

    /**
     * Method that returns an address by its id
     * @param id Address id
     * @return AddressResponseDTO
     */
    @Override
    public AddressResponseDTO findAddressById(Long id){

        Optional<Address> optionalAddress = addressRepository.findById(id);

        return addressMapper.entityToResponseDto(optionalAddress.get());

    }

    /**
     * Method that creates an address passed by parameter
     * @param addressDTO Address to create
     * @return AddressResponseDTO
     */
    @Override
    public AddressResponseDTO createAddress(AddressDTO addressDTO){

        AddressResponseDTO addressResponseDTO;

        Address addressToCreate = addressMapper.requestDtoToEntity(addressDTO);
        addressToCreate.setCreatedAt(LocalDateTime.now());

        addressResponseDTO = addressMapper.entityToResponseDto(addressRepository.save(addressToCreate));

        return addressResponseDTO;

    }

    /**
     * Method that updates an address passed by parameter by its id
     * @param addressDTO Address to update
     * @param id Address id
     * @return AddressResponseDTO
     */
    @Override
    public AddressResponseDTO updateAddress(AddressDTO addressDTO, Long id){

        AddressResponseDTO addressResponseDTO;

        Optional<Address> optionalAddress = addressRepository.findById(id);

        if(optionalAddress.isEmpty()){
            throw new RuntimeException();
        }

        Address addressToUpdate = addressMapper.requestDtoToEntity(addressDTO);
        addressToUpdate.setAddressId(optionalAddress.get().getAddressId());
        addressToUpdate.setCreatedAt(optionalAddress.get().getCreatedAt());
        addressToUpdate.setUpdatedAt(LocalDateTime.now());

        Address addressUpdated = addressRepository.save(addressToUpdate);

        addressResponseDTO = addressMapper.entityToResponseDto(addressUpdated);

        return addressResponseDTO;

    }

    /**
     * Method that removes an address by its id
     * @param id Address id
     */
    @Override
    public void deleteAddressById(Long id){

        Optional<Address> optionalAddress = addressRepository.findById(id);

        if(optionalAddress.isEmpty()){
            throw new RuntimeException();
        }

        addressRepository.delete(optionalAddress.get());

    }

}
