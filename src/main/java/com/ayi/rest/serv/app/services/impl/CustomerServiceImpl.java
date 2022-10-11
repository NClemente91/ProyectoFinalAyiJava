package com.ayi.rest.serv.app.services.impl;

import com.ayi.rest.serv.app.dtos.request.CustomerDTO;
import com.ayi.rest.serv.app.dtos.request.CustomerUpdateDTO;
import com.ayi.rest.serv.app.dtos.response.InvoiceWithoutCustomerResponseDTO;
import com.ayi.rest.serv.app.entities.Invoice;
import com.ayi.rest.serv.app.mappers.IAddressMapper;
import com.ayi.rest.serv.app.dtos.response.CustomerResponseDTO;
import com.ayi.rest.serv.app.dtos.response.PagesResponseDTO;
import com.ayi.rest.serv.app.entities.Address;
import com.ayi.rest.serv.app.entities.Customer;
import com.ayi.rest.serv.app.entities.CustomerDetail;
import com.ayi.rest.serv.app.exceptions.customsExceptions.BadRequestException;
import com.ayi.rest.serv.app.exceptions.customsExceptions.NotFoundException;
import com.ayi.rest.serv.app.mappers.ICustomerDetailMapper;
import com.ayi.rest.serv.app.mappers.ICustomerMapper;
import com.ayi.rest.serv.app.mappers.IInvoiceMapper;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository customerRepository;
    @Autowired
    private IAddressMapper addressMapper;
    @Autowired
    private ICustomerMapper customerMapper;
    @Autowired
    private ICustomerDetailMapper customerDetailMapper;
    @Autowired
    private IInvoiceMapper invoiceMapper;

    /**
     * Method that returns a list of customers
     * @param page Page number (Default 0)
     * @param size Page size (Default 10)
     * @return PagesResponseDTO<CustomerResponseDTO>
     */
    @Override
    public PagesResponseDTO<CustomerResponseDTO> findAllCustomers(Integer page, Integer size){

        if(page < 0 || size <= 0) {
            throw new BadRequestException("The page cannot be less than zero and the size less than one");
        }

        PagesResponseDTO<CustomerResponseDTO> customerPageResponseDTO = new PagesResponseDTO<>();

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
     * Method that returns customer invoices by its id
     * @param id Customer id
     * @return CustomerResponseDTO
     */
    @Override
    public List<InvoiceWithoutCustomerResponseDTO> findAllInvoicesById(Long id) {

        List<InvoiceWithoutCustomerResponseDTO> invoicesList = new ArrayList<>();

        if (id < 0) {
            throw new BadRequestException("Id cannot be negative");
        }

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isEmpty()) {
            throw new IllegalStateException("Record with id " + id + " does not exist");
        }

        for(Invoice i:optionalCustomer.get().getInvoiceList()){
            invoicesList.add(invoiceMapper.entityToInvoiceWithoutCustomerResponseDto(i));
        }

        return invoicesList;

    }

    /**
     * Method that returns a customer by its id
     * @param id Customer id
     * @return CustomerResponseDTO
     */
    @Override
    public CustomerResponseDTO findCustomerById(Long id) {

        if (id < 0) {
            throw new BadRequestException("Id cannot be negative");
        }

        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isEmpty()) {
            throw new BadRequestException("Customer with id " + id + " does not exist");
        }

        return customerMapper.entityToResponseDto(optionalCustomer.get());

    }

    /**
     * Method that returns a customer by its dni
     * @param dni Customer dni
     * @return CustomerResponseDTO
     */
    @Override
    public CustomerResponseDTO findCustomerByDni(String dni) {

        Customer customer = customerRepository.findByDni(dni);

        if (customer == null) {
            throw new BadRequestException("Customer with dni " + dni + " does not exist");
        }

        return customerMapper.entityToResponseDto(customer);

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
            throw new BadRequestException("The customer to add already exists");
        }

        Customer customerToCreate = customerMapper.requestDtoToEntity(customerDTO);

        CustomerDetail customerDetail = customerDetailMapper.requestDtoToEntity(customerDTO.getDetail());
        customerDetail.setCreatedAt(LocalDateTime.now());

        Address address = addressMapper.requestDtoToEntity(customerDTO.getAddress());
        address.setCustomer(customerToCreate);
        address.setCreatedAt(LocalDateTime.now());

        customerToCreate.addAddress(address);
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
    public CustomerResponseDTO updateCustomer(CustomerUpdateDTO customerDTO, Long id){

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
        customerToUpdate.setCustomerDetail(optionalCustomer.get().getCustomerDetail());
        customerToUpdate.setAddressList(optionalCustomer.get().getAddressList());
        customerToUpdate.setCreatedAt(optionalCustomer.get().getCreatedAt());
        customerToUpdate.setUpdatedAt(LocalDateTime.now());

        Customer customerUpdated = customerRepository.save(customerToUpdate);

        return customerMapper.entityToResponseDto(customerUpdated);

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

        if(!optionalCustomer.get().getInvoiceList().isEmpty()){
            throw new BadRequestException("Cannot delete a customer with associated invoices");
        }

        customerRepository.delete(optionalCustomer.get());

    }

}
