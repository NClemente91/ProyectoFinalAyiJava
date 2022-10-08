package com.ayi.rest.serv.app.controllers;

import com.ayi.rest.serv.app.dtos.request.CustomerDetailDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerDetailResponseDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerResponseDTO;
import com.ayi.rest.serv.app.dtos.response.PagesResponseDTO;
import com.ayi.rest.serv.app.services.ICustomerDetailService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@Api(value = "Customer Detail Api", tags = {"Customer Detail Service"})
@RestController
@RequestMapping(value = "/customerDetail", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerDetailController {

    private ICustomerDetailService customerDetailService;

    /**
     * Endpoint that returns a list of customers details
     * @return PagesResponseDTO<CustomerDetailResponseDTO>
     */
    @GetMapping(value = "/all")
    @ApiOperation(
            value = "Returns a list with all customers details",
            httpMethod = "GET",
            response = CustomerDetailResponseDTO[].class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with information about a list of customer details",
                    response = CustomerDetailResponseDTO[].class),
            @ApiResponse(
                    code = 404,
                    message = "Information about a list of customer details not found")
    })
    public ResponseEntity<PagesResponseDTO<CustomerDetailResponseDTO>> findAllCustomersDetails(
            @ApiParam(value = "Page to display", required = true, example = "0")
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @ApiParam(value = "Number of elements per page", required = true, example = "10")
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {

        PagesResponseDTO<CustomerDetailResponseDTO> customerDetailList = customerDetailService.findAllCustomersDetails(page, size);

        return new ResponseEntity<>(customerDetailList, HttpStatus.OK);

    }

    /**
     * Endpoint that returns a customer detail
     * @param id CustomerDetail id
     * @return CustomerDetailResponseDTO
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(
            value = "Returns a customer detail",
            httpMethod = "GET",
            response = CustomerDetailResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with information about a customer detail",
                    response = CustomerDetailResponseDTO.class),
            @ApiResponse(
                    code = 404,
                    message = "Information about a customer detail not found")
    })
    public ResponseEntity<CustomerDetailResponseDTO> findOneCustomerDetail(
            @ApiParam(name = "id", required = true, value = "Id", example = "1")
            @PathVariable("id") Long id) {

        CustomerDetailResponseDTO customerDetail = customerDetailService.findCustomerDetailById(id);

        return new ResponseEntity<>(customerDetail, HttpStatus.OK);

    }

    /**
     * Endpoint that updates a customer detail by its id and returns it
     * @param customerDetail CustomerDetail to update
     * @param id      CustomerDetail id
     * @return CustomerDetailResponseDTO
     */
    @PutMapping(value = "/update/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "Returns the updated customerDetail",
            httpMethod = "PUT",
            response = CustomerDetailResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Body content with information about a successfully updated customer detail",
                    response = CustomerDetailResponseDTO.class),
            @ApiResponse(
                    code = 400,
                    message = "Information about an error updating a existing customer detail")
    })
    public ResponseEntity<CustomerDetailResponseDTO> updateOneCustomerDetail(
            @ApiParam(name = "customerDetail", required = true, value = "CustomerDetail")
            @Valid @RequestBody CustomerDetailDTO customerDetail,
            @ApiParam(name = "id", required = true, value = "Id", example = "1")
            @PathVariable("id") Long id) {

        CustomerDetailResponseDTO customerDetailUpdated = customerDetailService.updateCustomerDetail(customerDetail, id);

        return new ResponseEntity<>(customerDetailUpdated, HttpStatus.OK);

    }

}
