package com.ayi.rest.serv.app.dtos.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@ApiModel(
        value = "PagesResponseDTO",
        description = "Represents the paginated information returned for a certain entity"
)
public class PagesResponseDTO<EntityResponseDTO> {

    @ApiModelProperty(position = 1, notes = "Non negative value, The List of entities are required.")
    private List<EntityResponseDTO> entityResponseDTOs = new ArrayList<>();

    @ApiModelProperty(position = 2, notes = "Total pages of the entity")
    private Integer totalPages;

    @ApiModelProperty(position = 3, notes = "Current page")
    private Integer currentPage;

    @ApiModelProperty(position = 4, notes = "Size of each page")
    private Integer pageSize;

    @ApiModelProperty(position = 5, notes = "Total elements of the entity")
    private Integer totalElements;

}