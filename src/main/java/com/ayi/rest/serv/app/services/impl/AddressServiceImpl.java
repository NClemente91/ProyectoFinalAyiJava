package com.ayi.rest.serv.app.services.impl;

import com.ayi.rest.serv.app.dtos.request.AddressDTO;
import com.ayi.rest.serv.app.dtos.response.AddressResponseDTO;
import com.ayi.rest.serv.app.dtos.response.PagesResponseDTO;
import com.ayi.rest.serv.app.entities.Address;
import com.ayi.rest.serv.app.exceptions.BadRequestException;
import com.ayi.rest.serv.app.exceptions.NotFoundException;
import com.ayi.rest.serv.app.dtos.request.mappers.IAddressMapper;
import com.ayi.rest.serv.app.repositories.IAddressRepository;
import com.ayi.rest.serv.app.services.IAddressService;
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
            addressPageResponseDTO.getEntityResponseDTOs().add(addressMapper.entityToResponseDto(a));
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

        AddressResponseDTO addressResponseDTO;

        if (ObjectUtils.isEmpty(addressDTO)) {
            throw new BadRequestException("Empty data in the entered entity");
        }

        long repeatedAddress = addressRepository.repeatedAddressValidation(
                addressDTO.getStreet(),
                addressDTO.getStreetNumber(),
                addressDTO.getApartment(),
                addressDTO.getCity()
        );

        if (repeatedAddress > 0) {
            throw new BadRequestException("Existing address");
        }

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

        if (ObjectUtils.isEmpty(addressDTO)) {
            throw new BadRequestException("Empty data in the entered entity");
        }

        long repeatedAddress = addressRepository.repeatedAddressValidation(
                addressDTO.getStreet(),
                addressDTO.getStreetNumber(),
                addressDTO.getApartment(),
                addressDTO.getCity()
        );

        if (repeatedAddress > 0) {
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

}
