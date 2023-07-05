package com.vkiprono.springbootrestserver.services.common;

/**
 * @author vkiprono
 * @created 6/14/23
 */

/**
 * For conversion of dto to entity and vice versa
 * @param <DTO>
 * @param <ENTITY>
 *
 */
public interface EntityMapper <DTO, ENTITY>{
    ENTITY dtoToEntity(DTO dto);
    DTO entityToDTO(ENTITY entity);
}
