package com.ayi.rest.serv.app.controllers;

import com.ayi.rest.serv.app.dtos.request.CustomerDTO;
import com.ayi.rest.serv.app.dtos.request.CustomerUpdateDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerResponseDTO;
import com.ayi.rest.serv.app.dtos.response.InvoiceWithoutCustomerResponseDTO;
import com.ayi.rest.serv.app.dtos.response.PagesResponseDTO;
import com.ayi.rest.serv.app.services.ICustomerService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Api(value = "Customer Api", tags = {"Customer Service"})
@RestController
@RequestMapping(value = "/customer", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {
    private ICustomerService customerService;

    /**
     * Endpoint that returns a list of customers
     * @return PagesResponseDTO<CustomerResponseDTO>
     */
    @GetMapping(value = "/all")
    @ApiOperation(
            value = "Returns a list with all customers",
            httpMethod = "GET",
            response = CustomerResponseDTO[].class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with information about a customer list",
                    response = CustomerResponseDTO[].class),
            @ApiResponse(
                    code = 404,
                    message = "Information about a customer list not found")
    })
    public ResponseEntity<PagesResponseDTO<CustomerResponseDTO>> findAllCustomers(
            @ApiParam(value = "Page to display", required = true, example = "0")
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @ApiParam(value = "Number of elements per page", required = true, example = "10")
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {

        PagesResponseDTO<CustomerResponseDTO> customerList = customerService.findAllCustomers(page, size);

        return new ResponseEntity<>(customerList, HttpStatus.OK);

    }

    /**
     * Endpoint that returns all customer invoices
     * @param id Customer id
     * @return List<InvoiceWithoutCustomerResponseDTO>
     */
    @GetMapping(value = "/invoices/{id}")
    @ApiOperation(
            value = "Retrieves all customer invoices",
            httpMethod = "GET",
            response = InvoiceWithoutCustomerResponseDTO[].class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with information about a list of invoices from a customer",
                    response = InvoiceWithoutCustomerResponseDTO[].class),
            @ApiResponse(
                    code = 404,
                    message = "Information about a list of invoices from a customer not found")
    })
    public ResponseEntity<List<InvoiceWithoutCustomerResponseDTO>> findCustomerInvoicesById(
            @ApiParam(name = "id", required = true, value = "Id", example = "1")
            @PathVariable("id") Long id) {

        List<InvoiceWithoutCustomerResponseDTO> customerInvoices = customerService.findAllInvoicesById(id);

        return new ResponseEntity<>(customerInvoices, HttpStatus.OK);

    }

    /**
     * Endpoint that returns a customer
     * @param id Customer id
     * @return CustomerResponseDTO
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(
            value = "Returns a customer",
            httpMethod = "GET",
            response = CustomerResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with information about a customer",
                    response = CustomerResponseDTO.class),
            @ApiResponse(
                    code = 404,
                    message = "Information about a customer not found")
    })
    public ResponseEntity<CustomerResponseDTO> findOneCustomer(
            @ApiParam(name = "id", required = true, value = "Id", example = "1")
            @PathVariable("id") Long id) {

        CustomerResponseDTO customer = customerService.findCustomerById(id);

        return new ResponseEntity<>(customer, HttpStatus.OK);

    }

    /**
     * Endpoint that creates a customer with and returns it
     * @param customerDTO Customer with detail to create
     * @return CustomerWithDetailResponseDTO
     */
    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "Returns the created customer",
            httpMethod = "POST",
            response = CustomerResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201,
                    message = "Body content with information about a successfully created customer",
                    response = CustomerResponseDTO.class),
            @ApiResponse(
                    code = 400,
                    message = "Information about an error creating a new customer")
    })
    public ResponseEntity<CustomerResponseDTO> createNewCustomer(
            @ApiParam(name = "customer", required = true, value = "Customer")
            @Valid @RequestBody CustomerDTO customerDTO) {

        return new ResponseEntity<>(customerService.createCustomer(customerDTO), HttpStatus.CREATED);

    }

    /**
     * Endpoint that updates a customer by its id and returns it
     * @param customer Customer to update
     * @param id      Customer id
     * @return CustomerResponseDTO
     */
    @PutMapping(value = "/update/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "Returns the updated customer",
            httpMethod = "PUT",
            response = CustomerResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with information about a successfully updated customer",
                    response = CustomerResponseDTO.class),
            @ApiResponse(
                    code = 400,
                    message = "Information about an error updating a existing customer")
    })
    public ResponseEntity<CustomerResponseDTO> updateOneCustomer(
            @ApiParam(name = "customer", required = true, value = "Customer")
            @Valid @RequestBody CustomerUpdateDTO customer,
            @ApiParam(name = "id", required = true, value = "Id", example = "1")
            @PathVariable("id") Long id) {

        CustomerResponseDTO customerUpdated = customerService.updateCustomer(customer, id);

        return new ResponseEntity<>(customerUpdated, HttpStatus.OK);

    }

    /**
     * Endpoint that deletes a customer by its id
     * @param id Customer id
     * @return Void
     */
    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(
            value = "Empty response when deleting the customer",
            httpMethod = "DELETE"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 204,
                    message = "Body content about a successfully deleted customer"),
            @ApiResponse(
                    code = 400,
                    message = "Information about an error deleting a existing customer"),
            @ApiResponse(
                    code = 404,
                    message = "Information about a customer to delete not found")
    })
    public ResponseEntity<Void> deleteOneCustomer(
            @ApiParam(name = "id", required = true, value = "Id", example = "1")
            @PathVariable("id") Long id) {

        customerService.deleteCustomerById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
