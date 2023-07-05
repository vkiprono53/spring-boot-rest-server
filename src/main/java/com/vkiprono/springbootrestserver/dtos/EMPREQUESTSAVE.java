package com.vkiprono.springbootrestserver.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vkiprono
 * @created 6/19/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EMPREQUESTSAVE {
    @JsonProperty("REQUEST")
    private EmpSaveRequestDTO requestDTO;
}
