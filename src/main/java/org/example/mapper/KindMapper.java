package org.example.mapper;

import org.example.dto.KindDto;
import org.example.entities.Kind;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TypeMapper.class})
public interface KindMapper {

    KindDto map(Kind kind);
    @InheritInverseConfiguration
    Kind map(KindDto dto);
}
