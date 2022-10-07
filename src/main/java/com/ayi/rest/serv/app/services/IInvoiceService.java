package com.ayi.rest.serv.app.services;

import com.ayi.rest.serv.app.dtos.request.InvoiceDTO;
import com.ayi.rest.serv.app.dtos.request.InvoiceUpdateDTO;
import com.ayi.rest.serv.app.dtos.response.InvoiceResponseDTO;
import com.ayi.rest.serv.app.dtos.response.PagesResponseDTO;

public interface IInvoiceService {

    PagesResponseDTO<InvoiceResponseDTO> findAllInvoices(Integer page, Integer size);

    InvoiceResponseDTO findInvoiceById(Long id);

    InvoiceResponseDTO createInvoice(InvoiceDTO invoiceDTO);

    InvoiceResponseDTO updateInvoice(InvoiceUpdateDTO invoiceDTO, Long id);

    void deleteInvoiceById(Long id);
}
