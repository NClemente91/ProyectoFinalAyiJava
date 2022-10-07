package com.ayi.rest.serv.app.services.impl;

import com.ayi.rest.serv.app.dtos.request.AddressDTO;
import com.ayi.rest.serv.app.dtos.response.AddressResponseDTO;
import com.ayi.rest.serv.app.dtos.response.PagesResponseDTO;
import com.ayi.rest.serv.app.entities.Address;
import com.ayi.rest.serv.app.entities.Customer;
import com.ayi.rest.serv.app.exceptions.BadRequestException;
import com.ayi.rest.serv.app.exceptions.NotFoundException;
import com.ayi.rest.serv.app.mappers.IAddressMapper;
import com.ayi.rest.serv.app.mappers.ICustomerMapper;
import com.ayi.rest.serv.app.repositories.IAddressRepository;
import com.ayi.rest.serv.app.services.IAddressService;
import com.ayi.rest.serv.app.services.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private IAddressRepository addressRepository;

    @Autowired
    private IAddressMapper addressMapper;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ICustomerMapper customerMapper;

    /**
     * Method that returns a list of addresses
     * @param page Page number (Default 0)
     * @param size Page size (Default 10)
     * @return PagesResponseDTO<AddressResponseDTO>
     */
    @Override
    public PagesResponseDTO<AddressResponseDTO> findAllAddresses(Integer page, Integer size){

        PagesResponseDTO<AddressResponseDTO> addressPageResponseDTO = new PagesResponseDTO<AddressResponseDTO>();

        Pageable pageable = PageRequest.of(page, size);

        Page<Address> addressPageList = addressRepository.findAll(pageable);

        if (addressPageList.isEmpty()){
            throw new NotFoundException("There are no records related to addresses");
        }

        for(Address a:addressPageList){
            addressPageResponseDTO.getEntities().add(addressMapper.entityToResponseDto(a));
        }
        addressPageResponseDTO.setTotalPages(addressPageList.getTotalPages());
        addressPageResponseDTO.setCurrentPage(addressPageList.getNumber() + 1);
        addressPageResponseDTO.setPageSize(addressPageList.getSize());
        addressPageResponseDTO.setTotalElements((int) addressPageList.getTotalElements());

        return addressPageResponseDTO;
    }

    /**
     * Method that returns an address by its id
     * @param id Address id
     * @return AddressResponseDTO
     */
    @Override
    public AddressResponseDTO findAddressById(Long id){

        Optional<Address> optionalAddress = addressRepository.findById(id);

        if (optionalAddress.isEmpty()){
            throw new NotFoundException("Record with id " + id + " does not exist");
        }

        return addressMapper.entityToResponseDto(optionalAddress.get());

    }

    /**
     * Method that creates an address passed by parameter
     * @param addressDTO Address to create
     * @return AddressResponseDTO
     */
    @Override
    public AddressResponseDTO createAddress(AddressDTO addressDTO){

        Address createdAddress;

        if (ObjectUtils.isEmpty(addressDTO)) {
            throw new BadRequestException("Empty data in the entered entity");
        }

        Customer customerByDni = customerMapper.responseDtoToEntity(customerService.findCustomerByDni(addressDTO.getCustomerDni()));

        if (customerByDni == null) {
            throw new BadRequestException("Cannot create an address without an associated customer");
        }

        Address repeatedAddress = addressMapper.responseDtoToEntity(addressVerified(addressDTO));

        if(repeatedAddress != null) {
            repeatedAddress.getCustomerList().add(customerByDni);
            customerByDni.getAddressList().add(repeatedAddress);
            createdAddress = addressRepository.save(repeatedAddress);
        } else {
            Address addressToCreate = addressMapper.requestDtoToEntity(addressDTO);
            addressToCreate.getCustomerList().add(customerByDni);
            addressToCreate.setCreatedAt(LocalDateTime.now());
            customerByDni.getAddressList().add(addressToCreate);
            createdAddress = addressRepository.save(addressToCreate);
        }

        return addressMapper.entityToResponseDto(createdAddress);

    }

    /**
     * Method that updates an address passed by parameter by its id
     * @param addressDTO Address to update
     * @param id Address id
     * @return AddressResponseDTO
     */
    @Override
    public AddressResponseDTO updateAddress(AddressDTO addressDTO, Long id){

        if (ObjectUtils.isEmpty(addressDTO)) {
            throw new BadRequestException("Empty data in the entered entity");
        }

        AddressResponseDTO addressResponseDTO = addressVerified(addressDTO);
        Address repeatedAddress = addressMapper.responseDtoToEntity(addressResponseDTO);

        if (repeatedAddress != null) {
            throw new BadRequestException("Existing address");
        }

        Optional<Address> optionalAddress = addressRepository.findById(id);

        if(optionalAddress.isEmpty()){
            throw new NotFoundException("Address to update not found");
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
            throw new NotFoundException("Address to delete not found");
        }

        addressRepository.delete(optionalAddress.get());

    }

    /**
     * Method that returns an address to verify
     * @param addressDTO Address to verify
     * @return AddressResponseDTO
     */
    @Override
    public AddressResponseDTO addressVerified(AddressDTO addressDTO) {

        Address repeatedAddress = addressRepository.isAddressExist(
                addressDTO.getStreet(),
                addressDTO.getStreetNumber(),
                addressDTO.getApartment(),
                addressDTO.getPostcode(),
                addressDTO.getCity(),
                addressDTO.getProvince(),
                addressDTO.getCountry()
        );

        return addressMapper.entityToResponseDto(repeatedAddress);

    }

}
