package com.ayi.rest.serv.app.services;

import com.ayi.rest.serv.app.dtos.request.InvoiceDTO;
import com.ayi.rest.serv.app.dtos.response.InvoiceResponseDTO;

import java.util.List;

public interface IInvoiceService {
    List<InvoiceResponseDTO> findAllInvoices();

    InvoiceResponseDTO findInvoiceById(Long id);

    InvoiceResponseDTO createInvoice(InvoiceDTO invoiceDTO);

    InvoiceResponseDTO updateInvoice(InvoiceDTO invoiceDTO);

    void deleteInvoiceById(Long id);
}
