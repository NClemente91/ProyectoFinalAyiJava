package com.ayi.rest.serv.app.services.impl;

import com.ayi.rest.serv.app.dtos.request.CustomerDetailDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerDetailResponseDTO;
import com.ayi.rest.serv.app.dtos.response.PagesResponseDTO;
import com.ayi.rest.serv.app.entities.CustomerDetail;
import com.ayi.rest.serv.app.exceptions.BadRequestException;
import com.ayi.rest.serv.app.exceptions.NotFoundException;
import com.ayi.rest.serv.app.mappers.ICustomerDetailMapper;
import com.ayi.rest.serv.app.repositories.ICustomerDetailRepository;
import com.ayi.rest.serv.app.services.ICustomerDetailService;
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
public class CustomerDetailServiceImpl implements ICustomerDetailService {

    @Autowired
    private ICustomerDetailRepository customerDetailRepository;

    @Autowired
    private ICustomerDetailMapper customerDetailMapper;

    /**
     * Method that returns a list of customers details
     * @param page Page number (Default 0)
     * @param size Page size (Default 10)
     * @return PagesResponseDTO<CustomerDetailResponseDTO>
     */
    @Override
    public PagesResponseDTO<CustomerDetailResponseDTO> findAllCustomersDetails(Integer page, Integer size){

        PagesResponseDTO<CustomerDetailResponseDTO> customerDetailPageResponseDTO = new PagesResponseDTO<CustomerDetailResponseDTO>();

        Pageable pageable = PageRequest.of(page, size);

        Page<CustomerDetail> customerDetailPageList = customerDetailRepository.findAll(pageable);

        if (customerDetailPageList.isEmpty()){
            throw new NotFoundException("There are no records related to customers details");
        }

        for(CustomerDetail c:customerDetailPageList){
            customerDetailPageResponseDTO.getEntities().add(customerDetailMapper.entityToResponseDto(c));
        }
        customerDetailPageResponseDTO.setTotalPages(customerDetailPageList.getTotalPages());
        customerDetailPageResponseDTO.setCurrentPage(customerDetailPageList.getNumber() + 1);
        customerDetailPageResponseDTO.setPageSize(customerDetailPageList.getSize());
        customerDetailPageResponseDTO.setTotalElements((int) customerDetailPageList.getTotalElements());

        return customerDetailPageResponseDTO;
    }

    /**
     * Method that returns a customer detail by its id
     * @param id CustomerDetail id
     * @return CustomerDetailResponseDTO
     */
    @Override
    public CustomerDetailResponseDTO findCustomerDetailById(Long id){

        Optional<CustomerDetail> optionalCustomerDetail = customerDetailRepository.findById(id);

        if (optionalCustomerDetail.isEmpty()){
            throw new NotFoundException("Record with id " + id +" does not exist");
        }

        return customerDetailMapper.entityToResponseDto(optionalCustomerDetail.get());

    }

    /**
     * Method that creates a customer detail passed by parameter
     * @param customerDetailDTO CustomerDetail to create
     * @return CustomerDetailResponseDTO
     */
    @Override
    public CustomerDetailResponseDTO createCustomerDetail(CustomerDetailDTO customerDetailDTO){

        CustomerDetailResponseDTO customerDetailResponseDTO;

        if (ObjectUtils.isEmpty(customerDetailDTO)) {
            throw new BadRequestException("Empty data in the entered entity");
        }

//        CustomerDetail customerByDni = customerDetailRepository.findByDni(customerDetailDTO.getDni());
//
//        if (customerByDni != null) {
//            throw new BadRequestException("Existing customer detail");
//        }

        CustomerDetail customerDetailToCreate = customerDetailMapper.requestDtoToEntity(customerDetailDTO);
        customerDetailToCreate.setCreatedAt(LocalDateTime.now());

        customerDetailResponseDTO = customerDetailMapper.entityToResponseDto(customerDetailRepository.save(customerDetailToCreate));

        return customerDetailResponseDTO;

    }

    /**
     * Method that updates a customer detail passed by parameter by its id
     * @param customerDetailDTO CustomerDetail to update
     * @param id CustomerDetail id
     * @return CustomerDetailResponseDTO
     */
    @Override
    public CustomerDetailResponseDTO updateCustomerDetail(CustomerDetailDTO customerDetailDTO, Long id){

        CustomerDetailResponseDTO customerDetailResponseDTO;

        if (ObjectUtils.isEmpty(customerDetailDTO)) {
            throw new BadRequestException("Empty data in the entered entity");
        }

        Optional<CustomerDetail> optionalCustomerDetail = customerDetailRepository.findById(id);

        if(optionalCustomerDetail.isEmpty()){
            throw new NotFoundException("CustomerDetail to update not found");
        }

//        CustomerDetail customerByDni = customerDetailRepository.findByDni(customerDetailDTO.getDni());
//
//        if(!Objects.equals(customerDetailDTO.getDni(), optionalCustomerDetail.get().getDni()) && customerByDni != null){
//            throw new BadRequestException("CustomerDetail with DNI entered already exists");
//        }

        CustomerDetail customerDetailToUpdate = customerDetailMapper.requestDtoToEntity(customerDetailDTO);
        customerDetailToUpdate.setCustomerDetailId(optionalCustomerDetail.get().getCustomerDetailId());
        customerDetailToUpdate.setCreatedAt(optionalCustomerDetail.get().getCreatedAt());
        customerDetailToUpdate.setUpdatedAt(LocalDateTime.now());

        CustomerDetail customerUpdated = customerDetailRepository.save(customerDetailToUpdate);

        customerDetailResponseDTO = customerDetailMapper.entityToResponseDto(customerUpdated);

        return customerDetailResponseDTO;

    }

    /**
     * Method that removes a customer by its id
     * @param id CustomerDetail id
     */
    @Override
    public void deleteCustomerDetailById(Long id){

        Optional<CustomerDetail> optionalCustomerDetail = customerDetailRepository.findById(id);

        if(optionalCustomerDetail.isEmpty()){
            throw new NotFoundException("Customer Detail to delete not found");
        }

        customerDetailRepository.delete(optionalCustomerDetail.get());

    }

}
