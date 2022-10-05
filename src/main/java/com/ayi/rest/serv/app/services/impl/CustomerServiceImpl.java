package com.ayi.rest.serv.app.services.impl;

import com.ayi.rest.serv.app.dtos.request.CustomerDTO;
import com.ayi.rest.serv.app.dtos.request.FullCustomerDTO;
import com.ayi.rest.serv.app.mappers.IAddressMapper;
import com.ayi.rest.serv.app.dtos.response.CustomerResponseDTO;
import com.ayi.rest.serv.app.dtos.response.FullCustomerResponseDTO;
import com.ayi.rest.serv.app.dtos.response.PagesResponseDTO;
import com.ayi.rest.serv.app.entities.Address;
import com.ayi.rest.serv.app.entities.Customer;
import com.ayi.rest.serv.app.entities.CustomerDetail;
import com.ayi.rest.serv.app.exceptions.BadRequestException;
import com.ayi.rest.serv.app.exceptions.NotFoundException;
import com.ayi.rest.serv.app.mappers.ICustomerDetailMapper;
import com.ayi.rest.serv.app.mappers.ICustomerMapper;
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
    public FullCustomerResponseDTO findCustomerById(Long id) {

        if (id < 0) {
            throw new BadRequestException("El id no puede ser un número negativo.");
        }

        FullCustomerResponseDTO fullCustomerResponseDTO;

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isEmpty()) {
            throw new IllegalStateException("El registro con el id " + id + " no existe.");
        }

        fullCustomerResponseDTO = customerMapper.entityToFullResponseDto(optionalCustomer.get());

        return fullCustomerResponseDTO;
    }

    /**
     * Method that creates a customer with detail passed by parameter
     * @param fullCustomerDTO Customer to create
     * @return CustomerWithDetailResponseDTO
     */
    @Override
    public FullCustomerResponseDTO createCustomer(FullCustomerDTO fullCustomerDTO){

        if (ObjectUtils.isEmpty(fullCustomerDTO)) {
            throw new BadRequestException("Empty data in the entered entity");
        }

        Customer customerByDni = customerRepository.findByDni(fullCustomerDTO.getCustomer().getDni());

        if (customerByDni != null) {
            throw new BadRequestException("Existing customer");
        }

        Customer customerToCreate = customerMapper.requestDtoToEntity(fullCustomerDTO.getCustomer());
        CustomerDetail customerDetail = customerDetailMapper.requestDtoToEntity(fullCustomerDTO.getDetail());
        Address address = addressMapper.requestDtoToEntity(fullCustomerDTO.getAddress());
        customerDetail.setCreatedAt(LocalDateTime.now());
        address.setCreatedAt(LocalDateTime.now());

        customerToCreate.setCustomerDetail(customerDetail);
        customerToCreate.getAddressList().add(address);
        customerToCreate.setCreatedAt(LocalDateTime.now());

        Customer customerCreated = customerRepository.save(customerToCreate);

        return customerMapper.entityToFullResponseDto(customerCreated);

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
