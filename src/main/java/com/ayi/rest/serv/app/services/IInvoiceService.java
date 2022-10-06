package com.ayi.rest.serv.app.services;

import com.ayi.rest.serv.app.dtos.request.FullInvoiceDTO;
import com.ayi.rest.serv.app.dtos.request.InvoiceDTO;
import com.ayi.rest.serv.app.dtos.response.FullInvoiceResponseDTO;
import com.ayi.rest.serv.app.dtos.response.InvoiceResponseDTO;
import com.ayi.rest.serv.app.dtos.response.PagesResponseDTO;

import java.util.List;

public interface IInvoiceService {

    PagesResponseDTO<InvoiceResponseDTO> findAllInvoices(Integer page, Integer size);

    FullInvoiceResponseDTO findInvoiceById(Long id);

    FullInvoiceResponseDTO createInvoice(FullInvoiceDTO fullInvoiceDTO);

    InvoiceResponseDTO updateInvoice(InvoiceDTO invoiceDTO, Long id);

    void deleteInvoiceById(Long id);
}
