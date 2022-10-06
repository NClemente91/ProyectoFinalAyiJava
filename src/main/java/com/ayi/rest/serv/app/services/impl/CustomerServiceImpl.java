package com.ayi.rest.serv.app.services.impl;

import com.ayi.rest.serv.app.dtos.request.CustomerDTO;
import com.ayi.rest.serv.app.mappers.IAddressMapper;
import com.ayi.rest.serv.app.dtos.response.CustomerResponseDTO;
import com.ayi.rest.serv.app.dtos.response.PagesResponseDTO;
import com.ayi.rest.serv.app.entities.Address;
import com.ayi.rest.serv.app.entities.Customer;
import com.ayi.rest.serv.app.entities.CustomerDetail;
import com.ayi.rest.serv.app.exceptions.BadRequestException;
import com.ayi.rest.serv.app.exceptions.NotFoundException;
import com.ayi.rest.serv.app.mappers.ICustomerDetailMapper;
import com.ayi.rest.serv.app.mappers.ICustomerMapper;
import com.ayi.rest.serv.app.repositories.IAddressRepository;
import com.ayi.rest.serv.app.repositories.ICustomerRepository;
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
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private ICustomerMapper customerMapper;
    @Autowired
    private ICustomerDetailMapper customerDetailMapper;
    @Autowired
    private IAddressMapper addressMapper;
    @Autowired
    private IAddressRepository addressRepository;

    /**
     * Method that returns a list of customers
     * @param page Page number (Default 0)
     * @param size Page size (Default 10)
     * @return PagesResponseDTO<CustomerResponseDTO>
     */
    @Override
    public PagesResponseDTO<CustomerResponseDTO> findAllCustomers(Integer page, Integer size){

        PagesResponseDTO<CustomerResponseDTO> customerPageResponseDTO = new PagesResponseDTO<CustomerResponseDTO>();

        Pageable pageable = PageRequest.of(page, size);

        Page<Customer> customerPageList = customerRepository.findAll(pageable);

        if (customerPageList.isEmpty()){
            throw new NotFoundException("There are no records related to customers");
        }

        for(Customer c:customerPageList){
            customerPageResponseDTO.getEntities().add(customerMapper.entityToResponseDto(c));
        }
        customerPageResponseDTO.setTotalPages(customerPageList.getTotalPages());
        customerPageResponseDTO.setCurrentPage(customerPageList.getNumber() + 1);
        customerPageResponseDTO.setPageSize(customerPageList.getSize());
        customerPageResponseDTO.setTotalElements((int) customerPageList.getTotalElements());

        return customerPageResponseDTO;
    }

    /**
     * Method that returns a customer by its id
     * @param id Customer id
     * @return CustomerResponseDTO
     */
    @Override
    public CustomerResponseDTO findCustomerById(Long id) {

        if (id < 0) {
            throw new BadRequestException("El id no puede ser un número negativo.");
        }

        CustomerResponseDTO customerResponseDTO;

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isEmpty()) {
            throw new IllegalStateException("El registro con el id " + id + " no existe.");
        }

        customerResponseDTO = customerMapper.entityToResponseDto(optionalCustomer.get());

        return customerResponseDTO;
    }

    /**
     * Method that creates a customer with detail passed by parameter
     * @param customerDTO Customer to create
     * @return CustomerResponseDTO
     */
    @Override
    public CustomerResponseDTO createCustomer(CustomerDTO customerDTO){

        if (ObjectUtils.isEmpty(customerDTO)) {
            throw new BadRequestException("Empty data in the entered entity");
        }

        Customer customerByDni = customerRepository.findByDni(customerDTO.getDni());

        if (customerByDni != null) {
            throw new BadRequestException("Existing customer");
        }

        Customer customerToCreate = customerMapper.requestDtoToEntity(customerDTO);
        CustomerDetail customerDetail = customerDetailMapper.requestDtoToEntity(customerDTO.getDetail());
        Address address = addressMapper.requestDtoToEntity(customerDTO.getAddress());
        customerDetail.setCreatedAt(LocalDateTime.now());
        address.setCreatedAt(LocalDateTime.now());

        Optional<Address> repeatedAddress = addressRepository.isAddressExist(
                address.getStreet(),
                address.getStreetNumber(),
                address.getApartment(),
                address.getPostcode(),
                address.getCity(),
                address.getProvince(),
                address.getCountry()
        );

        if (repeatedAddress.isPresent()) {
            customerToCreate.addAddress(repeatedAddress.get());
        } else {
            customerToCreate.addAddress(address);
        }

        customerToCreate.setCustomerDetail(customerDetail);
        customerToCreate.setCreatedAt(LocalDateTime.now());

        Customer customerCreated = customerRepository.save(customerToCreate);

        return customerMapper.entityToResponseDto(customerCreated);

    }

    /**
     * Method that updates a customer passed by parameter by its id
     * @param customerDTO Customer to update
     * @param id Customer id
     * @return CustomerResponseDTO
     */
    @Override
    public CustomerResponseDTO updateCustomer(CustomerDTO customerDTO, Long id){

        CustomerResponseDTO customerResponseDTO;

        if (ObjectUtils.isEmpty(customerDTO)) {
            throw new BadRequestException("Empty data in the entered entity");
        }

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if(optionalCustomer.isEmpty()){
            throw new NotFoundException("Customer to update not found");
        }

        Customer customerByDni = customerRepository.findByDni(customerDTO.getDni());

        if(!Objects.equals(customerDTO.getDni(), optionalCustomer.get().getDni()) && customerByDni != null){
            throw new BadRequestException("Customer with DNI entered already exists");
        }

        Customer customerToUpdate = customerMapper.requestDtoToEntity(customerDTO);
        customerToUpdate.setCustomerId(optionalCustomer.get().getCustomerId());
        customerToUpdate.setCreatedAt(optionalCustomer.get().getCreatedAt());
        customerToUpdate.setUpdatedAt(LocalDateTime.now());

        Customer customerUpdated = customerRepository.save(customerToUpdate);

        customerResponseDTO = customerMapper.entityToResponseDto(customerUpdated);

        return customerResponseDTO;

    }

    /**
     * Method that removes a customer by its id
     * @param id Customer id
     */
    @Override
    public void deleteCustomerById(Long id){

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if(optionalCustomer.isEmpty()){
            throw new NotFoundException("Customer to delete not found");
        }

        customerRepository.delete(optionalCustomer.get());

    }

}
