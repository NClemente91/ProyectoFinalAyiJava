package com.ayi.rest.serv.app.mappers;

import com.ayi.rest.serv.app.dtos.request.CustomerDetailDTO;
import com.ayi.rest.serv.app.dtos.response.CustomerDetailResponseDTO;
import com.ayi.rest.serv.app.entities.CustomerDetail;

public interface ICustomerDetailMapper {
    CustomerDetailResponseDTO entityToResponseDto(CustomerDetail entity);
    CustomerDetail requestDtoToEntity(CustomerDetailDTO requestDto);
}
