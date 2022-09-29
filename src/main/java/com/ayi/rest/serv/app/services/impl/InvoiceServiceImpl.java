package com.ayi.rest.serv.app.services.impl;

import com.ayi.rest.serv.app.dtos.request.InvoiceDTO;
import com.ayi.rest.serv.app.dtos.response.InvoiceResponseDTO;
import com.ayi.rest.serv.app.mappers.IInvoiceMapper;
import com.ayi.rest.serv.app.repositories.IInvoiceRepository;
import com.ayi.rest.serv.app.services.IInvoiceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class InvoiceServiceImpl extends Exception implements IInvoiceService {

    @Autowired
    private IInvoiceRepository invoiceRepository;

    @Autowired
    private IInvoiceMapper invoiceMapper;

    @Override
    public List<InvoiceResponseDTO> findAllInvoices(){
        return null;
    }

    @Override
    public InvoiceResponseDTO findInvoiceById(Long id){
        return null;
    }

    @Override
    public InvoiceResponseDTO createInvoice(InvoiceDTO invoiceDTO){
        return null;
    }

    @Override
    public InvoiceResponseDTO updateInvoice(InvoiceDTO invoiceDTO){
        return null;
    }

    @Override
    public void deleteInvoiceById(Long id){

    }

}
