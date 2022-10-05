package com.ayi.rest.serv.app.services.impl;

import com.ayi.rest.serv.app.dtos.request.FullInvoiceDTO;
import com.ayi.rest.serv.app.dtos.request.InvoiceDTO;
import com.ayi.rest.serv.app.dtos.response.FullInvoiceResponseDTO;
import com.ayi.rest.serv.app.dtos.response.InvoiceResponseDTO;
import com.ayi.rest.serv.app.dtos.response.PagesResponseDTO;
import com.ayi.rest.serv.app.entities.Customer;
import com.ayi.rest.serv.app.entities.Invoice;
import com.ayi.rest.serv.app.exceptions.BadRequestException;
import com.ayi.rest.serv.app.exceptions.NotFoundException;
import com.ayi.rest.serv.app.mappers.IInvoiceMapper;
import com.ayi.rest.serv.app.repositories.ICustomerRepository;
import com.ayi.rest.serv.app.repositories.IInvoiceRepository;
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
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class InvoiceServiceImpl implements IInvoiceService {

    @Autowired
    private IInvoiceRepository invoiceRepository;

    @Autowired
    private ICustomerRepository customerRepository;

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

        PagesResponseDTO<InvoiceResponseDTO> invoicePageResponseDTO = new PagesResponseDTO<InvoiceResponseDTO>();

        Pageable pageable = PageRequest.of(page, size);

        Page<Invoice> invoicePageList = invoiceRepository.findAll(pageable);

        if (invoicePageList.isEmpty()){
            throw new NotFoundException("There are no records related to invoices");
        }

        for(Invoice c:invoicePageList){
            invoicePageResponseDTO.getEntityResponseDTOs().add(invoiceMapper.entityToResponseDto(c));
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

        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);

        if (optionalInvoice.isEmpty()){
            throw new NotFoundException("Record with id " + id +" does not exist");
        }

        return invoiceMapper.entityToResponseDto(optionalInvoice.get());

    }

    /**
     * Method that creates an invoice passed by parameter
     * @param fullInvoiceDTO Invoice to create
     * @return InvoiceResponseDTO
     */
    @Override
    public FullInvoiceResponseDTO createInvoice(FullInvoiceDTO fullInvoiceDTO){

        FullInvoiceResponseDTO fullInvoiceResponseDTO;

        if (ObjectUtils.isEmpty(fullInvoiceDTO)) {
            throw new BadRequestException("Empty data in the entered entity");
        }

        if (fullInvoiceDTO.getInvoice().getTotal() <= 0) {
            throw new BadRequestException("The invoice total must be greater than zero");
        }

        Customer customerByDni = customerRepository.findByDni(fullInvoiceDTO.getCustomerDni());

        if (customerByDni == null) {
            throw new BadRequestException("Customer does not exist");
        }

        Invoice invoiceToCreate = invoiceMapper.requestDtoToEntity(fullInvoiceDTO.getInvoice());
        invoiceToCreate.setCreatedAt(LocalDateTime.now());
        invoiceToCreate.setCustomer(customerByDni);

        Invoice invoiceCreated = invoiceRepository.save(invoiceToCreate);

        return invoiceMapper.entitiesToFullInvoiceResponseDto(invoiceCreated);

    }

    /**
     * Method that updates an invoice passed by parameter by its id
     * @param invoiceDTO Invoice to update
     * @param id Invoice id
     * @return InvoiceResponseDTO
     */
    @Override
    public InvoiceResponseDTO updateInvoice(InvoiceDTO invoiceDTO, Long id){

        InvoiceResponseDTO invoiceResponseDTO;

        if (ObjectUtils.isEmpty(invoiceDTO)) {
            throw new BadRequestException("Empty data in the entered entity");
        }

        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);

        if(optionalInvoice.isEmpty()){
            throw new NotFoundException("Invoice to update not found");
        }

//        Invoice invoiceByDni = invoiceRepository.findByDni(invoiceDTO.getDni());
//
//        if(!Objects.equals(invoiceDTO.getDni(), optionalInvoice.get().getDni()) && invoiceByDni != null){
//            throw new BadRequestException("Invoice with DNI entered already exists");
//        }

        Invoice invoiceToUpdate = invoiceMapper.requestDtoToEntity(invoiceDTO);
        invoiceToUpdate.setInvoiceId(optionalInvoice.get().getInvoiceId());
        invoiceToUpdate.setCreatedAt(optionalInvoice.get().getCreatedAt());
        invoiceToUpdate.setUpdatedAt(LocalDateTime.now());

        Invoice invoiceUpdated = invoiceRepository.save(invoiceToUpdate);

        invoiceResponseDTO = invoiceMapper.entityToResponseDto(invoiceUpdated);

        return invoiceResponseDTO;

    }

    /**
     * Method that removes an invoice by its id
     * @param id Invoice id
     */
    @Override
    public void deleteInvoiceById(Long id){

        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);

        if(optionalInvoice.isEmpty()){
            throw new NotFoundException("Invoice to delete not found");
        }

        invoiceRepository.delete(optionalInvoice.get());

    }

}
