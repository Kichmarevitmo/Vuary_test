package org.example.mapper;

import org.example.dto.TypeDto;
import org.example.entities.Type;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeMapper {

    TypeDto map(Type type);

    @InheritInverseConfiguration
    Type map(TypeDto dto);
}
