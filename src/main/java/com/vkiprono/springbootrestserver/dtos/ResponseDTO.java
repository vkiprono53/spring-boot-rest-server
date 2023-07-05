package com.vkiprono.springbootrestserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vkiprono
 * @created 6/15/23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private String code;
    private String message;
    private String status;
}
