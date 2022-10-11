package com.ayi.rest.serv.app.services.impl;

import com.ayi.rest.serv.app.dtos.request.CustomerDetailDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerDetailResponseDTO;
import com.ayi.rest.serv.app.dtos.response.PagesResponseDTO;
import com.ayi.rest.serv.app.entities.CustomerDetail;
import com.ayi.rest.serv.app.exceptions.customsExceptions.BadRequestException;
import com.ayi.rest.serv.app.exceptions.customsExceptions.NotFoundException;
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

        if(page < 0 || size <= 0) {
            throw new BadRequestException("The page cannot be less than zero and the size less than one");
        }

        PagesResponseDTO<CustomerDetailResponseDTO> customerDetailPageResponseDTO = new PagesResponseDTO<>();

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

        if (id < 0) {
            throw new BadRequestException("Id cannot be negative");
        }

        Optional<CustomerDetail> optionalCustomerDetail = customerDetailRepository.findById(id);

        if (optionalCustomerDetail.isEmpty()){
            throw new NotFoundException("Record with id " + id +" does not exist");
        }

        return customerDetailMapper.entityToResponseDto(optionalCustomerDetail.get());

    }

    /**
     * Method that updates a customer detail passed by parameter by its id
     * @param customerDetailDTO CustomerDetail to update
     * @param id CustomerDetail id
     * @return CustomerDetailResponseDTO
     */
    @Override
    public CustomerDetailResponseDTO updateCustomerDetail(CustomerDetailDTO customerDetailDTO, Long id){

        if (ObjectUtils.isEmpty(customerDetailDTO)) {
            throw new BadRequestException("Empty data in the entered entity");
        }

        Optional<CustomerDetail> optionalCustomerDetail = customerDetailRepository.findById(id);

        if(optionalCustomerDetail.isEmpty()){
            throw new NotFoundException("CustomerDetail to update not found");
        }

        CustomerDetail customerDetailToUpdate = customerDetailMapper.requestDtoToEntity(customerDetailDTO);
        customerDetailToUpdate.setCustomerDetailId(optionalCustomerDetail.get().getCustomerDetailId());
        customerDetailToUpdate.setCreatedAt(optionalCustomerDetail.get().getCreatedAt());
        customerDetailToUpdate.setUpdatedAt(LocalDateTime.now());

        CustomerDetail customerUpdated = customerDetailRepository.save(customerDetailToUpdate);

        return customerDetailMapper.entityToResponseDto(customerUpdated);

    }

}
