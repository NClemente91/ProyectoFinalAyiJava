package com.ayi.rest.serv.app.controllers;

import com.ayi.rest.serv.app.dtos.request.InvoiceDTO;
import com.ayi.rest.serv.app.dtos.request.InvoiceUpdateDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerResponseDTO;
import com.ayi.rest.serv.app.dtos.response.InvoiceResponseDTO;
import com.ayi.rest.serv.app.dtos.response.PagesResponseDTO;
import com.ayi.rest.serv.app.services.IInvoiceService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@Api(value = "Invoice Api", tags = {"Invoice Service"})
@RestController
@RequestMapping(value = "/invoice", produces = {MediaType.APPLICATION_JSON_VALUE})
public class InvoiceController {

    private IInvoiceService invoiceService;

    /**
     * Endpoint that returns a list of invoices
     * @return PagesResponseDTO<InvoiceResponseDTO>
     */
    @GetMapping(value = "/all")
    @ApiOperation(
            value = "Returns a list with all invoices",
            httpMethod = "GET",
            response = InvoiceResponseDTO[].class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with information about an invoice list",
                    response = InvoiceResponseDTO[].class),
            @ApiResponse(
                    code = 404,
                    message = "Information about an invoice list not found")
    })
    public ResponseEntity<PagesResponseDTO<InvoiceResponseDTO>> findAllInvoices(
            @ApiParam(value = "Page to display", required = true, example = "0")
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @ApiParam(value = "Number of elements per page", required = true, example = "10")
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {

        PagesResponseDTO<InvoiceResponseDTO> invoiceList = invoiceService.findAllInvoices(page, size);

        return new ResponseEntity<>(invoiceList, HttpStatus.OK);

    }

    /**
     * Endpoint that returns an invoice
     * @param id Invoice id
     * @return InvoiceResponseDTO
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(
            value = "Returns a invoice",
            httpMethod = "GET",
            response = InvoiceResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with information about an invoice",
                    response = CustomerResponseDTO.class),
            @ApiResponse(
                    code = 404,
                    message = "Information about an invoice not found")
    })
    public ResponseEntity<InvoiceResponseDTO> findOneInvoice(
            @ApiParam(name = "id", required = true, value = "Id", example = "1")
            @PathVariable("id") Long id) {

        InvoiceResponseDTO invoice = invoiceService.findInvoiceById(id);

        return new ResponseEntity<>(invoice, HttpStatus.OK);

    }

    /**
     * Endpoint that creates an invoice and returns it
     * @param invoiceDTO Invoice to create
     * @return InvoiceResponseDTO
     */
    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "Returns the created invoice",
            httpMethod = "POST",
            response = InvoiceResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201,
                    message = "Body content with information about a successfully created invoice",
                    response = InvoiceResponseDTO.class),
            @ApiResponse(
                    code = 400,
                    message = "Information about an error creating a new invoice")
    })
    public ResponseEntity<InvoiceResponseDTO> createNewInvoice(
            @ApiParam(name = "invoice", required = true, value = "Invoice")
            @Valid @RequestBody InvoiceDTO invoiceDTO) {

        return new ResponseEntity<>(invoiceService.createInvoice(invoiceDTO), HttpStatus.CREATED);

    }

    /**
     * Endpoint that updates an invoice by its id and returns it
     * @param invoice Invoice to update
     * @param id      Invoice id
     * @return InvoiceResponseDTO
     */
    @PutMapping(value = "/update/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "Returns the updated invoice",
            httpMethod = "PUT",
            response = InvoiceResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with information about a successfully updated invoice",
                    response = CustomerResponseDTO.class),
            @ApiResponse(
                    code = 400,
                    message = "Information about an error updating a existing invoice")
    })
    public ResponseEntity<InvoiceResponseDTO> updateOneInvoice(
            @ApiParam(name = "invoice", required = true, value = "Invoice")
            @Valid @RequestBody InvoiceUpdateDTO invoice,
            @ApiParam(name = "id", required = true, value = "Id", example = "1")
            @PathVariable("id") Long id) {

        InvoiceResponseDTO invoiceUpdated = invoiceService.updateInvoice(invoice, id);

        return new ResponseEntity<>(invoiceUpdated, HttpStatus.OK);

    }

    /**
     * Endpoint that deletes an invoice by its id
     * @param id Invoice id
     * @return Void
     */
    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(
            value = "Empty response when deleting the invoice",
            httpMethod = "DELETE"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 204,
                    message = "Body content about a successfully deleted invoice"),
            @ApiResponse(
                    code = 400,
                    message = "Information about an error deleting a existing invoice"),
            @ApiResponse(
                    code = 404,
                    message = "Information about an invoice to delete not found")
    })
    public ResponseEntity<Void> deleteOneInvoice(
            @ApiParam(name = "id", required = true, value = "Id", example = "1")
            @PathVariable("id") Long id) {

        invoiceService.deleteInvoiceById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
