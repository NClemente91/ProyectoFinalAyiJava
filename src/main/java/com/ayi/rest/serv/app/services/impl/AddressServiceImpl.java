package com.ayi.rest.serv.app.services.impl;

import com.ayi.rest.serv.app.dtos.request.AddressDTO;
import com.ayi.rest.serv.app.dtos.request.AddressWithCustomerDniDTO;
import com.ayi.rest.serv.app.dtos.response.AddressResponseDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerResponseDTO;
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

        if(page < 0 || size <= 0) {
            throw new BadRequestException("The page cannot be less than zero and the size less than one");
        }

        PagesResponseDTO<AddressResponseDTO> addressPageResponseDTO = new PagesResponseDTO<>();

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

        if (id < 0){
            throw new BadRequestException("Id cannot be negative");
        }

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
    public AddressResponseDTO createAddress(AddressWithCustomerDniDTO addressDTO){

        Address createdAddress;

        System.out.println(addressDTO);

        if (ObjectUtils.isEmpty(addressDTO)) {
            throw new BadRequestException("Empty data in the entered entity");
        }

        CustomerResponseDTO customerResponse = customerService.findCustomerByDni(addressDTO.getCustomerDni());
        Customer customerByDni = customerMapper.responseDtoToEntity(customerResponse);

        if (customerByDni == null) {
            throw new BadRequestException("Cannot create an address without an associated customer");
        }

        Optional<Address> repeatedAddress = addressRepository.isAddressExist(
                addressDTO.getStreet(),
                addressDTO.getStreetNumber(),
                addressDTO.getApartment(),
                addressDTO.getPostcode(),
                addressDTO.getCity(),
                addressDTO.getProvince(),
                addressDTO.getCountry(),
                customerByDni
        );

        if(repeatedAddress.isPresent()) {
            throw new BadRequestException("The address to add already exists");
        } else {
            Address addressToCreate = addressMapper.requestDtoToEntity(addressDTO);
            addressToCreate.setCustomer(customerByDni);
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

        Optional<Address> optionalAddress = addressRepository.findById(id);

        if(optionalAddress.isEmpty()){
            throw new NotFoundException("Address to update not found");
        }

        Optional<Address> repeatedAddress = addressRepository.isAddressExist(
                addressDTO.getStreet(),
                addressDTO.getStreetNumber(),
                addressDTO.getApartment(),
                addressDTO.getPostcode(),
                addressDTO.getCity(),
                addressDTO.getProvince(),
                addressDTO.getCountry(),
                optionalAddress.get().getCustomer()
        );

        if (repeatedAddress.isPresent()) {
            throw new BadRequestException("The address to update already exists");
        }

        Address addressToUpdate = addressMapper.requestDtoToEntity(addressDTO);
        addressToUpdate.setAddressId(id);
        addressToUpdate.setCustomer(optionalAddress.get().getCustomer());
        addressToUpdate.setCreatedAt(optionalAddress.get().getCreatedAt());
        addressToUpdate.setUpdatedAt(LocalDateTime.now());

        Address addressUpdated = addressRepository.save(addressToUpdate);

        return addressMapper.entityToResponseDto(addressUpdated);

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

}
