package com.ayi.rest.serv.app.controllers;

import com.ayi.rest.serv.app.dtos.request.AddressDTO;
import com.ayi.rest.serv.app.dtos.response.AddressResponseDTO;
import com.ayi.rest.serv.app.dtos.response.PagesResponseDTO;
import com.ayi.rest.serv.app.services.IAddressService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Api(value = "Address Api", tags = {"Address Service"})
@RestController
@RequestMapping(value = "/address", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AddressController {

    private IAddressService addressService;

    /**
     * Endpoint that returns a list of addresses
     * @return List<AddressResponseDTO>
     */
    @GetMapping(value = "/all")
    @ApiOperation(
            value = "Returns a list with all addresses",
            httpMethod = "GET",
            response = AddressResponseDTO[].class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about address",
                    response = AddressResponseDTO[].class),
            @ApiResponse(
                    code = 204,
                    message = "Body content empty")
    })
    public ResponseEntity<PagesResponseDTO<AddressResponseDTO>> findAllAddresses(
            @ApiParam(value = "Page to display", required = true, example = "0")
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @ApiParam(value = "Number of elements per page", required = true, example = "10")
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {

        PagesResponseDTO<AddressResponseDTO> addressList = addressService.findAllAddresses(page, size);

        return new ResponseEntity<>(addressList, HttpStatus.OK);

    }

    /**
     * Endpoint that returns an address
     * @param id Address id
     * @return AddressResponseDTO
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(
            value = "Returns an address",
            httpMethod = "GET",
            response = AddressResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about address",
                    response = AddressResponseDTO.class),
            @ApiResponse(
                    code = 204,
                    message = "Body content empty")
    })
    public ResponseEntity<?> findOneAddress(
            @ApiParam(name = "id", required = true, value = "Id", example = "1")
            @PathVariable("id") Long id) {

        AddressResponseDTO address = addressService.findAddressById(id);

        return new ResponseEntity<>(address, HttpStatus.OK);

    }

    /**
     * Endpoint that creates an address and returns it
     * @param address Address to create
     * @return AddressResponseDTO
     */
    @PostMapping(value = "/add", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "Returns the created address",
            httpMethod = "POST",
            response = AddressResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201,
                    message = "Body content with basic information about address",
                    response = AddressResponseDTO.class),
            @ApiResponse(
                    code = 204,
                    message = "Body content empty")
    })
    public ResponseEntity<AddressResponseDTO> createNewAddress(
            @ApiParam(name = "address", required = true, value = "Address")
            @Valid @RequestBody AddressDTO address){

        AddressResponseDTO addressCreated = addressService.createAddress(address);

        return new ResponseEntity<>(addressCreated, HttpStatus.CREATED);

    }

    /**
     * Endpoint that updates an address by its id and returns it
     * @param address Address to update
     * @param id Address id
     * @return AddressResponseDTO
     */
    @PutMapping(value = "/update/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(
            value = "Returns the updated address",
            httpMethod = "PUT",
            response = AddressResponseDTO.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Body content with basic information about address",
                    response = AddressResponseDTO.class),
            @ApiResponse(
                    code = 204,
                    message = "Body content empty")
    })
    public ResponseEntity<AddressResponseDTO> updateOneAddress(
            @ApiParam(name = "address", required = true, value = "Address")
            @Valid @RequestBody AddressDTO address,
            @ApiParam(name = "id", required = true, value = "Id", example = "1")
            @PathVariable("id") Long id){

        AddressResponseDTO addressUpdated = addressService.updateAddress(address, id);

        return new ResponseEntity<>(addressUpdated, HttpStatus.OK);

    }

    /**
     * Endpoint that deletes an address by its id
     * @param id Address id
     * @return Void
     */
    @DeleteMapping(value = "/delete/{id}")
    @ApiOperation(
            value = "Empty response when deleting the address",
            httpMethod = "DELETE"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    code = 204,
                    message = "Body content empty")
    })
    public ResponseEntity<Void> deleteOneAddress(
            @ApiParam(name = "id", required = true, value = "Id", example = "1")
            @PathVariable("id") Long id){

        addressService.deleteAddressById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
