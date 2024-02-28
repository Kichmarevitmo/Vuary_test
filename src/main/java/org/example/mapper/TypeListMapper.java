package org.example.mapper;

import org.example.dto.TypeDto;
import org.example.entities.Type;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = TypeMapper.class)
public interface TypeListMapper {

    List<TypeDto> toDTOList(List<Type> types);

    @InheritInverseConfiguration
    List<Type> toEntityList(List<TypeDto> typeDtos);
}
