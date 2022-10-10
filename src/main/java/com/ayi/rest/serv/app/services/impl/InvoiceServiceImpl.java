package com.ayi.rest.serv.app.services.impl;

import com.ayi.rest.serv.app.dtos.request.InvoiceDTO;
import com.ayi.rest.serv.app.dtos.request.InvoiceUpdateDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerResponseDTO;
import com.ayi.rest.serv.app.dtos.response.InvoiceResponseDTO;
import com.ayi.rest.serv.app.dtos.response.PagesResponseDTO;
import com.ayi.rest.serv.app.entities.Customer;
import com.ayi.rest.serv.app.entities.Invoice;
import com.ayi.rest.serv.app.exceptions.BadRequestException;
import com.ayi.rest.serv.app.exceptions.NotFoundException;
import com.ayi.rest.serv.app.mappers.ICustomerMapper;
import com.ayi.rest.serv.app.mappers.IInvoiceMapper;
import com.ayi.rest.serv.app.repositories.IInvoiceRepository;
import com.ayi.rest.serv.app.services.ICustomerService;
import com.ayi.rest.serv.app.services.IInvoiceService;
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
public class InvoiceServiceImpl implements IInvoiceService {

    @Autowired
    private IInvoiceRepository invoiceRepository;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICustomerMapper customerMapper;
    @Autowired
    private IInvoiceMapper invoiceMapper;

    /**
     * Method that returns a list of invoices
     * @param page Page number (Default 0)
     * @param size Page size (Default 10)
     * @return PagesResponseDTO<InvoiceResponseDTO>
     */
    @Override
    public PagesResponseDTO<InvoiceResponseDTO> findAllInvoices(Integer page, Integer size){

        if(page < 0 || size <= 0) {
            throw new BadRequestException("The page cannot be less than zero and the size less than one");
        }

        PagesResponseDTO<InvoiceResponseDTO> invoicePageResponseDTO = new PagesResponseDTO<>();

        Pageable pageable = PageRequest.of(page, size);

        Page<Invoice> invoicePageList = invoiceRepository.findAll(pageable);

        if (invoicePageList.isEmpty()){
            throw new NotFoundException("There are no records related to invoices");
        }

        for(Invoice c:invoicePageList){
            invoicePageResponseDTO.getEntities().add(invoiceMapper.entityToResponseDto(c));
        }
        invoicePageResponseDTO.setTotalPages(invoicePageList.getTotalPages());
        invoicePageResponseDTO.setCurrentPage(invoicePageList.getNumber() + 1);
        invoicePageResponseDTO.setPageSize(invoicePageList.getSize());
        invoicePageResponseDTO.setTotalElements((int) invoicePageList.getTotalElements());

        return invoicePageResponseDTO;
    }

    /**
     * Method that returns an invoice by its id
     * @param id Invoice id
     * @return InvoiceResponseDTO
     */
    @Override
    public InvoiceResponseDTO findInvoiceById(Long id){

        if (id < 0) {
            throw new BadRequestException("Id cannot be negative");
        }

        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);

        if (optionalInvoice.isEmpty()){
            throw new NotFoundException("Record with id " + id +" does not exist");
        }

        return invoiceMapper.entityToResponseDto(optionalInvoice.get());

    }

    /**
     * Method that creates an invoice passed by parameter
     * @param invoiceDTO Invoice to create
     * @return InvoiceResponseDTO
     */
    @Override
    public InvoiceResponseDTO createInvoice(InvoiceDTO invoiceDTO){

        if (ObjectUtils.isEmpty(invoiceDTO)) {
            throw new BadRequestException("Empty data in the entered entity");
        }

        if (invoiceDTO.getTotal() <= 0) {
            throw new BadRequestException("The invoice total must be greater than zero");
        }

        CustomerResponseDTO customerResponse = customerService.findCustomerByDni(invoiceDTO.getCustomerDni());
        Customer customerByDni = customerMapper.responseDtoToEntity(customerResponse);

        Invoice invoiceToCreate = invoiceMapper.requestDtoToEntity(invoiceDTO);
        invoiceToCreate.setCreatedAt(LocalDateTime.now());
        invoiceToCreate.setCustomer(customerByDni);

        Invoice invoiceCreated = invoiceRepository.save(invoiceToCreate);

        return invoiceMapper.entityToResponseDto(invoiceCreated);

    }

    /**
     * Method that updates an invoice passed by parameter by its id
     * @param invoiceDTO Invoice to update
     * @param id Invoice id
     * @return InvoiceResponseDTO
     */
    @Override
    public InvoiceResponseDTO updateInvoice(InvoiceUpdateDTO invoiceDTO, Long id){

        if (ObjectUtils.isEmpty(invoiceDTO)) {
            throw new BadRequestException("Empty data in the entered entity");
        }

        if (id < 0) {
            throw new BadRequestException("Id cannot be negative");
        }

        if (invoiceDTO.getTotal() <= 0) {
            throw new BadRequestException("The invoice total must be greater than zero");
        }

        if (Objects.equals(invoiceDTO.getDescription(), "")) {
            throw new BadRequestException("Description can not be empty");
        }

        //Agregar verificación tipos de factura

        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);

        if(optionalInvoice.isEmpty()){
            throw new NotFoundException("Invoice to update not found");
        }

        Invoice invoiceToUpdate = invoiceMapper.requestDtoToEntity(invoiceDTO);
        invoiceToUpdate.setInvoiceId(optionalInvoice.get().getInvoiceId());
        invoiceToUpdate.setCustomer(optionalInvoice.get().getCustomer());
        invoiceToUpdate.setCreatedAt(optionalInvoice.get().getCreatedAt());
        invoiceToUpdate.setUpdatedAt(LocalDateTime.now());

        Invoice invoiceUpdated = invoiceRepository.save(invoiceToUpdate);

        return invoiceMapper.entityToResponseDto(invoiceUpdated);

    }

    /**
     * Method that removes an invoice by its id
     * @param id Invoice id
     */
    @Override
    public void deleteInvoiceById(Long id){

        if (id < 0) {
            throw new BadRequestException("Id cannot be negative");
        }

        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);

        if(optionalInvoice.isEmpty()){
            throw new NotFoundException("Invoice to delete not found");
        }

        invoiceRepository.delete(optionalInvoice.get());

    }

}
